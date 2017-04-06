package com.qi.common.web.interceptor;

import com.qi.common.web.annotation.AvoidDuplicateSubmission;
//import com.qi.common.web.annotation.NotRequireLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 登录拦截校验
 *
 * @author 张麒 2016/5/12.
 * @version Description:
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * 过滤重复提交  限制提交时间间隔
     *
     * @param request
     * @param response
     * @return
     */
//    @SuppressWarnings("rawtypes")
//    public boolean feysx(ServletRequest request, ServletResponse response) {
//        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
//        ServletContext servletContext = webApplicationContext.getServletContext();
//        long ppp = 2000;
//        HttpServletRequest req = (HttpServletRequest) request;
//        String sessionID = ((HttpServletRequest) request).getSession().getId();
//        String url = req.getRequestURL().toString();
//        Map<String, String[]> paramMap = request.getParameterMap();
//        for (Iterator iter = paramMap.entrySet().iterator(); iter.hasNext(); ) {
//            Map.Entry element = (Map.Entry) iter.next();
//            Object strKey = element.getKey();
//            String[] value = (String[]) element.getValue();
//            url += strKey.toString() + "=";
//            for (int i = 0; i < value.length; i++) {
//                url += value[i] + ",";
//            }
//        }
//        if (paramMap != null && paramMap.size() > 2) {
//            sessionID = sessionID + url;
//            Date now = new Date();
//            Visitor vis = (Visitor) servletContext.getAttribute(sessionID);
//            if (vis != null) {
//                vis.getRequestTimeQueue().insert(now.getTime());
//                Long span = vis.getRequestTimeQueue().getLast() - vis.getRequestTimeQueue().getFirst();
//                if (now.getTime() - vis.getStime() < ppp) {
//                    vis.setStime(now.getTime());
//                    return false;
//                }
//                vis.setStime(now.getTime());
//            } else {
//                ArrayTime timeQueue = new ArrayTime();
//                timeQueue.setLength(2);
//                timeQueue.init();
//                vis = new Visitor();
//                vis.setSessionID(sessionID);
//                vis.setRequestTimeQueue(timeQueue);
//                vis.getRequestTimeQueue().insert(now.getTime());
//                vis.setStime(now.getTime());
//                servletContext.setAttribute(sessionID, vis);
//            }
//            return true;
//        }
//        return true;
//    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        boolean b = feysx(request, response);
//        if (b == false) {
//            try {
//                ResponseBody bodyResponse = ResponseBody.instance(response);
//                bodyResponse.setSuccess(true);
//            } catch (IOException e) {
//                e.printStackTrace();
//                logger.error(e.getMessage());
//                return false;
//            }
//        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method m = handlerMethod.getMethod();

        /**
         * 以下生成TOKEN，防止重复提交
         * 原理：在新建页面中Session或memcache中保存token随机码，当保存时验证，通过后删除，当再次点击保存时由于服务器端的Session中已经不存在了，所有无法验证通过。
         *
         * 使用方法：
         *  1.新增的controller   action前增加一行： @AvoidDuplicateSubmission(needSaveToken = true) ，作用：生成token
         *  2.页面里form增加    <input type="hidden" name="token" value="$!{token}"> ，如果是ajax提交需将token参数传递过去
         *  3.保存的controller  action方法前增加一行： @AvoidDuplicateSubmission(needRemoveToken = true) ，作用：删除token
         */

        handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        AvoidDuplicateSubmission annotation = method.getAnnotation(AvoidDuplicateSubmission.class);
        if (annotation != null) {
            boolean needSaveSession = annotation.needSaveToken();
            if (needSaveSession && request.getSession() != null && request.getSession().getId() != null) {
                //TODO: 此处可放入Memcache
//                request.getSession(false).setAttribute("token", NumUtil.getInstance().generateToken(request.getSession().getId()));
            }

            boolean needRemoveSession = annotation.needRemoveToken();
            if (needRemoveSession) {
                if (isRepeatSubmit(request)) {
                    logger.warn("please don't repeat submit,[url:" + request.getServletPath() + "]");
                    return false;
                }
                request.getSession(false).removeAttribute("token");
            }
        }
        //end

//        NotRequireLogin notLogin = m.getAnnotation(NotRequireLogin.class);
//        if (notLogin != null)
//            return true;

//        String userInfo = UserSessionHolder.getSessionInfo().getJsonUserInfo();
//        if (StringUtils.isEmpty(userInfo))
//            throw new BizException(UMConstants.RespCode.PMS_NOT_LOGIN);
//        AuthorityModel loginUser = null;
//        try {
//            loginUser = (AuthorityModel) (new RemoteCacheClient().getCachedObject(Md5.MD5(userInfo, "UTF-8")));
//        } catch (Exception e1) {
//            logger.error("Failed to Get login user information from remote Memcache Service .. ");
//            e1.printStackTrace();
//        }
//
//        UserAuthData userAuthData = UserSessionHolder.getSessionInfo().getUserInfo();
//        if (loginUser == null) {
//            loginUser = getLoginInfoFromCache(loginUser, userInfo);
//        } else {
//            if (userAuthData.getUserGuid().intValue() != loginUser.getUserGuid().intValue()) {
//                loginUser = getLoginInfoFromCache(loginUser, userInfo);
//            }
//        }
//        if (loginUser != null && loginUser.getUserGuid() > 0) {
//            AuthorityModel.set(loginUser);
//            return true;
//        }
//        throw new RuntimeException("SSO登录异常 : sso-userInfo " + userInfo);
        return false;
    }

    private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession(false).getAttribute("token");
        if (serverToken == null) {
            return true;
        }
        String clinetToken = request.getParameter("token");
        if (clinetToken == null) {
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }

//    private AuthorityModel getLoginInfoFromCache(AuthorityModel loginUser, String userInfo) {
//
//        if (loginUser == null) {
//            loginUser = new AuthorityModel();
//            UserAuthData authData = JSON.parseObject(userInfo, UserAuthData.class);
//            loginUser.setLoginName(authData.getUserLogonId());
//            loginUser.setOrgId(authData.getEmpOrgId());
//            loginUser.setStatId(authData.getEmpStationID());
//            loginUser.setUserGuid(authData.getUserGuid());
//            loginUser.setIsHR(authData.getIsHR());
//            loginUser.setEmpId(authData.getEmpGuid());
//            try {
//                new RemoteCacheClient().cacheObjectTimeOut(Md5.MD5(userInfo, "UTF-8"), loginUser, 1000 * 60 * 30);//默认为30分钟
//            } catch (MalformedURLException e) {
//                logger.error("Failed to cache AuthorityModel for user : [" + loginUser.getLoginName() + "]");
//                e.printStackTrace();
//            }
//        }
//
//        return loginUser;
//    }
}
