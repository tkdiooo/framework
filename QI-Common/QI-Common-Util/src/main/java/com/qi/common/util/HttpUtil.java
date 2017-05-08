package com.qi.common.util;

import com.qi.common.constants.CommonConstants;
import com.qi.common.constants.LabelConstants;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Class HttpUtil
 *
 * @author 张麒 2017/3/29.
 * @version Description:
 */
public class HttpUtil {

    public static String getFullUrlRequest(HttpServletRequest request) {
        return request.getScheme() + LabelConstants.COLON + LabelConstants.DOUBLE_SLASH + request.getServerName() + LabelConstants.COLON + request.getServerPort() + request.getRequestURI() + (StringUtil.isBlank(request.getQueryString()) ? "" : (LabelConstants.QUESTION + request.getQueryString()));

    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String acceptHeader = request.getHeader("Accept");
        String ajaxParam = request.getParameter(CommonConstants.AJAX_TIME_FRESH);
        return CommonConstants.AJAX_ACCEPT_CONTENT_TYPE.equals(acceptHeader) || StringUtils.hasText(ajaxParam);
    }
}
