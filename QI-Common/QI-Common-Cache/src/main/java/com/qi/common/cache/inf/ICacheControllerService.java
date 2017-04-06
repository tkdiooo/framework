package com.qi.common.cache.inf;

/**
 * Class ICacheControllerService
 *
 * @author 张麒 2016/5/13.
 * @version Description:
 */
public interface ICacheControllerService<T extends ICacheService<?, ?>> {

    /**
     * <pre>
     * 配置Cache系统配置文件
     * </pre>
     */
    void setConfigFile();

    /**
     * 重新载入Cache配置文件
     *
     * @param configFile
     */
    void reload(String configFile);

    /**
     * 获取Cache客户端
     *
     * @return
     */
    T getCacheClient();

    /**
     * <pre>
     * 初始化CACHE信息
     * </pre>
     */
    void InitConfig();
}
