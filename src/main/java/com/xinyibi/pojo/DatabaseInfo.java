package com.xinyibi.pojo;

import java.util.Date;

public class DatabaseInfo {

	private String id;
	private String name;
	private String uname;
	private String upwd;
	private String url;
	private String driverClassName;
	private Long driverFileId;
	private String databaseType;
	private Long databaseFileId;
	private Date addTime;
	private String comment;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname == null ? null : uname.trim();
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd == null ? null : upwd.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName == null ? null : driverClassName.trim();
	}

	public Long getDriverFileId() {
		return driverFileId;
	}

	public void setDriverFileId(Long driverFileId) {
		this.driverFileId = driverFileId;
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType == null ? null : databaseType.trim();
	}

	public Long getDatabaseFileId() {
		return databaseFileId;
	}

	public void setDatabaseFileId(Long databaseFileId) {
		this.databaseFileId = databaseFileId;
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
}