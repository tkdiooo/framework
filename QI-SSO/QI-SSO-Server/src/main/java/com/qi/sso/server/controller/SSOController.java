package com.qi.sso.server.controller;

import com.alibaba.fastjson.JSON;
import com.qi.common.tool.Logger;
import com.qi.common.util.StringUtil;
import com.qi.sso.common.constants.SSOConstants;
import com.qi.sso.core.client.SSOClient;
import com.qi.sso.model.dto.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Class SSOController
 *
 * @author 张麒 2016/6/14.
 * @version Description:
 */
@Controller("/")
public class SSOController {

    private final Logger logger = new Logger(SSOController.class);

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    @Autowired
    private SSOClient ssoService;

    @RequestMapping(value = "check.html", method = RequestMethod.POST)
    public void check(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType(CONTENT_TYPE);
        String result = "{}";
        try {
            String utJson = request.getParameter(SSOConstants.UAMS_CHECK_PARAM_NAME);
            if (StringUtil.isNotBlank(utJson)) {
                UserToken ut = JSON.parseObject(utJson, UserToken.class);
                ut = ssoService.ssoCheck(ut);
                logger.info("UserToken验证结果：" + ut);
                if (ut != null && !Objects.equals(ut.getOperatorResult(), SSOConstants.OperatorResult.FAIL.getKey())) {
                    result = JSON.toJSONString(ut);
                }
                logger.info("UserToken验证返回json信息：" + result);
                response.getWriter().write(result);
            }
        } catch (Exception e) {
            logger.error("token验证错误", e);
            try {
                response.getWriter().write(result);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
