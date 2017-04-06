package com.qi.common.model.result;

import com.qi.common.model.abs.BasicResult;

import java.util.List;

/**
 * Restful 通信接口响应对象
 * success：成功 or 失败<br/>
 * ReplyCode：响应状态码<br/>
 * messages：响应消息列表<br/>
 * dataSet：数据集合<br/>
 * result：单一数据对象
 *
 * @author 张麒 2017/3/21.
 * @version Description:
 */
public class RestResult<T> extends BasicResult {

    private static final long serialVersionUID = -5771508025833426474L;

    private List<T> dataSet;

    private T result;

    public List<T> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<T> dataSet) {
        this.dataSet = dataSet;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}
