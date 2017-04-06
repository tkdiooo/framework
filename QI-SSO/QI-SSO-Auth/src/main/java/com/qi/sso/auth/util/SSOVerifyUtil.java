package com.qi.sso.auth.util;

import com.qi.common.util.ArrayUtil;

import java.util.regex.Pattern;

/**
 * Class SSOVerifyUtil
 *
 * @author 张麒 2016/6/23.
 * @version Description:
 */
public class SSOVerifyUtil {


    public static final String LOGIN_URL_PREFIX = "loginUrlPrefix";

    public static final String CHECK_URL_PREFIX = "checkUrlPrefix";

    public static final String EXCLUDE_PATTERN = "excludePattern";

    public static final String LOGIN_SERVER = "/login.html";

    public static final String CHECK_SERVER = "/check.html";


    public static boolean excludeVerify(String servletPath, Pattern[] excludeUrlPatterns) {
        boolean bool = false;
        if (ArrayUtil.isNotEmpty(excludeUrlPatterns)) {
            for (Pattern p : excludeUrlPatterns) {
                if (p.matcher(servletPath).matches()) {
                    bool = true;
                    break;
                }
            }
        }
        return bool;
    }
}
