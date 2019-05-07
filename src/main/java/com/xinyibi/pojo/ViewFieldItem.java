package com.xinyibi.pojo;

public class ViewFieldItem {
    private Long id;

    private String content;

    private String type;

    private Integer order;

    private String tableFieldId;

    private Long viewFieldId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getTableFieldId() {
        return tableFieldId;
    }

    public void setTableFieldId(String tableFieldId) {
        this.tableFieldId = tableFieldId == null ? null : tableFieldId.trim();
    }

    public Long getViewFieldId() {
        return viewFieldId;
    }

    public void setViewFieldId(Long viewFieldId) {
        this.viewFieldId = viewFieldId;
    }
}