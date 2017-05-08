package com.qi.menu.web.config;

import com.qi.common.constants.LabelConstants;
import com.qi.common.util.PropertyUtil;
import com.qi.menu.model.constants.CommonConstants;
import com.qi.menu.web.model.vo.SystemVO;

/**
 * Class ApplicationConfig
 *
 * @author 张麒 2016/6/21.
 * @version Description:
 */
public class ApplicationConfig {

    /**
     * 部署模式 - deve:开发、test:测试、prod:生产
     */
    public static final String DEPLOY_MODE = PropertyUtil.getProps("deploy.mode");

    /**
     * 常量：0L
     */
    public static final Long CONSTANT_0 = 0L;

    public static String getLayout(SystemVO VO) {
        return VO.getDomain() + CommonConstants.PLATFORM_CONTEXT_PATH + (
                VO.getLayout().equals(CommonConstants.LAYOUT_TSHAPE) ? CommonConstants.LAYOUT_SPLIT : CommonConstants.LAYOUT_TSHAPE
        ) + VO.getContextpath() + LabelConstants.PERIOD + LabelConstants.HTML;
    }
//
//    /**
//     * 开发菜单ID
//     */
//    public static final Long DEVE_MENU_ID = 10000L;
//
//    /**
//     * 开发菜单编号
//     */
//    public static final String DEVE_MENU_CODE = "navigation.development";
//
//    /**
//     * 开发菜单名称
//     */
//    public static final String DEVE_MENU_NAME = "开发管理";
//
//    public static final String[] DEVE_CHILD_MENU_CODE = {"navigation.development.menu", "navigation.development.portal", "navigation.development.dict", "navigation.development.pageConfig"};
//
//    public static final String[] DEVE_CHILD_MENU_NAME = {"导航菜单", "门户注册", "数据字典", "页面配置"};
//
//    public static final String DEVE_MENU_DOMAIN = "http://www.zzl.com";
//
//    public static final String[] DEVE_CHILD_MENU_CONTEXTPATH = {"/menu", "/menu", "/dict", "/menu"};
//
//    public static final String[] DEVE_CHILD_MENU_CONTROLLER = {"/menu", "/registry", "", "/pageConfig"};
//
//    public static final String[] DEVE_CHILD_MENU_REQUEST = {"/index.html", "/index.html", "/index.html", "/index.html"};
//
//    public static final String PORTAL_PLATFORM_CODE = "platform.management";
//
//    public static final String PORTAL_PLATFORM_NAME = "平台管理";
//
//    public static final String PORTAL_PLATFORM_DOMAIN = "http://www.zzl.com";
//
//    public static final String PORTAL_PLATFORM_CONTEXTPATH = "/menu";
//
//    public static final String PORTAL_PLATFORM_HOMEPAGE = "/index.html";
//
//    public static final Integer PORTAL_PLATFORM_PORTALTYPE = PortalConstants.Type.Menu.getKey();
//
//    public static final String PORTAL_PLATFORM_LOGOIMG = "";
//
//    public static final String PORTAL_PLATFORM_LOGONAME = "Platform Management";
//
//    public static final Integer PORTAL_PLATFORM_STATUS = PortalConstants.Status.Normal.getKey();


}
