package com.weaverboot.frame.ioc.handler.register.inte;

public interface WeaRegisterBeanDefinitionHandler {

    void registerBeanDefinition(Class clazz) throws IllegalAccessException, InstantiationException, ClassNotFoundException;

}
