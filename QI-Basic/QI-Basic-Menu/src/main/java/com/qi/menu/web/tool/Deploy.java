package com.qi.menu.web.tool;

import com.qi.menu.web.config.ApplicationConfig;

/**
 * Class Deploy
 *
 * @author 张麒 2016/6/27.
 * @version Description:
 */
public class Deploy {

    private Deploy() {
    }

    private static Deploy deploy = null;

    public static Deploy getInstance() {
        if (null == deploy)
            synchronized (Deploy.class) {
                if (null == deploy) deploy = new Deploy();
            }
        return deploy;
    }

    public boolean isDevelopment() {
        return ApplicationConfig.DEPLOY_MODE.equals("deve");
    }

//    public List<MenuVO> buildDeveMenu() {
//        List<MenuVO> menus = ListUtil.getInstance();
//        MenuVO menuVO = new MenuVO();
//        menuVO.setGuid(ApplicationConfig.DEVE_MENU_ID);
//        menuVO.setParentid(ApplicationConfig.CONSTANT_0);
//        menuVO.setMenucode(ApplicationConfig.DEVE_MENU_CODE);
//        menuVO.setMenuname(ApplicationConfig.DEVE_MENU_NAME);
//        menuVO.setRootid(ApplicationConfig.CONSTANT_0);
//        menuVO.setLevel(0);
//        menuVO.setSort(0);
//        menuVO.setIsleaf(StatusConstants.YesNo.No.getKey());
//        menuVO.setStatus(BoolUtil.toBoolean(StatusConstants.Status.VALID.getKey()));
//        menuVO.setChildren(buildChildren(menuVO));
//        menus.add(menuVO);
//        return menus;
//    }

//    private List<MenuVO> buildChildren(MenuVO parent) {
//        Long id = parent.getGuid();
//        List<MenuVO> menus = ListUtil.getInstance();
//        MenuVO menuVO;
//        for (int i = 0; i < ApplicationConfig.DEVE_CHILD_MENU_CODE.length; i++) {
//            menuVO = new MenuVO();
//            menuVO.setGuid(id++);
//            menuVO.setParentid(parent.getGuid());
//            menuVO.setMenucode(ApplicationConfig.DEVE_CHILD_MENU_CODE[i]);
//            menuVO.setMenuname(ApplicationConfig.DEVE_CHILD_MENU_NAME[i]);
//            menuVO.setDomain(ApplicationConfig.DEVE_MENU_DOMAIN);
//            menuVO.setContextpath(ApplicationConfig.DEVE_CHILD_MENU_CONTEXTPATH[i]);
//            menuVO.setController(ApplicationConfig.DEVE_CHILD_MENU_CONTROLLER[i]);
//            menuVO.setRequestmapping(ApplicationConfig.DEVE_CHILD_MENU_REQUEST[i]);
//            menuVO.setRootid(parent.getGuid());
//            menuVO.setLevel(1);
//            menuVO.setSort(i);
//            menuVO.setIsleaf(StatusConstants.YesNo.Yes.getKey());
//            menuVO.setStatus(BoolUtil.toBoolean(StatusConstants.Status.VALID.getKey()));
//            menus.add(menuVO);
//        }
//        return menus;
//    }

//    public BasicPortal buildBasicPortal() {
//        BasicPortal portal = new BasicPortal();
//        portal.setGuid(ApplicationConfig.CONSTANT_0);
//        portal.setPortalcode(ApplicationConfig.PORTAL_PLATFORM_CODE);
//        portal.setPortalname(ApplicationConfig.PORTAL_PLATFORM_NAME);
//        portal.setDomain(ApplicationConfig.PORTAL_PLATFORM_DOMAIN);
//        portal.setContextpath(ApplicationConfig.PORTAL_PLATFORM_CONTEXTPATH);
//        portal.setHomepage(ApplicationConfig.PORTAL_PLATFORM_HOMEPAGE);
//        portal.setPortaltype(ApplicationConfig.PORTAL_PLATFORM_PORTALTYPE);
//        portal.setLogoimg(ApplicationConfig.PORTAL_PLATFORM_LOGOIMG);
//        portal.setLogoname(ApplicationConfig.PORTAL_PLATFORM_LOGONAME);
//        portal.setStatus(ApplicationConfig.PORTAL_PLATFORM_STATUS);
//        return portal;
//    }
}
