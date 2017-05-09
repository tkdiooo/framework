package com.qi.common.web.tool;

import com.qi.common.constants.PropertiesConstants;
import com.qi.common.util.PropertyUtil;

/**
 * Class PropertyTool
 *
 * @author 张麒 2016/5/12.
 * @version Description:
 */
public class PropertyTool {

    public String getResourcePath() {
        return PropertyUtil.getProps(PropertiesConstants.PROPERTY_STATIC_RESOURCE);
    }

    public String getSystemChinese() {
        return PropertyUtil.getProps("website.system.chinese");
    }

    public String getSystemEnglish() {
        return PropertyUtil.getProps("website.system.english");
    }

    public String getLogoutPath() {
        return PropertyUtil.getProps().getProperty("sso.logout.url");
    }

}
