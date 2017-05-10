package com.qi.common.spring.boot.configurer;

import com.qi.common.constants.CommonConstants;
import com.qi.common.constants.LabelConstants;
import com.qi.common.spring.boot.constants.PropertyConstants;
import com.qi.common.util.ByteSizeUtil;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

/**
 * Class WebMvcConfigurer
 *
 * @author 张麒 2017/3/16.
 * @version Description:
 */
@Configuration
@ComponentScan(basePackages = "com.qi.common")
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Resource
    private MessageSource messageSource;

    @Autowired
    private PropertyConstants property;

    /**
     * 默认首页
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(LabelConstants.FORWARD_SLASH).setViewName("forward:/index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    /**
     * 定制Spring MVC URL 匹配规则
     *
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false).setUseTrailingSlashMatch(true);
    }

    /**
     * Spring MVC Validator 加载：添加自定义验证工厂类
     *
     * @return
     */
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setProviderClass(HibernateValidator.class);
        validator.setValidationMessageSource(messageSource);
        return validator;
    }

    /**
     * 设置Servlet配置
     *
     * @param servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet servlet) {
        // 404请求抛出NoHandlerFoundException
        servlet.setThrowExceptionIfNoHandlerFound(true);
        ServletRegistrationBean registration = new ServletRegistrationBean(servlet);
        // 上传文件配置
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(property.getLocation(), ByteSizeUtil.parseBytesSize(property.getMaxFileSize()), ByteSizeUtil.parseBytesSize(property.getMaxRequestSize()), property.getFileSizeThreshold());
        registration.setMultipartConfig(multipartConfigElement);
        return registration;
    }

    /**
     * 添加国际化资源<br/>
     * <b>Note<b/> 静态方法保证其在系统启动时就调用
     *
     * @return
     */
    @Bean
    public static MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames(CommonConstants.RESOURCES_I18N_PATH);
        messageSource.setDefaultEncoding(LabelConstants.UTF8);
        messageSource.setCacheSeconds(600);
        return messageSource;
    }
}
