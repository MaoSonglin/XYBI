package com.xinyibi.adapter;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.tsc9526.monalisa.core.query.datatable.DataMap;
import com.tsc9526.monalisa.core.query.datatable.DataTable;
import com.xinyibi.exception.ViewNotFoundException;
import com.xinyibi.mapper.DataTableInfoMapper;
import com.xinyibi.mapper.TableFieldInfoMapper;
import com.xinyibi.mapper.TableViewMapper;
import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.pojo.TableFieldInfo;
import com.xinyibi.pojo.TableView;

import lombok.Setter;

@Setter
public class DataReader {
	
	private ApplicationContext context;
	
	public DataTable<DataMap> getDataTable(String viewId) throws ViewNotFoundException{
		TableView view = context.getBean(TableViewMapper.class).selectByPrimaryKey(viewId);
		if(view == null){
			throw new ViewNotFoundException(viewId);
		}
		List<DataTableInfo> tables = context.getBean(DataTableInfoMapper.class).findByViewId(viewId);
		List<TableFieldInfo> tableFieldInfos = context.getBean(TableFieldInfoMapper.class).findByViewId(viewId);
		if(tables.size() == 1){
			String dbId = tables.get(0).getDbId();
			for (TableFieldInfo tableFieldInfo : tableFieldInfos) {
				
			}
		}
		return null;
	}
}
