package com.weaverboot.frame.ioc.postProcessor.register.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.handler.register.inte.WeaRegisterIocAnnoHandler;

import java.lang.annotation.Annotation;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-01-06 11:42
 * @Version : 1.0
 */
public interface WeaCreateWeaBeanDefinitionPostProcessor {

    //bean初始化前增强
    void postProcessBeforeRegister(AbstractWeaBeanDefinition abstractWeaBeanDefinition);

    //bean初始化后增强
    void postProcessAfterRegister(AbstractWeaBeanDefinition abstractWeaBeanDefinition);

    WeaRegisterIocAnnoHandler getWeaRegisterIocAnnoHandler() throws IllegalAccessException, InstantiationException;

    void setWeaRegisterIocAnnoHandler(WeaRegisterIocAnnoHandler weaRegisterIocAnnoHandler);

    //bean初始化前增强
    void initWeaBeanDefinition(AbstractWeaBeanDefinition abstractWeaBeanDefinition, Class clazz, Annotation annotation) throws InstantiationException, IllegalAccessException;

    void initEarlyContainer(String beanId,AbstractWeaBeanDefinition abstractWeaBeanDefinition);

}
