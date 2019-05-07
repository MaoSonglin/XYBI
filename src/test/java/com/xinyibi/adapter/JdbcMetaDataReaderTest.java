package com.xinyibi.adapter;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.pojo.DatabaseInfo;
import com.xinyibi.pojo.ForeignKeyInfo;
import com.xinyibi.util.StrUtils;

public class JdbcMetaDataReaderTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void run1(){
		DatabaseInfo databaseInfo = new DatabaseInfo();
		databaseInfo.setUname("root");
		databaseInfo.setUpwd("123456");
		databaseInfo.setUrl("jdbc:mysql://localhost:3306/sqlite");
		databaseInfo.setDriverClassName("com.mysql.jdbc.Driver");
		databaseInfo.setAddTime(new Date());
		databaseInfo.setDatabaseType("MySQL");
		
		JdbcMetaDataReader jdbcMetaDataReader = new JdbcMetaDataReader();
		jdbcMetaDataReader.setDriverClassName(databaseInfo.getDriverClassName());
		jdbcMetaDataReader.setUsername(databaseInfo.getUname());
		jdbcMetaDataReader.setPassword(databaseInfo.getUpwd());
		jdbcMetaDataReader.setUrl(databaseInfo.getUrl());
		
		List<DataTableInfo> dataTableList = jdbcMetaDataReader.getDataTableList();
		for (DataTableInfo dataTableInfo : dataTableList) {
//			System.out.println(StrUtils.toString(dataTableInfo));
//			List<TableFieldInfo> tableFieldList = jdbcMetaDataReader.getTableFieldList(dataTableInfo.getTableName());
			List<ForeignKeyInfo> foreignKeyList = jdbcMetaDataReader.getForeignKeyList(dataTableInfo.getTableName());
			for (ForeignKeyInfo foreignKey : foreignKeyList) {
				System.out.println(StrUtils.toString(foreignKey));
			}
			
			System.out.println();
//			foreignKeys.addAll(foreignKeyList);
//			fieldList.addAll(tableFieldList);
		}
		/*for (TableFieldInfo fieldInfo : fieldList) {
			System.out.println(StrUtils.toString(fieldInfo));
		}
		for (ForeignKeyInfo foreignKey : foreignKeys) {
			System.out.println(StrUtils.toString(foreignKey));
		}*/
	}
	
	@Test
	public void run3(){
		for(int i = 0; i <10; i++){
			System.out.println(StrUtils.getNextId("fk"));
		}
	}
}
