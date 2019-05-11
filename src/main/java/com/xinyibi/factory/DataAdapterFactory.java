package com.xinyibi.factory;

import com.xinyibi.App;
import com.xinyibi.adapter.DataAdapter;
import com.xinyibi.adapter.JdbcDataAdapter;
import com.xinyibi.mapper.DatabaseInfoMapper;
import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.pojo.DatabaseInfo;

public class DataAdapterFactory {

	public static DataAdapter getDataAdapter(DatabaseInfo databaseInfo) {
		String type = databaseInfo.getDatabaseType();
		if(type == null) {
			return null;
		}
		switch(type) {
		case "MySQL":
		case "Oracle":
		case "SQL Server":
		case "SQLite":
			return new JdbcDataAdapter();
		}
		return null;
	}
	public static DataAdapter getDataAdapter(DataTableInfo datatable) {
		DatabaseInfo databaseInfo = App.getApplicationContext().getBean(DatabaseInfoMapper.class).selectByPrimaryKey(datatable.getDbId());
		if(databaseInfo == null)
			return null;
		String type = databaseInfo.getDatabaseType();
		if(type == null) {
			return null;
		}
		switch(type) {
		case "MySQL":
		case "Oracle":
		case "SQL Server":
		case "SQLite":
			return new JdbcDataAdapter();
		}
		return null;
	}
}
