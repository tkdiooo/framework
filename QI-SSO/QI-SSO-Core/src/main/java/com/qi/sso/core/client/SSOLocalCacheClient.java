package com.qi.sso.core.client;

import com.qi.common.cache.ehcache.CacheFactory;
import com.qi.common.cache.ehcache.inf.CacheOperator;
import com.qi.common.tool.Logger;
import com.qi.common.util.StringUtil;
import com.qi.sso.core.common.config.ClientConfig;
import com.qi.sso.core.common.config.ToKenConfig;
import com.qi.sso.core.common.constants.MemcacheConstants;
import com.qi.sso.common.constants.SSOConstants;
import com.qi.sso.core.util.ReturnUtil;
import com.qi.sso.core.util.TokenUtil;
import com.qi.sso.core.util.VerifyUtil;
import com.qi.sso.model.dto.UserToken;

import java.io.IOException;

/**
 * Class SSOLocalCacheClient
 *
 * @author 张麒 2016/5/27.
 * @version Description:
 */
public class SSOLocalCacheClient implements SSOClient {

    private static final Logger logger = new Logger(SSOLocalCacheClient.class);

    private SSOClient client;
    private CacheOperator cache;

    public SSOLocalCacheClient(final SSOClient client) throws IOException {
        this.client = client;
        this.cache = CacheFactory.getInstance(ClientConfig.getInstance().getCacheVO());
    }

    @Override
    public UserToken ssoLogin(final UserToken token) throws Exception {
        UserToken result;
        try {
            //验证数据合法性
            if (!VerifyUtil.verifyLogin(token)) {
                logger.error("用户登录失败：loginName[" + token.getLoginName() + "] userID[" + token.getUserID() + "] sessionID[" + token.getSessionId() + "] 校验登录数据无法通过。");
                return ReturnUtil.buildLoginToken("", "", "", 0, "3000001");
            }

            logger.info("用户登录操作开始。");
            // 单点登录
            result = client.ssoLogin(token);

            if (result.getOperatorResult() == 0) {
                logger.error("用户登录失败：loginName[" + token.getLoginName() + "] userID[" + token.getUserID() + "] sessionID[" + token.getSessionId() + "] 无法返回登录结果。");
                return ReturnUtil.buildLoginToken("", "", "", 0, "3000002");
            }

            logger.info("用户登录正常：结果为：" + result.toString());

            // 添加本地缓存
            if (ClientConfig.getInstance().isCache()) {
                UserToken userTokenCacheKey = TokenUtil.buildUserTokenByCacheKey(result, SSOConstants.SPLIT_FLAG, ToKenConfig.getInstance().getCacheIdKey());
                // 储存Token的内存key
                String token_ehcache_key = MemcacheConstants.getToken_Key(userTokenCacheKey);
                // 储存加密的内存key
                String encrypt_ehcache_key = MemcacheConstants.getToken_encryptKey_key(userTokenCacheKey);
                cache.putObjectToCache(token_ehcache_key, result.getToken());
                cache.putObjectToCache(encrypt_ehcache_key, result.getToken_Key());
            }
            return ReturnUtil.buildLoginToken(result.getToken(), result.getToken_Key(), result.getTokenKey_Cache_Key(), result.getOperatorResult(), result.getOperatorCode());
        } catch (Exception e) {
            logger.error("用户登录失败：loginName[" + token.getLoginName() + "] userID[" + token.getUserID() + "] sessionID[" + token.getSessionId() + "] 登录失败。", e);
            return ReturnUtil.buildLoginToken("", "", "", 0, "3000002");
        }
    }

    @Override
    public UserToken ssoLogout(final UserToken token) throws Exception {
        UserToken result;
        try {
            //验证数据合法性
            if (!VerifyUtil.verifyToken(token)) {
                logger.error("用户登出失败：token[" + token.getToken() + "],tokenKey[" + token.getTokenKey_Cache_Key() + "] 数据校验无法通过。");
                return ReturnUtil.buildLogoutToken("", "", 0, "4000001");
            }

            logger.info("用户登出操作开始。");
            result = client.ssoLogout(token);

            if (result.getOperatorResult() == 0) {
                logger.error("用户登出失败：token[" + token.getToken() + "],tokenKey[" + token.getTokenKey_Cache_Key() + "] 服务器无法返回结果。");
                return ReturnUtil.buildLogoutToken("", "", 0, "4000002");
            }
            logger.info("用户登出操作结束。返回结果[" + result.toString() + "]");

            //清除本地缓存
            if (ClientConfig.getInstance().isCache()) {
                UserToken userTokenCacheKey = TokenUtil.buildUserTokenByCacheKey(token, SSOConstants.SPLIT_FLAG, ToKenConfig.getInstance().getCacheIdKey());
                clearCache(userTokenCacheKey);
            }

            return ReturnUtil.buildLogoutToken(result.getUserID(), result.getSessionId(), result.getOperatorResult(), result.getOperatorCode());
        } catch (Exception e) {
            logger.error("用户登出失败：token[" + token.getToken() + "],tokenKey[" + token.getTokenKey_Cache_Key() + "] 用户登出出现未知异常。", e);
            return ReturnUtil.buildLogoutToken("", "", 0, "4000002");
        }
    }

    @Override
    public UserToken ssoCheck(final UserToken token) throws Exception {
        try {
            logger.info("用户校验：TOKEN [" + token.getToken() + "], cacheKey [" + token.getTokenKey_Cache_Key() + "] 开始校验。");
            //1、验证数据合法性
            if (!VerifyUtil.verifyToken(token)) {
                logger.error("用户校验失败：token[" + token.getToken() + "],tokenKey[" + token.getTokenKey_Cache_Key() + "] 数据校验无法通过。");
                return ReturnUtil.buildCheckToken("", "", "", "", "", "", 0, "5000001");
            }

            logger.info("用户校验：TOKEN [" + token.getToken() + "], cacheKey [" + token.getTokenKey_Cache_Key() + "] 开始转换 userTokenCacheKey 对象。");
            //2、转换对象
            UserToken userTokenCacheKey = TokenUtil.buildUserTokenByCacheKey(token, SSOConstants.SPLIT_FLAG, ToKenConfig.getInstance().getCacheIdKey());
            if (userTokenCacheKey == null) {
                logger.error("用户校验失败：{" + token.toString() + "} 无法把CacheKey 转换成对象。");
                return ReturnUtil.buildCheckToken("", "", "", "", "", "", 0, "5000002");
            }

            logger.info("用户校验：TOKEN [" + token.getToken() + "], cacheKey [" + token.getTokenKey_Cache_Key() + "] userID:["
                    + userTokenCacheKey.getUserID()
                    + "] SessionID:[" + userTokenCacheKey.getSessionId() + "]" +
                    " 已转换转换 userTokenCacheKey 对象。");

            //3、获取储存Token的内存Key 地址
            String key = MemcacheConstants.getToken_Key(userTokenCacheKey);
            String cache_key = MemcacheConstants.getToken_encryptKey_key(userTokenCacheKey);
            UserToken tokenObj;


            logger.info("用户校验：key [" + key + "], cache_key [" + cache_key + "] userID:["
                    + userTokenCacheKey.getUserID()
                    + "] SessionID:[" + userTokenCacheKey.getSessionId() + "]" +
                    " 已转换转换 userTokenCacheKey 对象。");

            //4、判断客户端是否开启缓存
            if (ClientConfig.getInstance().isCache()) {

                logger.info("用户校验：userID[" + userTokenCacheKey.getUserID() + "], sessionID[" + userTokenCacheKey.getSessionId() + "] 本地已使用缓存正在从本地获取用户校验信息。");

                //5、获取Token 动态加密串
                String tokenKey = (String) cache.getObjectOfCache(cache_key);

                logger.info("用户校验：userID[" + userTokenCacheKey.getUserID() + "], sessionID[" + userTokenCacheKey.getSessionId() + "] tokenKey[" + tokenKey + "] 已获取Token串密。");

                if (StringUtil.isNotBlank(tokenKey)) {
                    logger.info("用户校验：userID[" + userTokenCacheKey.getUserID() + "], sessionID[" + userTokenCacheKey.getSessionId() + "] 开始解Token串密。");
                    String tokenStr = TokenUtil.decodeToken(token.getToken(), tokenKey);
                    logger.info("用户校验：userID[" + userTokenCacheKey.getUserID() + "], sessionID[" + userTokenCacheKey.getSessionId() + "] 已解出Token串密 tokenStr[" + tokenStr + "]。");
                    tokenObj = TokenUtil.buildUserTokenByToken(tokenStr, SSOConstants.SPLIT_FLAG);

                    if (tokenObj == null) {
                        clearCache(userTokenCacheKey);
                        logger.error("用户校验失败：userID[" + userTokenCacheKey.getUserID() + "], sessionID[" + userTokenCacheKey.getSessionId() + "] 无法把tokenStr 转换成对象。");
                        return ReturnUtil.buildCheckToken("", "", "", "", "", "", 0, "5000002");
                    } else {
                        logger.info("用户校验：userID[" + tokenObj.getUserID() + "], sessionID[" + tokenObj.getSessionId() + "] ,LoginName [" + tokenObj.getLoginName() + "] 已解出Token开始校验时间。");

                        //用户超时验证
                        long loginTime = System.currentTimeMillis() - (tokenObj.getLoginTimeStamp() + ClientConfig.getInstance().getCacheVO().getMaxLifeTime());
                        if (loginTime > 0) {

                            logger.info("用户校验失败：userID[" + tokenObj.getUserID() + "], sessionID[" + tokenObj.getSessionId() + "] ,LoginName [" + tokenObj.getLoginName() + "] 登录已超时[" + loginTime + "]毫秒 须重新登录。");
                            return ReturnUtil.buildCheckToken("", "", "", "", "", "", 0, "5000005");
                        } else if (tokenObj.getUserID().equals(userTokenCacheKey.getUserID()) &&
                                tokenObj.getSessionId().equals(userTokenCacheKey.getSessionId())) {

                            logger.info("用户校验通过：userID[" + tokenObj.getUserID() + "], sessionID[" + tokenObj.getSessionId() + "] ,LoginName [" + tokenObj.getLoginName() + "] 开始判断是否需要重新刷新Ticket。");

                            //如果离超时间还有一半左右
                            loginTime = (System.currentTimeMillis() - tokenObj.getLoginTimeStamp());
                            long loginTimeHelf = ClientConfig.getInstance().getCacheVO().getMaxLifeTime() / 2;
                            if (loginTime >= loginTimeHelf) {

                                logger.info("用户校验通过：userID[" + tokenObj.getUserID() + "], sessionID[" + tokenObj.getSessionId() + "] ,LoginName [" + tokenObj.getLoginName() + "] 需要重新刷新Ticket。");

                                //调用SSO登录服务
                                tokenObj = client.ssoCheck(token);

                                if (tokenObj.getOperatorResult() == 2) {
                                    cache.putObjectToCache(key, tokenObj.getToken());
                                    cache.putObjectToCache(cache_key, tokenObj.getToken_Key());
                                }

                                logger.info("用户校验通过：userID[" + tokenObj.getUserID() + "], sessionID[" + tokenObj.getSessionId() + "] ,LoginName [" + tokenObj.getLoginName() + "] 重新刷新Ticket结果[" + tokenObj.getOperatorResult() + "]。");
                            } else {
                                tokenObj.setOperatorCode("5000000");
                                tokenObj.setOperatorResult(1);

                                logger.info("用户校验通过：userID[" + userTokenCacheKey.getUserID() + "], sessionID[" + userTokenCacheKey.getSessionId() + "] ,LoginName [" + tokenObj.getLoginName() + "] 解密完成 返回结果。");

                            }

                            return ReturnUtil.buildCheckToken(tokenObj.getUserID(),
                                    tokenObj.getSessionId(),
                                    tokenObj.getLoginName(),
                                    tokenObj.getToken(),
                                    tokenObj.getToken_Key(),
                                    tokenObj.getTokenKey_Cache_Key(),
                                    tokenObj.getOperatorResult(),
                                    tokenObj.getOperatorCode());
                        } else {
                            logger.info("用户校验失败：Token {userID[" + tokenObj.getUserID() + "], sessionID[" + tokenObj.getSessionId() + "]}" +
                                    "Token {userID[" + userTokenCacheKey.getUserID() + "], sessionID[" + userTokenCacheKey.getSessionId() + "]}" +
                                    " 无法匹配用户  。");
                            return ReturnUtil.buildCheckToken("", "", "", "", "", "", 0, "5000004");
                        }
                    }
                } else {

                    //调用SSO登录服务
                    logger.info("用户校验：本地缓存无法找到该用户，直接从服务器验证开始");
                    tokenObj = client.ssoCheck(token);
                    key = MemcacheConstants.getToken_Key(userTokenCacheKey);
                    //cache_key = MemcacheConstant.getToken_encryptKey_key(userTokenCacheKey);
                    if (tokenObj.getOperatorResult() == 1 || tokenObj.getOperatorResult() == 2) {
                        cache.putObjectToCache(key, tokenObj.getToken());
                        cache.putObjectToCache(cache_key, tokenObj.getToken_Key());
                    }
                    logger.info("用户校验：本地缓存无法找到该用户，直接从服务器验证返回结果。userID[" + tokenObj.toString() + "]");
                    return ReturnUtil.buildCheckToken(tokenObj.getUserID(),
                            tokenObj.getSessionId(),
                            tokenObj.getLoginName(),
                            tokenObj.getToken(),
                            tokenObj.getToken_Key(),
                            tokenObj.getTokenKey_Cache_Key(),
                            tokenObj.getOperatorResult(),
                            tokenObj.getOperatorCode());
                }
            } else {
                logger.info("用户校验：本地缓存已禁用，直接从服务器验证开始");
                //调用SSO服务
                tokenObj = client.ssoCheck(token);

                logger.info("用户校验：本地缓存已禁用，到服务器验证结果:{" + tokenObj.toString() + "}");

                //添加本地缓存
                if (ClientConfig.getInstance().isCache() && tokenObj != null && tokenObj.getOperatorResult() == 1 || tokenObj.getOperatorResult() == 2) {
                    cache.putObjectToCache(key, tokenObj.getToken());
                    cache.putObjectToCache(cache_key, tokenObj.getToken_Key());
                }
                return ReturnUtil.buildCheckToken(tokenObj.getUserID(),
                        tokenObj.getSessionId(),
                        tokenObj.getLoginName(),
                        tokenObj.getToken(),
                        tokenObj.getToken_Key(),
                        tokenObj.getTokenKey_Cache_Key(),
                        tokenObj.getOperatorResult(),
                        tokenObj.getOperatorCode());
            }
        } catch (Exception e) {
            logger.error("用户校验失败：校验出现异常。", e);
            return ReturnUtil.buildCheckToken("", "", "", "", "", "", 0, "5000006");
        }
    }

    /**
     * 清除ehcache缓存
     *
     * @param userToken UserToken
     * @throws IOException
     */
    private void clearCache(final UserToken userToken) throws IOException {
        // 储存Token的内存key
        String tokenKey = MemcacheConstants.getToken_Key(userToken);
        // 储存加密的内存key
        String encryptKey = MemcacheConstants.getToken_encryptKey_key(userToken);
        cache.removeObjectOfCache(tokenKey);
        cache.removeObjectOfCache(encryptKey);
    }
}
