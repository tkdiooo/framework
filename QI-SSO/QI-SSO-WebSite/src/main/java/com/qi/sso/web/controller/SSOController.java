package com.qi.sso.web.controller;

import com.qi.common.constants.CommonConstants;
import com.qi.common.security.EncrypterTool;
import com.qi.common.tool.Logger;
import com.qi.common.util.StringUtil;
import com.qi.sso.common.constants.SSOConstants;
import com.qi.sso.core.authentication.AuthenticationHelper;
import com.qi.sso.web.config.ApplicationConfig;
import com.qi.sso.web.util.SSOUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class SSOController
 *
 * @author 张麒 2016/5/18.
 * @version Description:
 */
@Controller
public class SSOController {

    private static final Logger logger = new Logger(SSOController.class);

    @RequestMapping(value = "login.html")
    public String loginPage(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String account = request.getParameter(SSOConstants.LOGIN_ACCOUNT);
        String password = request.getParameter(SSOConstants.LOGIN_PASSWORD);
        // 登录操作请求
        if (StringUtil.isNotBlank(account) && StringUtil.isNotBlank(password)) {
            String decryptAccount = SSOUtil.loginDecrypt(request, account);
            String decryptPwd = SSOUtil.loginDecrypt(request, password);
            String remember = request.getParameter(SSOConstants.LOGIN_REMEMBER);
            // 解密正常
            if (StringUtil.isNotBlank(decryptAccount) && StringUtil.isNotBlank(decryptPwd)) {
                try {
                    String form_url = request.getParameter(SSOConstants.PARAM_FROM_URL);
                    String portal = request.getParameter(SSOConstants.LOGIN_PORTAL);
                    // 验证登录信息，返回用户对象
                    boolean bool = true;
                    // 默认登录成功
                    if (bool) {
                        AuthenticationHelper helper = new AuthenticationHelper(request, response);
                        // 记住账号
                        if (StringUtil.isNotBlank(remember)) {
                            // 记录cookie
                            helper.setCookie(SSOConstants.COOKIE_REMEMBER_LOGIN_ACCOUNT, EncrypterTool.encrypt(EncrypterTool.Security.Des3, decryptAccount));
                        } else {
                            // 删除cookie
                            helper.clearCookie(SSOConstants.COOKIE_REMEMBER_LOGIN_ACCOUNT);
                        }
                        // 生成token
                        helper.buildToken(decryptAccount, "10000", request.getSession().getId(), "{account:\"" + decryptAccount + "\",password:\"" + decryptPwd + "\"}");
                        // 跳转成功页面
                        response.sendRedirect(SSOUtil.loginSuccess(form_url, portal));
                        return null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 登录失败，记录日志
            logger.error("登录错误：账户密码解密错误。");
            model.put(SSOConstants.LOGIN_ACCOUNT, decryptAccount);
            model.put(SSOConstants.LOGIN_REMEMBER, remember);
            model.put(CommonConstants.MESSAGES, "登录失败");
        }
        // 登录准备
        SSOUtil.loginBefore(model, request, response);
        // 用户注册页面
        model.put(SSOConstants.LOGIN_REGISTER, ApplicationConfig.REGISTER_URL);
        // 找回密码页面
        model.put(SSOConstants.LOGIN_FORGET, ApplicationConfig.FORGET_URL);
        return "login";
    }

    @RequestMapping(value = "logout.html", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            AuthenticationHelper helper = new AuthenticationHelper(request, response);
            helper.destroyToken();
        } catch (Exception e) {
            logger.error("登出异常", e);
        }
        return "redirect:/login.html";
    }
}
