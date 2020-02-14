package com.weaverboot.frame.ioc.handler.wired.anno.impl;

import com.weaverboot.frame.ioc.anno.fieldAnno.WeaIocValue;
import com.weaverboot.frame.ioc.handler.wired.anno.inte.WeaWiredFieldAnnoHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.value.utils.WeaIocValueTransDataUtil;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.logTools.LogTools;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-02-13 22:47
 * @Version : 1.0
 */
public class WeaWiredValueFieldAnnoHandler implements WeaWiredFieldAnnoHandler {

    @Override
    public void wiredField(Field field, Object object,String beanName) {

        if (field.isAnnotationPresent(WeaIocValue.class)){

            field.setAccessible(true);

            WeaIocValue weaIocValue = field.getAnnotation(WeaIocValue.class);

            String value = weaIocValue.value();

            if (WeaIocValueTransDataUtil.checkIsSpEL(value)){

                try {

                    value = WeaIocValueTransDataUtil.getPropertiesValue(value);

                } catch (IOException e) {

                    LogTools.error("从配置文件注入" + object.getClass().getName() + "的属性" + field.getName() + "发生错误，配置的EL表达式为" + value + ",错误原因为:" + e.getMessage());

                    value = null;

                }

            }

            if (BaseTools.notNullString(value)) {

                try {

                    field.set(object, WeaIocValueTransDataUtil.transFieldDataToBasicType(value, field.getType()));

                } catch (IllegalAccessException e) {

                    LogTools.error("设置" + object.getClass().getName() + "的属性" + field.getName() + "发生错误，错误原因为:" + e.getMessage());

                }

            }

        }

    }

}
