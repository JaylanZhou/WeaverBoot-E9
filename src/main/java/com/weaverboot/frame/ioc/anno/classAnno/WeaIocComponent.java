package com.weaverboot.frame.ioc.anno.classAnno;

import java.lang.annotation.*;

/**
 *
 * 组件根注解，注册类组件由此派生
 *
 * 注册规则：如指定名称，则按指定的名称注入，否则按类的全路径注入
 *
 * @Author : Jaylan Zhou
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WeaIocComponent {

    String value() default "";

}
