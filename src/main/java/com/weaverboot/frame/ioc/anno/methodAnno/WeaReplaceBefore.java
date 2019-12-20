package com.weaverboot.frame.ioc.anno.methodAnno;

import java.lang.annotation.*;

/**
 *
 * 前置方法标记类，与WeaIocReplaceComponent注解共同使用
 *
 * 被此注解标记的方法，会作为拦截接口的前置方法执行
 *
 * 被标注的方法，返回类型必须为void，且参数为WeaReplaceBeforeParam
 *
 * @Author : Jaylan Zhou
 *
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WeaReplaceBefore {

    String value();

    String order() default "0";

}
