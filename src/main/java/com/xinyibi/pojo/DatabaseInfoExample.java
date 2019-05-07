package com.xinyibi.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseInfoExample {
    protected String orderByClause;
	protected boolean distinct;
	protected List<Criteria> oredCriteria;

	public DatabaseInfoExample() {
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

		public Criteria andUnameIsNull() {
			addCriterion("uname is null");
			return (Criteria) this;
		}

		public Criteria andUnameIsNotNull() {
			addCriterion("uname is not null");
			return (Criteria) this;
		}

		public Criteria andUnameEqualTo(String value) {
			addCriterion("uname =", value, "uname");
			return (Criteria) this;
		}

		public Criteria andUnameNotEqualTo(String value) {
			addCriterion("uname <>", value, "uname");
			return (Criteria) this;
		}

		public Criteria andUnameGreaterThan(String value) {
			addCriterion("uname >", value, "uname");
			return (Criteria) this;
		}

		public Criteria andUnameGreaterThanOrEqualTo(String value) {
			addCriterion("uname >=", value, "uname");
			return (Criteria) this;
		}

		public Criteria andUnameLessThan(String value) {
			addCriterion("uname <", value, "uname");
			return (Criteria) this;
		}

		public Criteria andUnameLessThanOrEqualTo(String value) {
			addCriterion("uname <=", value, "uname");
			return (Criteria) this;
		}

		public Criteria andUnameLike(String value) {
			addCriterion("uname like", value, "uname");
			return (Criteria) this;
		}

		public Criteria andUnameNotLike(String value) {
			addCriterion("uname not like", value, "uname");
			return (Criteria) this;
		}

		public Criteria andUnameIn(List<String> values) {
			addCriterion("uname in", values, "uname");
			return (Criteria) this;
		}

		public Criteria andUnameNotIn(List<String> values) {
			addCriterion("uname not in", values, "uname");
			return (Criteria) this;
		}

		public Criteria andUnameBetween(String value1, String value2) {
			addCriterion("uname between", value1, value2, "uname");
			return (Criteria) this;
		}

		public Criteria andUnameNotBetween(String value1, String value2) {
			addCriterion("uname not between", value1, value2, "uname");
			return (Criteria) this;
		}

		public Criteria andUpwdIsNull() {
			addCriterion("upwd is null");
			return (Criteria) this;
		}

		public Criteria andUpwdIsNotNull() {
			addCriterion("upwd is not null");
			return (Criteria) this;
		}

		public Criteria andUpwdEqualTo(String value) {
			addCriterion("upwd =", value, "upwd");
			return (Criteria) this;
		}

		public Criteria andUpwdNotEqualTo(String value) {
			addCriterion("upwd <>", value, "upwd");
			return (Criteria) this;
		}

		public Criteria andUpwdGreaterThan(String value) {
			addCriterion("upwd >", value, "upwd");
			return (Criteria) this;
		}

		public Criteria andUpwdGreaterThanOrEqualTo(String value) {
			addCriterion("upwd >=", value, "upwd");
			return (Criteria) this;
		}

		public Criteria andUpwdLessThan(String value) {
			addCriterion("upwd <", value, "upwd");
			return (Criteria) this;
		}

		public Criteria andUpwdLessThanOrEqualTo(String value) {
			addCriterion("upwd <=", value, "upwd");
			return (Criteria) this;
		}

		public Criteria andUpwdLike(String value) {
			addCriterion("upwd like", value, "upwd");
			return (Criteria) this;
		}

		public Criteria andUpwdNotLike(String value) {
			addCriterion("upwd not like", value, "upwd");
			return (Criteria) this;
		}

		public Criteria andUpwdIn(List<String> values) {
			addCriterion("upwd in", values, "upwd");
			return (Criteria) this;
		}

		public Criteria andUpwdNotIn(List<String> values) {
			addCriterion("upwd not in", values, "upwd");
			return (Criteria) this;
		}

		public Criteria andUpwdBetween(String value1, String value2) {
			addCriterion("upwd between", value1, value2, "upwd");
			return (Criteria) this;
		}

		public Criteria andUpwdNotBetween(String value1, String value2) {
			addCriterion("upwd not between", value1, value2, "upwd");
			return (Criteria) this;
		}

		public Criteria andUrlIsNull() {
			addCriterion("url is null");
			return (Criteria) this;
		}

		public Criteria andUrlIsNotNull() {
			addCriterion("url is not null");
			return (Criteria) this;
		}

		public Criteria andUrlEqualTo(String value) {
			addCriterion("url =", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotEqualTo(String value) {
			addCriterion("url <>", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlGreaterThan(String value) {
			addCriterion("url >", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlGreaterThanOrEqualTo(String value) {
			addCriterion("url >=", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlLessThan(String value) {
			addCriterion("url <", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlLessThanOrEqualTo(String value) {
			addCriterion("url <=", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlLike(String value) {
			addCriterion("url like", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotLike(String value) {
			addCriterion("url not like", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlIn(List<String> values) {
			addCriterion("url in", values, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotIn(List<String> values) {
			addCriterion("url not in", values, "url");
			return (Criteria) this;
		}

		public Criteria andUrlBetween(String value1, String value2) {
			addCriterion("url between", value1, value2, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotBetween(String value1, String value2) {
			addCriterion("url not between", value1, value2, "url");
			return (Criteria) this;
		}

		public Criteria andDriverClassNameIsNull() {
			addCriterion("driver_class_name is null");
			return (Criteria) this;
		}

		public Criteria andDriverClassNameIsNotNull() {
			addCriterion("driver_class_name is not null");
			return (Criteria) this;
		}

		public Criteria andDriverClassNameEqualTo(String value) {
			addCriterion("driver_class_name =", value, "driverClassName");
			return (Criteria) this;
		}

		public Criteria andDriverClassNameNotEqualTo(String value) {
			addCriterion("driver_class_name <>", value, "driverClassName");
			return (Criteria) this;
		}

		public Criteria andDriverClassNameGreaterThan(String value) {
			addCriterion("driver_class_name >", value, "driverClassName");
			return (Criteria) this;
		}

		public Criteria andDriverClassNameGreaterThanOrEqualTo(String value) {
			addCriterion("driver_class_name >=", value, "driverClassName");
			return (Criteria) this;
		}

		public Criteria andDriverClassNameLessThan(String value) {
			addCriterion("driver_class_name <", value, "driverClassName");
			return (Criteria) this;
		}

		public Criteria andDriverClassNameLessThanOrEqualTo(String value) {
			addCriterion("driver_class_name <=", value, "driverClassName");
			return (Criteria) this;
		}

		public Criteria andDriverClassNameLike(String value) {
			addCriterion("driver_class_name like", value, "driverClassName");
			return (Criteria) this;
		}

		public Criteria andDriverClassNameNotLike(String value) {
			addCriterion("driver_class_name not like", value, "driverClassName");
			return (Criteria) this;
		}

		public Criteria andDriverClassNameIn(List<String> values) {
			addCriterion("driver_class_name in", values, "driverClassName");
			return (Criteria) this;
		}

		public Criteria andDriverClassNameNotIn(List<String> values) {
			addCriterion("driver_class_name not in", values, "driverClassName");
			return (Criteria) this;
		}

		public Criteria andDriverClassNameBetween(String value1, String value2) {
			addCriterion("driver_class_name between", value1, value2, "driverClassName");
			return (Criteria) this;
		}

		public Criteria andDriverClassNameNotBetween(String value1, String value2) {
			addCriterion("driver_class_name not between", value1, value2, "driverClassName");
			return (Criteria) this;
		}

		public Criteria andDriverFileIdIsNull() {
			addCriterion("driver_file_id is null");
			return (Criteria) this;
		}

		public Criteria andDriverFileIdIsNotNull() {
			addCriterion("driver_file_id is not null");
			return (Criteria) this;
		}

		public Criteria andDriverFileIdEqualTo(Long value) {
			addCriterion("driver_file_id =", value, "driverFileId");
			return (Criteria) this;
		}

		public Criteria andDriverFileIdNotEqualTo(Long value) {
			addCriterion("driver_file_id <>", value, "driverFileId");
			return (Criteria) this;
		}

		public Criteria andDriverFileIdGreaterThan(Long value) {
			addCriterion("driver_file_id >", value, "driverFileId");
			return (Criteria) this;
		}

		public Criteria andDriverFileIdGreaterThanOrEqualTo(Long value) {
			addCriterion("driver_file_id >=", value, "driverFileId");
			return (Criteria) this;
		}

		public Criteria andDriverFileIdLessThan(Long value) {
			addCriterion("driver_file_id <", value, "driverFileId");
			return (Criteria) this;
		}

		public Criteria andDriverFileIdLessThanOrEqualTo(Long value) {
			addCriterion("driver_file_id <=", value, "driverFileId");
			return (Criteria) this;
		}

		public Criteria andDriverFileIdIn(List<Long> values) {
			addCriterion("driver_file_id in", values, "driverFileId");
			return (Criteria) this;
		}

		public Criteria andDriverFileIdNotIn(List<Long> values) {
			addCriterion("driver_file_id not in", values, "driverFileId");
			return (Criteria) this;
		}

		public Criteria andDriverFileIdBetween(Long value1, Long value2) {
			addCriterion("driver_file_id between", value1, value2, "driverFileId");
			return (Criteria) this;
		}

		public Criteria andDriverFileIdNotBetween(Long value1, Long value2) {
			addCriterion("driver_file_id not between", value1, value2, "driverFileId");
			return (Criteria) this;
		}

		public Criteria andDatabaseTypeIsNull() {
			addCriterion("database_type is null");
			return (Criteria) this;
		}

		public Criteria andDatabaseTypeIsNotNull() {
			addCriterion("database_type is not null");
			return (Criteria) this;
		}

		public Criteria andDatabaseTypeEqualTo(String value) {
			addCriterion("database_type =", value, "databaseType");
			return (Criteria) this;
		}

		public Criteria andDatabaseTypeNotEqualTo(String value) {
			addCriterion("database_type <>", value, "databaseType");
			return (Criteria) this;
		}

		public Criteria andDatabaseTypeGreaterThan(String value) {
			addCriterion("database_type >", value, "databaseType");
			return (Criteria) this;
		}

		public Criteria andDatabaseTypeGreaterThanOrEqualTo(String value) {
			addCriterion("database_type >=", value, "databaseType");
			return (Criteria) this;
		}

		public Criteria andDatabaseTypeLessThan(String value) {
			addCriterion("database_type <", value, "databaseType");
			return (Criteria) this;
		}

		public Criteria andDatabaseTypeLessThanOrEqualTo(String value) {
			addCriterion("database_type <=", value, "databaseType");
			return (Criteria) this;
		}

		public Criteria andDatabaseTypeLike(String value) {
			addCriterion("database_type like", value, "databaseType");
			return (Criteria) this;
		}

		public Criteria andDatabaseTypeNotLike(String value) {
			addCriterion("database_type not like", value, "databaseType");
			return (Criteria) this;
		}

		public Criteria andDatabaseTypeIn(List<String> values) {
			addCriterion("database_type in", values, "databaseType");
			return (Criteria) this;
		}

		public Criteria andDatabaseTypeNotIn(List<String> values) {
			addCriterion("database_type not in", values, "databaseType");
			return (Criteria) this;
		}

		public Criteria andDatabaseTypeBetween(String value1, String value2) {
			addCriterion("database_type between", value1, value2, "databaseType");
			return (Criteria) this;
		}

		public Criteria andDatabaseTypeNotBetween(String value1, String value2) {
			addCriterion("database_type not between", value1, value2, "databaseType");
			return (Criteria) this;
		}

		public Criteria andDatabaseFileIdIsNull() {
			addCriterion("database_file_id is null");
			return (Criteria) this;
		}

		public Criteria andDatabaseFileIdIsNotNull() {
			addCriterion("database_file_id is not null");
			return (Criteria) this;
		}

		public Criteria andDatabaseFileIdEqualTo(Long value) {
			addCriterion("database_file_id =", value, "databaseFileId");
			return (Criteria) this;
		}

		public Criteria andDatabaseFileIdNotEqualTo(Long value) {
			addCriterion("database_file_id <>", value, "databaseFileId");
			return (Criteria) this;
		}

		public Criteria andDatabaseFileIdGreaterThan(Long value) {
			addCriterion("database_file_id >", value, "databaseFileId");
			return (Criteria) this;
		}

		public Criteria andDatabaseFileIdGreaterThanOrEqualTo(Long value) {
			addCriterion("database_file_id >=", value, "databaseFileId");
			return (Criteria) this;
		}

		public Criteria andDatabaseFileIdLessThan(Long value) {
			addCriterion("database_file_id <", value, "databaseFileId");
			return (Criteria) this;
		}

		public Criteria andDatabaseFileIdLessThanOrEqualTo(Long value) {
			addCriterion("database_file_id <=", value, "databaseFileId");
			return (Criteria) this;
		}

		public Criteria andDatabaseFileIdIn(List<Long> values) {
			addCriterion("database_file_id in", values, "databaseFileId");
			return (Criteria) this;
		}

		public Criteria andDatabaseFileIdNotIn(List<Long> values) {
			addCriterion("database_file_id not in", values, "databaseFileId");
			return (Criteria) this;
		}

		public Criteria andDatabaseFileIdBetween(Long value1, Long value2) {
			addCriterion("database_file_id between", value1, value2, "databaseFileId");
			return (Criteria) this;
		}

		public Criteria andDatabaseFileIdNotBetween(Long value1, Long value2) {
			addCriterion("database_file_id not between", value1, value2, "databaseFileId");
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
     * This class corresponds to the database table database_info
     *
     * @mbg.generated do_not_delete_during_merge Sun Apr 28 22:55:15 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}