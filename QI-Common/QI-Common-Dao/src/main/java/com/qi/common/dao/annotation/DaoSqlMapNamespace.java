package com.qi.common.dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class DaoSqlMapNamespace
 *
 * @author 张麒 2016/5/12.
 * @version Description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DaoSqlMapNamespace {
    String value();
}
