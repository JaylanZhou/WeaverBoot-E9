package com.weaverboot.frame.ioc.anno.classAnno;

import java.lang.annotation.*;

/**
 *
 * IOC 接口拦截组件注解
 *
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@WeaIocComponent
public @interface WeaIocReplaceComponent {

    String value() default "";

}
