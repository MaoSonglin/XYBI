package com.xinyibi.adapter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;

import com.tsc9526.monalisa.core.query.datatable.DataMap;
import com.tsc9526.monalisa.core.query.datatable.DataTable;
import com.xinyibi.App;
import com.xinyibi.exception.NoSuchVertexException;
import com.xinyibi.factory.DataAdapterFactory;
import com.xinyibi.mapper.DataTableInfoMapper;
import com.xinyibi.mapper.DatabaseInfoMapper;
import com.xinyibi.mapper.ForeignKeyInfoMapper;
import com.xinyibi.mapper.TableViewMapper;
import com.xinyibi.mapper.ViewFieldMapper;
import com.xinyibi.model.Graph;
import com.xinyibi.model.TreeNode;
import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.pojo.DataTableInfoExample;
import com.xinyibi.pojo.DatabaseInfo;
import com.xinyibi.pojo.ForeignKeyInfo;
import com.xinyibi.pojo.ForeignKeyInfoExample;
import com.xinyibi.pojo.TableFieldInfoExample;
import com.xinyibi.pojo.TableView;
import com.xinyibi.pojo.ViewField;
import com.xinyibi.pojo.ViewFieldExample;
import com.xinyibi.service.DataBaseService;
import com.xinyibi.util.Accessibility;
import com.xinyibi.util.Djiestra;

public class SingleViewAdapter implements ViewAdapter {
	
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
			DataTable<DataMap> multiTable = multiTable(context, dataTables);
			Formula formula = new FormulaImpl(multiTable);
			return formula.map(viewFields);
		}
		
	}
	/**
	 * 多表连接查询
	 * @param context	spring 容器
	 * @param dataTables 要连接查询的数据表
	 * @return	查询结果
	 * @throws Exception
	 */
	protected DataTable<DataMap> multiTable(ApplicationContext context, List<DataTableInfo> dataTables)
			throws Exception {
		// 获取系统中的网模型
		Graph graph = context.getBean(DataBaseService.class).getGraph();
		
		Djiestra djiestra = new Djiestra();
		Accessibility access = new Accessibility();
		access.setGraph(graph);
		
		
		
		// 判断根节点到其他所有数据表是否存在关联
		boolean allMatch = dataTables.stream().allMatch(table->{
			try {
				return access.prediction(dataTables.get(0).getId(), table.getId());
			} catch (NoSuchVertexException e) {
				throw new IllegalStateException("判断数据表之间是否存在关联时发出异常",e);
			}
		});
		
		if(allMatch) {// 根节点到其他所有表都存在关联关系
			Iterator<DataTableInfo> iterator = dataTables.iterator();
			// 获取第一个表作为跟节点
			DataTableInfo firstTable = iterator.next();
			// 待访问的数据表的ID
			Set<String> ids = new HashSet<>();
			ids.add(firstTable.getId());
			// 使用一个树模型存放数据表之间的关联路径，根节点为第一个表
			TreeNode<String> root = new TreeNode<>(firstTable.getId());
			// 遍历剩下的所有数据表
			while(iterator.hasNext()) {
				DataTableInfo tableInfo = iterator.next();
				// 获取第一个表到当前表的连接路径
				List<String> path = djiestra.getPath(firstTable.getId(), tableInfo.getId(),graph);
				// 将路径中的所有数据表都添加到待访问集合中
				ids.addAll(path);
				// 更新路径树
				buildTree(root,path);
			}
			
			// 查询出所有的待访问的数据表
			DataTableInfoExample dataTableInfoExample = new DataTableInfoExample();
			dataTableInfoExample.createCriteria().andIdIn(ids.stream().collect(Collectors.toList()));
			List<DataTableInfo> list = context.getBean(DataTableInfoMapper.class).selectByExample(dataTableInfoExample);
			
			// 暂存数据表中的数据的map集合
			Map<String,DataTable<DataMap>> tmpMap = new HashMap<>();
			// 遍历所有数据表查询出每个数据表的数据存放到tmpMap中
			for (DataTableInfo dataTableInfo : list) {
				String id = dataTableInfo.getId();
				DataTable<DataMap> table = getTable(dataTableInfo);
				tmpMap.put(id, table);
			}
			
			return join(tmpMap,root);
			
		}
		else {
			throw new IllegalStateException("视图关联的数据表不能联通!");
		}
	}
	/**
	 * 连接表
	 * @param tmpMap
	 * @param root
	 * @return
	 */
	private DataTable<DataMap> join(Map<String, DataTable<DataMap>> tmpMap, TreeNode<String> root) {
		ApplicationContext context = App.getApplicationContext();
		ForeignKeyInfoMapper mapper = context.getBean(ForeignKeyInfoMapper.class);
		ForeignKeyInfoExample example = new ForeignKeyInfoExample();
		String data = root.getData();
		DataTable<DataMap> dataTable = tmpMap.get(data);
		TreeNode<String> children = root.getLeft();
		while(children != null) {
			String key = children.getData();
			DataTable<DataMap> tmpTable = tmpMap.get(key);
			example.clear();
			
			example.createCriteria().andTbIdEqualTo(data).andRefTbIdEqualTo(key);
			example.createCriteria().andTbIdEqualTo(key).andRefTbIdEqualTo(key);
			
			List<ForeignKeyInfo> foreign = mapper.selectByExample(example);
			if(foreign.isEmpty()) {
				throw new IllegalStateException("没有找到外键");
			}
			ForeignKeyInfo keyInfo = foreign.iterator().next();
			
			DataTable<DataMap> tmp = join(tmpMap,children);
			
			if(tmp != null) {
				dataTable = dataTable.join(tmpTable, keyInfo.getTbId(), keyInfo.getRefFdId());
			}
			children = children.getRight();
		}
		return dataTable;
	}
	
	
	/**
	 * 将路径path中的节点都添加到二叉树root中，path的第一个元素应该是root的根节点
	 * @param root
	 * @param path
	 */
	private void buildTree(TreeNode<String> root, List<String> path) {
		String data = root.getData();
		Iterator<String> iterator = path.iterator();
		String string = iterator.next();
		
		if(data.equals(string)) {
			while(iterator.hasNext()) {// 遍历路径中的节点
				// 如果当前路径顶点已经添加到tree中
				String next = iterator.next();
				if(root.hasChild(next)) {
					root = root.getChild(next);
				}else {
					root = root.addChild(next);
				}
			}
		}else {
			throw new IllegalStateException("构建二叉树异常");
		}
	}

	/**
	 * 获取数据表中的数据
	 * @param dataTableInfo
	 * @return
	 * @throws Exception
	 */
	protected DataTable<DataMap> getTable( DataTableInfo dataTableInfo) throws Exception {
		ApplicationContext context = App.getApplicationContext();
		TableFieldInfoExample ex = new TableFieldInfoExample();
		ex.createCriteria().andTbIdEqualTo(dataTableInfo.getId());
		DatabaseInfo databaseInfo = context.getBean(DatabaseInfoMapper.class).selectByPrimaryKey(dataTableInfo.getDbId());
		
		DataAdapter dataAdapter = DataAdapterFactory.getDataAdapter(databaseInfo);
		DataTable<DataMap> dataTable = dataAdapter.getDataTable(dataTableInfo);
		return dataTable;
	}

}
