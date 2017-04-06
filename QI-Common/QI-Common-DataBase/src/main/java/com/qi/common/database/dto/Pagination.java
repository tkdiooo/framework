package com.qi.common.database.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Pagination
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class Pagination extends BaseListDto {

    private static final long serialVersionUID = -2347438840805341687L;

    @SuppressWarnings("unchecked")
    private List resultList = new ArrayList();

    @SuppressWarnings("unchecked")
    public List getResultList() {
        return resultList;
    }

    @SuppressWarnings("unchecked")
    public void setResultList(List resultList) {
        this.resultList = resultList;
    }
}
