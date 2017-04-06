package com.qi.sso.core.util;

import com.qi.common.security.EncrypterTool;
import com.qi.common.util.HexUtil;
import com.qi.sso.common.constants.SSOConstants;
import com.qi.sso.model.dto.UserToken;

/**
 * Class TokenUtil
 *
 * @author 张麒 2016/5/27.
 * @version Description:
 */
public class TokenUtil {

    /**
     * 加密存储动态密码的CacheID
     *
     * @param cacheKey 存储令牌解密Key的CacheID
     * @param salt     加密盐值
     * @return 返回加密后的字符串
     */
    public static String encodeCacheKey(String cacheKey, String salt) {
        return EncrypterTool.encrypt(cacheKey + SSOConstants.SPLIT_FLAG + HexUtil.getHashCode(), salt);
    }

    /**
     * 解密密存储动态密码的CacheID
     *
     * @param cacheKey 存储令牌解密 KEY的已加密索引
     * @param salt     加密盐值
     * @return 返回解密后的字符串
     */
    public static String decodeCacheKey(String cacheKey, String salt) {
        return EncrypterTool.decrypt(cacheKey, salt);
    }

    /**
     * 传入加密盐值和加密向量加密token值
     *
     * @param encrypted 需要加密的字符串
     * @param salt      加密盐值
     * @return 返回Token
     */
    public static String encodeToken(String encrypted, String salt) {
        return EncrypterTool.encrypt(encrypted + SSOConstants.SPLIT_FLAG + HexUtil.getHashCode(), salt);
    }

    /**
     * 传入加密盐值和加密向量解密token值
     *
     * @param decrypted 需要解密的字符串
     * @param salt      加密盐值
     * @return 返回解密后的字符串
     */
    public static String decodeToken(String decrypted, String salt) {
        return EncrypterTool.decrypt(decrypted, salt);
    }


    /**
     * 生成TokenKey_Cache_Key 用于存储密钥
     *
     * @param token UserToken
     * @param split 分隔符
     * @return TokenKey_Cache_Key
     */
    public static String buildCacheKey(UserToken token, String split) {
        return token.getUserID() + split + token.getSessionId();
    }


    /**
     * 根据TokenKey_Cache_Key生成UserToken
     *
     * @param cacheKey TokenKey_Cache_Key
     * @param split    分隔符
     * @return UserToken
     */
    public static UserToken buildUserTokenByCacheKey(String cacheKey, String split) {
        if (cacheKey == null || split == null) {
            return null;
        }
        String[] list = cacheKey.split(split);

        if (list.length < 2) {
            return null;
        }

        UserToken userToken = new UserToken();
        userToken.setUserID(list[0]);
        userToken.setSessionId(list[1]);
        userToken.setCacheKeyHashCode(list[2]);
        return userToken;
    }

    /**
     * 获得解密后的CacheKey 标识
     *
     * @param userToken  UserToken
     * @param split      分隔符
     * @param cacheIdKey CacheIdKey
     * @return UserToken
     */
    public static UserToken buildUserTokenByCacheKey(final UserToken userToken, String split, String cacheIdKey) {
        String cacheKey = decodeCacheKey(userToken.getTokenKey_Cache_Key(), cacheIdKey);
        return buildUserTokenByCacheKey(cacheKey, split);
    }

    /**
     * 生成token字符串
     *
     * @param token UserToken
     * @param split 分隔符
     * @return token字符串
     */
    public static String buildToken(UserToken token, String split) {
        return token.getLoginName() + split + token.getSessionId() + split + token.getUserID() + split + token.getLoginTimeStamp() + split + token.getLogoutTime();
    }

    /**
     * 根据token值生成UserToken
     *
     * @param token token值
     * @param split 分隔符
     * @return UserToken
     */
    public static UserToken buildUserTokenByToken(String token, String split) {
        if (token == null || split == null) {
            return null;
        }
        String[] list = token.split(split);

        if (list.length < 5) {
            return null;
        }

        UserToken userToken = new UserToken();
        userToken.setLoginName(list[0]);
        userToken.setSessionId(list[1]);
        userToken.setUserID(list[2]);
        userToken.setLoginTimeStamp(Long.parseLong(list[3]));
        userToken.setLogoutTime(Long.valueOf(list[4]));
        return userToken;
    }

    /**
     * 获得解密后的CacheKey 标识
     *
     * @param userToken UserToken
     * @param split     分隔符
     * @param cacheKey  CacheKey
     * @return UserToken
     */
    public static UserToken buildUserTokenByToken(final UserToken userToken, String split, String cacheKey) {
        String token = TokenUtil.decodeToken(userToken.getToken(), cacheKey);
        return buildUserTokenByToken(token, split);
    }
}
