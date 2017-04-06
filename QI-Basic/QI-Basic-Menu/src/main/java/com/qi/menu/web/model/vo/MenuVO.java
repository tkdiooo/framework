package com.qi.menu.web.model.vo;


import com.qi.menu.model.domain.BasicMenu;

import java.util.List;

/**
 * Class MenuVO
 *
 * @author 张麒 2016/6/22.
 * @version Description:
 */
public class MenuVO extends BasicMenu {

    private static final long serialVersionUID = -1121042026100274892L;

    // 是否显示
    private boolean isHide = true;

    private String domain;

    private List<MenuVO> children;

    public boolean isHide() {
        return isHide;
    }

    public void setHide(boolean hide) {
        isHide = hide;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public List<MenuVO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVO> children) {
        this.children = children;
    }

}
