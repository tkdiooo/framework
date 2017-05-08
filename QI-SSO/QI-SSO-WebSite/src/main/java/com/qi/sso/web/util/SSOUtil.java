package com.qi.sso.web.util;

import com.qi.common.constants.LabelConstants;
import com.qi.common.security.EncrypterTool;
import com.qi.common.security.rsa.KeyPairModel;
import com.qi.common.security.rsa.RSA;
import com.qi.common.util.*;
import com.qi.menu.model.dto.SystemDto;
import com.qi.menu.rpc.cache.PortalCacheFactory;
import com.qi.menu.rpc.hessian.consumer.PortalConsumer;
import com.qi.sso.common.constants.SSOConstants;
import com.qi.sso.core.authentication.AuthenticationHelper;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class SSOUtil
 *
 * @author 张麒 2016/8/10.
 * @version Description:
 */
public class SSOUtil {

    /**
     * 登录之前准备
     *
     * @param model    ModelMap
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    public static void loginBefore(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        // RSA加密公钥生成
        KeyPairModel keyPair;
        if (null != RSA.LOCAL_KEYPAIRMODEL.get(request.getSession().getId())) {
            keyPair = RSA.LOCAL_KEYPAIRMODEL.get(request.getSession().getId());
        } else {
            keyPair = RSA.getKeys();
            RSA.LOCAL_KEYPAIRMODEL.put(request.getSession().getId(), keyPair);
        }

        String Modulus = keyPair.getPublicKey().getModulus().toString(16);
        String Exponent = keyPair.getPublicKey().getPublicExponent().toString(16);
        model.put("Digits", Modulus.length());
        model.put("Modulus", Modulus);
        model.put("Exponent", Exponent);
        // form_url处理
        String form_url = request.getParameter(SSOConstants.PARAM_FROM_URL);
        if (StringUtil.isNotBlank(form_url)) {
            model.put(SSOConstants.PARAM_FROM_URL, form_url);
        }

        AuthenticationHelper helper = new AuthenticationHelper(request, response);
        String account = helper.getCookie(SSOConstants.COOKIE_REMEMBER_LOGIN_ACCOUNT);
        if (StringUtil.isNotBlank(account)) {
            model.put(SSOConstants.LOGIN_ACCOUNT, EncrypterTool.decrypt(EncrypterTool.Security.Des3, account));
            model.put(SSOConstants.LOGIN_REMEMBER, "on");
        }
        try {
            List<SystemDto> dataSet = PortalCacheFactory.getBasicSystemCache();
            if (ListUtil.isContentNull(dataSet)) {
                // 获取门户列表
//                PortalConsumer consumer = (PortalConsumer) SpringContextUtil.getBean("portalConsumer");
//                ActionResult<SystemDto> ar = consumer.findValidPortal();
//                model.put("portal", ar.getDataMap());
            } else {
//                model.put("portal", MapUtil.toMap(dataSet, "code", SystemDto.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录成功处理
     *
     * @param form_url Form Url
     * @param portal   门户编号
     * @return 成功跳转页面
     */
    public static String loginSuccess(String form_url, String portal) {
        PortalConsumer consumer = (PortalConsumer) SpringContextUtil.getBean("portalConsumer");
        SystemDto dto = consumer.getPortalByCode(portal).getResult();
        // form_url为空，直接赋值当前系统首页路径
        if (StringUtil.isBlank(form_url)) {
            // 默认首页
            form_url = dto.getPortalUrl();
        }
        // form_url不为空
        else {
            // 解密，并且替换80端口
            form_url = EncrypterTool.decrypt(EncrypterTool.Security.Des3ECB, form_url).replace(":80", "");
            // 判断是否有嵌套路径
            while (form_url.startsWith(SSOConstants.PARAM_FROM_URL)) {
                form_url = EncrypterTool.decrypt(EncrypterTool.Security.Des3ECB, form_url.substring(SSOConstants.PARAM_FROM_URL.length() + 1, form_url.length())).replace(":80", "");
            }
            // 如果路径包含平台首页
//            if (form_url.contains("/platform/index.html")) {
//                form_url = dto.getPortalUrl();
//            }
            // 如果路径不包含系统路径，或包含门户入口
//            else
            if (!form_url.contains((dto.getDomain() + dto.getContextpath())) || form_url.contains(dto.getContextpath() + LabelConstants.PERIOD + LabelConstants.HTML)) {
                // 默认首页
                form_url = dto.getPortalUrl();
            } else {
                form_url = dto.getPortalUrl() + LabelConstants.QUESTION + SSOConstants.PARAM_FROM_URL + LabelConstants.EQUAL + EncrypterTool.encrypt(EncrypterTool.Security.Des3ECB, form_url);
            }
        }
        return form_url;
    }

    /**
     * 登录信息解密
     *
     * @param request HttpServletRequest
     * @param result  待解密信息
     * @return 解密后明文
     */
    public static String loginDecrypt(HttpServletRequest request, String result) {
        KeyPairModel keyPair = RSA.LOCAL_KEYPAIRMODEL.get(request.getSession().getId());
        if (null != keyPair) {
            byte[] result_byte = RSA.simpleDecrypt(keyPair.getPrivateKey(), HexUtil.hexStringToBytes(result));
            if (null != result_byte) {
                StringBuilder sb = new StringBuilder(new String(result_byte));
                return sb.reverse().toString();
            }
            RSA.LOCAL_KEYPAIRMODEL.remove(request.getSession().getId());
        }
        return "";
    }
}
