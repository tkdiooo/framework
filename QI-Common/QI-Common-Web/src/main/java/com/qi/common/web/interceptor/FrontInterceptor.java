package com.qi.common.web.interceptor;

import com.qi.common.util.StringUtil;
import com.qi.common.web.annotation.BreadcrumbNavigation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class FrontInterceptor
 *
 * @author 张麒 2017/2/9.
 * @version Description:
 */
public class FrontInterceptor extends HandlerInterceptorAdapter {

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 只有返回true才会继续向下执行，返回false取消当前请求
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        BreadcrumbNavigation breadcrumb = method.getAnnotation(BreadcrumbNavigation.class);
        // 面包屑功能
        if (null != breadcrumb) {
            Map<String, String> map = new LinkedHashMap<>();
            this.recursionBreadcrumb(map, breadcrumb, method.getAnnotation(RequestMapping.class), handlerMethod.getBean().getClass());
            modelAndView.getModel().put("breadcrumb", map);
        }
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 会在Controller方法异步执行时开始执行
     *
     * @param request
     * @param response
     * @param handler
     * @throws Exception
     */
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    }

    /**
     * 读取面包屑
     *
     * @param map
     * @param breadcrumb
     * @param mapping
     * @param cls
     * @param <T>
     * @throws NoSuchMethodException
     */
    private <T> void recursionBreadcrumb(Map<String, String> map, BreadcrumbNavigation breadcrumb, RequestMapping mapping, Class<T> cls) throws NoSuchMethodException {
        RequestMapping requestMapping = cls.getAnnotation(RequestMapping.class);
        if (StringUtil.isNotEmpty(breadcrumb.root())) {
            map.put(breadcrumb.root(), "");
        } else if (StringUtil.isNotEmpty(breadcrumb.aliasFor())) {
            Method method = cls.getMethod(breadcrumb.aliasFor());
            this.recursionBreadcrumb(map, method.getAnnotation(BreadcrumbNavigation.class), method.getAnnotation(RequestMapping.class), cls);
        }
        map.put(breadcrumb.value(), requestMapping.value()[0] + mapping.value()[0]);
    }
}
