package com.qi.common.model.vo.paging;

import java.util.Arrays;
import java.util.List;

/**
 * Class OperateVO
 *
 * @author 张麒 2016/8/4.
 * @version Description:
 */
public class OperateVO {

    public enum operateType {
        search, delete, edited, operate
    }

    List<operateType> type;

    public OperateVO(operateType... types) {
        type = Arrays.asList(types);
    }

    public List<operateType> getType() {
        return type;
    }

    public void setType(List<operateType> type) {
        this.type = type;
    }
}
