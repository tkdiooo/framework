package com.qi.sso.core.common.constants;


import com.qi.common.tool.Logger;
import com.qi.sso.common.constants.SSOConstants;
import com.qi.sso.model.dto.UserToken;

public class MemcacheConstants {

    private static final Logger logger = new Logger(MemcacheConstants.class);

//    public final static Long SECOND = 1L;
//
//    public final static Long MINUTE = 60L;
//
//    public final static Long HOUR = (long) (60 * 60);
//
//    public final static Long DAY = (long) (60 * 60 * 24);
//
//    public final static Long WEEK = (long) (60 * 60 * 24 * 7);

    // 存储用户TOKEN 内容 头部标识
    public final static String UAMS_USER_TOKEN = "UAMST";

    // 存储用户TOKEN 解密字符串头部标识
    public final static String UAMS_USER_TOKEN_KEY = "UAMSTK";

    public final static String UAMS_USER_SESSION_KEY = "uams_session_";


    /**
     * 存储Token的Key标识生成
     */
    public static String getToken_Key(final UserToken userToken) {
        return MemcacheConstants.UAMS_USER_TOKEN + SSOConstants.SPLIT_FLAG + userToken.getSessionId() + SSOConstants.SPLIT_FLAG + userToken.getUserID();
    }

    /**
     * 存储Token的动态密码的Key标识生成
     */
    public static String getToken_encryptKey_key(final UserToken userToken) {
        logger.info("用户：" + userToken.getLoginName() + " 生成 encrypt_cache_key[" + MemcacheConstants.UAMS_USER_TOKEN_KEY + SSOConstants.SPLIT_FLAG + userToken.getSessionId() + SSOConstants.SPLIT_FLAG + userToken.getUserID() + SSOConstants.SPLIT_FLAG + userToken.getCacheKeyHashCode() + "]。");
        return MemcacheConstants.UAMS_USER_TOKEN_KEY + SSOConstants.SPLIT_FLAG + userToken.getSessionId() + SSOConstants.SPLIT_FLAG + userToken.getUserID() + SSOConstants.SPLIT_FLAG + userToken.getCacheKeyHashCode();
    }

    //登录会话保存最长时间
//    public static long getLoginOutTime(long time) {
//        return (time * 2);
//    }
//
//    public static String getTokenKeyCachekey(final UserToken userToken) {
//        return userToken.getSessionId() + SSOConstants.splitFlag + userToken.getUserID();
//    }


}
