package com.qi.sso.common.util;

import com.qi.common.tool.Cookies;
import com.qi.common.tool.Logger;
import com.qi.common.util.StringUtil;
import com.qi.common.util.ThrowableUtil;
import com.qi.sso.common.config.CookiesConfig;
import com.qi.sso.common.constants.SSOConstants;
import com.qi.sso.model.dto.UserToken;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Class UserTokenUtil
 *
 * @author 张麒 2016/6/14.
 * @version Description:
 */
public class UserTokenUtil {

    private static final Logger logger = new Logger(UserTokenUtil.class);

    /**
     * 根据Cookie获取UserToken对象
     *
     * @param cookies
     * @return
     */
    public static UserToken getUserCookiesToken(Cookies cookies) {
        String tokenVal = cookies.getCookieValue(SSOConstants.COOKIE_USER_TOKEN_NAME);
        String tokenKeyCacheKey = cookies.getCookieValue(SSOConstants.COOKIE_USER_TOKEN_KEY_CACHENAME);
        if (StringUtil.isNotBlank(tokenVal) && StringUtil.isNotBlank(tokenKeyCacheKey)) {
            try {
                UserToken userToken = new UserToken();
                userToken.setToken(URLDecoder.decode(tokenVal, SSOConstants.UTF_8));
                userToken.setTokenKey_Cache_Key(URLDecoder.decode(tokenKeyCacheKey, SSOConstants.UTF_8));
                return userToken;
            } catch (UnsupportedEncodingException e) {
                clearUserToken(cookies);
                logger.error(ThrowableUtil.getRootMessage(e));
            }
        }
        return null;
    }

    /**
     * 根据UserToken更新Cookie
     *
     * @param cookies Cookies
     * @param ut      UserToken
     */
    public static void updateToken(Cookies cookies, UserToken ut) {
        updateToken(cookies, SSOConstants.COOKIE_USER_TOKEN_NAME, SSOConstants.COOKIE_USER_TOKEN_KEY_CACHENAME, ut);
    }

    /**
     * 根据UserToken更新Cookie
     *
     * @param cookies      Cookies
     * @param tokenKey     COOKIE_USER_TOKEN_NAME
     * @param tokenSaltKey COOKIE_USER_TOKEN_KEY_CACHENAME
     * @param ut           UserToken
     */
    public static void updateToken(Cookies cookies, String tokenKey, String tokenSaltKey, UserToken ut) {
        try {
            cookies.setCookie(tokenKey, URLEncoder.encode(ut.getToken(), SSOConstants.UTF_8), CookiesConfig.getInstance().getLoginTimeOut(), CookiesConfig.getInstance().getDomain());
            cookies.setCookie(tokenSaltKey, URLEncoder.encode(ut.getTokenKey_Cache_Key(), SSOConstants.UTF_8), CookiesConfig.getInstance().getLoginTimeOut(), CookiesConfig.getInstance().getDomain());
            logger.info("set cookies token value [domain:" + CookiesConfig.getInstance().getDomain() + " Token_Key:" + ut.getToken() + " Token_Salt_Key:" + ut.getTokenKey_Cache_Key() + "]");
        } catch (UnsupportedEncodingException e) {
            clearUserToken(cookies);
            logger.error(ThrowableUtil.getRootMessage(e));
        }
    }

    /**
     * 清除UserToken Cookie缓存
     *
     * @param cookies
     */
    public static void clearUserToken(Cookies cookies) {
        cookies.remove(SSOConstants.COOKIE_USER_TOKEN_NAME, CookiesConfig.getInstance().getDomain());
        cookies.remove(SSOConstants.COOKIE_USER_TOKEN_KEY_CACHENAME, CookiesConfig.getInstance().getDomain());
    }
}
