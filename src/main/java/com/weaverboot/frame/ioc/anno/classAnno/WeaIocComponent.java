package com.weaverboot.frame.ioc.anno.classAnno;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WeaIocComponent {

    String value() default "";

}
