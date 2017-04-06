package com.qi.common.database.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

import java.util.Map;

/**
 * Class BaseAspect
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public abstract class BaseAspect implements Ordered {
    private Map<String, Integer> aopOrderDefine;
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public BaseAspect() {
        super();
    }

    public void setAopOrderDefine(Map<String, Integer> aopOrderDefine) {
        this.aopOrderDefine = aopOrderDefine;
    }

    public int getOrder() {
        return aopOrderDefine.get(getClass().getName());
    }
}
