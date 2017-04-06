package com.qi.common.database.datasource.dynamic;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Class ReadWriteAdvice
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class ReadWriteAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method arg0, Object[] arg1, Object arg2)
            throws Throwable {
        //判断该类是否加了注解
        if (arg2.getClass().isAnnotationPresent(ReadWriteDataSource.class)) {
            ReadWriteDataSource rwd = arg2.getClass().getAnnotation(ReadWriteDataSource.class);
            DbTypeHolder.setDbType(rwd.value());
        }
        // 判断该方法是否加了注解
        if (arg0.isAnnotationPresent(ReadWriteDataSource.class)) {
            ReadWriteDataSource rwd = arg0.getAnnotation(ReadWriteDataSource.class);
            DbTypeHolder.setDbType(rwd.value());
        }

    }
}
