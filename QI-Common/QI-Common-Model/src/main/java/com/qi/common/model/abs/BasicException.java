package com.qi.common.model.abs;

import com.qi.common.constants.inf.OptionEnum;

import java.text.MessageFormat;

/**
 * Class BaseException
 *
 * @author 张麒 2016/4/8.
 * @version Description:
 */
public abstract class BasicException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private OptionEnum<String, String> tips;
    private String message;
    private String[] params = new String[0];

    public BasicException(OptionEnum<String, String> tips, String... params) {
        this.tips = tips;
        if (null != params) this.params = params;
    }

    public BasicException(String message, String... params) {
        this.message = message;
        if (null != params) this.params = params;
    }

    public OptionEnum<String, String> getTips() {
        return tips;
    }

    public void setTips(OptionEnum<String, String> tips) {
        this.tips = tips;
    }

    @Override
    public String getMessage() {
        if (params.length > 0) return MessageFormat.format(this.message, (Object[]) this.params);
        else return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getParams() {
        return this.params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public void addParams(String param) {
        this.params[params.length] = param;
    }
}
