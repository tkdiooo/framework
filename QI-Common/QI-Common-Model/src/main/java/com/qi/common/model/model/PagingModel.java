package com.qi.common.model.model;

import com.qi.common.constants.StringConstants;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class PagingModel
 *
 * @author 张麒 2016/5/12.
 * @version Description:
 */
public class PagingModel<T> implements Serializable {

    private static final long serialVersionUID = -2015185869747076769L;
    private int pageSize = 10;
    private int currentPage = 1;
    private int totalCount;
    private List<T> result;
    private List<Map<String, Object>> mapResult;
    private String fullText;

    private T param;

    private Map<String, Boolean> orderCols = new HashMap<>();

    public Map<String, Boolean> getOrderBy() {
        return orderCols;
    }

    public void setOrderBy(String column, boolean asc) {
        orderCols.put(column, asc);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public List<Map<String, Object>> getMapResult() {
        return mapResult;
    }

    public void setMapResult(List<Map<String, Object>> mapResult) {
        this.mapResult = mapResult;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    // oracle
    public int getStartRow() {
        return (this.currentPage - 1) * this.pageSize;
    }

    public int getEndRow() {
        return currentPage * pageSize - 1;
    }

    // mysql
    public int getStartIndex() {
        return (this.currentPage - 1) * this.pageSize;
    }

    public void setParam(T param) {
        this.param = param;
    }

    public T getParam() {
        return param;
    }

    public String getOrderByClause() {
        if (orderCols.size() == 0) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            orderCols.forEach((key, value) -> {
                if (null != value)
                    sb.append(StringConstants.COMMA).append(key).append(StringConstants.SPACE).append(value ? "DESC" : "ASC");
            });
            if (sb.length() > 0) {
                sb.deleteCharAt(0);
                return sb.toString();
            } else {
                return null;
            }
        }
    }
}
