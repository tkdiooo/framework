package com.qi.common.database.datasource.dynamic;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Class ReadWriteDataSourceInterceptor
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class ReadWriteDataSourceInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation arg0) throws Throwable {
        // 判断该方法是否加了注解
        if (arg0.getMethod().isAnnotationPresent(ReadWriteDataSource.class)) {
            ReadWriteDataSource rwd = arg0.getMethod().getAnnotation(ReadWriteDataSource.class);
            DbTypeHolder.setDbType(rwd.value());
        }
        //执行被拦截的方法，切记，如果此方法不调用，则被拦截的方法不会被执行。
        return arg0.proceed();
    }

}
