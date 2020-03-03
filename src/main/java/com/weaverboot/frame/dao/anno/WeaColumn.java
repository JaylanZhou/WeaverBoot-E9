package com.weaverboot.frame.dao.anno;

import com.weaverboot.tools.enumTools.weaComponent.WeaAlignEnum;
import com.weaverboot.tools.enumTools.weaComponent.WeaBelongEnum;
import com.weaverboot.tools.enumTools.weaComponent.WeaBooleanEnum;
import com.weaverboot.tools.enumTools.weaComponent.WeaMobileViewTypeEnum;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WeaColumn {

    WeaAlignEnum align() default WeaAlignEnum.LEFT; //支持对齐方式   center,left,right

    int order() default 0;

    WeaAlignEnum dataAlign() default WeaAlignEnum.LEFT;

    WeaBooleanEnum hide() default WeaBooleanEnum.FALSE;

    String width() default "";

    String value(); //text

    String column() default "";

    String orderkey() default "";

    int showType() default 0;

    WeaBooleanEnum isPrimarykey() default WeaBooleanEnum.FALSE;

    WeaBooleanEnum isInputCol() default WeaBooleanEnum.FALSE;

    String collapse() default "";

    String labelid() default "";

    String thumbnail() default "";

    String customCol() default "";

    String key() default "";

    WeaBooleanEnum display() default WeaBooleanEnum.TRUE;

    WeaBelongEnum belong() default WeaBelongEnum.PC;

    WeaMobileViewTypeEnum mobileviewtype() default WeaMobileViewTypeEnum.HIGHLIGHT;

    WeaBooleanEnum isBase64() default WeaBooleanEnum.FALSE;

    String fixed() default "";

}
