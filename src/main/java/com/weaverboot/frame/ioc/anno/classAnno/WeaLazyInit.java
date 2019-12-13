package com.weaverboot.frame.ioc.anno.classAnno;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WeaLazyInit {
}
