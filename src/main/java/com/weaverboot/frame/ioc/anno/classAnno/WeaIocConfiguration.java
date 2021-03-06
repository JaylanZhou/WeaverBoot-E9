package com.weaverboot.frame.ioc.anno.classAnno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 配置注册类注解
 *
 * @Author : Jaylan Zhou
 *
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@WeaIocComponent
public @interface WeaIocConfiguration {

    String value() default "";

}
