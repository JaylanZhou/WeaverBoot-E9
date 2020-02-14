package com.weaverboot.frame.ioc.handler.wired.anno.impl;

import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.handler.wired.anno.inte.WeaWiredColumnAnnoInovkeHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.inte.WeaWiredFieldAnnoHandler;
import com.weaverboot.tools.logTools.LogTools;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-02-14 19:50
 * @Version : 1.0
 */
public class DefaultWeaWiredColumnAnnoInvokeHandler implements WeaWiredColumnAnnoInovkeHandler {

    @Override
    public void invokeWiredColumn(Field field, Object object,String beanName) {

        Annotation[] annotations = field.getAnnotations();

        for (Annotation annotation : annotations
             ) {

            try {

                Class<WeaWiredColumnAnnoHanlder> weaWiredFieldAnnoHandlerClass = (Class<WeaWiredColumnAnnoHanlder>) WeaIocContainer.getWiredFieldAnnoHandlerMap().get(annotation.annotationType());

                if (weaWiredFieldAnnoHandlerClass != null){

                    WeaWiredFieldAnnoHandler weaWiredFieldAnnoHandler = weaWiredFieldAnnoHandlerClass.newInstance();

                    weaWiredFieldAnnoHandler.wiredField(field,object,beanName);

                }

            } catch (Exception e) {

                LogTools.writeLog("执行字段注解" + annotation.getClass().getName() + "的注入逻辑失败，原因为:" + e.getMessage());

            }


        }

    }

}
