package com.weaverboot.frame.ioc.anno.methodAnno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 与@WeaIocConfiguration注解搭配使用，被此注解标注的方法，其返回类型会作为bean注入到容器中
 *
 * 其注册的实例为此方法返回的对象
 *
 * @Author : Jaylan Zhou
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WeaIocBean {

    String value() default "";

    String initMethod() default "";

    String destoryMethod() default "";

}
