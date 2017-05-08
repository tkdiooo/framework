package com.qi.menu.web.controller;

import com.qi.common.constants.LabelConstants;
import com.qi.common.security.EncrypterTool;
import com.qi.common.tool.Logger;
import com.qi.common.util.ListUtil;
import com.qi.common.util.StringUtil;
import com.qi.menu.web.cache.CacheFactory;
import com.qi.menu.web.config.ApplicationConfig;
import com.qi.menu.web.model.vo.SystemVO;
import com.qi.menu.web.service.read.MenuReadService;
import com.qi.menu.web.service.read.SystemReadService;
import com.qi.sso.common.constants.SSOConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class LayoutController
 *
 * @author 张麒 2016/6/27.
 * @version Description:
 */
@Controller("/")
public class LayoutController {

    private static final Logger logger = new Logger(LayoutController.class);

    @Autowired
    private MenuReadService menuReadService;

    @Autowired
    private SystemReadService portalReadService;

    @RequestMapping(value = "{layout}/{path}.html", method = RequestMethod.GET)
    public String index(@PathVariable("layout") String layout, @PathVariable("path") String path, ModelMap model, HttpServletRequest request) {
        SystemVO portal = portalReadService.getSystemVOByContextPath(path);
        portal.setLayout(LabelConstants.FORWARD_SLASH + layout);
        String form_url = request.getParameter(SSOConstants.PARAM_FROM_URL);
        if (StringUtil.isNotBlank(form_url)) {
            // TODO 缺少url页面级别验证，如果是非一级页面则选择修改
            System.out.println(EncrypterTool.decrypt(EncrypterTool.Security.Des3ECB, form_url));
            model.put("form_url", EncrypterTool.decrypt(EncrypterTool.Security.Des3ECB, form_url));
        }
        // 如果之前的请求不是顶级页面，跳转首页,防止嵌套页面跳转
        else if (!portal.getPortalUrl().contains(request.getRequestURI())) {
            model.put("form_url", portal.getHomeUrl());
        }
        // 设置为默认首页
        else {
            model.put("form_url", portal.getHomeUrl());
        }
        model.put("layoutUrl", ApplicationConfig.getLayout(portal));
        model.put("menus", menuReadService.findMenuByAuthority(0L, portal.getCode()));
        model.put("portal", portal);
        List<SystemVO> systems = ListUtil.copyConvert(SystemVO.class, CacheFactory.getBasicSystemCache());
        systems.forEach((vo) -> vo.setLayout(LabelConstants.FORWARD_SLASH + layout));
        model.put("systems", systems);
        return layout + "/index";
    }

    @RequestMapping(value = "index.html", method = RequestMethod.GET)
    public String split(ModelMap model, HttpServletRequest request) {
        return "index";
    }
}
