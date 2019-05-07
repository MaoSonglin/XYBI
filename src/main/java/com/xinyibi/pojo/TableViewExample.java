package com.xinyibi.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TableViewExample {
    protected String orderByClause;
	protected boolean distinct;
	protected List<Criteria> oredCriteria;

	public TableViewExample() {
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

		public Criteria andIdEqualTo(String value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(String value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(String value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(String value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(String value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(String value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLike(String value) {
			addCriterion("id like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotLike(String value) {
			addCriterion("id not like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<String> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<String> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(String value1, String value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(String value1, String value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andViewNameIsNull() {
			addCriterion("view_name is null");
			return (Criteria) this;
		}

		public Criteria andViewNameIsNotNull() {
			addCriterion("view_name is not null");
			return (Criteria) this;
		}

		public Criteria andViewNameEqualTo(String value) {
			addCriterion("view_name =", value, "viewName");
			return (Criteria) this;
		}

		public Criteria andViewNameNotEqualTo(String value) {
			addCriterion("view_name <>", value, "viewName");
			return (Criteria) this;
		}

		public Criteria andViewNameGreaterThan(String value) {
			addCriterion("view_name >", value, "viewName");
			return (Criteria) this;
		}

		public Criteria andViewNameGreaterThanOrEqualTo(String value) {
			addCriterion("view_name >=", value, "viewName");
			return (Criteria) this;
		}

		public Criteria andViewNameLessThan(String value) {
			addCriterion("view_name <", value, "viewName");
			return (Criteria) this;
		}

		public Criteria andViewNameLessThanOrEqualTo(String value) {
			addCriterion("view_name <=", value, "viewName");
			return (Criteria) this;
		}

		public Criteria andViewNameLike(String value) {
			addCriterion("view_name like", value, "viewName");
			return (Criteria) this;
		}

		public Criteria andViewNameNotLike(String value) {
			addCriterion("view_name not like", value, "viewName");
			return (Criteria) this;
		}

		public Criteria andViewNameIn(List<String> values) {
			addCriterion("view_name in", values, "viewName");
			return (Criteria) this;
		}

		public Criteria andViewNameNotIn(List<String> values) {
			addCriterion("view_name not in", values, "viewName");
			return (Criteria) this;
		}

		public Criteria andViewNameBetween(String value1, String value2) {
			addCriterion("view_name between", value1, value2, "viewName");
			return (Criteria) this;
		}

		public Criteria andViewNameNotBetween(String value1, String value2) {
			addCriterion("view_name not between", value1, value2, "viewName");
			return (Criteria) this;
		}

		public Criteria andViewZhChNameIsNull() {
			addCriterion("view_zh_ch_name is null");
			return (Criteria) this;
		}

		public Criteria andViewZhChNameIsNotNull() {
			addCriterion("view_zh_ch_name is not null");
			return (Criteria) this;
		}

		public Criteria andViewZhChNameEqualTo(String value) {
			addCriterion("view_zh_ch_name =", value, "viewZhChName");
			return (Criteria) this;
		}

		public Criteria andViewZhChNameNotEqualTo(String value) {
			addCriterion("view_zh_ch_name <>", value, "viewZhChName");
			return (Criteria) this;
		}

		public Criteria andViewZhChNameGreaterThan(String value) {
			addCriterion("view_zh_ch_name >", value, "viewZhChName");
			return (Criteria) this;
		}

		public Criteria andViewZhChNameGreaterThanOrEqualTo(String value) {
			addCriterion("view_zh_ch_name >=", value, "viewZhChName");
			return (Criteria) this;
		}

		public Criteria andViewZhChNameLessThan(String value) {
			addCriterion("view_zh_ch_name <", value, "viewZhChName");
			return (Criteria) this;
		}

		public Criteria andViewZhChNameLessThanOrEqualTo(String value) {
			addCriterion("view_zh_ch_name <=", value, "viewZhChName");
			return (Criteria) this;
		}

		public Criteria andViewZhChNameLike(String value) {
			addCriterion("view_zh_ch_name like", value, "viewZhChName");
			return (Criteria) this;
		}

		public Criteria andViewZhChNameNotLike(String value) {
			addCriterion("view_zh_ch_name not like", value, "viewZhChName");
			return (Criteria) this;
		}

		public Criteria andViewZhChNameIn(List<String> values) {
			addCriterion("view_zh_ch_name in", values, "viewZhChName");
			return (Criteria) this;
		}

		public Criteria andViewZhChNameNotIn(List<String> values) {
			addCriterion("view_zh_ch_name not in", values, "viewZhChName");
			return (Criteria) this;
		}

		public Criteria andViewZhChNameBetween(String value1, String value2) {
			addCriterion("view_zh_ch_name between", value1, value2, "viewZhChName");
			return (Criteria) this;
		}

		public Criteria andViewZhChNameNotBetween(String value1, String value2) {
			addCriterion("view_zh_ch_name not between", value1, value2, "viewZhChName");
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

		public Criteria andAddTimeEqualTo(Date value) {
			addCriterion("add_time =", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeNotEqualTo(Date value) {
			addCriterion("add_time <>", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeGreaterThan(Date value) {
			addCriterion("add_time >", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("add_time >=", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeLessThan(Date value) {
			addCriterion("add_time <", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeLessThanOrEqualTo(Date value) {
			addCriterion("add_time <=", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeIn(List<Date> values) {
			addCriterion("add_time in", values, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeNotIn(List<Date> values) {
			addCriterion("add_time not in", values, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeBetween(Date value1, Date value2) {
			addCriterion("add_time between", value1, value2, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeNotBetween(Date value1, Date value2) {
			addCriterion("add_time not between", value1, value2, "addTime");
			return (Criteria) this;
		}

		public Criteria andCommentIsNull() {
			addCriterion("comment is null");
			return (Criteria) this;
		}

		public Criteria andCommentIsNotNull() {
			addCriterion("comment is not null");
			return (Criteria) this;
		}

		public Criteria andCommentEqualTo(String value) {
			addCriterion("comment =", value, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentNotEqualTo(String value) {
			addCriterion("comment <>", value, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentGreaterThan(String value) {
			addCriterion("comment >", value, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentGreaterThanOrEqualTo(String value) {
			addCriterion("comment >=", value, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentLessThan(String value) {
			addCriterion("comment <", value, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentLessThanOrEqualTo(String value) {
			addCriterion("comment <=", value, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentLike(String value) {
			addCriterion("comment like", value, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentNotLike(String value) {
			addCriterion("comment not like", value, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentIn(List<String> values) {
			addCriterion("comment in", values, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentNotIn(List<String> values) {
			addCriterion("comment not in", values, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentBetween(String value1, String value2) {
			addCriterion("comment between", value1, value2, "comment");
			return (Criteria) this;
		}

		public Criteria andCommentNotBetween(String value1, String value2) {
			addCriterion("comment not between", value1, value2, "comment");
			return (Criteria) this;
		}

		public Criteria andDataSetIdIsNull() {
			addCriterion("data_set_id is null");
			return (Criteria) this;
		}

		public Criteria andDataSetIdIsNotNull() {
			addCriterion("data_set_id is not null");
			return (Criteria) this;
		}

		public Criteria andDataSetIdEqualTo(String value) {
			addCriterion("data_set_id =", value, "dataSetId");
			return (Criteria) this;
		}

		public Criteria andDataSetIdNotEqualTo(String value) {
			addCriterion("data_set_id <>", value, "dataSetId");
			return (Criteria) this;
		}

		public Criteria andDataSetIdGreaterThan(String value) {
			addCriterion("data_set_id >", value, "dataSetId");
			return (Criteria) this;
		}

		public Criteria andDataSetIdGreaterThanOrEqualTo(String value) {
			addCriterion("data_set_id >=", value, "dataSetId");
			return (Criteria) this;
		}

		public Criteria andDataSetIdLessThan(String value) {
			addCriterion("data_set_id <", value, "dataSetId");
			return (Criteria) this;
		}

		public Criteria andDataSetIdLessThanOrEqualTo(String value) {
			addCriterion("data_set_id <=", value, "dataSetId");
			return (Criteria) this;
		}

		public Criteria andDataSetIdLike(String value) {
			addCriterion("data_set_id like", value, "dataSetId");
			return (Criteria) this;
		}

		public Criteria andDataSetIdNotLike(String value) {
			addCriterion("data_set_id not like", value, "dataSetId");
			return (Criteria) this;
		}

		public Criteria andDataSetIdIn(List<String> values) {
			addCriterion("data_set_id in", values, "dataSetId");
			return (Criteria) this;
		}

		public Criteria andDataSetIdNotIn(List<String> values) {
			addCriterion("data_set_id not in", values, "dataSetId");
			return (Criteria) this;
		}

		public Criteria andDataSetIdBetween(String value1, String value2) {
			addCriterion("data_set_id between", value1, value2, "dataSetId");
			return (Criteria) this;
		}

		public Criteria andDataSetIdNotBetween(String value1, String value2) {
			addCriterion("data_set_id not between", value1, value2, "dataSetId");
			return (Criteria) this;
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

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table table_view
     *
     * @mbg.generated do_not_delete_during_merge Sun Apr 28 22:55:15 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}