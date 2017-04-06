package com.qi.common.util;

import com.qi.common.constants.CommonConstants;
import com.qi.common.constants.StringConstants;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Class HttpUtil
 *
 * @author 张麒 2017/3/29.
 * @version Description:
 */
public class HttpUtil {

    public static String getFullRequestURI(HttpServletRequest request) {
        return request.getScheme() + StringConstants.COLON + StringConstants.DOUBLE_SLASH + request.getServerName() + StringConstants.COLON + request.getServerPort() + request.getRequestURI() + (StringUtil.isBlank(request.getQueryString()) ? "" : (StringConstants.QUESTION + request.getQueryString()));

    }

    public static boolean isAjax(HttpServletRequest request) {
        String acceptHeader = request.getHeader("Accept");
        String ajaxParam = request.getParameter(CommonConstants.AJAX_TIME_FRESH);
        return CommonConstants.AJAX_ACCEPT_CONTENT_TYPE.equals(acceptHeader) || StringUtils.hasText(ajaxParam);
    }
}
