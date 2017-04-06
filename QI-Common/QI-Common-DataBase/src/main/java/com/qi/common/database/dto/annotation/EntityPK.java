package com.qi.common.database.dto.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class EntityPK
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EntityPK {
    String Pk();

    boolean defaultColumn() default true;

    String tableName();
}
