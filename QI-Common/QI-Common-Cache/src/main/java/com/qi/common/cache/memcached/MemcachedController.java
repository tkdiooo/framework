package com.qi.common.cache.memcached;

import com.danga.MemCached.MemCachedClient;
import com.qi.common.cache.common.CacheXMLParser;
import com.qi.common.cache.common.MemcacheUtil;
import com.qi.common.cache.common.SystemPropertys;
import com.qi.common.cache.inf.ICacheControllerService;
import com.qi.common.cache.memcached.inf.IMemcachedService;
import org.apache.commons.net.telnet.TelnetClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class MemcachedController
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class MemcachedController implements ICacheControllerService<IMemcachedService>, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(MemcachedController.class);

    private CacheXMLParser cacheXMLParser = null;

    private MemCachedClient mc = null;

    private String fileName = "cache.properties";

    private String configFile;

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public void Connection() {
        try {
            TelnetClient telnet = new TelnetClient();
            String[] servers = cacheXMLParser.getCommonSocketPoolBean().getServers().get(0).split(":");
            telnet.connect(servers[0], Integer.valueOf(servers[1]));
        } catch (Exception e) {
            logger.info("Memcached初始化错误", e);
            MemcahcedClientProxy.setSuccess(false);
        }
    }

    @Override
    public void InitConfig() {
        logger.info("memcached init start");
        setConfigFile();
        cacheXMLParser = new CacheXMLParser(MemcacheUtil.getConfigFilePath());
        Connection();
        mc = cacheXMLParser.getCommonMemcachedClient();
        MemcahcedClientProxy.setMc(mc);
        logger.info("memcached init success");
    }

    @Override
    public MemcahcedClientProxy getCacheClient() {
        return MemcahcedClientProxy.getInstance();
    }

    @Override
    public void reload(String configFile) {
    }

    @Override
    public void setConfigFile() {
//        if (this.configFile == null) {
//            throw new IllegalArgumentException("Property 'configFile' is required");
//        }
        InputStream inputStream = null;
//        String filePath = System.getProperty("resources.config.path") + File.separator + fileName;
        if (configFile != null) {
            try {
                logger.info("cache配置文件地址：" + configFile);
                inputStream = new FileInputStream(configFile);
            } catch (FileNotFoundException e) {
                logger.error("加载cache配置文件错误", e);
            }
        } else {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        }

        Properties props = new Properties();
        try {
            props.load(inputStream);
            SystemPropertys.resolvePlaceholder(props);
        } catch (IOException e) {
            logger.error("读取Memcahced配置文件错误", e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.InitConfig();
    }
}
