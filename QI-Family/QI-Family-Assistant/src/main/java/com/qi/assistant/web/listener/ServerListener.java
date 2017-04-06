package com.qi.assistant.web.listener;

import com.qi.common.util.PropertyUtil;
import com.qi.menu.rpc.constants.PortalConstants;
import com.qi.menu.rpc.hessian.consumer.RegisterConsumer;
import com.qi.menu.rpc.inf.RegisterAPI;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Class ServerListener
 *
 * @author 张麒 2016/9/3.
 * @version Description:
 */
public class ServerListener implements ServletContextListener {

    private RegisterAPI registerConsumer = new RegisterConsumer();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        PortalDto dto = new PortalDto();
//        dto.setPortalcode(PropertyPlaceholderUtil.getProps("portalcode"));
//        dto.setPortalname(PropertyPlaceholderUtil.getProps("portalname"));
//        dto.setDomain(PropertyPlaceholderUtil.getProps("domain"));
//        dto.setContextpath(PropertyPlaceholderUtil.getProps("contextpath"));
//        dto.setHomepage(PropertyPlaceholderUtil.getProps("homepage"));
//        dto.setPortaltype(Integer.valueOf(PropertyPlaceholderUtil.getProps("portaltype")));
//        dto.setDomainrewrite(PropertyPlaceholderUtil.getProps("domainrewrite"));
//        dto.setLogoimg(PropertyPlaceholderUtil.getProps("logoimg"));
//        dto.setLogoname(PropertyPlaceholderUtil.getProps("logoname"));
//        dto.setStatus(PortalConstants.Status.Normal.getKey());
//        registerConsumer.register(dto);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        registerConsumer.updateStatus(PropertyUtil.getProps("code"), PortalConstants.Status.Shutdown.getKey());
    }
}
