package com.lesson.source.mybatis.spring.model;

import java.util.ArrayList;
import java.util.List;

/**
* @author Mybatis Generator
* @version created on 2019/01/23.
*/
public class UserBankInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserBankInfoExample() {
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
            addCriterion("`id` is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("`id` is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("`id` =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("`id` <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("`id` >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`id` >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("`id` <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("`id` <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("`id` in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("`id` not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("`id` between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("`id` not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("`uid` is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("`uid` is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Long value) {
            addCriterion("`uid` =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Long value) {
            addCriterion("`uid` <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Long value) {
            addCriterion("`uid` >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Long value) {
            addCriterion("`uid` >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Long value) {
            addCriterion("`uid` <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Long value) {
            addCriterion("`uid` <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Long> values) {
            addCriterion("`uid` in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Long> values) {
            addCriterion("`uid` not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Long value1, Long value2) {
            addCriterion("`uid` between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Long value1, Long value2) {
            addCriterion("`uid` not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andBankFirstNameIsNull() {
            addCriterion("`bank_first_name` is null");
            return (Criteria) this;
        }

        public Criteria andBankFirstNameIsNotNull() {
            addCriterion("`bank_first_name` is not null");
            return (Criteria) this;
        }

        public Criteria andBankFirstNameEqualTo(String value) {
            addCriterion("`bank_first_name` =", value, "bankFirstName");
            return (Criteria) this;
        }

        public Criteria andBankFirstNameNotEqualTo(String value) {
            addCriterion("`bank_first_name` <>", value, "bankFirstName");
            return (Criteria) this;
        }

        public Criteria andBankFirstNameGreaterThan(String value) {
            addCriterion("`bank_first_name` >", value, "bankFirstName");
            return (Criteria) this;
        }

        public Criteria andBankFirstNameGreaterThanOrEqualTo(String value) {
            addCriterion("`bank_first_name` >=", value, "bankFirstName");
            return (Criteria) this;
        }

        public Criteria andBankFirstNameLessThan(String value) {
            addCriterion("`bank_first_name` <", value, "bankFirstName");
            return (Criteria) this;
        }

        public Criteria andBankFirstNameLessThanOrEqualTo(String value) {
            addCriterion("`bank_first_name` <=", value, "bankFirstName");
            return (Criteria) this;
        }

        public Criteria andBankFirstNameLike(String value) {
            addCriterion("`bank_first_name` like", value, "bankFirstName");
            return (Criteria) this;
        }

        public Criteria andBankFirstNameNotLike(String value) {
            addCriterion("`bank_first_name` not like", value, "bankFirstName");
            return (Criteria) this;
        }

        public Criteria andBankFirstNameIn(List<String> values) {
            addCriterion("`bank_first_name` in", values, "bankFirstName");
            return (Criteria) this;
        }

        public Criteria andBankFirstNameNotIn(List<String> values) {
            addCriterion("`bank_first_name` not in", values, "bankFirstName");
            return (Criteria) this;
        }

        public Criteria andBankFirstNameBetween(String value1, String value2) {
            addCriterion("`bank_first_name` between", value1, value2, "bankFirstName");
            return (Criteria) this;
        }

        public Criteria andBankFirstNameNotBetween(String value1, String value2) {
            addCriterion("`bank_first_name` not between", value1, value2, "bankFirstName");
            return (Criteria) this;
        }

        public Criteria andBankMiddleNameIsNull() {
            addCriterion("`bank_middle_name` is null");
            return (Criteria) this;
        }

        public Criteria andBankMiddleNameIsNotNull() {
            addCriterion("`bank_middle_name` is not null");
            return (Criteria) this;
        }

        public Criteria andBankMiddleNameEqualTo(String value) {
            addCriterion("`bank_middle_name` =", value, "bankMiddleName");
            return (Criteria) this;
        }

        public Criteria andBankMiddleNameNotEqualTo(String value) {
            addCriterion("`bank_middle_name` <>", value, "bankMiddleName");
            return (Criteria) this;
        }

        public Criteria andBankMiddleNameGreaterThan(String value) {
            addCriterion("`bank_middle_name` >", value, "bankMiddleName");
            return (Criteria) this;
        }

        public Criteria andBankMiddleNameGreaterThanOrEqualTo(String value) {
            addCriterion("`bank_middle_name` >=", value, "bankMiddleName");
            return (Criteria) this;
        }

        public Criteria andBankMiddleNameLessThan(String value) {
            addCriterion("`bank_middle_name` <", value, "bankMiddleName");
            return (Criteria) this;
        }

        public Criteria andBankMiddleNameLessThanOrEqualTo(String value) {
            addCriterion("`bank_middle_name` <=", value, "bankMiddleName");
            return (Criteria) this;
        }

        public Criteria andBankMiddleNameLike(String value) {
            addCriterion("`bank_middle_name` like", value, "bankMiddleName");
            return (Criteria) this;
        }

        public Criteria andBankMiddleNameNotLike(String value) {
            addCriterion("`bank_middle_name` not like", value, "bankMiddleName");
            return (Criteria) this;
        }

        public Criteria andBankMiddleNameIn(List<String> values) {
            addCriterion("`bank_middle_name` in", values, "bankMiddleName");
            return (Criteria) this;
        }

        public Criteria andBankMiddleNameNotIn(List<String> values) {
            addCriterion("`bank_middle_name` not in", values, "bankMiddleName");
            return (Criteria) this;
        }

        public Criteria andBankMiddleNameBetween(String value1, String value2) {
            addCriterion("`bank_middle_name` between", value1, value2, "bankMiddleName");
            return (Criteria) this;
        }

        public Criteria andBankMiddleNameNotBetween(String value1, String value2) {
            addCriterion("`bank_middle_name` not between", value1, value2, "bankMiddleName");
            return (Criteria) this;
        }

        public Criteria andBankLastNameIsNull() {
            addCriterion("`bank_last_name` is null");
            return (Criteria) this;
        }

        public Criteria andBankLastNameIsNotNull() {
            addCriterion("`bank_last_name` is not null");
            return (Criteria) this;
        }

        public Criteria andBankLastNameEqualTo(String value) {
            addCriterion("`bank_last_name` =", value, "bankLastName");
            return (Criteria) this;
        }

        public Criteria andBankLastNameNotEqualTo(String value) {
            addCriterion("`bank_last_name` <>", value, "bankLastName");
            return (Criteria) this;
        }

        public Criteria andBankLastNameGreaterThan(String value) {
            addCriterion("`bank_last_name` >", value, "bankLastName");
            return (Criteria) this;
        }

        public Criteria andBankLastNameGreaterThanOrEqualTo(String value) {
            addCriterion("`bank_last_name` >=", value, "bankLastName");
            return (Criteria) this;
        }

        public Criteria andBankLastNameLessThan(String value) {
            addCriterion("`bank_last_name` <", value, "bankLastName");
            return (Criteria) this;
        }

        public Criteria andBankLastNameLessThanOrEqualTo(String value) {
            addCriterion("`bank_last_name` <=", value, "bankLastName");
            return (Criteria) this;
        }

        public Criteria andBankLastNameLike(String value) {
            addCriterion("`bank_last_name` like", value, "bankLastName");
            return (Criteria) this;
        }

        public Criteria andBankLastNameNotLike(String value) {
            addCriterion("`bank_last_name` not like", value, "bankLastName");
            return (Criteria) this;
        }

        public Criteria andBankLastNameIn(List<String> values) {
            addCriterion("`bank_last_name` in", values, "bankLastName");
            return (Criteria) this;
        }

        public Criteria andBankLastNameNotIn(List<String> values) {
            addCriterion("`bank_last_name` not in", values, "bankLastName");
            return (Criteria) this;
        }

        public Criteria andBankLastNameBetween(String value1, String value2) {
            addCriterion("`bank_last_name` between", value1, value2, "bankLastName");
            return (Criteria) this;
        }

        public Criteria andBankLastNameNotBetween(String value1, String value2) {
            addCriterion("`bank_last_name` not between", value1, value2, "bankLastName");
            return (Criteria) this;
        }

        public Criteria andBankIdIsNull() {
            addCriterion("`bank_id` is null");
            return (Criteria) this;
        }

        public Criteria andBankIdIsNotNull() {
            addCriterion("`bank_id` is not null");
            return (Criteria) this;
        }

        public Criteria andBankIdEqualTo(Integer value) {
            addCriterion("`bank_id` =", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdNotEqualTo(Integer value) {
            addCriterion("`bank_id` <>", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdGreaterThan(Integer value) {
            addCriterion("`bank_id` >", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`bank_id` >=", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdLessThan(Integer value) {
            addCriterion("`bank_id` <", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdLessThanOrEqualTo(Integer value) {
            addCriterion("`bank_id` <=", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdIn(List<Integer> values) {
            addCriterion("`bank_id` in", values, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdNotIn(List<Integer> values) {
            addCriterion("`bank_id` not in", values, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdBetween(Integer value1, Integer value2) {
            addCriterion("`bank_id` between", value1, value2, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`bank_id` not between", value1, value2, "bankId");
            return (Criteria) this;
        }

        public Criteria andAccountNoIsNull() {
            addCriterion("`account_no` is null");
            return (Criteria) this;
        }

        public Criteria andAccountNoIsNotNull() {
            addCriterion("`account_no` is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNoEqualTo(String value) {
            addCriterion("`account_no` =", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotEqualTo(String value) {
            addCriterion("`account_no` <>", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoGreaterThan(String value) {
            addCriterion("`account_no` >", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("`account_no` >=", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLessThan(String value) {
            addCriterion("`account_no` <", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLessThanOrEqualTo(String value) {
            addCriterion("`account_no` <=", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLike(String value) {
            addCriterion("`account_no` like", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotLike(String value) {
            addCriterion("`account_no` not like", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoIn(List<String> values) {
            addCriterion("`account_no` in", values, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotIn(List<String> values) {
            addCriterion("`account_no` not in", values, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoBetween(String value1, String value2) {
            addCriterion("`account_no` between", value1, value2, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotBetween(String value1, String value2) {
            addCriterion("`account_no` not between", value1, value2, "accountNo");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("`create_time` is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("`create_time` is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Long value) {
            addCriterion("`create_time` =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Long value) {
            addCriterion("`create_time` <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Long value) {
            addCriterion("`create_time` >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("`create_time` >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Long value) {
            addCriterion("`create_time` <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Long value) {
            addCriterion("`create_time` <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Long> values) {
            addCriterion("`create_time` in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Long> values) {
            addCriterion("`create_time` not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Long value1, Long value2) {
            addCriterion("`create_time` between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Long value1, Long value2) {
            addCriterion("`create_time` not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andJsonBankImagesIsNull() {
            addCriterion("`json_bank_images` is null");
            return (Criteria) this;
        }

        public Criteria andJsonBankImagesIsNotNull() {
            addCriterion("`json_bank_images` is not null");
            return (Criteria) this;
        }

        public Criteria andJsonBankImagesEqualTo(String value) {
            addCriterion("`json_bank_images` =", value, "jsonBankImages");
            return (Criteria) this;
        }

        public Criteria andJsonBankImagesNotEqualTo(String value) {
            addCriterion("`json_bank_images` <>", value, "jsonBankImages");
            return (Criteria) this;
        }

        public Criteria andJsonBankImagesGreaterThan(String value) {
            addCriterion("`json_bank_images` >", value, "jsonBankImages");
            return (Criteria) this;
        }

        public Criteria andJsonBankImagesGreaterThanOrEqualTo(String value) {
            addCriterion("`json_bank_images` >=", value, "jsonBankImages");
            return (Criteria) this;
        }

        public Criteria andJsonBankImagesLessThan(String value) {
            addCriterion("`json_bank_images` <", value, "jsonBankImages");
            return (Criteria) this;
        }

        public Criteria andJsonBankImagesLessThanOrEqualTo(String value) {
            addCriterion("`json_bank_images` <=", value, "jsonBankImages");
            return (Criteria) this;
        }

        public Criteria andJsonBankImagesLike(String value) {
            addCriterion("`json_bank_images` like", value, "jsonBankImages");
            return (Criteria) this;
        }

        public Criteria andJsonBankImagesNotLike(String value) {
            addCriterion("`json_bank_images` not like", value, "jsonBankImages");
            return (Criteria) this;
        }

        public Criteria andJsonBankImagesIn(List<String> values) {
            addCriterion("`json_bank_images` in", values, "jsonBankImages");
            return (Criteria) this;
        }

        public Criteria andJsonBankImagesNotIn(List<String> values) {
            addCriterion("`json_bank_images` not in", values, "jsonBankImages");
            return (Criteria) this;
        }

        public Criteria andJsonBankImagesBetween(String value1, String value2) {
            addCriterion("`json_bank_images` between", value1, value2, "jsonBankImages");
            return (Criteria) this;
        }

        public Criteria andJsonBankImagesNotBetween(String value1, String value2) {
            addCriterion("`json_bank_images` not between", value1, value2, "jsonBankImages");
            return (Criteria) this;
        }

        public Criteria andSubBranchIsNull() {
            addCriterion("`sub_branch` is null");
            return (Criteria) this;
        }

        public Criteria andSubBranchIsNotNull() {
            addCriterion("`sub_branch` is not null");
            return (Criteria) this;
        }

        public Criteria andSubBranchEqualTo(String value) {
            addCriterion("`sub_branch` =", value, "subBranch");
            return (Criteria) this;
        }

        public Criteria andSubBranchNotEqualTo(String value) {
            addCriterion("`sub_branch` <>", value, "subBranch");
            return (Criteria) this;
        }

        public Criteria andSubBranchGreaterThan(String value) {
            addCriterion("`sub_branch` >", value, "subBranch");
            return (Criteria) this;
        }

        public Criteria andSubBranchGreaterThanOrEqualTo(String value) {
            addCriterion("`sub_branch` >=", value, "subBranch");
            return (Criteria) this;
        }

        public Criteria andSubBranchLessThan(String value) {
            addCriterion("`sub_branch` <", value, "subBranch");
            return (Criteria) this;
        }

        public Criteria andSubBranchLessThanOrEqualTo(String value) {
            addCriterion("`sub_branch` <=", value, "subBranch");
            return (Criteria) this;
        }

        public Criteria andSubBranchLike(String value) {
            addCriterion("`sub_branch` like", value, "subBranch");
            return (Criteria) this;
        }

        public Criteria andSubBranchNotLike(String value) {
            addCriterion("`sub_branch` not like", value, "subBranch");
            return (Criteria) this;
        }

        public Criteria andSubBranchIn(List<String> values) {
            addCriterion("`sub_branch` in", values, "subBranch");
            return (Criteria) this;
        }

        public Criteria andSubBranchNotIn(List<String> values) {
            addCriterion("`sub_branch` not in", values, "subBranch");
            return (Criteria) this;
        }

        public Criteria andSubBranchBetween(String value1, String value2) {
            addCriterion("`sub_branch` between", value1, value2, "subBranch");
            return (Criteria) this;
        }

        public Criteria andSubBranchNotBetween(String value1, String value2) {
            addCriterion("`sub_branch` not between", value1, value2, "subBranch");
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