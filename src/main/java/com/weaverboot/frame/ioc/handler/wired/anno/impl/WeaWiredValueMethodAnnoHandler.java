package com.weaverboot.frame.ioc.handler.wired.anno.impl;

import com.weaverboot.frame.ioc.anno.fieldAnno.WeaIocValue;
import com.weaverboot.frame.ioc.handler.wired.anno.inte.WeaWiredMethodAnnoHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.value.utils.WeaIocValueTransDataUtil;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.logTools.LogTools;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-02-13 22:58
 * @Version : 1.0
 */
public class WeaWiredValueMethodAnnoHandler implements WeaWiredMethodAnnoHandler {

    private String get_suffix = "get";

    private String set_suffix = "set";

    @Override
    public void wiredMethod(Method method, Object object, String beanName) {

        if (method.isAnnotationPresent(WeaIocValue.class)){

            WeaIocValue weaIocValue = method.getAnnotation(WeaIocValue.class);

            String value = weaIocValue.value();

            if (WeaIocValueTransDataUtil.checkIsSpEL(value)){

                try {

                    value = WeaIocValueTransDataUtil.getPropertiesValue(value);

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

            if (BaseTools.notNullString(value)) {

                if (method.getName().startsWith(get_suffix)){

                    try {

                        Object getResult = method.invoke(object);

                    } catch (Exception e) {

                        LogTools.error("注入" + object.getClass().getName() + "类的" + method + "方法");

                    }

                } else if (method.getName().startsWith(set_suffix)){



                }

                else{

                    LogTools.error("@WeaIocValue注解只允许标注在get，set方法上，方法 " + method.getName() + " 注入失败");

                }

            }

        }

    }

}
