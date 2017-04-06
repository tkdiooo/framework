package com.qi.common.web.tool;

import com.alibaba.fastjson.JSON;
import com.qi.common.constants.CommonConstants;
import com.qi.common.http.ResponseContent;
import com.qi.common.http.synch.HttpHelper;
import com.qi.common.model.model.DictModel;
import com.qi.common.tool.Logger;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Class DictTool
 *
 * @author 张麒 2016/5/12.
 * @version Description:
 */
public class DictTool {

    private static final Logger logger = new Logger(DictTool.class);

    private static String DictArrayUrl = null;//字典数组
    private static String DictChildUrl = null;//字典子级数据
    private static String DictNameUrl = null; //字典名称

    private static final String ENCODE = "UTF-8";

    static {
        Properties properties = new Properties();
        try {
            String path = System.getProperty(CommonConstants.RESOURCES_CONFIG_PATH) == null ? System.getenv(CommonConstants.RESOURCES_CONFIG_PATH) : System.getProperty(CommonConstants.RESOURCES_CONFIG_PATH);
            InputStream inputStream = new BufferedInputStream(new FileInputStream(path.concat("common.properties")));
            properties.load(inputStream);

            DictArrayUrl = properties.getProperty("dict.array.address").trim();
            DictChildUrl = properties.getProperty("dict.child.address").trim();
            DictNameUrl = properties.getProperty("dict.name.address").trim();

        } catch (Exception e) {
            logger.error("dictUtil init error," + e);
        }
    }

    public static List<DictModel> getDictArray(String code) {
        List<DictModel> list = null;
        try {
            String url = DictArrayUrl.concat(code);
            ResponseContent content = HttpHelper.getUrlRespContent(url);
            if (null != content && StringUtils.isNotBlank(content.getContent(ENCODE))) {

                list = JSON.parseArray(content.getContent(ENCODE), DictModel.class);
            }
        } catch (Exception e) {
            logger.error("get dictobj error," + e);
        }
        return list;
    }

    public static List<DictModel> getDictChild(String code) {
        List<DictModel> list = null;
        try {
            String url = DictChildUrl.concat(code);
//			ResponseContent content = HttpHelper.postJsonEntity(url, null);
            ResponseContent content = HttpHelper.getUrlRespContent(url);
            if (null != content && StringUtils.isNotBlank(content.getContent(ENCODE))) {

                list = JSON.parseArray(content.getContent(ENCODE), DictModel.class);
            }
        } catch (Exception e) {
            logger.error("get dictobj error," + e);
        }
        return list;
    }

    public static DictModel getDictName(String code) {

        DictModel model = null;

        try {
            String url = DictNameUrl.concat(code);
//			ResponseContent content = HttpHelper.postJsonEntity(url, null);
            ResponseContent content = HttpHelper.getUrlRespContent(url);
            if (null != content && StringUtils.isNotBlank(content.getContent(ENCODE))) {

                model = JSON.parseObject(content.getContent(ENCODE), DictModel.class);
            }
        } catch (Exception e) {
            logger.error("get dictval error," + e);
        }
        return model;
    }

    public static String getItemNameByCode(String code) {
        if (StringUtils.isEmpty(code)) return "";
        DictModel model = getDictName(code);
        return model != null ? model.getItemName() : "";
    }
}
