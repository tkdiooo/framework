package com.qi.dict.web.model.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table t_basic_dictionary
     *
     * @ibatorgenerated Mon Jul 25 15:45:32 GMT+08:00 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table t_basic_dictionary
     *
     * @ibatorgenerated Mon Jul 25 15:45:32 GMT+08:00 2016
     */
    protected List oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_basic_dictionary
     *
     * @ibatorgenerated Mon Jul 25 15:45:32 GMT+08:00 2016
     */
    public DictionaryExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_basic_dictionary
     *
     * @ibatorgenerated Mon Jul 25 15:45:32 GMT+08:00 2016
     */
    protected DictionaryExample(DictionaryExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_basic_dictionary
     *
     * @ibatorgenerated Mon Jul 25 15:45:32 GMT+08:00 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_basic_dictionary
     *
     * @ibatorgenerated Mon Jul 25 15:45:32 GMT+08:00 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_basic_dictionary
     *
     * @ibatorgenerated Mon Jul 25 15:45:32 GMT+08:00 2016
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_basic_dictionary
     *
     * @ibatorgenerated Mon Jul 25 15:45:32 GMT+08:00 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_basic_dictionary
     *
     * @ibatorgenerated Mon Jul 25 15:45:32 GMT+08:00 2016
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_basic_dictionary
     *
     * @ibatorgenerated Mon Jul 25 15:45:32 GMT+08:00 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_basic_dictionary
     *
     * @ibatorgenerated Mon Jul 25 15:45:32 GMT+08:00 2016
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table t_basic_dictionary
     *
     * @ibatorgenerated Mon Jul 25 15:45:32 GMT+08:00 2016
     */
    public static class Criteria {
        protected List criteriaWithoutValue;

        protected List criteriaWithSingleValue;

        protected List criteriaWithListValue;

        protected List criteriaWithBetweenValue;

        protected Criteria() {
            super();
            criteriaWithoutValue = new ArrayList();
            criteriaWithSingleValue = new ArrayList();
            criteriaWithListValue = new ArrayList();
            criteriaWithBetweenValue = new ArrayList();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List list = new ArrayList();
            list.add(value1);
            list.add(value2);
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        public Criteria andGuidIsNull() {
            addCriterion("Guid is null");
            return this;
        }

        public Criteria andGuidIsNotNull() {
            addCriterion("Guid is not null");
            return this;
        }

        public Criteria andGuidEqualTo(Long value) {
            addCriterion("Guid =", value, "guid");
            return this;
        }

        public Criteria andGuidNotEqualTo(Long value) {
            addCriterion("Guid <>", value, "guid");
            return this;
        }

        public Criteria andGuidGreaterThan(Long value) {
            addCriterion("Guid >", value, "guid");
            return this;
        }

        public Criteria andGuidGreaterThanOrEqualTo(Long value) {
            addCriterion("Guid >=", value, "guid");
            return this;
        }

        public Criteria andGuidLessThan(Long value) {
            addCriterion("Guid <", value, "guid");
            return this;
        }

        public Criteria andGuidLessThanOrEqualTo(Long value) {
            addCriterion("Guid <=", value, "guid");
            return this;
        }

        public Criteria andGuidIn(List values) {
            addCriterion("Guid in", values, "guid");
            return this;
        }

        public Criteria andGuidNotIn(List values) {
            addCriterion("Guid not in", values, "guid");
            return this;
        }

        public Criteria andGuidBetween(Long value1, Long value2) {
            addCriterion("Guid between", value1, value2, "guid");
            return this;
        }

        public Criteria andGuidNotBetween(Long value1, Long value2) {
            addCriterion("Guid not between", value1, value2, "guid");
            return this;
        }

        public Criteria andParentcodeIsNull() {
            addCriterion("ParentCode is null");
            return this;
        }

        public Criteria andParentcodeIsNotNull() {
            addCriterion("ParentCode is not null");
            return this;
        }

        public Criteria andParentcodeEqualTo(String value) {
            addCriterion("ParentCode =", value, "parentcode");
            return this;
        }

        public Criteria andParentcodeNotEqualTo(String value) {
            addCriterion("ParentCode <>", value, "parentcode");
            return this;
        }

        public Criteria andParentcodeGreaterThan(String value) {
            addCriterion("ParentCode >", value, "parentcode");
            return this;
        }

        public Criteria andParentcodeGreaterThanOrEqualTo(String value) {
            addCriterion("ParentCode >=", value, "parentcode");
            return this;
        }

        public Criteria andParentcodeLessThan(String value) {
            addCriterion("ParentCode <", value, "parentcode");
            return this;
        }

        public Criteria andParentcodeLessThanOrEqualTo(String value) {
            addCriterion("ParentCode <=", value, "parentcode");
            return this;
        }

        public Criteria andParentcodeLike(String value) {
            addCriterion("ParentCode like", value, "parentcode");
            return this;
        }

        public Criteria andParentcodeNotLike(String value) {
            addCriterion("ParentCode not like", value, "parentcode");
            return this;
        }

        public Criteria andParentcodeIn(List values) {
            addCriterion("ParentCode in", values, "parentcode");
            return this;
        }

        public Criteria andParentcodeNotIn(List values) {
            addCriterion("ParentCode not in", values, "parentcode");
            return this;
        }

        public Criteria andParentcodeBetween(String value1, String value2) {
            addCriterion("ParentCode between", value1, value2, "parentcode");
            return this;
        }

        public Criteria andParentcodeNotBetween(String value1, String value2) {
            addCriterion("ParentCode not between", value1, value2, "parentcode");
            return this;
        }

        public Criteria andDictcodeIsNull() {
            addCriterion("DictCode is null");
            return this;
        }

        public Criteria andDictcodeIsNotNull() {
            addCriterion("DictCode is not null");
            return this;
        }

        public Criteria andDictcodeEqualTo(String value) {
            addCriterion("DictCode =", value, "dictcode");
            return this;
        }

        public Criteria andDictcodeNotEqualTo(String value) {
            addCriterion("DictCode <>", value, "dictcode");
            return this;
        }

        public Criteria andDictcodeGreaterThan(String value) {
            addCriterion("DictCode >", value, "dictcode");
            return this;
        }

        public Criteria andDictcodeGreaterThanOrEqualTo(String value) {
            addCriterion("DictCode >=", value, "dictcode");
            return this;
        }

        public Criteria andDictcodeLessThan(String value) {
            addCriterion("DictCode <", value, "dictcode");
            return this;
        }

        public Criteria andDictcodeLessThanOrEqualTo(String value) {
            addCriterion("DictCode <=", value, "dictcode");
            return this;
        }

        public Criteria andDictcodeLike(String value) {
            addCriterion("DictCode like", value, "dictcode");
            return this;
        }

        public Criteria andDictcodeNotLike(String value) {
            addCriterion("DictCode not like", value, "dictcode");
            return this;
        }

        public Criteria andDictcodeIn(List values) {
            addCriterion("DictCode in", values, "dictcode");
            return this;
        }

        public Criteria andDictcodeNotIn(List values) {
            addCriterion("DictCode not in", values, "dictcode");
            return this;
        }

        public Criteria andDictcodeBetween(String value1, String value2) {
            addCriterion("DictCode between", value1, value2, "dictcode");
            return this;
        }

        public Criteria andDictcodeNotBetween(String value1, String value2) {
            addCriterion("DictCode not between", value1, value2, "dictcode");
            return this;
        }

        public Criteria andDictnameIsNull() {
            addCriterion("DictName is null");
            return this;
        }

        public Criteria andDictnameIsNotNull() {
            addCriterion("DictName is not null");
            return this;
        }

        public Criteria andDictnameEqualTo(String value) {
            addCriterion("DictName =", value, "dictname");
            return this;
        }

        public Criteria andDictnameNotEqualTo(String value) {
            addCriterion("DictName <>", value, "dictname");
            return this;
        }

        public Criteria andDictnameGreaterThan(String value) {
            addCriterion("DictName >", value, "dictname");
            return this;
        }

        public Criteria andDictnameGreaterThanOrEqualTo(String value) {
            addCriterion("DictName >=", value, "dictname");
            return this;
        }

        public Criteria andDictnameLessThan(String value) {
            addCriterion("DictName <", value, "dictname");
            return this;
        }

        public Criteria andDictnameLessThanOrEqualTo(String value) {
            addCriterion("DictName <=", value, "dictname");
            return this;
        }

        public Criteria andDictnameLike(String value) {
            addCriterion("DictName like", value, "dictname");
            return this;
        }

        public Criteria andDictnameNotLike(String value) {
            addCriterion("DictName not like", value, "dictname");
            return this;
        }

        public Criteria andDictnameIn(List values) {
            addCriterion("DictName in", values, "dictname");
            return this;
        }

        public Criteria andDictnameNotIn(List values) {
            addCriterion("DictName not in", values, "dictname");
            return this;
        }

        public Criteria andDictnameBetween(String value1, String value2) {
            addCriterion("DictName between", value1, value2, "dictname");
            return this;
        }

        public Criteria andDictnameNotBetween(String value1, String value2) {
            addCriterion("DictName not between", value1, value2, "dictname");
            return this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("Description is null");
            return this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("Description is not null");
            return this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("Description =", value, "description");
            return this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("Description <>", value, "description");
            return this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("Description >", value, "description");
            return this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("Description >=", value, "description");
            return this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("Description <", value, "description");
            return this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("Description <=", value, "description");
            return this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("Description like", value, "description");
            return this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("Description not like", value, "description");
            return this;
        }

        public Criteria andDescriptionIn(List values) {
            addCriterion("Description in", values, "description");
            return this;
        }

        public Criteria andDescriptionNotIn(List values) {
            addCriterion("Description not in", values, "description");
            return this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("Description between", value1, value2, "description");
            return this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("Description not between", value1, value2, "description");
            return this;
        }

        public Criteria andSortIsNull() {
            addCriterion("Sort is null");
            return this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("Sort is not null");
            return this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("Sort =", value, "sort");
            return this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("Sort <>", value, "sort");
            return this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("Sort >", value, "sort");
            return this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("Sort >=", value, "sort");
            return this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("Sort <", value, "sort");
            return this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("Sort <=", value, "sort");
            return this;
        }

        public Criteria andSortIn(List values) {
            addCriterion("Sort in", values, "sort");
            return this;
        }

        public Criteria andSortNotIn(List values) {
            addCriterion("Sort not in", values, "sort");
            return this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("Sort between", value1, value2, "sort");
            return this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("Sort not between", value1, value2, "sort");
            return this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("Status is null");
            return this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("Status is not null");
            return this;
        }

        public Criteria andStatusEqualTo(Boolean value) {
            addCriterion("Status =", value, "status");
            return this;
        }

        public Criteria andStatusNotEqualTo(Boolean value) {
            addCriterion("Status <>", value, "status");
            return this;
        }

        public Criteria andStatusGreaterThan(Boolean value) {
            addCriterion("Status >", value, "status");
            return this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("Status >=", value, "status");
            return this;
        }

        public Criteria andStatusLessThan(Boolean value) {
            addCriterion("Status <", value, "status");
            return this;
        }

        public Criteria andStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("Status <=", value, "status");
            return this;
        }

        public Criteria andStatusIn(List values) {
            addCriterion("Status in", values, "status");
            return this;
        }

        public Criteria andStatusNotIn(List values) {
            addCriterion("Status not in", values, "status");
            return this;
        }

        public Criteria andStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("Status between", value1, value2, "status");
            return this;
        }

        public Criteria andStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("Status not between", value1, value2, "status");
            return this;
        }
    }
}