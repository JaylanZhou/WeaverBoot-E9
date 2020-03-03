package com.weaverboot.frame.ioc.anno.parameterAnno;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WeaParamBean {

    boolean reciveEmpty() default false;

}
