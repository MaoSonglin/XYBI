package com.xinyibi.adapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.xinyibi.model.PathVertexModel;
import com.xinyibi.model.ViewGraphModel;
import com.xinyibi.model.ViewModel;
import com.xinyibi.pojo.DatabaseInfo;
import com.xinyibi.pojo.TableFieldInfo;
import com.xinyibi.util.DriverUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class DataAdapter {
	
	public static void main(String[] args) {
	}
	private ViewModel model;
	
	public void setViewModel(ViewModel model){
		this.model = model;
	}
	

	public void reader(){
		List<DatabaseInfo> databases = model.getDatabases();
		List<ViewGraphModel> heads = model.getHeads();
		// 待查询的字段
		List<TableFieldInfo> tableFields = model.getTableFields();
		// 各个数据库中需要查的字段
		Map<DatabaseInfo,TableFieldInfo[]> df = new HashMap<>();
		Map<DatabaseInfo,ViewGraphModel[]> map = new HashMap<>();
		for (DatabaseInfo databaseInfo : databases) {
			Stream<ViewGraphModel> filter = heads.stream().filter(head->head.getFrom().getDbId().equalsIgnoreCase(databaseInfo.getId()));
			map.put(databaseInfo, filter.toArray(ViewGraphModel[]::new));
			TableFieldInfo[] array = tableFields.stream()
					.filter(tableField -> heads.stream()
							.anyMatch(head -> head.getFrom().getDbId().equalsIgnoreCase(databaseInfo.getId())
									&& head.getFrom().getId().equalsIgnoreCase(tableField.getTbId())))
					.toArray(TableFieldInfo[]::new);
			df.put(databaseInfo, array);
		}
		
		map.forEach((databaseInfo,model)->{
			StringBuffer sBuffer = new StringBuffer();
			
			TableFieldInfo[] tableFieldInfos = df.get(databaseInfo);
			if(tableFieldInfos==null) throw new IllegalStateException();
			appendField(tableFields, sBuffer);
			
			try {
				DriverUtil.loadDriverClass(databaseInfo.getDriverClassName(), null);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			
			String sql = sBuffer.toString();
			
			log.debug(sql);
			
			
			
		});
		
	}
	
	

	protected void appendField(List<TableFieldInfo> tableFields, StringBuffer sBuffer) {
		for (TableFieldInfo tableFieldInfo : tableFields) {
			sBuffer.append(tableFieldInfo.getTbId())
			.append(tableFieldInfo.getFieldName())
			.append(" ").append(tableFieldInfo.getId()).append(",");
		}
		sBuffer.deleteCharAt(sBuffer.length()-1);
	}

	protected void appendTable(List<ViewGraphModel> heads, StringBuffer sBuffer) {
		for (ViewGraphModel viewGraphModel : heads) {
			// 查询的表
			sBuffer.append(" from ");
			sBuffer.append(viewGraphModel.getFrom().getTableName()).append(" ").append(viewGraphModel.getFrom().getId()).append(",");
			sBuffer.deleteCharAt(sBuffer.length()-1);
		}
		// 表的连接条件
		sBuffer.append(" where 1=1 ");
		for (ViewGraphModel viewGraphModel1 : heads) {
			List<PathVertexModel> joins = viewGraphModel1.getJoins();
			for (PathVertexModel pathVertexModel : joins) {
				sBuffer.append(" and ").append(on(pathVertexModel.getLeftField())).append(" = ").append(on(pathVertexModel.getRightField()));
			}
		}
	}
	private String on(TableFieldInfo fieldInfo){
		StringBuffer buffer = new StringBuffer();
		buffer.append(fieldInfo.getTbId()).append(".").append(fieldInfo.getFieldName());
		return buffer.toString();
	}
	
}
