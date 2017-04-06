package com.qi.common.web.http;

import com.qi.common.constants.CommonConstants;
import com.qi.common.constants.i18n.I18NConstants;
import com.qi.common.util.MapUtil;
import com.qi.common.util.ResourceUtil;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;
import java.util.Map;

/**
 * Class ResponseResult
 *
 * @author 张麒 2016/7/5.
 * @version Description:
 */
public class ResponseResult implements Serializable {

    private static final long serialVersionUID = 5916109716437311485L;

    private boolean success = true;
    private String messages = null;
    private Map<String, Object> attachs = null;

    private ResponseResult() {
    }

    public static ResponseResult getInstance() {
        ResponseResult response = new ResponseResult();
        response.messages = ResourceUtil.getMessage(I18NConstants.Tips.OperateSuccess);
        response.attachs = MapUtil.getInstance();
        return response;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessages() {
        return this.messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public void attach(String key, Object value) {
        this.attachs.put(key, value);
    }

    public void attachMap(Map<String, Object> map) {
        this.attachs.putAll(map);
    }

    public Map<String, Object> getAttachs() {
        return this.attachs;
    }

    public ModelAndView getJsonModelView() {
        attachs.put(CommonConstants.SUCCESS, this.success);
        attachs.put(CommonConstants.MESSAGES, this.messages);
        return new ModelAndView(CommonConstants.VIEW_JSON, attachs);
    }
}
