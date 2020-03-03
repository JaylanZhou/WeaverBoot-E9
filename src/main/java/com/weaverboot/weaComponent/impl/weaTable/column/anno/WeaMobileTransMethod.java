package com.weaverboot.weaComponent.impl.weaTable.column.anno;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WeaMobileTransMethod {

    String value();

    String mobileotherpara() default "";

}
