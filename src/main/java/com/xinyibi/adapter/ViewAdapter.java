package com.xinyibi.adapter;

import com.tsc9526.monalisa.core.query.datatable.DataMap;
import com.tsc9526.monalisa.core.query.datatable.DataTable;

public interface ViewAdapter {
	
	/**
	 * 查询指定视图字段的数据
	 * @param viewFields
	 * @return
	 * @throws Exception
	 */
	DataTable<DataMap> getDataTable(String viewId) throws Exception;
	
}
