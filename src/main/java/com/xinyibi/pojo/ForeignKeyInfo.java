package com.xinyibi.pojo;

import java.util.Date;

public class ForeignKeyInfo {

	private String id;
	private String foreignKeyName;
	private String fieldId;
	private String tbId;
	private String refTbId;
	private String refFdId;
	private Date addTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getForeignKeyName() {
		return foreignKeyName;
	}

	public void setForeignKeyName(String foreignKeyName) {
		this.foreignKeyName = foreignKeyName == null ? null : foreignKeyName.trim();
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId == null ? null : fieldId.trim();
	}

	public String getTbId() {
		return tbId;
	}

	public void setTbId(String tbId) {
		this.tbId = tbId == null ? null : tbId.trim();
	}

	public String getRefTbId() {
		return refTbId;
	}

	public void setRefTbId(String refTbId) {
		this.refTbId = refTbId == null ? null : refTbId.trim();
	}

	public String getRefFdId() {
		return refFdId;
	}

	public void setRefFdId(String refFdId) {
		this.refFdId = refFdId == null ? null : refFdId.trim();
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
}