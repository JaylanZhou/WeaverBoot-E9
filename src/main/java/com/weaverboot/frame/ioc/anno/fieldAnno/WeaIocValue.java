package com.weaverboot.frame.ioc.anno.fieldAnno;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WeaIocValue {

    String value();

}
