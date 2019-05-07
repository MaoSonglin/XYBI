package com.xinyibi.model;

import java.util.List;

import com.xinyibi.pojo.DatabaseInfo;
import com.xinyibi.pojo.TableFieldInfo;
import com.xinyibi.pojo.TableView;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ViewModel extends TableView{
	
	private List<DatabaseInfo> databases;
	
	private List<TableFieldInfo> tableFields;
	
	private List<ViewGraphModel> heads;
	
}
