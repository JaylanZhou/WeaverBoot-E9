package com.weaverboot.frame.ioc.handler.wired.anno.inte;

import java.lang.reflect.Method;

public interface WeaWiredMethodAnnoHandler {

    void wiredMethod(Method method, Object object,String beanName);

}
