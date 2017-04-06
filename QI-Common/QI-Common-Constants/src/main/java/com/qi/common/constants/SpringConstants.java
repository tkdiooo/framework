package com.qi.common.constants;

/**
 * Class SpringConstants
 *
 * @author 张麒 2017/3/31.
 * @version Description:
 */
public class SpringConstants {


    /**
     * property：上传文件-存放生成的文件地址
     */
    public static String MULTIPART_LOCATION;
    /**
     * property：上传文件-允许上传的单个文件最大值。默认值为 -1，表示没有限制
     */
    public static String MULTIPART_MAX_FILE_SIZE;
    /**
     * property：上传文件-针对该 multipart/form-data 上传文件的最大值，默认值为 -1，表示没有限制
     */
    public static String MULTIPART_MAX_REQUEST_SIZE;
    /**
     * property：上传文件-当数据量大于该值时，内容将被写入文件
     */
    public static int MULTIPART_FILE_SIZE_THRESHOLD;
}
