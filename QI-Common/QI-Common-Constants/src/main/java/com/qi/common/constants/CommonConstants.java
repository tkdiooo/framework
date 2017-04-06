package com.qi.common.constants;

/**
 * Class CommonConstants
 *
 * @author 张麒 2016/5/25.
 * @version Description:
 */
public class CommonConstants {

    /**
     * Ajax请求表头
     */
    public static final String AJAX_ACCEPT_CONTENT_TYPE = "text/html;type=ajax";
    /**
     * 前端Ajax请求时间戳
     */
    public static final String AJAX_TIME_FRESH = "ajaxTimeFresh";

    public static final String VIEW_404 = "common/404";
    public static final String VIEW_500 = "common/500";
    public static final String VIEW_JSON = "jsonView";

    // Common Attribute
    //-------------------------------------------------------------------------------------------
    public static final String SUCCESS = "success";
    public static final String MESSAGES = "messages";
    public static final String MESSAGES_DETAIL = "messages_detail";
    public static final String INFORMATION = "information";


    // Startup Parameters Attribute
    //-------------------------------------------------------------------------------------------
    /**
     * 配置文件路径
     */
    public final static String RESOURCES_CONFIG_PATH = "resources.config.path";
    /**
     * 配置文件名
     */
    public final static String RESOURCES_PROPERTY_FILE = "resources.property.file";

    /**
     * 国际化文件路径
     */
    public final static String[] RESOURCES_I18N_PATH = {"i18n/defaultMessages", "i18n/messages"};

    // Front UI Attribute
    //-------------------------------------------------------------------------------------------
    /**
     * 静态资源地址
     */
    public final static String STATIC_RESOURCE = "static_resource";

    /**
     * 项目ContextPath
     */
    public final static String CONTEXT_PATH = "context_path";

    // Property File Attribute
    //-------------------------------------------------------------------------------------------
    /**
     * property：静态资源地址
     */
    public final static String PROPERTY_STATIC_RESOURCE = "website.static.resources";
    /**
     * property：单点登录 - 登录服务
     */
    public final static String PROPERTY_SSO_LOGIN_URL = "sso.login.url";
    /**
     * property：单点登录 - 验证服务
     */
    public final static String PROPERTY_SSO_CHECK_URL = "sso.check.url";
    /**
     * property：单点登录 - 登出服务
     */
    public final static String PROPERTY_SSO_LOGOUT_URL = "sso.logout.url";
    /**
     * property：上传文件-存放生成的文件地址
     */
    public final static String MULTIPART_LOCATION = "spring.http.multipart.location";
    /**
     * property：上传文件-允许上传的单个文件最大值。默认值为 -1，表示没有限制
     */
    public final static String MULTIPART_MAX_FILE_SIZE = "spring.http.multipart.max-file-size";
    /**
     * property：上传文件-针对该 multipart/form-data 上传文件的最大值，默认值为 -1，表示没有限制
     */
    public final static String MULTIPART_MAX_REQUEST_SIZE = "spring_http.multipart.max-request-size";
    /**
     * property：上传文件-当数据量大于该值时，内容将被写入文件
     */
    public final static String MULTIPART_FILE_SIZE_THRESHOLD = "spring.http.multipart.file-size-threshold";

}
