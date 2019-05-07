package com.xinyibi.adapter;

import java.util.List;

import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.pojo.ForeignKeyInfo;
import com.xinyibi.pojo.TableFieldInfo;

public interface MetaDataReader {
	
	/**
	 * 获取数据表信息
	 * @return
	 */
	List<DataTableInfo> getDataTableList();
	
	/**
	 * 获取字段信息
	 * @param tableName
	 * @return
	 */
	List<TableFieldInfo> getTableFieldList(String tableName);
	
	/**
	 * 获取外键信息
	 * @param tableInfo 数据表名称
	 * @return
	 */
	List<ForeignKeyInfo> getForeignKeyList(String tableInfo);
	
}
