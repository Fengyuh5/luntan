package com.xiansi.model;

import java.util.ArrayList;
import java.util.List;

public class NotificationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NotificationExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNotifierIsNull() {
            addCriterion("notifier is null");
            return (Criteria) this;
        }

        public Criteria andNotifierIsNotNull() {
            addCriterion("notifier is not null");
            return (Criteria) this;
        }

        public Criteria andNotifierEqualTo(Integer value) {
            addCriterion("notifier =", value, "notifier");
            return (Criteria) this;
        }

        public Criteria andNotifierNotEqualTo(Integer value) {
            addCriterion("notifier <>", value, "notifier");
            return (Criteria) this;
        }

        public Criteria andNotifierGreaterThan(Integer value) {
            addCriterion("notifier >", value, "notifier");
            return (Criteria) this;
        }

        public Criteria andNotifierGreaterThanOrEqualTo(Integer value) {
            addCriterion("notifier >=", value, "notifier");
            return (Criteria) this;
        }

        public Criteria andNotifierLessThan(Integer value) {
            addCriterion("notifier <", value, "notifier");
            return (Criteria) this;
        }

        public Criteria andNotifierLessThanOrEqualTo(Integer value) {
            addCriterion("notifier <=", value, "notifier");
            return (Criteria) this;
        }

        public Criteria andNotifierIn(List<Integer> values) {
            addCriterion("notifier in", values, "notifier");
            return (Criteria) this;
        }

        public Criteria andNotifierNotIn(List<Integer> values) {
            addCriterion("notifier not in", values, "notifier");
            return (Criteria) this;
        }

        public Criteria andNotifierBetween(Integer value1, Integer value2) {
            addCriterion("notifier between", value1, value2, "notifier");
            return (Criteria) this;
        }

        public Criteria andNotifierNotBetween(Integer value1, Integer value2) {
            addCriterion("notifier not between", value1, value2, "notifier");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNull() {
            addCriterion("receiver is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNotNull() {
            addCriterion("receiver is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverEqualTo(Integer value) {
            addCriterion("receiver =", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotEqualTo(Integer value) {
            addCriterion("receiver <>", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThan(Integer value) {
            addCriterion("receiver >", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThanOrEqualTo(Integer value) {
            addCriterion("receiver >=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThan(Integer value) {
            addCriterion("receiver <", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThanOrEqualTo(Integer value) {
            addCriterion("receiver <=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverIn(List<Integer> values) {
            addCriterion("receiver in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotIn(List<Integer> values) {
            addCriterion("receiver not in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverBetween(Integer value1, Integer value2) {
            addCriterion("receiver between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotBetween(Integer value1, Integer value2) {
            addCriterion("receiver not between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andOuter_idIsNull() {
            addCriterion("outer_id is null");
            return (Criteria) this;
        }

        public Criteria andOuter_idIsNotNull() {
            addCriterion("outer_id is not null");
            return (Criteria) this;
        }

        public Criteria andOuter_idEqualTo(Integer value) {
            addCriterion("outer_id =", value, "outer_id");
            return (Criteria) this;
        }

        public Criteria andOuter_idNotEqualTo(Integer value) {
            addCriterion("outer_id <>", value, "outer_id");
            return (Criteria) this;
        }

        public Criteria andOuter_idGreaterThan(Integer value) {
            addCriterion("outer_id >", value, "outer_id");
            return (Criteria) this;
        }

        public Criteria andOuter_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("outer_id >=", value, "outer_id");
            return (Criteria) this;
        }

        public Criteria andOuter_idLessThan(Integer value) {
            addCriterion("outer_id <", value, "outer_id");
            return (Criteria) this;
        }

        public Criteria andOuter_idLessThanOrEqualTo(Integer value) {
            addCriterion("outer_id <=", value, "outer_id");
            return (Criteria) this;
        }

        public Criteria andOuter_idIn(List<Integer> values) {
            addCriterion("outer_id in", values, "outer_id");
            return (Criteria) this;
        }

        public Criteria andOuter_idNotIn(List<Integer> values) {
            addCriterion("outer_id not in", values, "outer_id");
            return (Criteria) this;
        }

        public Criteria andOuter_idBetween(Integer value1, Integer value2) {
            addCriterion("outer_id between", value1, value2, "outer_id");
            return (Criteria) this;
        }

        public Criteria andOuter_idNotBetween(Integer value1, Integer value2) {
            addCriterion("outer_id not between", value1, value2, "outer_id");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andGmt_createIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmt_createIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmt_createEqualTo(Long value) {
            addCriterion("gmt_create =", value, "gmt_create");
            return (Criteria) this;
        }

        public Criteria andGmt_createNotEqualTo(Long value) {
            addCriterion("gmt_create <>", value, "gmt_create");
            return (Criteria) this;
        }

        public Criteria andGmt_createGreaterThan(Long value) {
            addCriterion("gmt_create >", value, "gmt_create");
            return (Criteria) this;
        }

        public Criteria andGmt_createGreaterThanOrEqualTo(Long value) {
            addCriterion("gmt_create >=", value, "gmt_create");
            return (Criteria) this;
        }

        public Criteria andGmt_createLessThan(Long value) {
            addCriterion("gmt_create <", value, "gmt_create");
            return (Criteria) this;
        }

        public Criteria andGmt_createLessThanOrEqualTo(Long value) {
            addCriterion("gmt_create <=", value, "gmt_create");
            return (Criteria) this;
        }

        public Criteria andGmt_createIn(List<Long> values) {
            addCriterion("gmt_create in", values, "gmt_create");
            return (Criteria) this;
        }

        public Criteria andGmt_createNotIn(List<Long> values) {
            addCriterion("gmt_create not in", values, "gmt_create");
            return (Criteria) this;
        }

        public Criteria andGmt_createBetween(Long value1, Long value2) {
            addCriterion("gmt_create between", value1, value2, "gmt_create");
            return (Criteria) this;
        }

        public Criteria andGmt_createNotBetween(Long value1, Long value2) {
            addCriterion("gmt_create not between", value1, value2, "gmt_create");
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

        public Criteria andNotifier_nameIsNull() {
            addCriterion("notifier_name is null");
            return (Criteria) this;
        }

        public Criteria andNotifier_nameIsNotNull() {
            addCriterion("notifier_name is not null");
            return (Criteria) this;
        }

        public Criteria andNotifier_nameEqualTo(String value) {
            addCriterion("notifier_name =", value, "notifier_name");
            return (Criteria) this;
        }

        public Criteria andNotifier_nameNotEqualTo(String value) {
            addCriterion("notifier_name <>", value, "notifier_name");
            return (Criteria) this;
        }

        public Criteria andNotifier_nameGreaterThan(String value) {
            addCriterion("notifier_name >", value, "notifier_name");
            return (Criteria) this;
        }

        public Criteria andNotifier_nameGreaterThanOrEqualTo(String value) {
            addCriterion("notifier_name >=", value, "notifier_name");
            return (Criteria) this;
        }

        public Criteria andNotifier_nameLessThan(String value) {
            addCriterion("notifier_name <", value, "notifier_name");
            return (Criteria) this;
        }

        public Criteria andNotifier_nameLessThanOrEqualTo(String value) {
            addCriterion("notifier_name <=", value, "notifier_name");
            return (Criteria) this;
        }

        public Criteria andNotifier_nameLike(String value) {
            addCriterion("notifier_name like", value, "notifier_name");
            return (Criteria) this;
        }

        public Criteria andNotifier_nameNotLike(String value) {
            addCriterion("notifier_name not like", value, "notifier_name");
            return (Criteria) this;
        }

        public Criteria andNotifier_nameIn(List<String> values) {
            addCriterion("notifier_name in", values, "notifier_name");
            return (Criteria) this;
        }

        public Criteria andNotifier_nameNotIn(List<String> values) {
            addCriterion("notifier_name not in", values, "notifier_name");
            return (Criteria) this;
        }

        public Criteria andNotifier_nameBetween(String value1, String value2) {
            addCriterion("notifier_name between", value1, value2, "notifier_name");
            return (Criteria) this;
        }

        public Criteria andNotifier_nameNotBetween(String value1, String value2) {
            addCriterion("notifier_name not between", value1, value2, "notifier_name");
            return (Criteria) this;
        }

        public Criteria andOuter_titleIsNull() {
            addCriterion("outer_title is null");
            return (Criteria) this;
        }

        public Criteria andOuter_titleIsNotNull() {
            addCriterion("outer_title is not null");
            return (Criteria) this;
        }

        public Criteria andOuter_titleEqualTo(String value) {
            addCriterion("outer_title =", value, "outer_title");
            return (Criteria) this;
        }

        public Criteria andOuter_titleNotEqualTo(String value) {
            addCriterion("outer_title <>", value, "outer_title");
            return (Criteria) this;
        }

        public Criteria andOuter_titleGreaterThan(String value) {
            addCriterion("outer_title >", value, "outer_title");
            return (Criteria) this;
        }

        public Criteria andOuter_titleGreaterThanOrEqualTo(String value) {
            addCriterion("outer_title >=", value, "outer_title");
            return (Criteria) this;
        }

        public Criteria andOuter_titleLessThan(String value) {
            addCriterion("outer_title <", value, "outer_title");
            return (Criteria) this;
        }

        public Criteria andOuter_titleLessThanOrEqualTo(String value) {
            addCriterion("outer_title <=", value, "outer_title");
            return (Criteria) this;
        }

        public Criteria andOuter_titleLike(String value) {
            addCriterion("outer_title like", value, "outer_title");
            return (Criteria) this;
        }

        public Criteria andOuter_titleNotLike(String value) {
            addCriterion("outer_title not like", value, "outer_title");
            return (Criteria) this;
        }

        public Criteria andOuter_titleIn(List<String> values) {
            addCriterion("outer_title in", values, "outer_title");
            return (Criteria) this;
        }

        public Criteria andOuter_titleNotIn(List<String> values) {
            addCriterion("outer_title not in", values, "outer_title");
            return (Criteria) this;
        }

        public Criteria andOuter_titleBetween(String value1, String value2) {
            addCriterion("outer_title between", value1, value2, "outer_title");
            return (Criteria) this;
        }

        public Criteria andOuter_titleNotBetween(String value1, String value2) {
            addCriterion("outer_title not between", value1, value2, "outer_title");
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