package com.weaverboot.frame.ioc.anno.classAnno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@WeaIocComponent
public @interface WeaIocConfiguration {

    String value() default "";

}
