package com.weaverboot.frame.ioc.anno.fieldAnno;

import java.lang.annotation.*;

/**
 *
 * 属性值注入注解
 *
 * 注入规则：可注入八种基本数据类型以及String，会根据字段类型自动识别
 * 也可通过${}的方式注入weaverboot.properties的属性
 *
 * @Author : Jaylan Zhou
 *
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WeaIocValue {

    String value();

}
