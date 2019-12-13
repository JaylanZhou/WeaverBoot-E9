package com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

import java.lang.annotation.Annotation;

public interface WeaRegisterBeanDefinitionFactory {

    AbstractWeaBeanDefinition creatWeaBeanDefinition(Class clazz, Annotation annotation) throws InstantiationException, IllegalAccessException, ClassNotFoundException;

}
