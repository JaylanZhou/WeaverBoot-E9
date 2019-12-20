package com.weaverboot.frame.ioc.anno.methodAnno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 注册判断条件 暂时无用
 *
 * @Author : Jaylan Zhou
 *
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WeaIocCondition {

    Class condition();

}
