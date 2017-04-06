package com.qi.sso.auth.filter;

import com.alibaba.fastjson.JSON;
import com.qi.common.constants.CommonConstants;
import com.qi.common.http.ResponseContent;
import com.qi.common.http.synch.HttpHelper;
import com.qi.common.security.EncrypterTool;
import com.qi.common.tool.Assert;
import com.qi.common.tool.Cookies;
import com.qi.common.util.*;
import com.qi.sso.auth.util.SSOVerifyUtil;
import com.qi.sso.common.constants.SSOConstants;
import com.qi.sso.auth.util.UserSessionHolder;
import com.qi.sso.common.util.UserTokenUtil;
import com.qi.sso.model.dto.SessionInfo;
import com.qi.sso.model.dto.UserToken;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * Class SSOVerifyFilter
 *
 * @author 张麒 2016/6/13.
 * @version Description:
 */
public class SSOVerifyFilter implements Filter {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private Pattern[] excludeUrlPatterns;

    private String checkUrlPrefix;

    private String loginUrlPrefix;

    private void initUrl(FilterConfig filterConfig) {
        try {
            String path = (System.getProperty(CommonConstants.RESOURCES_CONFIG_PATH) == null ? System.getenv(CommonConstants.RESOURCES_CONFIG_PATH) : System.getProperty(CommonConstants.RESOURCES_CONFIG_PATH)) + "common.properties";
            Properties prop = new Properties();
            prop.load(new FileInputStream(path));
            this.loginUrlPrefix = prop.getProperty("sso.login.url");
            this.checkUrlPrefix = prop.getProperty("sso.check.url");
        } catch (Exception e) {
            logger.error("读取sso配置文件错误", e);
        }

        if (StringUtil.isBlank(this.loginUrlPrefix)) {
            this.loginUrlPrefix = filterConfig.getInitParameter(SSOVerifyUtil.LOGIN_URL_PREFIX);
        }
        if (StringUtil.isBlank(this.checkUrlPrefix)) {
            this.checkUrlPrefix = filterConfig.getInitParameter(SSOVerifyUtil.CHECK_URL_PREFIX);
        }

        Assert.isNotBlank(this.loginUrlPrefix, "loginUrlPrefix must be set.");
        Assert.isNotBlank(this.checkUrlPrefix, "checkUrlPrefix must be set.");

        if (this.loginUrlPrefix.endsWith("/"))
            this.loginUrlPrefix = this.loginUrlPrefix.substring(0, this.loginUrlPrefix.length() - 1);
        if (this.checkUrlPrefix.endsWith("/"))
            this.checkUrlPrefix = this.checkUrlPrefix.substring(0, this.checkUrlPrefix.length() - 1);
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        initUrl(filterConfig);

        String excludeRegex = filterConfig.getInitParameter(SSOVerifyUtil.EXCLUDE_PATTERN);
        if (StringUtil.isNotBlank(excludeRegex)) {
            String[] excludeReg = excludeRegex.split(",");
            if (ArrayUtil.isNotEmpty(excludeReg)) {
                this.excludeUrlPatterns = new Pattern[excludeReg.length];
                for (int i = 0; i < excludeReg.length; i++) {
                    this.excludeUrlPatterns[i] = Pattern.compile(excludeReg[i]);
                }
            }
        }
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 排除请求路径验证
        if (SSOVerifyUtil.excludeVerify(request.getServletPath(), excludeUrlPatterns)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }

        Cookies cookies = new Cookies(request, response);

        final UserToken ut = UserTokenUtil.getUserCookiesToken(cookies);

        if (ut != null) {
            logger.debug("Attempting to validate ticket: {}", ut);
            try {
                ResponseContent rc = getResponseContent(ut);
                if (rc != null && rc.getStatusCode() == HttpStatus.SC_OK) {
                    logger.info("UserToken验证结果：" + rc);
                    String utJson = rc.getUTFContent();
                    if (StringUtil.isNotBlank(utJson) && !utJson.equals("{}")) {
                        UserToken rut = JSON.parseObject(utJson, UserToken.class);
                        logger.info("UserToken信息：" + rut);
                        // 操作结果标识为空
                        if (null == rut.getOperatorResult()) {
                            logger.error("UserToken验证失败，失败原因：UserToken.operatorResult错误[" + rut + "]");
                        }
                        // 登录成功
                        else if (!Objects.equals(rut.getOperatorResult(), SSOConstants.OperatorResult.FAIL.getKey())) {
                            if (Objects.equals(rut.getOperatorResult(), SSOConstants.OperatorResult.SUCCESS.getKey()))
                                logger.info("UserToken验证成功，UserToken {" + rut + "}");
                            if (Objects.equals(rut.getOperatorResult(), SSOConstants.OperatorResult.REWRITE.getKey())) {
                                UserTokenUtil.updateToken(cookies, rut);
                                logger.info("UserToken验证成功，重新生成Token {" + rut + "}");
                            }
                            SessionInfo session = new SessionInfo();
                            session.setLoginName(rut.getLoginName());
                            session.setJsonUserInfo(rut.getSessionInfoData());
                            logger.debug("Successfully authenticated user: {}", rut.getLoginName());
                            request.setAttribute(SSOConstants.CONST_UAMS_ASSERTION, session);
                            if (SSOConstants.USE_SESSION) {
                                request.getSession().setAttribute(SSOConstants.CONST_UAMS_ASSERTION, session);
                            }
                            try {
                                UserSessionHolder.setSessionInfo(session);
                                filterChain.doFilter(servletRequest, servletResponse);
                                return;
                            } finally {
                                UserSessionHolder.clear();
                            }
                        } else {
                            logger.info("UserToken验证失败，失败原因：用户登录超时[" + rut + "]");
                        }
                    } else {
                        logger.error("UserToken验证失败，失败原因：ResponseContent.Content为空[" + utJson + "]");
                    }
                } else {
                    logger.error("UserToken验证失败，失败原因：" + (rc == null ? "返回对象ResponseContent为空" : "statusCode状态错误，ResponseContent：") + rc);
                }
            } catch (final Exception e) {
                logger.debug("UserToken验证报错，错误信息：" + ThrowableUtil.getRootMessage(e), e);
                response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
                return;
            }
        }
        //to login page
        String form_url = HttpUtil.getFullRequestURI(request);
        // 嵌套form_url处理
        if (form_url.contains(SSOConstants.PARAM_FROM_URL)) {
            form_url = form_url.substring(form_url.indexOf(SSOConstants.PARAM_FROM_URL + "="), form_url.length());
        }
        Boolean ajax = false;
        // 如果是Ajax请求，获取上一步的请求路径
        if (HttpUtil.isAjax(request)) {
            form_url = request.getHeader("Referer");
            ajax = true;
        }
        final String loginUrl = this.loginUrlPrefix + SSOVerifyUtil.LOGIN_SERVER + "?" + SSOConstants.PARAM_FROM_URL + "=" + EncrypterTool.encrypt(EncrypterTool.Security.Des3ECB, form_url);
        ResponseUtil.setNoCacheHeaders(response);
        PrintWriter out = response.getWriter();
        if (ajax) {
            if (SSOConstants.IS_IFRAME)
                out.write("<script>window.parent.location.href='" + loginUrl + "';</script>");
            else
                response.sendRedirect(loginUrl);
        } else if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            Map<String, String> map = MapUtil.getInstance();
            map.put(SSOConstants.PARAM_FROM_URL, this.loginUrlPrefix + SSOVerifyUtil.LOGIN_SERVER);
            map.put("sessionState", SSOConstants.SESSION_TIME_OUT);
            ResponseUtil.writeJson(map, response);
        } else if (SSOConstants.IS_IFRAME) {
            out.write("<script>window.parent.location.href='" + loginUrl + "';</script>");
        } else {
            response.sendRedirect(loginUrl);
        }
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    private ResponseContent getResponseContent(UserToken ut) {
        final String jsonBody = JSON.toJSONString(ut);
        final String url = this.checkUrlPrefix + SSOVerifyUtil.CHECK_SERVER;
        ResponseContent rc = HttpHelper.postDefEntity(url, SSOConstants.UAMS_CHECK_PARAM_NAME, jsonBody);
        if (rc == null) {
            int i = 3;
            while (null == rc && i > 0) {
                rc = HttpHelper.postDefEntity(url, SSOConstants.UAMS_CHECK_PARAM_NAME, jsonBody);
                i--;
            }
        }
        return rc;
    }
}
