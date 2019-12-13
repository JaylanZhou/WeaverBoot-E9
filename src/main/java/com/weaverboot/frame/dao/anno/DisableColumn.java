package com.weaverboot.frame.dao.anno;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 *
 * 字段无效注解，标记即不会被扫描
 *
 * @Author : Jaylan Zhou
 *
 */

public @interface DisableColumn {

    String remark() default "";

}
