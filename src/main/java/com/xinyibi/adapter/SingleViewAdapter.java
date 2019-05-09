package com.xinyibi.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;

import com.tsc9526.monalisa.core.query.datatable.DataColumn;
import com.tsc9526.monalisa.core.query.datatable.DataMap;
import com.tsc9526.monalisa.core.query.datatable.DataTable;
import com.xinyibi.App;
import com.xinyibi.exception.ViewNotFoundException;
import com.xinyibi.factory.DataAdapterFactory;
import com.xinyibi.mapper.DataTableInfoMapper;
import com.xinyibi.mapper.DatabaseInfoMapper;
import com.xinyibi.mapper.ForeignKeyInfoMapper;
import com.xinyibi.mapper.TableFieldInfoMapper;
import com.xinyibi.mapper.TableViewMapper;
import com.xinyibi.mapper.ViewFieldItemMapper;
import com.xinyibi.mapper.ViewFieldMapper;
import com.xinyibi.model.Graph;
import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.pojo.DataTableInfoExample;
import com.xinyibi.pojo.DatabaseInfo;
import com.xinyibi.pojo.ForeignKeyInfo;
import com.xinyibi.pojo.ForeignKeyInfoExample;
import com.xinyibi.pojo.TableFieldInfo;
import com.xinyibi.pojo.TableFieldInfoExample;
import com.xinyibi.pojo.TableView;
import com.xinyibi.pojo.ViewField;
import com.xinyibi.pojo.ViewFieldExample;

public class SingleViewAdapter implements ViewAdapter {
	public static void main(String[] args) {
		DataTable<DataMap> table = new DataTable<DataMap>();
		  
		//创建测试数据
		for(int userId=1;userId<=20;userId++){
		    DataMap row = new DataMap();
		    row.put("user", userId);
		    row.put("area", "guangdong-"+(userId%2));
		    row.put("rank"  ,90+userId);
		    table.add(row);
		}
		 
		DataMap r=table.selectOne("count(*) as cnt", "rank  > 91", null, null);
		System.out.println(r); 
		//输出： {cnt=5}
		 
		 
		DataTable<DataMap> rs=table.select(
		    //字段选择: 支持常用的SQL聚合函数：sum/avg/count
		    //(null 或  "" 表示 *)
		    "area,rank - user" 
		         
		    //过滤条件: 支持AND, OR , 括号
		    //(null 或  "" 表示无条件)
		    , "rank>0"             
		         
		    //排序字段：ASC/DESC
		    //(null 或  "" 表示无指定的排序)
		    ,"area ASC" 
		         
		    //分组语句：GROUP BY ... HAVING ...
		    //(null 或  "" 表示无分组)
		    ,"");
		System.out.println(rs);
	}
	@Override
	public DataTable<DataMap> getDataTable(String viewId) throws Exception {
		ApplicationContext context = App.getApplicationContext();
		
		// 根据视图ID获取视图
		TableView view = context.getBean(TableViewMapper.class).selectByPrimaryKey(viewId);
		
		// 查询出该视图下的所有字段
		ViewFieldExample example = new ViewFieldExample();
		example.createCriteria().andViewIdEqualTo(view.getId());
		List<ViewField> viewFields = context.getBean(ViewFieldMapper.class).selectByExample(example);
		
		// 根据视图获取与视图相关的数据表
		List<DataTableInfo> dataTables = context.getBean(DataTableInfoMapper.class).findByViewId(viewId);
		if(dataTables.size() == 1){
			// 如果视图只来自一张表
			DataTableInfo dataTableInfo = dataTables.get(0);
			
			// 获取表中的数据
			DataTable<DataMap> dataTable = getTable(dataTableInfo);
			
			// 解析器
			FormulaImpl formula = new FormulaImpl(dataTable);
			// 将数据表中的数据映射到视图中
			DataTable<DataMap> table = formula.map(viewFields);
			return table;
		}else{
			for (DataTableInfo dataTableInfo : dataTables) {
				
			}
		}
		
		return null;
	}
	protected DataTable<DataMap> getTable( DataTableInfo dataTableInfo) throws Exception {
		ApplicationContext context = App.getApplicationContext();
		TableFieldInfoExample ex = new TableFieldInfoExample();
		ex.createCriteria().andTbIdEqualTo(dataTableInfo.getId());
		DatabaseInfo databaseInfo = context.getBean(DatabaseInfoMapper.class).selectByPrimaryKey(dataTableInfo.getDbId());
		
		DataAdapter dataAdapter = DataAdapterFactory.getDataAdapter(databaseInfo);
		List<TableFieldInfo> tableFields = context.getBean(TableFieldInfoMapper.class).selectByExample(ex);
		DataTable<DataMap> dataTable = dataAdapter.getDataTable(tableFields, null);
		return dataTable;
	}

	DataTable<DataMap> loadTableTable(String viewId) throws Exception {
		ApplicationContext context = App.getApplicationContext();
		TableViewMapper viewMapper = context.getBean(TableViewMapper.class);
		TableView view = viewMapper.selectByPrimaryKey(viewId);
		if(view == null)
			throw new ViewNotFoundException(viewId);

		DataTableInfoMapper dataTableInfoMapper = context.getBean(DataTableInfoMapper.class);
		List<DataTableInfo> dataTables = dataTableInfoMapper.findByViewId(viewId);
		int size = dataTables.size();
		if(size == 1){
			DataTableInfo dataTableInfo = dataTables.get(0);
			DatabaseInfo databaseInfo = context.getBean(DatabaseInfoMapper.class).selectByPrimaryKey(dataTableInfo.getDbId());
			DataAdapter dataAdapter = DataAdapterFactory.getDataAdapter(databaseInfo);
			return dataAdapter.getDataTable(null, null);
		}
		List<DataTableInfo> allTables = dataTableInfoMapper.selectByExample(new DataTableInfoExample());
		Graph graph = new Graph();
		allTables.forEach(i->graph.addVertex(i.getId()));
		List<ForeignKeyInfo> selectByExample = context.getBean(ForeignKeyInfoMapper.class).selectByExample(new ForeignKeyInfoExample());
		selectByExample.forEach(e->graph.addArc(e.getTbId(), e.getRefTbId()));
		
		return null;
		
	}

}
