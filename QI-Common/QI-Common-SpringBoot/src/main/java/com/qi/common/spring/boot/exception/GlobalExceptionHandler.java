package com.qi.common.spring.boot.exception;

import com.alibaba.fastjson.JSONObject;
import com.qi.common.constants.CommonConstants;
import com.qi.common.constants.i18n.I18NConstants;
import com.qi.common.model.exception.BizException;
import com.qi.common.model.exception.VerifyException;
import com.qi.common.spring.boot.constants.PropertyConstants;
import com.qi.common.spring.exception.abs.BasicExceptionHandler;
import com.qi.common.util.ResourceUtil;
import com.qi.common.util.ThrowableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class GlobalExceptionHandler
 *
 * @author 张麒 2017/3/29.
 * @version Description:
 */
@ControllerAdvice
public class GlobalExceptionHandler extends BasicExceptionHandler {

    @Autowired
    private PropertyConstants property;

    /**
     * 404异常捕获
     */
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handle404Error(HttpServletRequest request, HttpServletResponse response, Exception ex) throws Exception {
        JSONObject json = new JSONObject();
        json.put(CommonConstants.SUCCESS, false);
        json.put(CommonConstants.MESSAGES, ThrowableUtil.getRootMessage(ex));
        return handleError(request, response, json, CommonConstants.VIEW_404, HttpStatus.NOT_FOUND, ex);
    }

    /**
     * 文件上传异常捕获
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView handleError(HttpServletRequest request, HttpServletResponse response, MaxUploadSizeExceededException ex) throws Exception {
        JSONObject json = new JSONObject();
        json.put(CommonConstants.SUCCESS, false);
        json.put(CommonConstants.MESSAGES, ResourceUtil.getMessage(I18NConstants.Tips.ExceptionUpload, property.getMaxFileSize()));
        return handleError(request, response, json, CommonConstants.VIEW_500, HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    /**
     * 业务异常捕获
     */
    @ExceptionHandler(BizException.class)
    public ModelAndView runtimeExceptionHandler(HttpServletRequest request, HttpServletResponse response, BizException ex) throws Exception {
        JSONObject json = new JSONObject();
        json.put(CommonConstants.SUCCESS, false);
        json.put(CommonConstants.MESSAGES, ResourceUtil.getMessage(ex.getTips(), ex.getParams()));
        return handleError(request, response, json, CommonConstants.VIEW_500, HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    /**
     * 校验异常捕获
     */
    @ExceptionHandler(VerifyException.class)
    public ModelAndView runtimeExceptionHandler(HttpServletRequest request, HttpServletResponse response, VerifyException ex) throws Exception {
        JSONObject json = new JSONObject();
        json.put(CommonConstants.SUCCESS, false);
        json.put(CommonConstants.MESSAGES, ThrowableUtil.getRootMessage(ex));
        json.put(CommonConstants.MESSAGES_DETAIL, ex.getResult());
        return handleError(request, response, json, CommonConstants.VIEW_500, HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    /**
     * 运行异常捕获
     */
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView runtimeExceptionHandler(HttpServletRequest request, HttpServletResponse response, RuntimeException ex) throws Exception {
        JSONObject json = new JSONObject();
        json.put(CommonConstants.SUCCESS, false);
        json.put(CommonConstants.MESSAGES, ThrowableUtil.getRootMessage(ex));
        return handleError(request, response, json, CommonConstants.VIEW_500, HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    /**
     * 检查异常捕获
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest request, HttpServletResponse response, Exception ex) throws Exception {
        JSONObject json = new JSONObject();
        json.put(CommonConstants.SUCCESS, false);
        json.put(CommonConstants.MESSAGES, ThrowableUtil.getRootMessage(ex));
        return handleError(request, response, json, CommonConstants.VIEW_500, HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }
}
