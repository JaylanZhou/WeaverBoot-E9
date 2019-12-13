package com.weaverboot.frame.dao.anno;


import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 *
 * 关联查询 - 单条目注解
 *
 * @Author : Jaylan Zhou
 *
 */
public @interface Association {

    /**
     *
     * 主表的外键，必须跟实体类中的外键变量保持名称一致，区分大小写
     *
     * @return
     */

    String refColumn();

    /**
     *
     * 关联表的主键，必须跟实体类中的主键变量保持名称一致，区分大小写
     *
     * @return
     */

    String whereColumn() default "";

    /**
     *
     * 暂时无用
     *
     * @return
     */

    String javaType() default "";

    /**
     *
     * 暂时无用
     *
     * @return
     */

    String columnType() default "";

    /**
     *
     * 暂时无用
     *
     * @return
     */

    String returnType() default "";

    /**
     *
     * tableName(非必填)：关联表的表名，缺省为去关联表实体类寻找getTableName方法，填写则会优先使用填写值，如果未填写且没有getTableName方法则会报错
     *
     * @return
     */

    String tableName() default "";

}
