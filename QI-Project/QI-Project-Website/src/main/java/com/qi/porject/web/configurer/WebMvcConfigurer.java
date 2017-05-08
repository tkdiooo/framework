package com.qi.porject.web.configurer;

import com.qi.common.constants.LabelConstants;
import com.qi.common.web.interceptor.FrontInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Class ApplicationConfigurer
 *
 * @author 张麒 2017/2/9.
 * @version Description:
 */
@Configuration("ProjectWebConfigurer")
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new FrontInterceptor()).addPathPatterns("/**").excludePathPatterns(LabelConstants.FORWARD_SLASH);
        super.addInterceptors(registry);
    }

}
