package com.xinyibi.adapter;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.pojo.ForeignKeyInfo;
import com.xinyibi.pojo.TableFieldInfo;
import com.xinyibi.util.StrUtils;

import lombok.Setter;


public class JdbcMetaDataReader implements MetaDataReader {
	
	private @Setter String username;
	private @Setter String password;
	private @Setter String driverClassName;
	private @Setter String url;
	private @Setter String driverFile;
	
	private boolean flag = true;
	
	@Override
	public List<DataTableInfo> getDataTableList() {
		// 加载数据库驱动
		initDriverClass();
		// 打开数据库连接
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			// 查找的数据表类型包括table和view
			String[] tableTypes = new String[]{"TABLE","VIEW"};
			// 获取元数据，从元数据中获取数据表结构
			DatabaseMetaData metaData = conn.getMetaData();
			try(ResultSet resultSet = metaData.getTables(null, null, null, tableTypes)){
				List<DataTableInfo> list = new ArrayList<>();
				while(resultSet.next()){
					String tableName = resultSet.getString("TABLE_NAME");
					DataTableInfo tableInfo = new DataTableInfo();
					tableInfo.setTableName(tableName);
					tableInfo.setAddTime(new Date());
					tableInfo.setTableZhChName(resultSet.getString("REMARKS"));
					tableInfo.setId(StrUtils.getNextId("TB"));
					list.add(tableInfo);
				}
				return list;
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		} 
	}

	/**
	 * 加载数据库驱动
	 */
	private void initDriverClass() {
		if(flag){
			flag = false;
			if(driverFile != null){ 
				try {
					URL url2 = new URL(driverFile);
					ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
					try(URLClassLoader classLoader = new URLClassLoader(new URL[]{url2},contextClassLoader)){
						classLoader.loadClass(driverClassName);
					}
				} catch (Exception e) { 
					throw new IllegalArgumentException(e);
				}
			}else{
				try {
					Class.forName(driverClassName);
				} catch (ClassNotFoundException e) {
					throw new IllegalArgumentException(e);
				}
			}
		}
	}

	@Override
	public List<TableFieldInfo> getTableFieldList(String tableName) {
		initDriverClass();
		// 打开数据库连接
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			// 获取元数据，从元数据中获取数据表结构
			DatabaseMetaData metaData = conn.getMetaData();
			try(ResultSet resultSet = metaData.getColumns(null, null, tableName, null)){
				List<TableFieldInfo> list = new ArrayList<>();
				while(resultSet.next()){
					String COLUMN_NAME = resultSet.getString("COLUMN_NAME");
					int COLUMN_SIZE = resultSet.getInt("COLUMN_SIZE");
					String TYPE_NAME = resultSet.getString("TYPE_NAME");
					String REMARSKS = resultSet.getString("REMARKS");
					TableFieldInfo tableFieldInfo = new TableFieldInfo();
					tableFieldInfo.setFieldName(COLUMN_NAME);
					tableFieldInfo.setLength(COLUMN_SIZE);
					tableFieldInfo.setJdbcType(TYPE_NAME);
					tableFieldInfo.setComment(REMARSKS);
					tableFieldInfo.setAddTime(new Date());
					tableFieldInfo.setId(StrUtils.getNextId("DF"));
					tableFieldInfo.setJavaType("String");
					list.add(tableFieldInfo);
				}
				return list;

			}
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		} 
	}

	@Override
	public List<ForeignKeyInfo> getForeignKeyList(String tableName) {
		initDriverClass();
		// 打开数据库连接
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			// 获取元数据，从元数据中获取数据表结构
			DatabaseMetaData metaData = conn.getMetaData();
			try(ResultSet resultSet = metaData.getImportedKeys(null, null, tableName)){
				List<ForeignKeyInfo> list = new ArrayList<>();
				while(resultSet.next()){
					String FK_NAME = resultSet.getString("FK_NAME");
					String PKTABLE_NAME = resultSet.getString("PKTABLE_NAME");
					String FKTABLE_NAME = resultSet.getString("FKTABLE_NAME");
					String PKCOLUMN_NAME = resultSet.getString("PKCOLUMN_NAME");
					String FKCOLUMN_NAME = resultSet.getString("FKCOLUMN_NAME");
					ForeignKeyInfo foreignKeyInfo = new ForeignKeyInfo();
					foreignKeyInfo.setForeignKeyName(FK_NAME);
					foreignKeyInfo.setFieldId(FKCOLUMN_NAME);
					foreignKeyInfo.setRefFdId(PKCOLUMN_NAME);
					foreignKeyInfo.setTbId(FKTABLE_NAME);
					foreignKeyInfo.setRefTbId(PKTABLE_NAME);
					foreignKeyInfo.setAddTime(new Date());
					foreignKeyInfo.setId(StrUtils.getNextId("FK"));
					list.add(foreignKeyInfo);
				}
				return list;
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		} 
	}

}
