package com.qi.common.web.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class LoginUserInterceptor
 *
 * @author 张麒 2016/5/17.
 * @version Description:
 */
public class LoginUserInterceptor extends HandlerInterceptorAdapter {

    public LoginUserInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        AuthorityModel.set((AuthorityModel) null);
        super.afterCompletion(request, response, handler, ex);
    }
}
