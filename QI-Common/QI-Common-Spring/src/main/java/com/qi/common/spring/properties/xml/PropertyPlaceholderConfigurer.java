package com.qi.common.spring.properties.xml;

import com.qi.common.security.EncrypterTool;
import com.qi.common.util.StringUtil;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.Properties;

/**
 * Class PropertyPlaceholderConfigurer extends org.springframework.beans.factory.config.PropertyPlaceholderConfigurer <br>
 * Base on Spring 3.1
 *
 * @author 张麒 2016/5/17.
 * @version Description:
 */
public class PropertyPlaceholderConfigurer extends org.springframework.beans.factory.config.PropertyPlaceholderConfigurer implements ServletContextAware {
    private static final String DEFAULT_KEY_CTXPATH = "_ctxPath";
    private static final String DEFAULT_JSPVARPREFIX = "jspvar.";

    private String keyCtxpath = DEFAULT_KEY_CTXPATH;
    private String jspvarPrefix = DEFAULT_JSPVARPREFIX;
    private String firstStr = DEFAULT_PLACEHOLDER_PREFIX;
    private String lastStr = DEFAULT_PLACEHOLDER_SUFFIX;

    private ServletContext servletContext;
    private Properties props;

    @Override
    protected String resolvePlaceholder(String placeholder, Properties props) {
        this.props = props;
        //初始化页面用到的配置参数
        initApplicationScopeVar();
        //判断是否数据库链接加密配置
        String prop = props.getProperty(placeholder);
        if (StringUtil.isNotBlank(placeholder) && StringUtil.isNotBlank(prop)) {
            if (placeholder.startsWith("jdbc")) {
                if (placeholder.endsWith(".username")) {
                    prop = EncrypterTool.decrypt(EncrypterTool.Security.Des3, prop);
                }
                if (placeholder.endsWith(".password")) {
                    prop = EncrypterTool.decrypt(EncrypterTool.Security.Des3, prop);
                }
            } else {
                prop = super.resolvePlaceholder(placeholder, props);
            }
        }
        return prop;
    }


    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    private void initApplicationScopeVar() {
        if (null != servletContext && null != props) {
            String ctxPath = servletContext.getContextPath();
            servletContext.setAttribute(keyCtxpath, ctxPath);
            props.put(keyCtxpath, ctxPath);
            for (Object keyo : props.keySet()) {
                String key = String.valueOf(keyo);
                if (key.startsWith(jspvarPrefix)) {
                    String value = props.getProperty(key);
                    value = convertValue(value);
                    servletContext.setAttribute(key.substring(jspvarPrefix.length()), value);
                }
            }
        }
    }

    private String convertValue(String value) {
        int firstIndex = value.indexOf(firstStr);
        if (firstIndex != -1) {
            int lastIndex = value.indexOf(lastStr);
            String key1 = value.substring(firstIndex + 2, lastIndex);
            String value1 = props.getProperty(key1);

            value = value.replace(firstStr + key1 + lastStr, value1);

            return convertValue(value);
        } else {
            return value;
        }

    }

    public void setKeyCtxpath(String keyCtxpath) {
        this.keyCtxpath = keyCtxpath;
    }

    public void setJspvarPrefix(String jspvarPrefix) {
        this.jspvarPrefix = jspvarPrefix;
    }

    public void setFirstStr(String firstStr) {
        this.firstStr = firstStr;
    }

    public void setLastStr(String lastStr) {
        this.lastStr = lastStr;
    }

    public Properties getProps() {
        return props;
    }
}
