package com.weaverboot.frame.ioc.anno.methodAnno;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WeaReplaceAfter {

    String value();

    String order() default "0";


}
