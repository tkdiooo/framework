package com.qi.menu.web.model.vo;

import com.qi.common.constants.StringConstants;
import com.qi.common.util.StringUtil;
import com.qi.menu.model.constants.CommonConstants;
import com.qi.menu.model.domain.BasicSystem;

/**
 * Class PortalVO
 *
 * @author 张麒 2016/9/23.
 * @version Description:
 */
public class SystemVO extends BasicSystem {

    private static final long serialVersionUID = -6265444525662865295L;

    private String layout;

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getPortalUrl() {
        return this.getDomain() + CommonConstants.PLATFORM_CONTEXT_PATH + getLayout() + this.getContextpath() + StringConstants.PERIOD + StringConstants.HTML;
    }

    public String getHomeUrl() {
        return this.getDomain() + this.getHomepage();
    }

    public String getLogoICO() {
        return StringUtil.isNotBlank(getLogopath()) ? getLogopath().substring(0, getLogopath().lastIndexOf(StringConstants.PERIOD)) + ".ico" : "";
    }
}
