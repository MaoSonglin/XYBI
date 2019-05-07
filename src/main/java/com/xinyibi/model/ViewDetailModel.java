package com.xinyibi.model;

import java.io.Serializable;
import java.util.List;

import com.xinyibi.pojo.DataTableInfo;
import com.xinyibi.pojo.DatabaseInfo;
import com.xinyibi.pojo.TableFieldInfo;
import com.xinyibi.pojo.TableView;
import com.xinyibi.pojo.ViewField;

import lombok.Data;

@Data
public class ViewDetailModel implements Serializable{
	
	private static final long serialVersionUID = 102225537379252396L;

	private TableView view;
	
	private List<ViewField> viewFields;
	
	private List<DataTableInfo> relatedTables;
	
	private List<TableFieldInfo> relatedFields;
	
	private List<DatabaseInfo> databases;
	
	private List<ViewGraphModel> graph;
}
