package com.weaverboot.frame.aop.anno.aspect;

import com.weaverboot.frame.ioc.anno.classAnno.WeaIocComponent;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@WeaIocComponent
@Documented
public @interface WeaAopAspect {

    String value() default "";

    String pointCut();

    int order();

}
