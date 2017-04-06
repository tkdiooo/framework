package com.qi.assistant.web.model.vo;

import com.qi.common.dao.model.BaseModel;

/**
 * Class BarVO
 *
 * @author 张麒 2016/9/13.
 * @version Description:
 */
public class BarVO extends BaseModel {

    private static final long serialVersionUID = -5990894417056840941L;

    private String year;
    private String title;
    private String amount;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
