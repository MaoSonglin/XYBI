package com.xinyibi.adapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;

import com.tsc9526.monalisa.core.query.datatable.DataMap;
import com.tsc9526.monalisa.core.query.datatable.DataTable;
import com.xinyibi.App;
import com.xinyibi.mapper.DataTableInfoMapper;
import com.xinyibi.mapper.DatabaseInfoMapper;
import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.pojo.DataTableInfoExample;
import com.xinyibi.pojo.DatabaseInfo;
import com.xinyibi.pojo.TableFieldInfo;
import com.xinyibi.service.FileService;
import com.xinyibi.util.DriverUtil;
import com.xinyibi.vo.PageEntry;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JdbcDataAdapter implements DataAdapter {
	
	public DataTable<DataMap> getDataTable(List<TableFieldInfo> fields,PageEntry pageEntry) throws Exception{
		
		ApplicationContext context = App.getApplicationContext();
		
		DataTableInfoMapper dataTableInfoMapper = context.getBean(DataTableInfoMapper.class);
		DataTableInfoExample dateTableInfoExample = new DataTableInfoExample();
		dateTableInfoExample.createCriteria().andIdIn(fields.stream().map(field->field.getTbId()).distinct().collect(Collectors.toList()));
		List<DataTableInfo> selectByExample = dataTableInfoMapper.selectByExample(dateTableInfoExample);
		if(selectByExample.isEmpty() || selectByExample.size() > 1) {
			throw new IllegalArgumentException("获取字段数据表错误");
		}
		DataTableInfo dataTableInfo = selectByExample.get(0);
		
		DatabaseInfoMapper databaseInfoMapper = context.getBean(DatabaseInfoMapper.class);
		// 所属数据源
		DatabaseInfo databaseInfo = databaseInfoMapper.selectByPrimaryKey(dataTableInfo.getDbId());
		
		// 数据库驱动文件
		Long databaseFileId = databaseInfo.getDatabaseFileId();
		// 驱动文件访问路径
		String fileUrl = context.getBean(FileService.class).getFileUrl(databaseFileId);
		// 加载驱动
		DriverUtil.loadDriverClass(databaseInfo.getDriverClassName(), fileUrl);
		
		@Cleanup Connection conn = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUname(), databaseInfo.getUpwd());
		@Cleanup PreparedStatement ps = conn.prepareStatement("select count(*) count from "+dataTableInfo.getTableName());
		@Cleanup ResultSet rs = ps.executeQuery();
		long count = 0; 
		if(rs.next()) {
			count = rs.getLong(1);
		}
		
		if(count < 1000) {
			return simpleTableQuery(fields, dataTableInfo, databaseInfo);
		}
		
		return pageQuery(fields,dataTableInfo,databaseInfo);
	}

	protected DataTable<DataMap> simpleTableQuery(List<TableFieldInfo> fields, DataTableInfo dataTableInfo, DatabaseInfo databaseInfo) throws SQLException {
		StringBuffer sBuffer = new StringBuffer("select ");
		appendField(fields, sBuffer);
		sBuffer.append(" from ").append(dataTableInfo.getTableName()).append(" ").append(dataTableInfo.getId());
		log.debug(sBuffer.toString());
		@Cleanup Connection conn2 = DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUname(), databaseInfo.getUpwd());
		@Cleanup PreparedStatement ps2 = conn2.prepareStatement(sBuffer.toString());
		@Cleanup ResultSet rs2 = ps2.executeQuery();
		ResultSetMetaData metaData = rs2.getMetaData();
		int columnCount = metaData.getColumnCount();
		DataTable<DataMap> dataTable = new DataTable<>();
		int row = 1;
		while(rs2.next()) {
			DataMap dataMap = new DataMap();
			dataMap.put("rownum", row++);
			for(int i = 1; i <= columnCount; i++) {
				String columnLabel = metaData.getColumnLabel(i);
				Object object = rs2.getObject(i);
				dataMap.put(columnLabel, object);
			}
			dataTable.add(dataMap);
		}
		return dataTable;
	}
	
	protected DataTable<DataMap> pageQuery(List<TableFieldInfo> fields, DataTableInfo dataTableInfo, DatabaseInfo databaseInfo) throws SQLException{
		return simpleTableQuery(fields, dataTableInfo, databaseInfo);
	}


	protected void appendField(List<TableFieldInfo> tableFields, StringBuffer sBuffer) {
		for (TableFieldInfo tableFieldInfo : tableFields) {
			sBuffer.append(tableFieldInfo.getTbId()).append(".")
			.append(tableFieldInfo.getFieldName())
			.append(" ").append(tableFieldInfo.getId()).append(",");
		}
		sBuffer.deleteCharAt(sBuffer.length()-1);
	}
}
