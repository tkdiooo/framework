package com.qi.common.spring.exception.xml;

import com.alibaba.fastjson.JSONObject;
import com.qi.common.constants.CommonConstants;
import com.qi.common.constants.i18n.I18NConstants.Tips;
import com.qi.common.model.exception.BizException;
import com.qi.common.model.exception.VerifyException;
import com.qi.common.spring.exception.abs.BasicExceptionHandler;
import com.qi.common.util.ByteSizeUtil;
import com.qi.common.util.PropertyUtil;
import com.qi.common.util.ResourceUtil;
import com.qi.common.util.ThrowableUtil;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class ExceptionInterceptor
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class GlobalExceptionResolver extends BasicExceptionHandler implements HandlerExceptionResolver, Ordered {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        JSONObject json = new JSONObject();
        json.put(CommonConstants.SUCCESS, false);
        json.put("layout", "layout/empty.vm");
        // 404异常
        if (ex instanceof NoHandlerFoundException) {
            json.put(CommonConstants.MESSAGES, ThrowableUtil.getRootMessage(ex));
            return handleError(request, response, json, CommonConstants.VIEW_404, HttpStatus.NOT_FOUND, ex);
        }
        // 文件上传异常
        if (ex instanceof MaxUploadSizeExceededException) {
            json.put(CommonConstants.MESSAGES, ResourceUtil.getMessage(Tips.ExceptionUpload, ByteSizeUtil.MB().toMBFrac(Long.valueOf(PropertyUtil.getProps(CommonConstants.MULTIPART_MAX_FILE_SIZE))) + "MB"));
        }
        // 业务异常
        else if (ex instanceof BizException) {
            BizException bex = ((BizException) ex);
            json.put(CommonConstants.MESSAGES, ResourceUtil.getMessage(bex.getTips(), bex.getParams()));
        }
        // 校验异常
        else if (ex instanceof VerifyException) {
            VerifyException vex = ((VerifyException) ex);
            json.put(CommonConstants.MESSAGES, ResourceUtil.getMessage(vex.getTips(), vex.getParams()));
            json.put(CommonConstants.MESSAGES_DETAIL, vex.getResult());
        } else {
            json.put(CommonConstants.MESSAGES, ResourceUtil.getMessage(Tips.ExceptionSys));
        }

        return handleError(request, response, json, CommonConstants.VIEW_500, HttpStatus.NOT_FOUND, ex);
//
//        // TODO 系统异常页面缺少
//        if (HttpUtil.isAjax(request)) {
//            try {
//                ResponseUtil.writeJson(json, response);
//            } catch (IOException e) {
//                logger.error(e.getMessage(), e);
//            }
//            return null;
//        } else {
//            Map<String, Object> model = new HashMap<>();
//            if (ex instanceof BizException) {
//                BizException bizEx = (BizException) ex;
////                if (UMConstants.RespCode.PMS_NOT_LOGIN.getCode().equals(bizEx.getErrorCode())) {
////                    model.put("errmsg", "请重新登录");
////                    return new ModelAndView("login", model);
////                }
//            }
//            model.put("ex", ex);
//            model.put("layout", "layout/empty.vm");
//            return new ModelAndView("exception", model);
//        }
    }

    @Override
    public int getOrder() {
        // 记录日志的优先级最高
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
