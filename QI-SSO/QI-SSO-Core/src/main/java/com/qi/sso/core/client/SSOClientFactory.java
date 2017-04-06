package com.qi.sso.core.client;

import com.qi.common.util.ThrowableUtil;
import com.qi.sso.core.common.config.ClientConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Class SSOClientFactory
 *
 * @author 张麒 2016/5/26.
 * @version Description:
 */
public class SSOClientFactory {

    private static final Map<String, SSOClient> CLIENT_CACHE = new HashMap<>();

    private SSOClientFactory() {
    }

    /**
     * 从指定配置文件中创建一个全局的SSO服务接口
     *
     * @return SSOClient
     */
    public static SSOClient createSSOClient(String configFile) throws Exception {
        ClientConfig clientConfig = ClientConfig.getInstance(configFile);
        SSOClient client = CLIENT_CACHE.get(configFile + "_" + clientConfig.getConnectType());
        if (client == null) {
            synchronized (SSOClientFactory.class) {
                if (!CLIENT_CACHE.containsKey(configFile + "_" + clientConfig.getConnectType())) {
                    long connTimeout = clientConfig.getConnectTimeout();
                    long readTimeOut = clientConfig.getReadTimeout();
                    String url = clientConfig.getConnectUrl();
                    String serviceType = clientConfig.getConnectType();
                    boolean isCache = clientConfig.isCache();
                    if ("hessian".equals(serviceType)) {
                        client = new SSOHessianClient(connTimeout, readTimeOut, url);
                    } else if ("webservice".equals(serviceType)) {
                        client = new SSOWebServiceClient(url, connTimeout);
                    } else {
                        ThrowableUtil.throwRuntimeException("无法匹配到相对应的服务接口：" + clientConfig.getConnectType());
                    }

                    if (isCache) {
                        client = new SSOLocalCacheClient(client);
                    }

                    CLIENT_CACHE.put(configFile + "_" + clientConfig.getConnectType(), client);
                }
            }
        }
        return client;
    }

    /**
     * 从默认配置文件中创建一个全局的SSO服务接口
     *
     * @return SSOClient
     */
    public static SSOClient getSSOClient() throws Exception {
        return createSSOClient(ClientConfig.DEFAULT_PATH);
    }

}
