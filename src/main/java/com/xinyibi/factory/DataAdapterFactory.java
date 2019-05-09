package com.xinyibi.factory;

import com.xinyibi.adapter.DataAdapter;
import com.xinyibi.adapter.JdbcDataAdapter;
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
}
