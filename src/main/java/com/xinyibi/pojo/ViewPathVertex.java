package com.xinyibi.pojo;

public class ViewPathVertex {
    private Long id;

    private String tableId;

    private String leftFieldId;

    private String rightFieldId;

    private Long headerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId == null ? null : tableId.trim();
    }

    public String getLeftFieldId() {
        return leftFieldId;
    }

    public void setLeftFieldId(String leftFieldId) {
        this.leftFieldId = leftFieldId == null ? null : leftFieldId.trim();
    }

    public String getRightFieldId() {
        return rightFieldId;
    }

    public void setRightFieldId(String rightFieldId) {
        this.rightFieldId = rightFieldId == null ? null : rightFieldId.trim();
    }

    public Long getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Long headerId) {
        this.headerId = headerId;
    }
}