package com.weaverboot.frame.ioc.beans.bean.definition.handler.register.inte;

public interface WeaRegisterBeanDefinitionHandler {

    void registerBeanDefinition(Class clazz) throws IllegalAccessException, InstantiationException, ClassNotFoundException;

}
