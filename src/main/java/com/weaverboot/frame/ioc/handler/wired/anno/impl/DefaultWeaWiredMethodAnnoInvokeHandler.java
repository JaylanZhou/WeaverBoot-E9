package com.weaverboot.frame.ioc.handler.wired.anno.impl;

import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.handler.wired.anno.inte.WeaWiredFieldAnnoHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.inte.WeaWiredMethodAnnoHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.inte.WeaWiredMethodAnnoInvokeHandler;
import com.weaverboot.tools.logTools.LogTools;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-02-14 19:58
 * @Version : 1.0
 */
public class DefaultWeaWiredMethodAnnoInvokeHandler implements WeaWiredMethodAnnoInvokeHandler {

    @Override
    public void invokeWiredMethod(Method method, Object object, String beanName) {

        Annotation[] annotations = method.getAnnotations();

        for (Annotation annotation : annotations
        ) {

            try {

                Class<WeaWiredMethodAnnoHandler> weaWiredMethodAnnoHandlerClass = (Class<WeaWiredMethodAnnoHandler>) WeaIocContainer.getWiredMethodAnnoHandlerMap().get(annotation.annotationType());

                if (weaWiredMethodAnnoHandlerClass != null){

                    WeaWiredMethodAnnoHandler weaWiredMethodAnnoHandler = weaWiredMethodAnnoHandlerClass.newInstance();

                    weaWiredMethodAnnoHandler.wiredMethod(method,object,beanName);

                }

            } catch (Exception e) {

                LogTools.writeLog("执行方法注解" + annotation.getClass().getName() + "的注入逻辑失败，原因为:" + e.getMessage());

            }


        }

    }

}
