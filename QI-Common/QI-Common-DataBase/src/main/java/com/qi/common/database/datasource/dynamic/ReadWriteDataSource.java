package com.qi.common.database.datasource.dynamic;

import com.qi.common.database.dao.DBType;

import java.lang.annotation.*;

/**
 * Class ReadWriteDataSource
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ReadWriteDataSource {
    DBType value() default DBType.WRITE;
}
