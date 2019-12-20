package com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.anno.value.impl;

import com.weaverboot.frame.ioc.anno.fieldAnno.WeaIocValue;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.anno.value.inte.AbstractWeaIocValueHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.anno.value.utils.WeaIocValueTransDataUtil;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.logTools.LogTools;
import weaver.general.BaseBean;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DefaultWeaIocValueHandler extends AbstractWeaIocValueHandler {

    private String get_suffix = "get";

    private String set_suffix = "set";

    private BaseBean baseBean = new BaseBean();


    @Override
    public void valueField(Field field, Object object) throws IllegalAccessException, IOException {

        if (field.isAnnotationPresent(WeaIocValue.class)){

            field.setAccessible(true);

            WeaIocValue weaIocValue = field.getAnnotation(WeaIocValue.class);

            String value = weaIocValue.value();

            if (WeaIocValueTransDataUtil.checkIsSpEL(value)){

                value = WeaIocValueTransDataUtil.getPropertiesValue(value);

            }

            if (BaseTools.notNullString(value)) {

                field.set(object, WeaIocValueTransDataUtil.transFieldDataToBasicType(value, field.getType()));

            }

        }

    }

    @Override
    public void methodField(Method method, Object object) throws IllegalAccessException, IOException, InvocationTargetException {

        if (method.isAnnotationPresent(WeaIocValue.class)){

            WeaIocValue weaIocValue = method.getAnnotation(WeaIocValue.class);

            String value = weaIocValue.value();

            if (WeaIocValueTransDataUtil.checkIsSpEL(value)){

                value = WeaIocValueTransDataUtil.getPropertiesValue(value);

            }

            if (BaseTools.notNullString(value)) {

                if (method.getName().startsWith(get_suffix)){

                    Object getResult = method.invoke(object);

                } else if (method.getName().startsWith(set_suffix)){



                }

                else{

                    LogTools.writeLog("@WeaIocValue注解只允许标注在get，set方法上，方法 " + method.getName() + " 注入失败");

                }

            }

        }

    }

}
