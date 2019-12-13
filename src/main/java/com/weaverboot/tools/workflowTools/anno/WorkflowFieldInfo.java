package com.weaverboot.tools.workflowTools.anno;


import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 *
 * 创建流程注解
 *
 * @Author : Jaylan
 *
 */
public @interface WorkflowFieldInfo {

    /**
     *
     * 字段名称，缺省为字段变量名
     *
     * @return
     */

    public String name() default "";

    /**
     *
     * 字段是否可编辑，缺省为true
     *
     * @return
     */

    public boolean edit() default true;

    /**
     *
     * 字段是否可见，缺省为true
     *
     * @return
     */

    public boolean view() default true;

}
