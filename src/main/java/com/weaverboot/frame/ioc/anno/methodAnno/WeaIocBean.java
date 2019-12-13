package com.weaverboot.frame.ioc.anno.methodAnno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WeaIocBean {

    String value() default "";

    String initMethod() default "";

    String destoryMethod() default "";

}
