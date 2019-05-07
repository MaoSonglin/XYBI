package com.xinyibi.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForeignKeyInfoExample {
    protected String orderByClause;
	protected boolean distinct;
	protected List<Criteria> oredCriteria;

	public ForeignKeyInfoExample() {
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

		public Criteria andForeignKeyNameIsNull() {
			addCriterion("foreign_key_name is null");
			return (Criteria) this;
		}

		public Criteria andForeignKeyNameIsNotNull() {
			addCriterion("foreign_key_name is not null");
			return (Criteria) this;
		}

		public Criteria andForeignKeyNameEqualTo(String value) {
			addCriterion("foreign_key_name =", value, "foreignKeyName");
			return (Criteria) this;
		}

		public Criteria andForeignKeyNameNotEqualTo(String value) {
			addCriterion("foreign_key_name <>", value, "foreignKeyName");
			return (Criteria) this;
		}

		public Criteria andForeignKeyNameGreaterThan(String value) {
			addCriterion("foreign_key_name >", value, "foreignKeyName");
			return (Criteria) this;
		}

		public Criteria andForeignKeyNameGreaterThanOrEqualTo(String value) {
			addCriterion("foreign_key_name >=", value, "foreignKeyName");
			return (Criteria) this;
		}

		public Criteria andForeignKeyNameLessThan(String value) {
			addCriterion("foreign_key_name <", value, "foreignKeyName");
			return (Criteria) this;
		}

		public Criteria andForeignKeyNameLessThanOrEqualTo(String value) {
			addCriterion("foreign_key_name <=", value, "foreignKeyName");
			return (Criteria) this;
		}

		public Criteria andForeignKeyNameLike(String value) {
			addCriterion("foreign_key_name like", value, "foreignKeyName");
			return (Criteria) this;
		}

		public Criteria andForeignKeyNameNotLike(String value) {
			addCriterion("foreign_key_name not like", value, "foreignKeyName");
			return (Criteria) this;
		}

		public Criteria andForeignKeyNameIn(List<String> values) {
			addCriterion("foreign_key_name in", values, "foreignKeyName");
			return (Criteria) this;
		}

		public Criteria andForeignKeyNameNotIn(List<String> values) {
			addCriterion("foreign_key_name not in", values, "foreignKeyName");
			return (Criteria) this;
		}

		public Criteria andForeignKeyNameBetween(String value1, String value2) {
			addCriterion("foreign_key_name between", value1, value2, "foreignKeyName");
			return (Criteria) this;
		}

		public Criteria andForeignKeyNameNotBetween(String value1, String value2) {
			addCriterion("foreign_key_name not between", value1, value2, "foreignKeyName");
			return (Criteria) this;
		}

		public Criteria andFieldIdIsNull() {
			addCriterion("field_id is null");
			return (Criteria) this;
		}

		public Criteria andFieldIdIsNotNull() {
			addCriterion("field_id is not null");
			return (Criteria) this;
		}

		public Criteria andFieldIdEqualTo(String value) {
			addCriterion("field_id =", value, "fieldId");
			return (Criteria) this;
		}

		public Criteria andFieldIdNotEqualTo(String value) {
			addCriterion("field_id <>", value, "fieldId");
			return (Criteria) this;
		}

		public Criteria andFieldIdGreaterThan(String value) {
			addCriterion("field_id >", value, "fieldId");
			return (Criteria) this;
		}

		public Criteria andFieldIdGreaterThanOrEqualTo(String value) {
			addCriterion("field_id >=", value, "fieldId");
			return (Criteria) this;
		}

		public Criteria andFieldIdLessThan(String value) {
			addCriterion("field_id <", value, "fieldId");
			return (Criteria) this;
		}

		public Criteria andFieldIdLessThanOrEqualTo(String value) {
			addCriterion("field_id <=", value, "fieldId");
			return (Criteria) this;
		}

		public Criteria andFieldIdLike(String value) {
			addCriterion("field_id like", value, "fieldId");
			return (Criteria) this;
		}

		public Criteria andFieldIdNotLike(String value) {
			addCriterion("field_id not like", value, "fieldId");
			return (Criteria) this;
		}

		public Criteria andFieldIdIn(List<String> values) {
			addCriterion("field_id in", values, "fieldId");
			return (Criteria) this;
		}

		public Criteria andFieldIdNotIn(List<String> values) {
			addCriterion("field_id not in", values, "fieldId");
			return (Criteria) this;
		}

		public Criteria andFieldIdBetween(String value1, String value2) {
			addCriterion("field_id between", value1, value2, "fieldId");
			return (Criteria) this;
		}

		public Criteria andFieldIdNotBetween(String value1, String value2) {
			addCriterion("field_id not between", value1, value2, "fieldId");
			return (Criteria) this;
		}

		public Criteria andTbIdIsNull() {
			addCriterion("tb_id is null");
			return (Criteria) this;
		}

		public Criteria andTbIdIsNotNull() {
			addCriterion("tb_id is not null");
			return (Criteria) this;
		}

		public Criteria andTbIdEqualTo(String value) {
			addCriterion("tb_id =", value, "tbId");
			return (Criteria) this;
		}

		public Criteria andTbIdNotEqualTo(String value) {
			addCriterion("tb_id <>", value, "tbId");
			return (Criteria) this;
		}

		public Criteria andTbIdGreaterThan(String value) {
			addCriterion("tb_id >", value, "tbId");
			return (Criteria) this;
		}

		public Criteria andTbIdGreaterThanOrEqualTo(String value) {
			addCriterion("tb_id >=", value, "tbId");
			return (Criteria) this;
		}

		public Criteria andTbIdLessThan(String value) {
			addCriterion("tb_id <", value, "tbId");
			return (Criteria) this;
		}

		public Criteria andTbIdLessThanOrEqualTo(String value) {
			addCriterion("tb_id <=", value, "tbId");
			return (Criteria) this;
		}

		public Criteria andTbIdLike(String value) {
			addCriterion("tb_id like", value, "tbId");
			return (Criteria) this;
		}

		public Criteria andTbIdNotLike(String value) {
			addCriterion("tb_id not like", value, "tbId");
			return (Criteria) this;
		}

		public Criteria andTbIdIn(List<String> values) {
			addCriterion("tb_id in", values, "tbId");
			return (Criteria) this;
		}

		public Criteria andTbIdNotIn(List<String> values) {
			addCriterion("tb_id not in", values, "tbId");
			return (Criteria) this;
		}

		public Criteria andTbIdBetween(String value1, String value2) {
			addCriterion("tb_id between", value1, value2, "tbId");
			return (Criteria) this;
		}

		public Criteria andTbIdNotBetween(String value1, String value2) {
			addCriterion("tb_id not between", value1, value2, "tbId");
			return (Criteria) this;
		}

		public Criteria andRefTbIdIsNull() {
			addCriterion("ref_tb_id is null");
			return (Criteria) this;
		}

		public Criteria andRefTbIdIsNotNull() {
			addCriterion("ref_tb_id is not null");
			return (Criteria) this;
		}

		public Criteria andRefTbIdEqualTo(String value) {
			addCriterion("ref_tb_id =", value, "refTbId");
			return (Criteria) this;
		}

		public Criteria andRefTbIdNotEqualTo(String value) {
			addCriterion("ref_tb_id <>", value, "refTbId");
			return (Criteria) this;
		}

		public Criteria andRefTbIdGreaterThan(String value) {
			addCriterion("ref_tb_id >", value, "refTbId");
			return (Criteria) this;
		}

		public Criteria andRefTbIdGreaterThanOrEqualTo(String value) {
			addCriterion("ref_tb_id >=", value, "refTbId");
			return (Criteria) this;
		}

		public Criteria andRefTbIdLessThan(String value) {
			addCriterion("ref_tb_id <", value, "refTbId");
			return (Criteria) this;
		}

		public Criteria andRefTbIdLessThanOrEqualTo(String value) {
			addCriterion("ref_tb_id <=", value, "refTbId");
			return (Criteria) this;
		}

		public Criteria andRefTbIdLike(String value) {
			addCriterion("ref_tb_id like", value, "refTbId");
			return (Criteria) this;
		}

		public Criteria andRefTbIdNotLike(String value) {
			addCriterion("ref_tb_id not like", value, "refTbId");
			return (Criteria) this;
		}

		public Criteria andRefTbIdIn(List<String> values) {
			addCriterion("ref_tb_id in", values, "refTbId");
			return (Criteria) this;
		}

		public Criteria andRefTbIdNotIn(List<String> values) {
			addCriterion("ref_tb_id not in", values, "refTbId");
			return (Criteria) this;
		}

		public Criteria andRefTbIdBetween(String value1, String value2) {
			addCriterion("ref_tb_id between", value1, value2, "refTbId");
			return (Criteria) this;
		}

		public Criteria andRefTbIdNotBetween(String value1, String value2) {
			addCriterion("ref_tb_id not between", value1, value2, "refTbId");
			return (Criteria) this;
		}

		public Criteria andRefFdIdIsNull() {
			addCriterion("ref_fd_id is null");
			return (Criteria) this;
		}

		public Criteria andRefFdIdIsNotNull() {
			addCriterion("ref_fd_id is not null");
			return (Criteria) this;
		}

		public Criteria andRefFdIdEqualTo(String value) {
			addCriterion("ref_fd_id =", value, "refFdId");
			return (Criteria) this;
		}

		public Criteria andRefFdIdNotEqualTo(String value) {
			addCriterion("ref_fd_id <>", value, "refFdId");
			return (Criteria) this;
		}

		public Criteria andRefFdIdGreaterThan(String value) {
			addCriterion("ref_fd_id >", value, "refFdId");
			return (Criteria) this;
		}

		public Criteria andRefFdIdGreaterThanOrEqualTo(String value) {
			addCriterion("ref_fd_id >=", value, "refFdId");
			return (Criteria) this;
		}

		public Criteria andRefFdIdLessThan(String value) {
			addCriterion("ref_fd_id <", value, "refFdId");
			return (Criteria) this;
		}

		public Criteria andRefFdIdLessThanOrEqualTo(String value) {
			addCriterion("ref_fd_id <=", value, "refFdId");
			return (Criteria) this;
		}

		public Criteria andRefFdIdLike(String value) {
			addCriterion("ref_fd_id like", value, "refFdId");
			return (Criteria) this;
		}

		public Criteria andRefFdIdNotLike(String value) {
			addCriterion("ref_fd_id not like", value, "refFdId");
			return (Criteria) this;
		}

		public Criteria andRefFdIdIn(List<String> values) {
			addCriterion("ref_fd_id in", values, "refFdId");
			return (Criteria) this;
		}

		public Criteria andRefFdIdNotIn(List<String> values) {
			addCriterion("ref_fd_id not in", values, "refFdId");
			return (Criteria) this;
		}

		public Criteria andRefFdIdBetween(String value1, String value2) {
			addCriterion("ref_fd_id between", value1, value2, "refFdId");
			return (Criteria) this;
		}

		public Criteria andRefFdIdNotBetween(String value1, String value2) {
			addCriterion("ref_fd_id not between", value1, value2, "refFdId");
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
     * This class corresponds to the database table foreign_key_info
     *
     * @mbg.generated do_not_delete_during_merge Sun Apr 28 22:55:15 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}