package com.xinyibi.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TableFieldInfoExample {
    protected String orderByClause;
	protected boolean distinct;
	protected List<Criteria> oredCriteria;

	public TableFieldInfoExample() {
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

		public Criteria andFieldNameIsNull() {
			addCriterion("field_name is null");
			return (Criteria) this;
		}

		public Criteria andFieldNameIsNotNull() {
			addCriterion("field_name is not null");
			return (Criteria) this;
		}

		public Criteria andFieldNameEqualTo(String value) {
			addCriterion("field_name =", value, "fieldName");
			return (Criteria) this;
		}

		public Criteria andFieldNameNotEqualTo(String value) {
			addCriterion("field_name <>", value, "fieldName");
			return (Criteria) this;
		}

		public Criteria andFieldNameGreaterThan(String value) {
			addCriterion("field_name >", value, "fieldName");
			return (Criteria) this;
		}

		public Criteria andFieldNameGreaterThanOrEqualTo(String value) {
			addCriterion("field_name >=", value, "fieldName");
			return (Criteria) this;
		}

		public Criteria andFieldNameLessThan(String value) {
			addCriterion("field_name <", value, "fieldName");
			return (Criteria) this;
		}

		public Criteria andFieldNameLessThanOrEqualTo(String value) {
			addCriterion("field_name <=", value, "fieldName");
			return (Criteria) this;
		}

		public Criteria andFieldNameLike(String value) {
			addCriterion("field_name like", value, "fieldName");
			return (Criteria) this;
		}

		public Criteria andFieldNameNotLike(String value) {
			addCriterion("field_name not like", value, "fieldName");
			return (Criteria) this;
		}

		public Criteria andFieldNameIn(List<String> values) {
			addCriterion("field_name in", values, "fieldName");
			return (Criteria) this;
		}

		public Criteria andFieldNameNotIn(List<String> values) {
			addCriterion("field_name not in", values, "fieldName");
			return (Criteria) this;
		}

		public Criteria andFieldNameBetween(String value1, String value2) {
			addCriterion("field_name between", value1, value2, "fieldName");
			return (Criteria) this;
		}

		public Criteria andFieldNameNotBetween(String value1, String value2) {
			addCriterion("field_name not between", value1, value2, "fieldName");
			return (Criteria) this;
		}

		public Criteria andFieldZhChNameIsNull() {
			addCriterion("field_zh_ch_name is null");
			return (Criteria) this;
		}

		public Criteria andFieldZhChNameIsNotNull() {
			addCriterion("field_zh_ch_name is not null");
			return (Criteria) this;
		}

		public Criteria andFieldZhChNameEqualTo(String value) {
			addCriterion("field_zh_ch_name =", value, "fieldZhChName");
			return (Criteria) this;
		}

		public Criteria andFieldZhChNameNotEqualTo(String value) {
			addCriterion("field_zh_ch_name <>", value, "fieldZhChName");
			return (Criteria) this;
		}

		public Criteria andFieldZhChNameGreaterThan(String value) {
			addCriterion("field_zh_ch_name >", value, "fieldZhChName");
			return (Criteria) this;
		}

		public Criteria andFieldZhChNameGreaterThanOrEqualTo(String value) {
			addCriterion("field_zh_ch_name >=", value, "fieldZhChName");
			return (Criteria) this;
		}

		public Criteria andFieldZhChNameLessThan(String value) {
			addCriterion("field_zh_ch_name <", value, "fieldZhChName");
			return (Criteria) this;
		}

		public Criteria andFieldZhChNameLessThanOrEqualTo(String value) {
			addCriterion("field_zh_ch_name <=", value, "fieldZhChName");
			return (Criteria) this;
		}

		public Criteria andFieldZhChNameLike(String value) {
			addCriterion("field_zh_ch_name like", value, "fieldZhChName");
			return (Criteria) this;
		}

		public Criteria andFieldZhChNameNotLike(String value) {
			addCriterion("field_zh_ch_name not like", value, "fieldZhChName");
			return (Criteria) this;
		}

		public Criteria andFieldZhChNameIn(List<String> values) {
			addCriterion("field_zh_ch_name in", values, "fieldZhChName");
			return (Criteria) this;
		}

		public Criteria andFieldZhChNameNotIn(List<String> values) {
			addCriterion("field_zh_ch_name not in", values, "fieldZhChName");
			return (Criteria) this;
		}

		public Criteria andFieldZhChNameBetween(String value1, String value2) {
			addCriterion("field_zh_ch_name between", value1, value2, "fieldZhChName");
			return (Criteria) this;
		}

		public Criteria andFieldZhChNameNotBetween(String value1, String value2) {
			addCriterion("field_zh_ch_name not between", value1, value2, "fieldZhChName");
			return (Criteria) this;
		}

		public Criteria andJdbcTypeIsNull() {
			addCriterion("jdbc_type is null");
			return (Criteria) this;
		}

		public Criteria andJdbcTypeIsNotNull() {
			addCriterion("jdbc_type is not null");
			return (Criteria) this;
		}

		public Criteria andJdbcTypeEqualTo(String value) {
			addCriterion("jdbc_type =", value, "jdbcType");
			return (Criteria) this;
		}

		public Criteria andJdbcTypeNotEqualTo(String value) {
			addCriterion("jdbc_type <>", value, "jdbcType");
			return (Criteria) this;
		}

		public Criteria andJdbcTypeGreaterThan(String value) {
			addCriterion("jdbc_type >", value, "jdbcType");
			return (Criteria) this;
		}

		public Criteria andJdbcTypeGreaterThanOrEqualTo(String value) {
			addCriterion("jdbc_type >=", value, "jdbcType");
			return (Criteria) this;
		}

		public Criteria andJdbcTypeLessThan(String value) {
			addCriterion("jdbc_type <", value, "jdbcType");
			return (Criteria) this;
		}

		public Criteria andJdbcTypeLessThanOrEqualTo(String value) {
			addCriterion("jdbc_type <=", value, "jdbcType");
			return (Criteria) this;
		}

		public Criteria andJdbcTypeLike(String value) {
			addCriterion("jdbc_type like", value, "jdbcType");
			return (Criteria) this;
		}

		public Criteria andJdbcTypeNotLike(String value) {
			addCriterion("jdbc_type not like", value, "jdbcType");
			return (Criteria) this;
		}

		public Criteria andJdbcTypeIn(List<String> values) {
			addCriterion("jdbc_type in", values, "jdbcType");
			return (Criteria) this;
		}

		public Criteria andJdbcTypeNotIn(List<String> values) {
			addCriterion("jdbc_type not in", values, "jdbcType");
			return (Criteria) this;
		}

		public Criteria andJdbcTypeBetween(String value1, String value2) {
			addCriterion("jdbc_type between", value1, value2, "jdbcType");
			return (Criteria) this;
		}

		public Criteria andJdbcTypeNotBetween(String value1, String value2) {
			addCriterion("jdbc_type not between", value1, value2, "jdbcType");
			return (Criteria) this;
		}

		public Criteria andJavaTypeIsNull() {
			addCriterion("java_type is null");
			return (Criteria) this;
		}

		public Criteria andJavaTypeIsNotNull() {
			addCriterion("java_type is not null");
			return (Criteria) this;
		}

		public Criteria andJavaTypeEqualTo(String value) {
			addCriterion("java_type =", value, "javaType");
			return (Criteria) this;
		}

		public Criteria andJavaTypeNotEqualTo(String value) {
			addCriterion("java_type <>", value, "javaType");
			return (Criteria) this;
		}

		public Criteria andJavaTypeGreaterThan(String value) {
			addCriterion("java_type >", value, "javaType");
			return (Criteria) this;
		}

		public Criteria andJavaTypeGreaterThanOrEqualTo(String value) {
			addCriterion("java_type >=", value, "javaType");
			return (Criteria) this;
		}

		public Criteria andJavaTypeLessThan(String value) {
			addCriterion("java_type <", value, "javaType");
			return (Criteria) this;
		}

		public Criteria andJavaTypeLessThanOrEqualTo(String value) {
			addCriterion("java_type <=", value, "javaType");
			return (Criteria) this;
		}

		public Criteria andJavaTypeLike(String value) {
			addCriterion("java_type like", value, "javaType");
			return (Criteria) this;
		}

		public Criteria andJavaTypeNotLike(String value) {
			addCriterion("java_type not like", value, "javaType");
			return (Criteria) this;
		}

		public Criteria andJavaTypeIn(List<String> values) {
			addCriterion("java_type in", values, "javaType");
			return (Criteria) this;
		}

		public Criteria andJavaTypeNotIn(List<String> values) {
			addCriterion("java_type not in", values, "javaType");
			return (Criteria) this;
		}

		public Criteria andJavaTypeBetween(String value1, String value2) {
			addCriterion("java_type between", value1, value2, "javaType");
			return (Criteria) this;
		}

		public Criteria andJavaTypeNotBetween(String value1, String value2) {
			addCriterion("java_type not between", value1, value2, "javaType");
			return (Criteria) this;
		}

		public Criteria andLengthIsNull() {
			addCriterion("length is null");
			return (Criteria) this;
		}

		public Criteria andLengthIsNotNull() {
			addCriterion("length is not null");
			return (Criteria) this;
		}

		public Criteria andLengthEqualTo(Integer value) {
			addCriterion("length =", value, "length");
			return (Criteria) this;
		}

		public Criteria andLengthNotEqualTo(Integer value) {
			addCriterion("length <>", value, "length");
			return (Criteria) this;
		}

		public Criteria andLengthGreaterThan(Integer value) {
			addCriterion("length >", value, "length");
			return (Criteria) this;
		}

		public Criteria andLengthGreaterThanOrEqualTo(Integer value) {
			addCriterion("length >=", value, "length");
			return (Criteria) this;
		}

		public Criteria andLengthLessThan(Integer value) {
			addCriterion("length <", value, "length");
			return (Criteria) this;
		}

		public Criteria andLengthLessThanOrEqualTo(Integer value) {
			addCriterion("length <=", value, "length");
			return (Criteria) this;
		}

		public Criteria andLengthIn(List<Integer> values) {
			addCriterion("length in", values, "length");
			return (Criteria) this;
		}

		public Criteria andLengthNotIn(List<Integer> values) {
			addCriterion("length not in", values, "length");
			return (Criteria) this;
		}

		public Criteria andLengthBetween(Integer value1, Integer value2) {
			addCriterion("length between", value1, value2, "length");
			return (Criteria) this;
		}

		public Criteria andLengthNotBetween(Integer value1, Integer value2) {
			addCriterion("length not between", value1, value2, "length");
			return (Criteria) this;
		}

		public Criteria andPrimaryKeyIsNull() {
			addCriterion("primary_key is null");
			return (Criteria) this;
		}

		public Criteria andPrimaryKeyIsNotNull() {
			addCriterion("primary_key is not null");
			return (Criteria) this;
		}

		public Criteria andPrimaryKeyEqualTo(Boolean value) {
			addCriterion("primary_key =", value, "primaryKey");
			return (Criteria) this;
		}

		public Criteria andPrimaryKeyNotEqualTo(Boolean value) {
			addCriterion("primary_key <>", value, "primaryKey");
			return (Criteria) this;
		}

		public Criteria andPrimaryKeyGreaterThan(Boolean value) {
			addCriterion("primary_key >", value, "primaryKey");
			return (Criteria) this;
		}

		public Criteria andPrimaryKeyGreaterThanOrEqualTo(Boolean value) {
			addCriterion("primary_key >=", value, "primaryKey");
			return (Criteria) this;
		}

		public Criteria andPrimaryKeyLessThan(Boolean value) {
			addCriterion("primary_key <", value, "primaryKey");
			return (Criteria) this;
		}

		public Criteria andPrimaryKeyLessThanOrEqualTo(Boolean value) {
			addCriterion("primary_key <=", value, "primaryKey");
			return (Criteria) this;
		}

		public Criteria andPrimaryKeyIn(List<Boolean> values) {
			addCriterion("primary_key in", values, "primaryKey");
			return (Criteria) this;
		}

		public Criteria andPrimaryKeyNotIn(List<Boolean> values) {
			addCriterion("primary_key not in", values, "primaryKey");
			return (Criteria) this;
		}

		public Criteria andPrimaryKeyBetween(Boolean value1, Boolean value2) {
			addCriterion("primary_key between", value1, value2, "primaryKey");
			return (Criteria) this;
		}

		public Criteria andPrimaryKeyNotBetween(Boolean value1, Boolean value2) {
			addCriterion("primary_key not between", value1, value2, "primaryKey");
			return (Criteria) this;
		}

		public Criteria andForeignKeyIsNull() {
			addCriterion("foreign_key is null");
			return (Criteria) this;
		}

		public Criteria andForeignKeyIsNotNull() {
			addCriterion("foreign_key is not null");
			return (Criteria) this;
		}

		public Criteria andForeignKeyEqualTo(Boolean value) {
			addCriterion("foreign_key =", value, "foreignKey");
			return (Criteria) this;
		}

		public Criteria andForeignKeyNotEqualTo(Boolean value) {
			addCriterion("foreign_key <>", value, "foreignKey");
			return (Criteria) this;
		}

		public Criteria andForeignKeyGreaterThan(Boolean value) {
			addCriterion("foreign_key >", value, "foreignKey");
			return (Criteria) this;
		}

		public Criteria andForeignKeyGreaterThanOrEqualTo(Boolean value) {
			addCriterion("foreign_key >=", value, "foreignKey");
			return (Criteria) this;
		}

		public Criteria andForeignKeyLessThan(Boolean value) {
			addCriterion("foreign_key <", value, "foreignKey");
			return (Criteria) this;
		}

		public Criteria andForeignKeyLessThanOrEqualTo(Boolean value) {
			addCriterion("foreign_key <=", value, "foreignKey");
			return (Criteria) this;
		}

		public Criteria andForeignKeyIn(List<Boolean> values) {
			addCriterion("foreign_key in", values, "foreignKey");
			return (Criteria) this;
		}

		public Criteria andForeignKeyNotIn(List<Boolean> values) {
			addCriterion("foreign_key not in", values, "foreignKey");
			return (Criteria) this;
		}

		public Criteria andForeignKeyBetween(Boolean value1, Boolean value2) {
			addCriterion("foreign_key between", value1, value2, "foreignKey");
			return (Criteria) this;
		}

		public Criteria andForeignKeyNotBetween(Boolean value1, Boolean value2) {
			addCriterion("foreign_key not between", value1, value2, "foreignKey");
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
     * This class corresponds to the database table table_field_info
     *
     * @mbg.generated do_not_delete_during_merge Sun Apr 28 22:55:15 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}