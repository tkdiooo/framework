package com.qi.common.web.interceptor;

import com.qi.common.constants.CommonConstants;
import com.qi.common.util.StringUtil;
import com.qi.common.web.tool.PropertyTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class AuthorityInterceptor
 *
 * @author 张麒 2016/7/12.
 * @version Description:
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthorityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        if (StringUtil.isBlank(PropertyTool.layout) && request.getRequestURL().toString().endsWith(".html")) {
//            System.out.println(request.getSession().getAttribute(CommonConstants.SYSTEM_LAYOUT));
//
//            System.out.println(PropertyTool.layout);
//        }
//    }
}
