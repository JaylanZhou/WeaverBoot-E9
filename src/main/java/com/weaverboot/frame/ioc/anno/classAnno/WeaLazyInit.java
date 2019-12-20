package com.weaverboot.frame.ioc.anno.classAnno;

import java.lang.annotation.*;

/**
 *
 * 懒加载注解
 *
 * @Author : Jaylan Zhou
 *
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WeaLazyInit {
}
