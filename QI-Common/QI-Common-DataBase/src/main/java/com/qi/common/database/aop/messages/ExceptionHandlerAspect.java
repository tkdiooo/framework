package com.qi.common.database.aop.messages;

import com.qi.common.database.aop.BaseAspect;
import com.qi.common.model.abs.BasicException;
import com.qi.common.tool.Logger;
import com.qi.common.util.ResourceUtil;
import org.aspectj.lang.ProceedingJoinPoint;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

/**
 * Class ExceptionHandlerAspect
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class ExceptionHandlerAspect extends BaseAspect implements MessageSourceAware {

    private final Logger logger = new Logger(this.getClass());

    private MessageSource messageSource;

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public Object exceptionHandler(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (BasicException e) {
            e.setMessage(ResourceUtil.getMessage(e.getTips(), e.getParams()));
            logger.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }
}
