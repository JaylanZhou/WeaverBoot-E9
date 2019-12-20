package com.weaverboot.frame.aop.anno.pointcut;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WeaAopPointCut {

    String value();

}
