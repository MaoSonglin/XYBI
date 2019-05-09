package com.xinyibi.adapter;

import java.util.List;
import java.util.stream.Collectors;

import com.tsc9526.monalisa.core.query.datatable.DataMap;
import com.tsc9526.monalisa.core.query.datatable.DataTable;
import com.xinyibi.App;
import com.xinyibi.exception.ViewNotFoundException;
import com.xinyibi.mapper.TableViewMapper;
import com.xinyibi.pojo.TableView;
import com.xinyibi.pojo.ViewField;

public class SingleViewAdapter implements ViewAdapter {

	@Override
	public DataTable<DataMap> getDataTable(List<ViewField> viewFields) throws Exception {
		List<String> collect = viewFields.stream().map(f->f.getViewId()).distinct().collect(Collectors.toList());
		if(collect.size() == 1) {
			loadTableTable(collect);
		}else if(collect.size() > 1) {
			
		}
		return null;
	}

	private void loadTableTable(List<String> collect) throws ViewNotFoundException {
		String id = collect.get(0);
		TableViewMapper viewMapper = App.getApplicationContext().getBean(TableViewMapper.class);
		TableView view = viewMapper.selectByPrimaryKey(id);
		if(view == null)
			throw new ViewNotFoundException(id);
		
		
	}

}
