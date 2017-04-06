package com.qi.common.model.vo.paging;

import java.util.Map;

/**
 * Class HeaderVO
 *
 * @author 张麒 2016/7/11.
 * @version Description:
 */
public class HeaderVO {

    private boolean checkbox;

    private Map<String, TitleVO> title;

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public Map<String, TitleVO> getTitle() {
        return title;
    }

    public void setTitle(Map<String, TitleVO> title) {
        this.title = title;
    }
}
