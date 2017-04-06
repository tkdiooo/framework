package com.qi.common.cache.common;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.qi.common.tool.Logger;
import com.qi.common.util.Dom4jUtil;
import com.qi.common.util.ListUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * Class CacheXMLParser
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class CacheXMLParser {

    private static final Logger logger = new Logger(CacheXMLParser.class);

    private SocketPoolBean commonSocketPoolBean = null;

    public CacheXMLParser(String fileName) {
        readXML(fileName);
    }

    /**
     * 解析配置文件
     *
     * @param fileName
     */
    private void readXML(String fileName) {
        try {
            Document document = Dom4jUtil.build(new File(fileName));
            // 解析commonsocketpool标签
            commonSocketPoolBean = readCommonSocketPool(document);
        } catch (Exception e) {
            logger.error("file can not find", e);
        }
    }

    /**
     * 解析commonsocketpool标签
     *
     * @param document
     */
    @SuppressWarnings("unchecked")
    private SocketPoolBean readCommonSocketPool(Document document) {
        List<Element> socketpools = document.selectNodes("/cache/commonsocketpool");
        return readSocketPool(socketpools);
    }

    @SuppressWarnings("unchecked")
    private SocketPoolBean readSocketPool(List<Element> socketpools) {
        if (ListUtil.isEmpty(socketpools)) {
            logger.warn("Configuration file is not valid!");
            return null;
        }

        for (Element element : socketpools) {

            SocketPoolBean socketPoolBean = new SocketPoolBean();
            socketPoolBean.setName(element.attributeValue("name").trim());
            socketPoolBean.setFailover(Boolean.parseBoolean(element.attributeValue("failover").trim()));
            socketPoolBean.setInitConn(Integer.parseInt(element.attributeValue("initConn").trim()));
            socketPoolBean.setMinConn(Integer.parseInt(element.attributeValue("minConn").trim()));
            socketPoolBean.setMaxConn(Integer.parseInt(element.attributeValue("maxConn").trim()));
            socketPoolBean.setMaintSleep(Integer.parseInt(element.attributeValue("maintSleep").trim()));
            socketPoolBean.setNagle(Boolean.parseBoolean(element.attributeValue("nagle").trim()));
            socketPoolBean.setSocketTo(Integer.parseInt(element.attributeValue("socketTo").trim()));
            socketPoolBean.setAliveCheck(Boolean.parseBoolean(element.attributeValue("aliveCheck").trim()));
            List<Element> servers = element.selectNodes("servers/value");

            for (Element server : servers) {
                socketPoolBean.add(server.getTextTrim());
            }
            return socketPoolBean;
        }
        logger.warn("Configuration file is not valid!");
        return null;
    }

    /**
     * 实例化解析的commonsocketpool标签成SocketIOPool和MemcachedClient
     */
    public MemCachedClient getCommonMemcachedClient() {
        return getMemcachedClient(commonSocketPoolBean);
    }

    private MemCachedClient getMemcachedClient(SocketPoolBean socketPoolBean) {
        if (socketPoolBean == null) {
            logger.warn("please check your xml file!");
            return null;
        }

        SockIOPool pool = SockIOPool.getInstance(socketPoolBean.getName());

        pool.setServers(socketPoolBean.getServers().toArray(new String[socketPoolBean.getServers().size()]));
        // 设置cache服务器的初始化可用连接
        pool.setInitConn(socketPoolBean.getInitConn());
        // 设置每个服务器最少可用连接数
        pool.setMinConn(socketPoolBean.getMinConn());
        // 设置每个服务器最大可用连接数
        pool.setMaxConn(socketPoolBean.getMaxConn());
        // 设置可用连接池的等待时间
        pool.setMaxIdle(socketPoolBean.getMaxIdle());
        // 设置连接池维护线程的睡眠时间，设置为0，维护线程不启动
        pool.setMaintSleep(socketPoolBean.getMaintSleep());
        // 设置是否使用Nagle算法，因为我们的通讯数据量�?常比较大
        pool.setNagle(socketPoolBean.isNagle());
        // 设置读取等待超时
        pool.setSocketTO(socketPoolBean.getSocketTo());
        // 设置连接心跳监测
        pool.setAliveCheck(socketPoolBean.isAliveCheck());

        pool.setHashingAlg(SockIOPool.CONSISTENT_HASH);

        pool.initialize();

        MemCachedClient mc = new MemCachedClient(socketPoolBean.getName());
        // 压缩开关
        mc.setCompressEnable(true);
        // 压缩设置，超过指定大小（单位为K）的数据都会被压缩
        mc.setCompressThreshold(64 * 1024);

        return mc;
    }

    public SocketPoolBean getCommonSocketPoolBean() {
        return commonSocketPoolBean;
    }
}
