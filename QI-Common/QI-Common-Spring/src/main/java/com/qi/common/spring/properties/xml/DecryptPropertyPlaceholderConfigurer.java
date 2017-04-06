package com.qi.common.spring.properties.xml;

import com.qi.common.util.PropertyUtil;
import com.qi.common.security.EncrypterTool;
import com.qi.common.util.StringUtil;

import java.util.Properties;

/**
 * Class DecryptPropertyPlaceholderConfigurer extends org.springframework.beans.factory.config.PropertyPlaceholderConfigurer <br>
 * Base on Spring 3.1
 *
 * @author 张麒 2016/5/17.
 * @version Spring 3.1:
 */
public class DecryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    @Override
    protected String resolvePlaceholder(String placeholder, Properties props) {
        PropertyUtil.setProps(props);
        String prop = props.getProperty(placeholder);

        if (StringUtil.isNotBlank(placeholder) && StringUtil.isNotBlank(prop)) {
            if (placeholder.startsWith("jdbc")) {
                if (placeholder.endsWith(".username")) {
                    prop = EncrypterTool.decrypt(EncrypterTool.Security.Des3, prop);
                }
                if (placeholder.endsWith(".password")) {
                    prop = EncrypterTool.decrypt(EncrypterTool.Security.Des3, prop);
                }
            }
        }
        return prop;
    }
}
