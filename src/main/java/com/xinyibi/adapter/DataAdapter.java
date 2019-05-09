package com.xinyibi.adapter;

import java.util.List;

import com.tsc9526.monalisa.core.query.datatable.DataMap;
import com.tsc9526.monalisa.core.query.datatable.DataTable;
import com.xinyibi.pojo.TableFieldInfo;
import com.xinyibi.vo.PageEntry;

public interface DataAdapter {
	
	DataTable<DataMap> getDataTable(List<TableFieldInfo> fields,PageEntry pageEntry) throws Exception;
}
