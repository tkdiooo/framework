package com.qi.sso.web.config;

import com.qi.common.util.PropertyUtil;

/**
 * Class ApplicationConfig
 *
 * @author 张麒 2016/6/21.
 * @version Description:
 */
public class ApplicationConfig {

    /**
     * 单点登录 - 默认跳转页面
     */
    public static final String DEFAULT_URL = PropertyUtil.getProps("sso.default.url");

    /**
     * 用户注册页面
     */
    public static final String REGISTER_URL = PropertyUtil.getProps("account.register.url");

    /**
     * 找回密码页面
     */
    public static final String FORGET_URL = PropertyUtil.getProps("account.forget.url");
}
