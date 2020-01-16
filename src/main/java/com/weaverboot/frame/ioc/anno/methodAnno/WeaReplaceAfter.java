package com.weaverboot.frame.ioc.anno.methodAnno;

import java.lang.annotation.*;

/**
 *
 * 后置方法标记类，与WeaIocReplaceComponent注解共同使用
 *
 * 被此注解标记的方法，会作为拦截接口的后置方法执行
 *
 * 被标注的方法，返回类型必须为String，且参数为WeaReplaceAfterParam
 *
 * @Author : Jaylan Zhou
 *
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WeaReplaceAfter {

    String value(); //拦截的api地址

    int order(); //此方法在链中执行的顺序


}
