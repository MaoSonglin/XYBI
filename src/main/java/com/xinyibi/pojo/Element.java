package com.xinyibi.pojo;

public class Element {
    private Long id;

    private String name;

    private String title;

    private Double height;

    private Double width;

    private Double marginLeft;

    private Double marginTop;

    private Double marginRight;

    private Double marginBottom;

    private String setting;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(Double marginLeft) {
        this.marginLeft = marginLeft;
    }

    public Double getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(Double marginTop) {
        this.marginTop = marginTop;
    }

    public Double getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(Double marginRight) {
        this.marginRight = marginRight;
    }

    public Double getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(Double marginBottom) {
        this.marginBottom = marginBottom;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting == null ? null : setting.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}