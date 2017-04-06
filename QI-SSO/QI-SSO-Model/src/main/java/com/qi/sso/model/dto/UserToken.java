package com.qi.sso.model.dto;

import java.io.Serializable;

/**
 * 用户COOKIES的标记类
 */
public class UserToken implements Serializable {

    private static final long serialVersionUID = -723299916104448754L;

    //登录名
    private String loginName;

    //客户端CookieName
    private String sessionId;

    //session 用户信息
    private String sessionInfoData;

    //磁盘分区标识
    private String space;

    //用户编号
    private String userID;

    //Token串
    private String token;

    //Token解密Key
    private String token_Key;

    //Token解密Key 存储的 索引Key
    private String tokenKey_Cache_Key;

    //内存过期时间
    private Long logoutTime;

    //登录时间
    private Long loginTimeStamp;

    //返回代码
    private String operatorCode;

    private String cacheKeyHashCode;


    public String getCacheKeyHashCode() {
        return cacheKeyHashCode;
    }

    public void setCacheKeyHashCode(String cacheKeyHashCode) {
        this.cacheKeyHashCode = cacheKeyHashCode;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    /**
     * 操作结果标识 0= 失败， 1 = 成功，2=须重新写Cookies
     */
    private Integer operatorResult;

    public String getSpace() {
        return this.space;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken_Key() {
        return this.token_Key;
    }

    public void setToken_Key(String tokenKey) {
        this.token_Key = tokenKey;
    }

    public String getTokenKey_Cache_Key() {
        return this.tokenKey_Cache_Key;
    }

    public void setTokenKey_Cache_Key(String tokenKeyCacheKey) {
        this.tokenKey_Cache_Key = tokenKeyCacheKey;
    }

    public Long getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Long logoutTime) {
        this.logoutTime = logoutTime;
    }


    /**
     * 操作结果标识 0= 失败， 1 = 成功，2=须重新写Cookies
     */
    public Integer getOperatorResult() {
        return this.operatorResult;
    }

    /**
     * 操作结果标识 0= 失败， 1 = 成功，2=须重新写Cookies
     */
    public void setOperatorResult(Integer operatorResult) {
        this.operatorResult = operatorResult;
    }


    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Long getLoginTimeStamp() {
        return this.loginTimeStamp;
    }

    public void setLoginTimeStamp(Long loginTimeStamp) {
        this.loginTimeStamp = loginTimeStamp;
    }


    public String getSessionInfoData() {
        return sessionInfoData;
    }

    public void setSessionInfoData(String sessionInfoData) {
        this.sessionInfoData = sessionInfoData;
    }


    public String toString() {
        return "loginName[" + loginName + "]," +
                "sessionId[" + sessionId + "]," +
                "space[" + space + "]," +
                "userID[" + userID + "]," +
                "token[" + token + "]," +
                "CacheKey[" + tokenKey_Cache_Key + "]," +
                "operatorCode[" + operatorCode + "]," +
                "token_Key[" + token_Key + "]," +
                "operatorResult[" + operatorResult + "],";
    }

    public void refreshTimeStamp() {
        this.loginTimeStamp = System.currentTimeMillis();
    }
}
