package com.qi.sso.server.hessian.impl;

import com.qi.common.cache.inf.ICacheControllerService;
import com.qi.common.cache.memcached.inf.IMemcachedService;
import com.qi.common.tool.Logger;
import com.qi.common.util.HexUtil;
import com.qi.common.util.StringUtil;
import com.qi.sso.core.client.SSOClient;
import com.qi.sso.core.common.config.ToKenConfig;
import com.qi.sso.core.common.constants.MemcacheConstants;
import com.qi.sso.common.constants.SSOConstants;
import com.qi.sso.core.util.ReturnUtil;
import com.qi.sso.core.util.TokenUtil;
import com.qi.sso.core.util.VerifyUtil;
import com.qi.sso.model.dto.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Objects;

/**
 * Class SSOHessianServiceImpl
 *
 * @author 张麒 2016/6/6.
 * @version Description:
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
@Service("SSOHessianServiceImpl")
public class SSOHessianServiceImpl implements SSOClient {

    private static final Logger logger = new Logger(SSOHessianServiceImpl.class);

    @Autowired
    private ICacheControllerService<IMemcachedService> cacheClient;

    @Override
    public UserToken ssoLogin(final UserToken userToken) throws Exception {
        Long loginOutTime = userToken.getLogoutTime();
        if (loginOutTime == null) {
            loginOutTime = ToKenConfig.getInstance().getLoginTimeout();
            userToken.setLogoutTime(loginOutTime);
        }
        //验证数据合法性
        if (!VerifyUtil.verifyLogin(userToken)) {
            logger.error("用户登录失败：loginName[" + userToken.getLoginName() + "] userID[" + userToken.getUserID() + "] sessionID[" + userToken.getSessionId() + "] 校验登录数据无法通过。");
            return ReturnUtil.buildLoginToken(userToken.getToken(), userToken.getToken_Key(), userToken.getTokenKey_Cache_Key(), userToken.getOperatorResult(), userToken.getOperatorCode());
        }
        try {
            //生成加密盐值，用于解密Token信息
            String encrySalt = HexUtil.getEncryptKey();
            logger.info("用户：" + userToken.getLoginName() + " 生成加密盐值[" + encrySalt + "]。");

            // 设置登录时间
            userToken.setLoginTimeStamp(System.currentTimeMillis());

            // 生成Token字符串
            String token = TokenUtil.encodeToken(TokenUtil.buildToken(userToken, SSOConstants.SPLIT_FLAG), encrySalt);
            logger.info("用户：" + userToken.getLoginName() + " 生成token[" + token + "]。");

            // 生成Memcache缓存key，保存token
            String token_cache_key = MemcacheConstants.getToken_Key(userToken);
            // 缓存：Token值
            cacheClient.getCacheClient().putTimeOut(token_cache_key, token, loginOutTime.intValue());
            logger.info("用户：" + userToken.getLoginName() + " Token 已缓存 token_cache_key[" + token_cache_key + "] token[" + token + "]。");

            // 生成session缓存key，保存session信息
            String session_cache_key = MemcacheConstants.UAMS_USER_SESSION_KEY + token_cache_key;
            // 缓存：Session信息
            cacheClient.getCacheClient().putTimeOut(session_cache_key, userToken.getSessionInfoData(), loginOutTime.intValue());
            logger.info("用户：" + userToken.getLoginName() + " sessionInfoData 已缓存 session_cache_key[" + session_cache_key + "] sessionInfoData[" + userToken.getSessionInfoData() + "]。");

            // 加密存储动态密码的CacheID
            String cacheKey = TokenUtil.encodeCacheKey(TokenUtil.buildCacheKey(userToken, SSOConstants.SPLIT_FLAG), ToKenConfig.getInstance().getCacheIdKey());
            // 解密存储动态密码的CacheID，获取cacheKeyHashCode
            UserToken userTokenCacheKey = TokenUtil.buildUserTokenByCacheKey(TokenUtil.decodeCacheKey(cacheKey, ToKenConfig.getInstance().getCacheIdKey()), SSOConstants.SPLIT_FLAG);
            // token加密key缓存key
            String encrypt_cache_key = MemcacheConstants.getToken_encryptKey_key(userTokenCacheKey);
            // 缓存：加密盐值
            cacheClient.getCacheClient().putTimeOut(encrypt_cache_key, encrySalt, loginOutTime.intValue());
            logger.info("用户：" + userToken.getLoginName() + " 加密盐值 已缓存 encrypt_cache_key[" + encrypt_cache_key + "] encrySalt[" + encrySalt + "]。");

            logger.info("用户：" + userToken.getLoginName() + " 登录正常。");
            return ReturnUtil.buildLoginToken(token, encrySalt, cacheKey, SSOConstants.OperatorResult.SUCCESS.getKey(), SSOConstants.OperatorCode.OC_1000000.getKey());
        } catch (Exception e) {
            logger.error("用户：" + userToken.getLoginName() + " 登录出现异常。", e);
            return ReturnUtil.buildLoginToken(userToken.getToken(), userToken.getToken_Key(), userToken.getTokenKey_Cache_Key(), SSOConstants.OperatorResult.FAIL.getKey(), SSOConstants.OperatorCode.OC_1000001.getKey());
        }
    }

    @Override
    public UserToken ssoLogout(final UserToken userToken) throws Exception {
        try {
            logger.info("用户登出：token[" + userToken.getToken() + "] CacheKey[" + userToken.getTokenKey_Cache_Key() + "] 开始登出。");
            //验证数据合法性
            if (!VerifyUtil.verifyToken(userToken)) {
                logger.error("用户登出失败：token[" + userToken.getToken() + "],tokenKey[" + userToken.getTokenKey_Cache_Key() + "] 数据校验无法通过。");
                return ReturnUtil.buildLogoutToken(userToken.getUserID(), userToken.getSessionId(), userToken.getOperatorResult(), userToken.getOperatorCode());
            }

            //先解密CacheKey
            UserToken userTokenCacheKeyObj = TokenUtil.buildUserTokenByCacheKey(userToken, SSOConstants.SPLIT_FLAG, ToKenConfig.getInstance().getCacheIdKey());
            if (userTokenCacheKeyObj == null) {
                logger.error("用户登出失败：token[" + userToken.getToken() + "],tokenKey[" + userToken.getTokenKey_Cache_Key() + "] 无法解出CacheKey[" + userToken.getTokenKey_Cache_Key() + "]。");
                return ReturnUtil.buildLogoutToken(userToken.getUserID(), userToken.getSessionId(), SSOConstants.OperatorResult.FAIL.getKey(), SSOConstants.OperatorCode.OC_0000001.getKey());
            }

            logger.info("用户登出：userID [" + userTokenCacheKeyObj.getUserID() + "],CacheKey[" + userToken.getTokenKey_Cache_Key() + "] 开始登出。");

            //清除缓存
            clearCache(userTokenCacheKeyObj);

            logger.info("用户：" + userTokenCacheKeyObj.getUserID() + " 登出成功。");
            return ReturnUtil.buildLogoutToken(userTokenCacheKeyObj.getUserID(), userTokenCacheKeyObj.getSessionId(), SSOConstants.OperatorResult.SUCCESS.getKey(), SSOConstants.OperatorCode.OC_2000000.getKey());
        } catch (Exception e) {
            logger.error("用户：Token[" + userToken.getToken() + "] [" + userToken.getTokenKey_Cache_Key() + "] 登出失败。", e);
            return ReturnUtil.buildLogoutToken(userToken.getUserID(), userToken.getSessionId(), SSOConstants.OperatorResult.FAIL.getKey(), SSOConstants.OperatorCode.OC_2000001.getKey());
        }
    }

    @Override
    public UserToken ssoCheck(final UserToken userToken) throws Exception {
        try {
            logger.info("用户校验：token[" + userToken.getToken() + "] CacheKey[" + userToken.getTokenKey_Cache_Key() + "] 开始校验。");

            //1、数据校验
            if (!VerifyUtil.verifyToken(userToken)) {
                logger.error("用户校验失败：{" + userToken.toString() + "} 数据校验无法通过。");
                return ReturnUtil.buildCheckToken(userToken.getUserID(), userToken.getSessionId(), userToken.getLoginName(), userToken.getToken(), userToken.getToken_Key(), userToken.getTokenKey_Cache_Key(), userToken.getOperatorResult(), userToken.getOperatorCode());
            }
            logger.info("用户校验：token[" + userToken.getToken() + "] CacheKey[" + userToken.getTokenKey_Cache_Key() + "] 开始解密CacheKey。");

            //2、先解密CacheKey
            UserToken userTokenCacheKey = TokenUtil.buildUserTokenByCacheKey(userToken, SSOConstants.SPLIT_FLAG, ToKenConfig.getInstance().getCacheIdKey());
            if (userTokenCacheKey == null) {
                logger.error("用户校验失败：{" + userToken.toString() + "} 无法解出CacheKey。");
                return ReturnUtil.buildCheckToken(userToken.getUserID(), userToken.getSessionId(), userToken.getLoginName(), userToken.getToken(), userToken.getToken_Key(), userToken.getTokenKey_Cache_Key(), SSOConstants.OperatorResult.FAIL.getKey(), SSOConstants.OperatorCode.OC_0000002.getKey());
            }
            logger.info("用户校验：UserID[" + userTokenCacheKey.getUserID() + "] CacheKey[" + userToken.getTokenKey_Cache_Key() + "] CacheKey解密完成。");

            //3、获取加密盐值
            String tokenKey = (String) cacheClient.getCacheClient().get(MemcacheConstants.getToken_encryptKey_key(userTokenCacheKey));
            if (StringUtil.isBlank(tokenKey)) {
                logger.error("用户校验 :{" + userToken.toString() + "} 用户登录超时，已无法找到缓存中的Token解密串！");
                return ReturnUtil.buildCheckToken(userToken.getUserID(), userToken.getSessionId(), userToken.getLoginName(), userToken.getToken(), userToken.getToken_Key(), userToken.getTokenKey_Cache_Key(), SSOConstants.OperatorResult.FAIL.getKey(), SSOConstants.OperatorCode.OC_3000002.getKey());
            }
            logger.info("用户校验：UserID[" + userTokenCacheKey.getUserID() + "] TokenKey[" + tokenKey + "] 获取Token解密Key完成。");

            //4、解密TOKEN串
            UserToken userTokenObj = TokenUtil.buildUserTokenByToken(userToken, SSOConstants.SPLIT_FLAG, tokenKey);
            if (userTokenObj == null) {
                logger.error("用户校验 :{" + userToken.toString() + "} 无法解密 。");
                return ReturnUtil.buildCheckToken(userToken.getUserID(), userToken.getSessionId(), userToken.getLoginName(), userToken.getToken(), userToken.getToken_Key(), userToken.getTokenKey_Cache_Key(), SSOConstants.OperatorResult.FAIL.getKey(), SSOConstants.OperatorCode.OC_3000003.getKey());
            }

            logger.info("用户校验：UserID[" + userTokenObj.getUserID() + "] UserName[" + userTokenObj.getLoginName() + "] Token解密完成。");
            //超时时间
            Long loginOutTime = userTokenObj.getLogoutTime();
            if (loginOutTime == null) {
                loginOutTime = ToKenConfig.getInstance().getLoginTimeout();
            }

            //5、如果登录已超时
            long loginedTimeStamp = System.currentTimeMillis() - userTokenObj.getLoginTimeStamp();
            if (loginOutTime > 0 && loginedTimeStamp > loginOutTime) {
                clearCache(userTokenObj);
                logger.error("用户校验: 校验用户登录已超时。{" + userTokenObj.toString() + "}");
                return ReturnUtil.buildCheckToken(userTokenObj.getUserID(), userTokenObj.getSessionId(), userTokenObj.getLoginName(), userToken.getToken(), userToken.getToken_Key(), userToken.getTokenKey_Cache_Key(), SSOConstants.OperatorResult.FAIL.getKey(), SSOConstants.OperatorCode.OC_3000004.getKey());
            }

            logger.info("用户校验：UserID[" + userTokenObj.getUserID() + "] UserName[" + userTokenObj.getLoginName() + "] 登录超时验证完成。");

            //6、校验用户正确性
            if (userTokenObj.getUserID().equals(userTokenCacheKey.getUserID()) && userTokenObj.getSessionId().equals(userTokenCacheKey.getSessionId())) {

                logger.info("用户校验：UserID[" + userTokenObj.getUserID() + "] UserName[" + userTokenObj.getLoginName() + "] 登录信息验证完成开始检查是否需要重新登录。");

                //6.1、如果离超时间还有一半左右，重新生成Ticket
                if (loginOutTime > 0 && loginedTimeStamp >= (loginOutTime / 2)) {
                    // 6.1.0、获取SessionInfoData
                    String session_key = MemcacheConstants.UAMS_USER_SESSION_KEY + MemcacheConstants.getToken_Key(userTokenObj);
                    userTokenObj.setSessionInfoData((String) cacheClient.getCacheClient().get(session_key));
                    // 6.1.1、删除旧Ticket
                    clearCache(userTokenObj);

                    String userName = userTokenObj.getLoginName();
                    String userID = userTokenObj.getUserID();
                    String sessionID = userTokenObj.getSessionId();

                    //6.1.2、重新登录
                    userTokenObj = ssoLogin(userTokenObj);
                    logger.info("用户：" + userName + " 重新登录完成登录结果为：[" + userTokenObj.getOperatorResult() + "]");


                    if (Objects.equals(userTokenObj.getOperatorResult(), SSOConstants.OperatorResult.SUCCESS.getKey())) {
                        userTokenObj.setOperatorResult(SSOConstants.OperatorResult.REWRITE.getKey());
                        userTokenObj.setOperatorCode(SSOConstants.OperatorCode.OC_3000000.getKey());
                        userTokenObj.setLoginName(userName);
                        userTokenObj.setUserID(userID);
                        userTokenObj.setSessionId(sessionID);
                    }
                } else {
                    logger.info("用户校验：UserID[" + userTokenObj.getUserID() + "] UserName[" + userTokenObj.getLoginName() + "] 校验信息正常完成。");
                    userTokenObj.setToken(userToken.getToken());
                    userTokenObj.setToken_Key(tokenKey);
                    userTokenObj.setTokenKey_Cache_Key(userToken.getTokenKey_Cache_Key());
                    userTokenObj.setOperatorResult(SSOConstants.OperatorResult.SUCCESS.getKey());
                    userTokenObj.setOperatorCode(SSOConstants.OperatorCode.OC_3000000.getKey());
                }
                logger.info("用户校验：UserID[" + userTokenObj.getUserID() + "] UserName[" + userTokenObj.getLoginName() + "] Result[" + userTokenObj.getOperatorResult() + "] 校验完成。");
                //7、 校验成功
                UserToken re_ut = ReturnUtil.buildCheckToken(userTokenObj.getUserID(),
                        userTokenObj.getSessionId(),
                        userTokenObj.getLoginName(),
                        userTokenObj.getToken(),
                        userTokenObj.getToken_Key(),
                        userTokenObj.getTokenKey_Cache_Key(),
                        userTokenObj.getOperatorResult(), userTokenObj.getOperatorCode());
                String session_key = MemcacheConstants.UAMS_USER_SESSION_KEY + MemcacheConstants.getToken_Key(re_ut);
                re_ut.setSessionInfoData((String) cacheClient.getCacheClient().get(session_key));
                return re_ut;

            } else {
                logger.error("用户校验: 校验用户信息不相等。Token LoginName [" + userTokenObj.getLoginName() + "] CacheKey LoginName[" + userTokenCacheKey.getLoginName() + "]");
                return ReturnUtil.buildCheckToken(userTokenObj.getUserID(), userTokenObj.getSessionId(), userTokenObj.getLoginName(), userToken.getToken(), userToken.getToken_Key(), userToken.getTokenKey_Cache_Key(), SSOConstants.OperatorResult.FAIL.getKey(), SSOConstants.OperatorCode.OC_3000005.getKey());
            }
        } catch (Exception e) {
            logger.error("用户：Token[" + userToken.getToken() + "] [" + userToken.getTokenKey_Cache_Key() + "] 校验失败。", e);
            return ReturnUtil.buildCheckToken(userToken.getUserID(), userToken.getSessionId(), userToken.getLoginName(), userToken.getToken(), userToken.getToken_Key(), userToken.getTokenKey_Cache_Key(), SSOConstants.OperatorResult.FAIL.getKey(), SSOConstants.OperatorCode.OC_3000001.getKey());
        }
    }


    /**
     * 清除缓存
     *
     * @param userToken UserToken
     */
    private void clearCache(final UserToken userToken) {
        // token缓存key
        String token_cache_key = MemcacheConstants.getToken_Key(userToken);
        // session缓存key
        String session_cache_key = MemcacheConstants.UAMS_USER_SESSION_KEY + token_cache_key;
        // token加密key缓存key
        String encrypt_cache_key = MemcacheConstants.getToken_encryptKey_key(userToken);
        logger.info("用户登录超时，清除缓存：Token_Cache_Key:[" + token_cache_key + "]，Session_Cache_Key:[" + session_cache_key + "]，Salt_Cache_key:[" + encrypt_cache_key + "]");
        cacheClient.getCacheClient().remove(token_cache_key);
        //删除Token Key
        cacheClient.getCacheClient().remove(session_cache_key);
        //del session data
        cacheClient.getCacheClient().remove(encrypt_cache_key);
    }
}
