package com.weaverboot.frame.ioc.handler.wired.anno.inte;

import java.lang.reflect.Method;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-02-14 19:57
 * @Version : 1.0
 */
public interface WeaWiredMethodAnnoInvokeHandler {

    void invokeWiredMethod(Method method,Object object,String beanName);

}
