package com.weaverboot.frame.dao.anno;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WeaSqlWhere {

    String value();

}
