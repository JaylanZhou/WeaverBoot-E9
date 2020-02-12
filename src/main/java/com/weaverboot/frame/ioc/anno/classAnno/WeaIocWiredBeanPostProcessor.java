package com.weaverboot.frame.ioc.anno.classAnno;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@WeaIocComponent
public @interface WeaIocWiredBeanPostProcessor {

    int order() default 0;

}
