package com.qi.sso.core.authentication;

import com.qi.common.tool.Cookies;
import com.qi.common.tool.Logger;
import com.qi.sso.common.config.CookiesConfig;
import com.qi.sso.common.util.UserTokenUtil;
import com.qi.sso.core.client.SSOClientFactory;
import com.qi.sso.model.dto.UserToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

/**
 * Class AuthenticationHelper
 *
 * @author 张麒 2016/5/25.
 * @version Description:
 */
public class AuthenticationHelper {

    private static final Logger logger = new Logger(AuthenticationHelper.class);

    private Cookies cookies;
    private CookiesConfig cookiesConfig;

    public AuthenticationHelper(HttpServletRequest request, HttpServletResponse response) {
        this.cookies = new Cookies(request, response);
        this.cookiesConfig = CookiesConfig.getInstance();
    }

    public AuthenticationHelper(HttpServletRequest request, HttpServletResponse response, String cookieFilePath) {
        this.cookies = new Cookies(request, response);
        this.cookiesConfig = CookiesConfig.getInstance(cookieFilePath);
    }

    public AuthenticationHelper(HttpServletRequest request, HttpServletResponse response, Properties config) {
        this.cookies = new Cookies(request, response);
        this.cookiesConfig = CookiesConfig.getInstance(config);
    }

    public void setCookie(String key, String value) {
        cookies.setCookie(key, value, cookiesConfig.getLoginTimeOut(), cookiesConfig.getDomain());
    }

    public void setCookie(String key, String value, int expire) {
        cookies.setCookie(key, value, expire, cookiesConfig.getDomain());
    }

    public String getCookie(String key) {
        return cookies.getCookieValue(key);
    }

    public void clearCookie(String key) {
        cookies.remove(key, cookiesConfig.getDomain());
    }

    /**
     * 构建Token
     *
     * @param account     账号
     * @param accountGuid 账号Guid
     * @param sessionId   Session ID
     * @param sessionData Session Data
     * @throws Exception
     */
    public void buildToken(String account, String accountGuid, String sessionId, String sessionData) throws Exception {
        UserToken ut = new UserToken();
        ut.setLoginName(account);
        ut.setUserID(accountGuid);
        ut.setSessionId(sessionId);
        ut.setSessionInfoData(sessionData);
        UserToken rut = SSOClientFactory.getSSOClient().ssoLogin(ut);
        logger.info("用户：" + ut.getLoginName() + "登录结果 UserToken{" + rut + "}。");
        if (rut != null && rut.getOperatorResult() == 1) {
            UserTokenUtil.updateToken(cookies, rut);
        }
    }

    /**
     * 验证Token
     *
     * @return Boolean
     * @throws Exception
     */
    public boolean checkToken() throws Exception {
        UserToken ut = getUserCookiesToken();
        if (ut == null) {
            logger.warn("do not find cookies token!");
            return false;
        }
        UserToken rut = SSOClientFactory.getSSOClient().ssoCheck(ut);
        if (rut != null && rut.getOperatorResult() == 1) {
            UserTokenUtil.updateToken(cookies, rut);
            return true;
        }
        return false;
    }

    /**
     * 验证Token
     *
     * @param tokenKey tokenKey
     * @param token    token
     * @return Boolean
     * @throws Exception
     */
    public boolean checkToken(String tokenKey, String token) throws Exception {
        UserToken ut = getUserCookiesToken();
        if (ut == null) {
            logger.warn("do not find cookies token!");
            return false;
        }
        UserToken rut = SSOClientFactory.getSSOClient().ssoCheck(ut);
        if (rut != null && rut.getOperatorResult() == 1) {
            UserTokenUtil.updateToken(cookies, tokenKey, token, rut);
            return true;
        }
        return false;
    }

    /**
     * 获取UserToken
     *
     * @return UserToken
     * @throws Exception
     */
    public UserToken getTokenInfo() throws Exception {
        UserToken ut = getUserCookiesToken();
        if (ut == null) {
            return null;
        }
        return SSOClientFactory.getSSOClient().ssoCheck(ut);
    }

    /**
     * 销毁Token
     *
     * @throws Exception
     */
    public void destroyToken() throws Exception {
        UserToken ut = getUserCookiesToken();
        if (ut != null) {
            SSOClientFactory.getSSOClient().ssoLogout(ut);
        }
        UserTokenUtil.clearUserToken(cookies);
    }

    private UserToken getUserCookiesToken() {
        return UserTokenUtil.getUserCookiesToken(cookies);
    }
//
//    private void updateToken(UserToken ut) {
//        UserTokenUtil.updateToken(cookies, ut);
//
//    }

//    private void updateToken(String tokenKey, String token, UserToken ut) {
//        try {
//            cookies.setCookie(token, URLEncoder.encode(ut.getToken(), "UTF-8"), -1, cookiesConfig.getDomain());
//            cookies.setCookie(tokenKey, URLEncoder.encode(ut.getTokenKey_Cache_Key(), "UTF-8"), -1, cookiesConfig.getDomain());
//            logger.info("set cookies token value [domain:" + cookiesConfig.getDomain() + " tokenKey:" + tokenKey + ":" + ut.getToken() + "]");
//        } catch (UnsupportedEncodingException e) {
//            clearUserToken();
//            logger.error(e.getMessage());
//        }
//    }

//    private void clearUserToken() {
//        clearCookie(SSOConstants.COOKIE_USER_TOKEN_NAME);
//        clearCookie(SSOConstants.COOKIE_USER_TOKEN_KEY_CACHENAME);
//    }
}
