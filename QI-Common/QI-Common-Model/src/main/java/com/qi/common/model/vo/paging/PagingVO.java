package com.qi.common.model.vo.paging;

import com.qi.common.model.model.PagingModel;

/**
 * Class PagingVO
 *
 * @author 张麒 2016/6/28.
 * @version Description:
 */
public class PagingVO<T> {

    private HeaderVO header;

    private PagingModel<T> pagingModel;

    public HeaderVO getHeader() {
        return header;
    }

    public void setHeader(HeaderVO header) {
        this.header = header;
    }

    public PagingModel<T> getPagingModel() {
        return pagingModel;
    }

    public void setPagingModel(PagingModel<T> pagingModel) {
        this.pagingModel = pagingModel;
    }
}
