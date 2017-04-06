package com.qi.assistant.web.model.vo;

import com.qi.common.dao.model.BaseModel;

import java.math.BigDecimal;

/**
 * Class PieVO
 *
 * @author 张麒 2016/9/8.
 * @version Description:
 */
public class PieVO extends BaseModel {

    private static final long serialVersionUID = 3020876144821120841L;

    private String title;
    private BigDecimal amount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getAmount() {
        return null != amount ? amount : new BigDecimal(0);
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
