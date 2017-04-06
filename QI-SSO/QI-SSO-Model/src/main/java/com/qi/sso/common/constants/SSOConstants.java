package com.qi.sso.common.constants;

import com.qi.common.constants.inf.OptionEnum;

/**
 * Class SSOConstants
 *
 * @author 张麒 2016/5/19.
 * @version Description:
 */
public class SSOConstants {

    public static final String UTF_8 = "UTF-8";

    public static final String SPLIT_FLAG = "##";

    public static final String PARAM_FROM_URL = "from_url";

    public static final String LOGIN_ACCOUNT = "account";
    public static final String LOGIN_PASSWORD = "password";
    public static final String LOGIN_REMEMBER = "remember";
    public static final String LOGIN_PORTAL = "portal";

    public static final String LOGIN_REGISTER = "registerUrl";
    public static final String LOGIN_FORGET = "forgetUrl";

//    public static final String LOGIN_INDEX_URL = "/index.html";

    public static final String COOKIE_REMEMBER_LOGIN_ACCOUNT = "sfsc_re_lo_ac";

    public static final String COOKIE_USER_TOKEN_NAME = "uams_tok_na";
    public static final String COOKIE_USER_TOKEN_KEY_CACHENAME = "uams_tok_cac_na";
    // Session参数名称
    public static final String CONST_UAMS_ASSERTION = "const_uams_assertion";
    // SSO验证参数名称
    public static final String UAMS_CHECK_PARAM_NAME = "uams_check_param";
    // 是否使用Session
    public static final boolean USE_SESSION = false;
    // 前端是否使用iframe
    public static final boolean IS_IFRAME = true;
    // session 过期
    public static final String SESSION_TIME_OUT = "timeout";

    public enum OperatorResult implements OptionEnum<Integer, String> {

        REWRITE(2, "重写Cookies"),
        SUCCESS(1, "成功"),
        FAIL(0, "失败");

        OperatorResult(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        private Integer key;
        private String value;

        @Override
        public Integer getKey() {
            return key;
        }

        @Override
        public String getValue() {
            return value;
        }

        public static String getValueByKey(Integer key) {
            return OptionEnum.findValue(values(), key);
        }

        public static Integer getKeyByValue(String value) {
            return OptionEnum.findKey(values(), value);
        }
    }

    public enum OperatorCode implements OptionEnum<String, String> {

        OC_0000001("0000001", "数据校验无法通过"),
        OC_0000002("0000002", "无法解出CacheKey"),
        OC_1000000("1000000", "登录成功"),
        OC_1000001("1000001", "登录异常"),
        OC_2000000("2000000", "登出成功"),
        OC_2000001("2000001", "登出异常"),
        OC_3000000("3000000", "校验成功"),
        OC_3000001("3000001", "校验异常"),
        OC_3000002("3000002", "用户登录超时，无法找到缓存"),
        OC_3000003("3000003", "UserToken无法解密"),
        OC_3000004("3000004", "校验用户登录已超时"),
        OC_3000005("3000005", "校验用户信息不相等");

        OperatorCode(String key, String value) {
            this.key = key;
            this.value = value;
        }

        private String key;
        private String value;

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public String getValue() {
            return value;
        }

        public static String getValueByKey(String key) {
            return OptionEnum.findValue(values(), key);
        }

        public static String getKeyByValue(String value) {
            return OptionEnum.findKey(values(), value);
        }
    }

}
