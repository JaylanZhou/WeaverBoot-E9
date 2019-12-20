package com.weaverboot.frame.aop.anno.advice;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WeaAopAround {

    String value();

}
