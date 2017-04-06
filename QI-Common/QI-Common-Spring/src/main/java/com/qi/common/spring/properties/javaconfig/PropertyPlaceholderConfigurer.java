package com.qi.common.spring.properties.javaconfig;

import com.qi.common.constants.CommonConstants;
import com.qi.common.constants.StringConstants;
import com.qi.common.spring.properties.xml.DecryptPropertyPlaceholderConfigurer;
import com.qi.common.util.StringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Class ApplicationConfigurer
 *
 * @author 张麒 2017/2/4.
 * @version Description:
 */
//@Configuration
public class PropertyPlaceholderConfigurer {

    /**
     * 加载自定义配置文件
     *
     * @return
     */
    @Bean
    public static DecryptPropertyPlaceholderConfigurer properties() {
        final DecryptPropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new DecryptPropertyPlaceholderConfigurer();
        final List<Resource> resources = new ArrayList<>();
        // 设置如果未能找到属性资源应该被忽视 默认false
        propertyPlaceholderConfigurer.setIgnoreResourceNotFound(true);

        // 没有设置要读取的文件
        if (StringUtil.isBlank(System.getProperty(CommonConstants.RESOURCES_PROPERTY_FILE))) {
            return propertyPlaceholderConfigurer;
        }

        // 默认classpath路径config文件夹
        String location = "config";

        // 获取静态文件地址
        if (StringUtil.isNotBlank(System.getProperty(CommonConstants.RESOURCES_CONFIG_PATH))) {
            location = System.getProperty(CommonConstants.RESOURCES_CONFIG_PATH).trim();
        }

        // 分隔文件名称
        String[] propertyList = System.getProperty(CommonConstants.RESOURCES_PROPERTY_FILE).split(StringConstants.SEMICOLON);

        for (String value : propertyList) {
            value = value.trim();
            if (value.startsWith(StringConstants.MATCHING_FILE))
                resources.add(new FileSystemResource(StringUtil.replace(value, StringConstants.MATCHING_FILE, location)));
            else
                resources.add(new ClassPathResource(location + value));
        }

        propertyPlaceholderConfigurer.setLocations(resources.toArray(new Resource[]{}));
        return propertyPlaceholderConfigurer;
    }
}
