package com.xinyibi.adapter;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.tsc9526.monalisa.core.query.datatable.DataMap;
import com.tsc9526.monalisa.core.query.datatable.DataTable;
import com.xinyibi.model.PathVertexModel;
import com.xinyibi.model.ViewDetailModel;
import com.xinyibi.model.ViewGraphModel;
import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.pojo.DatabaseInfo;
import com.xinyibi.pojo.TableFieldInfo;
import com.xinyibi.pojo.ViewField;
import com.xinyibi.util.SqlHelper;
import com.xinyibi.vo.PageEntry;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbactractViewAdapter implements Serializable {
	
	private static final long serialVersionUID = -3757485970995417930L;

	private ViewDetailModel model;
	
	private PageEntry pageEntry;

	public DataTable<DataMap> getDataTable(){
		List<ViewGraphModel> graph = model.getGraph();
		
		Map<String,DataTable<DataMap>> map = new HashMap<>();
		for (ViewGraphModel model : graph) {
			DataTableInfo tableInfo = model.getFrom();
			// 待查询的字段
			Set<TableFieldInfo> collect = this.model.getRelatedFields().stream().filter(field->field.getTbId().equals(tableInfo.getId())).collect(Collectors.toSet());
			List<PathVertexModel> joins = model.getJoins();
			
			// 添加要用来连接表的字段
			for (PathVertexModel vertex : joins) {
				TableFieldInfo leftField = vertex.getLeftField();
				if(!collect.contains(leftField)){
					collect.add(leftField);
				}
			}
			DatabaseInfo databaseInfo = this.model.getDatabases().stream().peek(db->db.getId().equals(tableInfo.getDbId())).findFirst().orElse(null);
			DataTable<DataMap> loadTable = loadTable(databaseInfo,tableInfo,collect);
			map.put(tableInfo.getId(), loadTable);
		}
		
		int count = map.size();
		for (ViewGraphModel model : graph){
			String table1 = model.getFrom().getId();
			// 添加要用来连接表的字段
			for (PathVertexModel vertex : model.getJoins()) {
				DataTableInfo table = vertex.getTable();
				String table2 = table.getId();
				DataTable<DataMap> tmp = map.get(table1).join(map.get(table2), vertex.getLeftFieldId(), vertex.getRightFieldId());
				map.put(table1, tmp);
				map.put(table2, tmp);
				count--;
			}
		}
		
		if(count > 1){
			merge(map);
		}
		DataTable<DataMap> dataTable = map.values().iterator().next();
		// 映射到视图字段
		dataTable = mapToViewField(dataTable,this.model.getViewFields());
		System.gc();
		// TODO 过滤
		DataTable<DataMap> filter = filter(dataTable,pageEntry);
		if(filter != null)
			dataTable = filter;
		
		return dataTable;
	}
	
	
	/**
	 * 将数据表dataTable中的数据映射到数据视图viewField上
	 * @param dataTable
	 * @param viewFields
	 * @return 新的数据表,列名称为视图中字段对应的FieldName属性
	 */
	protected abstract DataTable<DataMap> mapToViewField(DataTable<DataMap> dataTable, List<ViewField> viewFields);


	private void merge(Map<String, DataTable<DataMap>> map) {
		Set<String> keySet = map.keySet();
		Iterator<String> iterator = keySet.iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			DataTable<DataMap> t1 = map.get(key);
			Iterator<String> iterator2 = keySet.iterator();
			while(iterator2.hasNext()){
				String key2 = iterator2.next();
				DataTable<DataMap> t2 = map.get(key2);
				DataTable<DataMap> join = t1.join(t2, "rownum", "rownum");
				map.put(key, join);
				map.put(key2, join);
			}
		}
	}


	/**
	 * 过滤查询结果，将DataTable中数据根据pageentry中的条件进行筛选
	 * @param dataTable
	 * @param pageEntry
	 * @return
	 */
	protected abstract DataTable<DataMap> filter(DataTable<DataMap> dataTable, PageEntry pageEntry) ;
		


	private DataTable<DataMap> loadTable(DatabaseInfo database, DataTableInfo table, Set<TableFieldInfo> collect) {
		// 拼接SQL语句
		StringBuffer sBuffer = new StringBuffer("select ");
		for (TableFieldInfo tableFieldInfo : collect) {
			sBuffer.append(tableFieldInfo.getFieldName()).append(" ").append(tableFieldInfo.getId()).append(",");
		}
		sBuffer.deleteCharAt(sBuffer.length()-1);
		sBuffer.append(" from ").append(table.getTableName());
		
		log.debug(sBuffer.toString());
		
		try {
			// jdbc帮助类
			SqlHelper sqlHelper = new SqlHelper(database);
			// 查询
			List<DataMap> list = sqlHelper.query(sBuffer.toString(), (i,r)->{
				DataMap dataMap = new DataMap();
				dataMap.put("rownum", i);
				for (TableFieldInfo column : collect) {
					Object value = r.getObject(column.getId());
					dataMap.put(column.getId(), value);
				}
				return dataMap;
			});
			DataTable<DataMap> dataTable = new DataTable<DataMap>();
			dataTable.addAll(list);
			return dataTable;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("执行SQL语句'"+sBuffer+"'时发生异常",e);
		}
		
	}


	/**
	 * 从数据库中加载数据表中的数据
	 * @param database	数据库信息
	 * @param table		数据表信息
	 * @return			数据表中的数据
	 */
	protected DataTable<DataMap> loadTable(DatabaseInfo database,DataTableInfo table){
		// 要查询的字段
		List<TableFieldInfo> collect = model.getRelatedFields().stream().filter(field->field.getTbId().equals(table.getId())).collect(Collectors.toList());
		
		return loadTable(database,table,new HashSet<>(collect));
	}
	
	/**
	 * 为SQL语句设置分页，不支持使用“?”参数形式
	 * @param databaseInfo	数据库相关信息
	 * @param sql			待设置的SQL语句
	 * @param page			起始页
	 * @param size			每页显示数据
	 * @return			分页之后的SQL语句
	 */
	protected abstract String setLimit(DatabaseInfo databaseInfo, String sql, Integer page, Integer size);

	

	public ViewDetailModel getModel() {
		return model;
	}

	public void setModel(ViewDetailModel model) {
		this.model = model;
	}

	public PageEntry getPageEntry() {
		return pageEntry;
	}

	public void setPageEntry(PageEntry pageEntry) {
		this.pageEntry = pageEntry;
	}

	
}
