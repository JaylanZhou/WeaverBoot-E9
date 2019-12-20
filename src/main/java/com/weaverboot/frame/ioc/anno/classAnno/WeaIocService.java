package com.weaverboot.frame.ioc.anno.classAnno;

import java.lang.annotation.*;

/**
 *
 * IOC 业务层注解
 *
 * @Author : Jaylan Zhou
 *
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@WeaIocComponent
public @interface WeaIocService{

    String value() default "";

}
