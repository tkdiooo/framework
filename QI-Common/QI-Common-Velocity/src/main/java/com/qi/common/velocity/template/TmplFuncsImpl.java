package com.qi.common.velocity.template;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.velocity.VelocityConfig;

import javax.servlet.http.HttpServletRequest;
import java.io.StringWriter;
import java.util.Enumeration;

/**
 * Class TmplFuncsImpl
 *
 * @author 张麒 2016/5/17.
 * @version Description:
 */
public class TmplFuncsImpl implements TmplFuncs {

    private VelocityConfig velocityConfigurer;

    public final String toHtml(HttpServletRequest request, String templateName) {
        Enumeration<String> enums = request.getAttributeNames();
        try {
            Template template = velocityConfigurer.getVelocityEngine().getTemplate(templateName, "utf-8");
            VelocityContext context = new VelocityContext();
            while (enums.hasMoreElements()) {
                String key = enums.nextElement();
                context.put(key, request.getAttribute(key));
            }
            StringWriter writer = new StringWriter();
            template.merge(context, writer);
            return StringUtils.replace(writer.toString(), "\t", " ");
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public VelocityConfig getVelocityConfigurer() {
        return velocityConfigurer;
    }

    public void setVelocityConfigurer(VelocityConfig velocityConfigurer) {
        this.velocityConfigurer = velocityConfigurer;
    }

}
