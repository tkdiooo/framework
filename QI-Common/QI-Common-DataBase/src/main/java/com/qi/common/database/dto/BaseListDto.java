package com.qi.common.database.dto;

import java.io.Serializable;

/**
 * Class BaseListDto
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class BaseListDto implements Serializable {

    private static final long serialVersionUID = -5467438840805341687L;

    public final static String DIRECTION_DESC = "DESC";

    public final static String DIRECTION_ASC = "ASC";

    private int start;

    private int limit = 9;

    // 数据库字段名
    private String sort;

    // 方向
    private String dir;

    private boolean needCount;

    private int totalCount;

    private String refStatementName;

    public String getRefStatementName() {
        return refStatementName;
    }

    public void setRefStatementName(String refStatementName) {
        this.refStatementName = refStatementName;
    }

    final public int getTotalCount() {
        return totalCount;
    }

    final public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    final public void calStart() {
        if (start >= totalCount) {
            start = ((int) ((totalCount - 1) / limit)) * limit;
        }
    }

    final public void setPgNumber(int pgNumber) {
        if (pgNumber < 1)
            pgNumber = 1;

        start = (pgNumber - 1) * limit;
    }

    final public int getPgNumber() {
        return start / limit + 1;
    }

    final public int getEnd() {
        return this.start + this.limit;
    }

    final public int getStart() {
        return start;
    }

    final public void setStart(int start) {
        this.start = start;
    }

    final public int getLimit() {
        return limit;
    }

    final public void setLimit(int limit) {
        this.limit = limit;
    }

    final public boolean isNeedCount() {
        return needCount;
    }

    final public void setNeedCount(boolean needCount) {
        this.needCount = needCount;
    }

    final public String getSort() {
        return sort;
    }

    final public void setSort(String sort) {
        this.sort = sort;
    }

    final public String getDir() {
        return dir;
    }

    final public void setDir(String dir) {
        this.dir = dir;
    }

    final public int getPgCount() {
        if (totalCount == 0) {
            return 0;
        } else {
            int pageCnt;
            if (totalCount % limit == 0) {
                pageCnt = totalCount / limit;
            } else {
                pageCnt = totalCount / limit + 1;
            }
            return pageCnt;
        }
    }
}
