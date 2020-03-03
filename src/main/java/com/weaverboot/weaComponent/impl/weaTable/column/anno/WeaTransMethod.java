package com.weaverboot.weaComponent.impl.weaTable.column.anno;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WeaTransMethod {

    String value();

    String otherpara() default "";

    String otherpara2() default "";

    boolean forceExecute() default false;

}
