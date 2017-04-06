package com.qi.common.database.service.impl;

import com.qi.common.database.service.IBaseService;
import org.springframework.context.MessageSource;

/**
 * Class BaseServiceImpl
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class BaseServiceImpl implements IBaseService {

    protected MessageSource messageSource;

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

}
