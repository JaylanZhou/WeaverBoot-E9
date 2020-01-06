package com.weaverboot.frame.ioc.handler.wired.anno.autowired.inte;

import java.io.IOException;
import java.lang.reflect.Field;

public interface WeaIocAutowiredHandler {

    void autoWiredField(Field field, Object object) throws IllegalAccessException, ClassNotFoundException, InstantiationException, IOException;

    Object checkIsImplBeanAndWired(Class fieldType,boolean isCustomBeanId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException;

    Object checkDependcy(String beanId, Field field,boolean isCustomBeanId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException;

}
