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

}
