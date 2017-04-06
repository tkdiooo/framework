package com.qi.sso.core.util;

import com.qi.common.tool.Logger;
import com.qi.sso.common.constants.SSOConstants;
import com.qi.sso.core.common.constants.MemcacheConstants;
import com.qi.sso.model.dto.UserToken;

/**
 * Class ReturnUtil
 *
 * @author 张麒 2016/5/27.
 * @version Description:
 */
public class ReturnUtil {

    private static final Logger logger = new Logger(ReturnUtil.class);

    /**
     * 登录统一返回对象值
     *
     * @param token          Token
     * @param tokenKey       加密盐值
     * @param cacheKey       加密盐值的Key
     * @param operatorResult 返回操作结果
     * @param operatorCode   返回操作代码
     * @return UserToken
     */
    public static UserToken buildLoginToken(final String token, final String tokenKey, final String cacheKey, final int operatorResult, final String operatorCode) {
        UserToken userToken = new UserToken();
        userToken.setToken(token);
        userToken.setToken_Key(tokenKey);
        userToken.setTokenKey_Cache_Key(cacheKey);
        userToken.setOperatorResult(operatorResult);
        userToken.setOperatorCode(operatorCode);
        logger.info("构建Login UserToken对象：{" + userToken + "}。");
        return userToken;
    }

    /**
     * 验证统一返回对象值
     *
     * @param userID         用户登录编号
     * @param sessionId      客户端CookieName
     * @param loginName      用户登录名称
     * @param token          已加密Ticket串
     * @param tokenKey       加密Ticket用到的动态Key
     * @param cacheKey       缓存动态Key的加密值
     * @param operatorResult 返回操作结果
     * @param operatorCode   返回操作代码
     * @return UserToken
     */
    public static UserToken buildCheckToken(final String userID, final String sessionId, final String loginName, final String token, final String tokenKey, final String cacheKey, final int operatorResult, final String operatorCode) {
        UserToken userToken = new UserToken();
        userToken.setLoginName(loginName);
        userToken.setUserID(userID);
        userToken.setSessionId(sessionId);
        userToken.setToken(token);
        userToken.setToken_Key(tokenKey);
        userToken.setTokenKey_Cache_Key(cacheKey);
        userToken.setOperatorResult(operatorResult);
        userToken.setOperatorCode(operatorCode);
        logger.info("构建Check UserToken对象：{" + userToken + "}。");
        return userToken;
    }

    /**
     * 登出统一返回对象值
     *
     * @param userID         用户登录编号
     * @param sessionID      客户端CookieName
     * @param operatorResult 返回操作结果
     * @param operatorCode   返回操作代码
     * @return UserToken
     */
    public static UserToken buildLogoutToken(final String userID, final String sessionID, final int operatorResult, final String operatorCode) {
        UserToken userToken = new UserToken();
        userToken.setOperatorResult(operatorResult);
        userToken.setOperatorCode(operatorCode);
        userToken.setUserID(userID);
        userToken.setSessionId(sessionID);
        logger.info("构建Logout UserToken对象：{" + userToken + "}。");
        return userToken;
    }

    private final static String XML_HEAD = "<?xml version= \"1.0\" encoding= \"UTF-8\"?>";
    private final static String XML_DOM_H = "<uams>";
    private final static String XML_DOM_E = "</uams>";

    /**
     * 登录统一返回XML值
     *
     * @param token          已加密Ticket串
     * @param tokenKey       加密Ticket用到的动态Key
     * @param cacheKey       缓存动态Key的加密值
     * @param operatorResult 返回操作结果
     * @param operatorCode   返回操作代码
     * @return
     */
    public static String returnLoginTokenToXml(final String token, final String tokenKey, final String cacheKey, final int operatorResult, final String operatorCode) {

        StringBuffer strBuffer = new StringBuffer();

        strBuffer.append(XML_HEAD);
        strBuffer.append(XML_DOM_H);

        strBuffer.append("<token>");
        strBuffer.append(token);
        strBuffer.append("</token>");

        strBuffer.append("<token_Key><![CDATA[");
        strBuffer.append(tokenKey);
        strBuffer.append("]]></token_Key>");

        strBuffer.append("<tokenKey_Cache_Key>");
        strBuffer.append(cacheKey);
        strBuffer.append("</tokenKey_Cache_Key>");


        strBuffer.append("<operatorResult>");
        strBuffer.append(operatorResult);
        strBuffer.append("</operatorResult>");


        strBuffer.append("<operatorCode>");
        strBuffer.append(operatorCode);
        strBuffer.append("</operatorCode>");
        strBuffer.append(XML_DOM_E);


        return strBuffer.toString();
    }

    /**
     * 验证统一返回XML值
     *
     * @param token          已加密Ticket串
     * @param tokenKey       加密Ticket用到的动态Key
     * @param cacheKey       缓存动态Key的加密值
     * @param operatorResult 返回操作结果
     * @param operatorCode   返回操作代码
     * @param userID         用户登录编号
     * @param sessionId
     * @param loginName      用户登录名称
     * @return
     */
    public static String returnCheckTokenToXml(final String userID, final String sessionId, final String loginName, final String token, final String tokenKey, final String cacheKey, final int operatorResult, final String operatorCode) {

        StringBuffer strBuffer = new StringBuffer();

        strBuffer.append(XML_HEAD);
        strBuffer.append(XML_DOM_H);

        strBuffer.append("<loginName>");
        strBuffer.append(loginName);
        strBuffer.append("</loginName>");

        strBuffer.append("<sessionId>");
        strBuffer.append(sessionId);
        strBuffer.append("</sessionId>");

        strBuffer.append("<userID>");
        strBuffer.append(userID);
        strBuffer.append("</userID>");


        strBuffer.append("<token>");
        strBuffer.append(token == null ? "" : token);
        strBuffer.append("</token>");

        strBuffer.append("<token_Key><![CDATA[");
        strBuffer.append(tokenKey);
        strBuffer.append("]]></token_Key>");

        strBuffer.append("<tokenKey_Cache_Key>");
        strBuffer.append(cacheKey == null ? "" : cacheKey);
        strBuffer.append("</tokenKey_Cache_Key>");


        strBuffer.append("<operatorResult>");
        strBuffer.append(operatorResult);
        strBuffer.append("</operatorResult>");


        strBuffer.append("<operatorCode>");
        strBuffer.append(operatorCode);
        strBuffer.append("</operatorCode>");
        strBuffer.append(XML_DOM_E);


        return strBuffer.toString();
    }

    /**
     * 登出统一返回XML值
     *
     * @param operatorResult 返回操作结果
     * @param operatorCode   返回操作代码
     * @return
     */
    public static String returnLogoutTokenToXml(final String userID, final String sessionID, final int operatorResult, final String operatorCode) {

        StringBuffer strBuffer = new StringBuffer();

        strBuffer.append(XML_HEAD);
        strBuffer.append(XML_DOM_H);

        strBuffer.append("<sessionId>");
        strBuffer.append(sessionID);
        strBuffer.append("</sessionId>");

        strBuffer.append("<userID>");
        strBuffer.append(userID);
        strBuffer.append("</userID>");

        strBuffer.append("<operatorResult>");
        strBuffer.append(operatorResult);
        strBuffer.append("</operatorResult>");


        strBuffer.append("<operatorCode>");
        strBuffer.append(operatorCode);
        strBuffer.append("</operatorCode>");
        strBuffer.append(XML_DOM_E);


        return strBuffer.toString();
    }
}
