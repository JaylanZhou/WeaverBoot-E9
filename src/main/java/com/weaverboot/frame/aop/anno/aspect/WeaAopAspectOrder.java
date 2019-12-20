package com.weaverboot.frame.aop.anno.aspect;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WeaAopAspectOrder {

    int value();

}
