package com.xinyibi.adapter;

import java.util.List;

import com.tsc9526.monalisa.core.query.datatable.DataMap;
import com.tsc9526.monalisa.core.query.datatable.DataTable;
import com.xinyibi.pojo.ViewField;

public interface ViewAdapter {
	
	/**
	 * 查询指定视图字段的数据
	 * @param viewFields
	 * @return
	 * @throws Exception
	 */
	DataTable<DataMap> getDataTable(List<ViewField> viewFields) throws Exception;
	
}
