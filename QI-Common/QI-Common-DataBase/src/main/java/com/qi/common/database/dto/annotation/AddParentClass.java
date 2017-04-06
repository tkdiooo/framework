package com.qi.common.database.dto.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class AddParentClass
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AddParentClass {

    boolean AddParentClass() default false;

    String DefaultValueClass() default "";
}
