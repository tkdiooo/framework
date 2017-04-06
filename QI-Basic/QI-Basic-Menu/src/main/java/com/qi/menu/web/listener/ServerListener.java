package com.qi.menu.web.listener;

import com.qi.common.util.PropertyUtil;
import com.qi.menu.rpc.constants.PortalConstants;
import com.qi.menu.web.service.write.SystemWriteService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Class ServerListener
 *
 * @author 张麒 2016/9/3.
 * @version Description:
 */
public class ServerListener implements ServletContextListener {

    private SystemWriteService service = null;

    private void initialized(ServletContextEvent event) {
        if (service == null) {
            ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
            service = (SystemWriteService) ac.getBean("PortalWriteService");
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        this.initialized(event);
//        BasicSystem model = new BasicSystem();
//        model.setCode(PropertyPlaceholderUtil.getProps("portalcode"));
//        model.setNamecn(PropertyPlaceholderUtil.getProps("portalname"));
//        model.setNameen(PropertyPlaceholderUtil.getProps("logoname"));
//        model.setDomain(PropertyPlaceholderUtil.getProps("domain"));
//        model.setContextpath(PropertyPlaceholderUtil.getProps("contextpath"));
//        model.setHomepage(PropertyPlaceholderUtil.getProps("homepage"));
//        model.setType(Integer.valueOf(PropertyPlaceholderUtil.getProps("portaltype")));
//        model.setLogopath(PropertyPlaceholderUtil.getProps("logoimg"));
//        model.setStatus(PortalConstants.Status.Normal.getKey());
//        service.register(model);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        this.initialized(event);
        service.updateStatus(PropertyUtil.getProps("code"), PortalConstants.Status.Shutdown.getKey());
    }
}
