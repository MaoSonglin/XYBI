package com.xinyibi.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileInfoExample {
    protected String orderByClause;
	protected boolean distinct;
	protected List<Criteria> oredCriteria;

	public FileInfoExample() {
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

		public Criteria andFileNameIsNull() {
			addCriterion("file_name is null");
			return (Criteria) this;
		}

		public Criteria andFileNameIsNotNull() {
			addCriterion("file_name is not null");
			return (Criteria) this;
		}

		public Criteria andFileNameEqualTo(String value) {
			addCriterion("file_name =", value, "fileName");
			return (Criteria) this;
		}

		public Criteria andFileNameNotEqualTo(String value) {
			addCriterion("file_name <>", value, "fileName");
			return (Criteria) this;
		}

		public Criteria andFileNameGreaterThan(String value) {
			addCriterion("file_name >", value, "fileName");
			return (Criteria) this;
		}

		public Criteria andFileNameGreaterThanOrEqualTo(String value) {
			addCriterion("file_name >=", value, "fileName");
			return (Criteria) this;
		}

		public Criteria andFileNameLessThan(String value) {
			addCriterion("file_name <", value, "fileName");
			return (Criteria) this;
		}

		public Criteria andFileNameLessThanOrEqualTo(String value) {
			addCriterion("file_name <=", value, "fileName");
			return (Criteria) this;
		}

		public Criteria andFileNameLike(String value) {
			addCriterion("file_name like", value, "fileName");
			return (Criteria) this;
		}

		public Criteria andFileNameNotLike(String value) {
			addCriterion("file_name not like", value, "fileName");
			return (Criteria) this;
		}

		public Criteria andFileNameIn(List<String> values) {
			addCriterion("file_name in", values, "fileName");
			return (Criteria) this;
		}

		public Criteria andFileNameNotIn(List<String> values) {
			addCriterion("file_name not in", values, "fileName");
			return (Criteria) this;
		}

		public Criteria andFileNameBetween(String value1, String value2) {
			addCriterion("file_name between", value1, value2, "fileName");
			return (Criteria) this;
		}

		public Criteria andFileNameNotBetween(String value1, String value2) {
			addCriterion("file_name not between", value1, value2, "fileName");
			return (Criteria) this;
		}

		public Criteria andFileSizeIsNull() {
			addCriterion("file_size is null");
			return (Criteria) this;
		}

		public Criteria andFileSizeIsNotNull() {
			addCriterion("file_size is not null");
			return (Criteria) this;
		}

		public Criteria andFileSizeEqualTo(Double value) {
			addCriterion("file_size =", value, "fileSize");
			return (Criteria) this;
		}

		public Criteria andFileSizeNotEqualTo(Double value) {
			addCriterion("file_size <>", value, "fileSize");
			return (Criteria) this;
		}

		public Criteria andFileSizeGreaterThan(Double value) {
			addCriterion("file_size >", value, "fileSize");
			return (Criteria) this;
		}

		public Criteria andFileSizeGreaterThanOrEqualTo(Double value) {
			addCriterion("file_size >=", value, "fileSize");
			return (Criteria) this;
		}

		public Criteria andFileSizeLessThan(Double value) {
			addCriterion("file_size <", value, "fileSize");
			return (Criteria) this;
		}

		public Criteria andFileSizeLessThanOrEqualTo(Double value) {
			addCriterion("file_size <=", value, "fileSize");
			return (Criteria) this;
		}

		public Criteria andFileSizeIn(List<Double> values) {
			addCriterion("file_size in", values, "fileSize");
			return (Criteria) this;
		}

		public Criteria andFileSizeNotIn(List<Double> values) {
			addCriterion("file_size not in", values, "fileSize");
			return (Criteria) this;
		}

		public Criteria andFileSizeBetween(Double value1, Double value2) {
			addCriterion("file_size between", value1, value2, "fileSize");
			return (Criteria) this;
		}

		public Criteria andFileSizeNotBetween(Double value1, Double value2) {
			addCriterion("file_size not between", value1, value2, "fileSize");
			return (Criteria) this;
		}

		public Criteria andCreateDateIsNull() {
			addCriterion("create_date is null");
			return (Criteria) this;
		}

		public Criteria andCreateDateIsNotNull() {
			addCriterion("create_date is not null");
			return (Criteria) this;
		}

		public Criteria andCreateDateEqualTo(Date value) {
			addCriterion("create_date =", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotEqualTo(Date value) {
			addCriterion("create_date <>", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateGreaterThan(Date value) {
			addCriterion("create_date >", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
			addCriterion("create_date >=", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateLessThan(Date value) {
			addCriterion("create_date <", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateLessThanOrEqualTo(Date value) {
			addCriterion("create_date <=", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateIn(List<Date> values) {
			addCriterion("create_date in", values, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotIn(List<Date> values) {
			addCriterion("create_date not in", values, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateBetween(Date value1, Date value2) {
			addCriterion("create_date between", value1, value2, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotBetween(Date value1, Date value2) {
			addCriterion("create_date not between", value1, value2, "createDate");
			return (Criteria) this;
		}

		public Criteria andMimeIsNull() {
			addCriterion("mime is null");
			return (Criteria) this;
		}

		public Criteria andMimeIsNotNull() {
			addCriterion("mime is not null");
			return (Criteria) this;
		}

		public Criteria andMimeEqualTo(String value) {
			addCriterion("mime =", value, "mime");
			return (Criteria) this;
		}

		public Criteria andMimeNotEqualTo(String value) {
			addCriterion("mime <>", value, "mime");
			return (Criteria) this;
		}

		public Criteria andMimeGreaterThan(String value) {
			addCriterion("mime >", value, "mime");
			return (Criteria) this;
		}

		public Criteria andMimeGreaterThanOrEqualTo(String value) {
			addCriterion("mime >=", value, "mime");
			return (Criteria) this;
		}

		public Criteria andMimeLessThan(String value) {
			addCriterion("mime <", value, "mime");
			return (Criteria) this;
		}

		public Criteria andMimeLessThanOrEqualTo(String value) {
			addCriterion("mime <=", value, "mime");
			return (Criteria) this;
		}

		public Criteria andMimeLike(String value) {
			addCriterion("mime like", value, "mime");
			return (Criteria) this;
		}

		public Criteria andMimeNotLike(String value) {
			addCriterion("mime not like", value, "mime");
			return (Criteria) this;
		}

		public Criteria andMimeIn(List<String> values) {
			addCriterion("mime in", values, "mime");
			return (Criteria) this;
		}

		public Criteria andMimeNotIn(List<String> values) {
			addCriterion("mime not in", values, "mime");
			return (Criteria) this;
		}

		public Criteria andMimeBetween(String value1, String value2) {
			addCriterion("mime between", value1, value2, "mime");
			return (Criteria) this;
		}

		public Criteria andMimeNotBetween(String value1, String value2) {
			addCriterion("mime not between", value1, value2, "mime");
			return (Criteria) this;
		}

		public Criteria andStatusIsNull() {
			addCriterion("status is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("status is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(Integer value) {
			addCriterion("status =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(Integer value) {
			addCriterion("status <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(Integer value) {
			addCriterion("status >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("status >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(Integer value) {
			addCriterion("status <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(Integer value) {
			addCriterion("status <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<Integer> values) {
			addCriterion("status in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<Integer> values) {
			addCriterion("status not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(Integer value1, Integer value2) {
			addCriterion("status between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("status not between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andPathIsNull() {
			addCriterion("path is null");
			return (Criteria) this;
		}

		public Criteria andPathIsNotNull() {
			addCriterion("path is not null");
			return (Criteria) this;
		}

		public Criteria andPathEqualTo(String value) {
			addCriterion("path =", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathNotEqualTo(String value) {
			addCriterion("path <>", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathGreaterThan(String value) {
			addCriterion("path >", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathGreaterThanOrEqualTo(String value) {
			addCriterion("path >=", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathLessThan(String value) {
			addCriterion("path <", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathLessThanOrEqualTo(String value) {
			addCriterion("path <=", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathLike(String value) {
			addCriterion("path like", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathNotLike(String value) {
			addCriterion("path not like", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathIn(List<String> values) {
			addCriterion("path in", values, "path");
			return (Criteria) this;
		}

		public Criteria andPathNotIn(List<String> values) {
			addCriterion("path not in", values, "path");
			return (Criteria) this;
		}

		public Criteria andPathBetween(String value1, String value2) {
			addCriterion("path between", value1, value2, "path");
			return (Criteria) this;
		}

		public Criteria andPathNotBetween(String value1, String value2) {
			addCriterion("path not between", value1, value2, "path");
			return (Criteria) this;
		}

		public Criteria andAccountIdIsNull() {
			addCriterion("account_id is null");
			return (Criteria) this;
		}

		public Criteria andAccountIdIsNotNull() {
			addCriterion("account_id is not null");
			return (Criteria) this;
		}

		public Criteria andAccountIdEqualTo(Integer value) {
			addCriterion("account_id =", value, "accountId");
			return (Criteria) this;
		}

		public Criteria andAccountIdNotEqualTo(Integer value) {
			addCriterion("account_id <>", value, "accountId");
			return (Criteria) this;
		}

		public Criteria andAccountIdGreaterThan(Integer value) {
			addCriterion("account_id >", value, "accountId");
			return (Criteria) this;
		}

		public Criteria andAccountIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("account_id >=", value, "accountId");
			return (Criteria) this;
		}

		public Criteria andAccountIdLessThan(Integer value) {
			addCriterion("account_id <", value, "accountId");
			return (Criteria) this;
		}

		public Criteria andAccountIdLessThanOrEqualTo(Integer value) {
			addCriterion("account_id <=", value, "accountId");
			return (Criteria) this;
		}

		public Criteria andAccountIdIn(List<Integer> values) {
			addCriterion("account_id in", values, "accountId");
			return (Criteria) this;
		}

		public Criteria andAccountIdNotIn(List<Integer> values) {
			addCriterion("account_id not in", values, "accountId");
			return (Criteria) this;
		}

		public Criteria andAccountIdBetween(Integer value1, Integer value2) {
			addCriterion("account_id between", value1, value2, "accountId");
			return (Criteria) this;
		}

		public Criteria andAccountIdNotBetween(Integer value1, Integer value2) {
			addCriterion("account_id not between", value1, value2, "accountId");
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
     * This class corresponds to the database table file_info
     *
     * @mbg.generated do_not_delete_during_merge Sun Apr 28 22:55:15 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}