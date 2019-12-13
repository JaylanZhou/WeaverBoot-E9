package com.weaverboot.frame.dao.anno;


import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Deprecated
/**
 *
 * 分页注解，暂时废弃
 *
 * @Author : Jaylan Zhou
 *
 */
public @interface ColumnPageInfo {

    boolean isDisplay() default false;

    String width() default "";

    String text() default "";

    String column() default "";

    String orderkey() default "";

    String transmethod() default "";

    String otherpara() default "";

    String customCol() default "";

    int showType() default 0;

}
