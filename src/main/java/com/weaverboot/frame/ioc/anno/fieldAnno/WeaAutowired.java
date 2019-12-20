package com.weaverboot.frame.ioc.anno.fieldAnno;

import java.lang.annotation.*;

/**
 *
 * 自动注入注解
 *
 * 注入规则：指定Value则寻找Value注入，不指定则以标注变量的类型全名称注入
 *
 * @Author : Jaylan Zhou
 *
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WeaAutowired {

    String value() default "";

}
