package com.xinyibi.pojo;

import java.util.Date;

public class DataTableInfo {

	private String id;
	private String tableName;
	private String tableZhChName;
	private String type;
	private Date addTime;
	private String dbId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName == null ? null : tableName.trim();
	}

	public String getTableZhChName() {
		return tableZhChName;
	}

	public void setTableZhChName(String tableZhChName) {
		this.tableZhChName = tableZhChName == null ? null : tableZhChName.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getDbId() {
		return dbId;
	}

	public void setDbId(String dbId) {
		this.dbId = dbId == null ? null : dbId.trim();
	}
}