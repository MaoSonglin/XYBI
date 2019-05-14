package com.xinyibi.pojo;

import java.util.ArrayList;
import java.util.List;

public class ReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReportExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andMinWidthIsNull() {
            addCriterion("min_width is null");
            return (Criteria) this;
        }

        public Criteria andMinWidthIsNotNull() {
            addCriterion("min_width is not null");
            return (Criteria) this;
        }

        public Criteria andMinWidthEqualTo(Double value) {
            addCriterion("min_width =", value, "minWidth");
            return (Criteria) this;
        }

        public Criteria andMinWidthNotEqualTo(Double value) {
            addCriterion("min_width <>", value, "minWidth");
            return (Criteria) this;
        }

        public Criteria andMinWidthGreaterThan(Double value) {
            addCriterion("min_width >", value, "minWidth");
            return (Criteria) this;
        }

        public Criteria andMinWidthGreaterThanOrEqualTo(Double value) {
            addCriterion("min_width >=", value, "minWidth");
            return (Criteria) this;
        }

        public Criteria andMinWidthLessThan(Double value) {
            addCriterion("min_width <", value, "minWidth");
            return (Criteria) this;
        }

        public Criteria andMinWidthLessThanOrEqualTo(Double value) {
            addCriterion("min_width <=", value, "minWidth");
            return (Criteria) this;
        }

        public Criteria andMinWidthIn(List<Double> values) {
            addCriterion("min_width in", values, "minWidth");
            return (Criteria) this;
        }

        public Criteria andMinWidthNotIn(List<Double> values) {
            addCriterion("min_width not in", values, "minWidth");
            return (Criteria) this;
        }

        public Criteria andMinWidthBetween(Double value1, Double value2) {
            addCriterion("min_width between", value1, value2, "minWidth");
            return (Criteria) this;
        }

        public Criteria andMinWidthNotBetween(Double value1, Double value2) {
            addCriterion("min_width not between", value1, value2, "minWidth");
            return (Criteria) this;
        }

        public Criteria andWidthIsNull() {
            addCriterion("width is null");
            return (Criteria) this;
        }

        public Criteria andWidthIsNotNull() {
            addCriterion("width is not null");
            return (Criteria) this;
        }

        public Criteria andWidthEqualTo(Double value) {
            addCriterion("width =", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotEqualTo(Double value) {
            addCriterion("width <>", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThan(Double value) {
            addCriterion("width >", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThanOrEqualTo(Double value) {
            addCriterion("width >=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThan(Double value) {
            addCriterion("width <", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThanOrEqualTo(Double value) {
            addCriterion("width <=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthIn(List<Double> values) {
            addCriterion("width in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotIn(List<Double> values) {
            addCriterion("width not in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthBetween(Double value1, Double value2) {
            addCriterion("width between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotBetween(Double value1, Double value2) {
            addCriterion("width not between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andMaxWidthIsNull() {
            addCriterion("max_width is null");
            return (Criteria) this;
        }

        public Criteria andMaxWidthIsNotNull() {
            addCriterion("max_width is not null");
            return (Criteria) this;
        }

        public Criteria andMaxWidthEqualTo(Double value) {
            addCriterion("max_width =", value, "maxWidth");
            return (Criteria) this;
        }

        public Criteria andMaxWidthNotEqualTo(Double value) {
            addCriterion("max_width <>", value, "maxWidth");
            return (Criteria) this;
        }

        public Criteria andMaxWidthGreaterThan(Double value) {
            addCriterion("max_width >", value, "maxWidth");
            return (Criteria) this;
        }

        public Criteria andMaxWidthGreaterThanOrEqualTo(Double value) {
            addCriterion("max_width >=", value, "maxWidth");
            return (Criteria) this;
        }

        public Criteria andMaxWidthLessThan(Double value) {
            addCriterion("max_width <", value, "maxWidth");
            return (Criteria) this;
        }

        public Criteria andMaxWidthLessThanOrEqualTo(Double value) {
            addCriterion("max_width <=", value, "maxWidth");
            return (Criteria) this;
        }

        public Criteria andMaxWidthIn(List<Double> values) {
            addCriterion("max_width in", values, "maxWidth");
            return (Criteria) this;
        }

        public Criteria andMaxWidthNotIn(List<Double> values) {
            addCriterion("max_width not in", values, "maxWidth");
            return (Criteria) this;
        }

        public Criteria andMaxWidthBetween(Double value1, Double value2) {
            addCriterion("max_width between", value1, value2, "maxWidth");
            return (Criteria) this;
        }

        public Criteria andMaxWidthNotBetween(Double value1, Double value2) {
            addCriterion("max_width not between", value1, value2, "maxWidth");
            return (Criteria) this;
        }

        public Criteria andMinHeightIsNull() {
            addCriterion("min_height is null");
            return (Criteria) this;
        }

        public Criteria andMinHeightIsNotNull() {
            addCriterion("min_height is not null");
            return (Criteria) this;
        }

        public Criteria andMinHeightEqualTo(Double value) {
            addCriterion("min_height =", value, "minHeight");
            return (Criteria) this;
        }

        public Criteria andMinHeightNotEqualTo(Double value) {
            addCriterion("min_height <>", value, "minHeight");
            return (Criteria) this;
        }

        public Criteria andMinHeightGreaterThan(Double value) {
            addCriterion("min_height >", value, "minHeight");
            return (Criteria) this;
        }

        public Criteria andMinHeightGreaterThanOrEqualTo(Double value) {
            addCriterion("min_height >=", value, "minHeight");
            return (Criteria) this;
        }

        public Criteria andMinHeightLessThan(Double value) {
            addCriterion("min_height <", value, "minHeight");
            return (Criteria) this;
        }

        public Criteria andMinHeightLessThanOrEqualTo(Double value) {
            addCriterion("min_height <=", value, "minHeight");
            return (Criteria) this;
        }

        public Criteria andMinHeightIn(List<Double> values) {
            addCriterion("min_height in", values, "minHeight");
            return (Criteria) this;
        }

        public Criteria andMinHeightNotIn(List<Double> values) {
            addCriterion("min_height not in", values, "minHeight");
            return (Criteria) this;
        }

        public Criteria andMinHeightBetween(Double value1, Double value2) {
            addCriterion("min_height between", value1, value2, "minHeight");
            return (Criteria) this;
        }

        public Criteria andMinHeightNotBetween(Double value1, Double value2) {
            addCriterion("min_height not between", value1, value2, "minHeight");
            return (Criteria) this;
        }

        public Criteria andHeightIsNull() {
            addCriterion("height is null");
            return (Criteria) this;
        }

        public Criteria andHeightIsNotNull() {
            addCriterion("height is not null");
            return (Criteria) this;
        }

        public Criteria andHeightEqualTo(Double value) {
            addCriterion("height =", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotEqualTo(Double value) {
            addCriterion("height <>", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThan(Double value) {
            addCriterion("height >", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThanOrEqualTo(Double value) {
            addCriterion("height >=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThan(Double value) {
            addCriterion("height <", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThanOrEqualTo(Double value) {
            addCriterion("height <=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightIn(List<Double> values) {
            addCriterion("height in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotIn(List<Double> values) {
            addCriterion("height not in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightBetween(Double value1, Double value2) {
            addCriterion("height between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotBetween(Double value1, Double value2) {
            addCriterion("height not between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andMaxHeightIsNull() {
            addCriterion("max_height is null");
            return (Criteria) this;
        }

        public Criteria andMaxHeightIsNotNull() {
            addCriterion("max_height is not null");
            return (Criteria) this;
        }

        public Criteria andMaxHeightEqualTo(Double value) {
            addCriterion("max_height =", value, "maxHeight");
            return (Criteria) this;
        }

        public Criteria andMaxHeightNotEqualTo(Double value) {
            addCriterion("max_height <>", value, "maxHeight");
            return (Criteria) this;
        }

        public Criteria andMaxHeightGreaterThan(Double value) {
            addCriterion("max_height >", value, "maxHeight");
            return (Criteria) this;
        }

        public Criteria andMaxHeightGreaterThanOrEqualTo(Double value) {
            addCriterion("max_height >=", value, "maxHeight");
            return (Criteria) this;
        }

        public Criteria andMaxHeightLessThan(Double value) {
            addCriterion("max_height <", value, "maxHeight");
            return (Criteria) this;
        }

        public Criteria andMaxHeightLessThanOrEqualTo(Double value) {
            addCriterion("max_height <=", value, "maxHeight");
            return (Criteria) this;
        }

        public Criteria andMaxHeightIn(List<Double> values) {
            addCriterion("max_height in", values, "maxHeight");
            return (Criteria) this;
        }

        public Criteria andMaxHeightNotIn(List<Double> values) {
            addCriterion("max_height not in", values, "maxHeight");
            return (Criteria) this;
        }

        public Criteria andMaxHeightBetween(Double value1, Double value2) {
            addCriterion("max_height between", value1, value2, "maxHeight");
            return (Criteria) this;
        }

        public Criteria andMaxHeightNotBetween(Double value1, Double value2) {
            addCriterion("max_height not between", value1, value2, "maxHeight");
            return (Criteria) this;
        }

        public Criteria andThumbnailIsNull() {
            addCriterion("thumbnail is null");
            return (Criteria) this;
        }

        public Criteria andThumbnailIsNotNull() {
            addCriterion("thumbnail is not null");
            return (Criteria) this;
        }

        public Criteria andThumbnailEqualTo(String value) {
            addCriterion("thumbnail =", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailNotEqualTo(String value) {
            addCriterion("thumbnail <>", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailGreaterThan(String value) {
            addCriterion("thumbnail >", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailGreaterThanOrEqualTo(String value) {
            addCriterion("thumbnail >=", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailLessThan(String value) {
            addCriterion("thumbnail <", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailLessThanOrEqualTo(String value) {
            addCriterion("thumbnail <=", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailLike(String value) {
            addCriterion("thumbnail like", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailNotLike(String value) {
            addCriterion("thumbnail not like", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailIn(List<String> values) {
            addCriterion("thumbnail in", values, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailNotIn(List<String> values) {
            addCriterion("thumbnail not in", values, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailBetween(String value1, String value2) {
            addCriterion("thumbnail between", value1, value2, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailNotBetween(String value1, String value2) {
            addCriterion("thumbnail not between", value1, value2, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andLackIsNull() {
            addCriterion("lack is null");
            return (Criteria) this;
        }

        public Criteria andLackIsNotNull() {
            addCriterion("lack is not null");
            return (Criteria) this;
        }

        public Criteria andLackEqualTo(String value) {
            addCriterion("lack =", value, "lack");
            return (Criteria) this;
        }

        public Criteria andLackNotEqualTo(String value) {
            addCriterion("lack <>", value, "lack");
            return (Criteria) this;
        }

        public Criteria andLackGreaterThan(String value) {
            addCriterion("lack >", value, "lack");
            return (Criteria) this;
        }

        public Criteria andLackGreaterThanOrEqualTo(String value) {
            addCriterion("lack >=", value, "lack");
            return (Criteria) this;
        }

        public Criteria andLackLessThan(String value) {
            addCriterion("lack <", value, "lack");
            return (Criteria) this;
        }

        public Criteria andLackLessThanOrEqualTo(String value) {
            addCriterion("lack <=", value, "lack");
            return (Criteria) this;
        }

        public Criteria andLackLike(String value) {
            addCriterion("lack like", value, "lack");
            return (Criteria) this;
        }

        public Criteria andLackNotLike(String value) {
            addCriterion("lack not like", value, "lack");
            return (Criteria) this;
        }

        public Criteria andLackIn(List<String> values) {
            addCriterion("lack in", values, "lack");
            return (Criteria) this;
        }

        public Criteria andLackNotIn(List<String> values) {
            addCriterion("lack not in", values, "lack");
            return (Criteria) this;
        }

        public Criteria andLackBetween(String value1, String value2) {
            addCriterion("lack between", value1, value2, "lack");
            return (Criteria) this;
        }

        public Criteria andLackNotBetween(String value1, String value2) {
            addCriterion("lack not between", value1, value2, "lack");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(String value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(String value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(String value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(String value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(String value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(String value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLike(String value) {
            addCriterion("add_time like", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotLike(String value) {
            addCriterion("add_time not like", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<String> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<String> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(String value1, String value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(String value1, String value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}