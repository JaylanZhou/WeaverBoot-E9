package com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.anno.autowired.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

import java.io.IOException;
import java.lang.reflect.Field;

public interface WeaIocAutowiredHandler {

    void autoWiredField(AbstractWeaBeanDefinition abstractWeaBeanDefinition,Field field, Object object) throws IllegalAccessException, ClassNotFoundException, InstantiationException, IOException;

}
