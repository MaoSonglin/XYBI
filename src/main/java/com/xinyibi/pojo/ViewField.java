package com.xinyibi.pojo;

import java.util.Date;
import java.util.List;

public class ViewField {
    private Long id;

    private String fieldName;

    private String fieldZhChName;

    private String dataType;

    private Date addTime;

    private String fieldType;

    private String viewId;

    private List<ViewFieldItem> items;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public String getFieldZhChName() {
        return fieldZhChName;
    }

    public void setFieldZhChName(String fieldZhChName) {
        this.fieldZhChName = fieldZhChName == null ? null : fieldZhChName.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType == null ? null : fieldType.trim();
    }

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId == null ? null : viewId.trim();
    }

	public List<ViewFieldItem> getItems() {
		return items;
	}

	public void setItems(List<ViewFieldItem> items) {
		this.items = items;
	}
}