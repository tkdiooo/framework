package com.qi.common.util;

import com.qi.common.tool.Logger;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Class SystemUtil
 *
 * @author 张麒 2016/4/7.
 * @version Description:
 */
public class SystemUtil extends SystemUtils {

    private static final Logger logger = new Logger(BeanUtil.class);

    /**
     * 获取机器名
     *
     * @return Host Name
     */
    public static String getHostName() {
        String hostname = "";
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            logger.error(e.getMessage(), e);
        }
        return hostname;
    }

    /**
     * 获取机器的IP地址
     *
     * @return Host Address
     */
    public static String getHostIP() {
        String hostaddress = "";
        try {
            hostaddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.error(e.getMessage(), e);
        }
        return hostaddress;
    }

    /**
     * 获取请求的IP地址
     *
     * @param request Request
     * @return Request IP Address
     */
    public String getRequestIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) return ip.substring(0, index);
            else return ip;
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) return ip;
        ip = request.getHeader("Proxy-Client-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) return ip;
        ip = request.getHeader("WL-Proxy-Client-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) return ip;
        ip = request.getHeader("HTTP_CLIENT_IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) return ip;
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) return ip;
        else return request.getRemoteAddr();
    }

}
