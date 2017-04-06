package com.qi.common.web.http;

import com.alibaba.fastjson.JSON;
import com.qi.common.constants.CommonConstants;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Class ResponseBody
 *
 * @author 张麒 2016/7/5.
 * @version Description:
 */
public class ResponseBody {

    private Map<String, Object> result = null;
    private PrintWriter writer = null;

    private ResponseBody() {
    }

    public static ResponseBody getInstance(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        ResponseBody responseBody = new ResponseBody();
        responseBody.writer = response.getWriter();
        responseBody.result = new HashMap<>();
        return responseBody;
    }

    public ResponseBody setSuccess(boolean success) {
        this.result.put(CommonConstants.SUCCESS, success);
        return this;
    }

    public ResponseBody set(String name, Object value) {
        this.result.put(name, value);
        return this;
    }

    public ResponseBody setInfo(String info) {
        this.result.put(CommonConstants.INFORMATION, info);
        return this;
    }

    public void write() {
        this.writer.write(JSON.toJSONString(this.result));
    }
}
