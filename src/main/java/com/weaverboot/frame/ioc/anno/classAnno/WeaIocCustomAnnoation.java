package com.weaverboot.frame.ioc.anno.classAnno;

import com.weaverboot.tools.enumTools.frame.ioc.WeaAnnoTargetEnum;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@WeaIocComponent
@Documented
public @interface WeaIocCustomAnnoation {

    Class<? extends Annotation> customAnnoation();

    WeaAnnoTargetEnum target();

}
