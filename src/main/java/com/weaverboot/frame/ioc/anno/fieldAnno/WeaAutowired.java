package com.weaverboot.frame.ioc.anno.fieldAnno;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WeaAutowired {

    String value() default "";

}
