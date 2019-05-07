package com.xinyibi.pojo;

import java.util.Date;

public class TableView {

	private String id;
	private String viewName;
	private String viewZhChName;
	private Date addTime;
	private String comment;
	private String dataSetId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName == null ? null : viewName.trim();
	}

	public String getViewZhChName() {
		return viewZhChName;
	}

	public void setViewZhChName(String viewZhChName) {
		this.viewZhChName = viewZhChName == null ? null : viewZhChName.trim();
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment == null ? null : comment.trim();
	}

	public String getDataSetId() {
		return dataSetId;
	}

	public void setDataSetId(String dataSetId) {
		this.dataSetId = dataSetId == null ? null : dataSetId.trim();
	}
}