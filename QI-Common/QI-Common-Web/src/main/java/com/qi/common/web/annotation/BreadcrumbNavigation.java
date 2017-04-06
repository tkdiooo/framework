package com.qi.common.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 面包屑导航
 *
 * @author 张麒 2017/2/9.
 * @version Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BreadcrumbNavigation {

    String root() default "";

    String value() default "";

    String aliasFor() default "";
}
