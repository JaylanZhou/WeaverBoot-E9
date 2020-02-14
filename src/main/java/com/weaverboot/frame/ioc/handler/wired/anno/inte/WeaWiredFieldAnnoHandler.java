package com.weaverboot.frame.ioc.handler.wired.anno.inte;

import java.lang.reflect.Field;

public interface WeaWiredFieldAnnoHandler {

    void wiredField(Field field, Object object,String beanName);

}
