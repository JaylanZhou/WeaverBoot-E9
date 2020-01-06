package com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.handler.register.inte.WeaRegisterIocAnnoHandler;
import com.weaverboot.frame.ioc.postProcessor.register.inte.WeaCreateWeaBeanDefinitionPostProcessor;

import java.lang.annotation.Annotation;

/**
 *
 * BeanDefinition 创建工厂
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2019-12-17
 *
 */

public interface WeaRegisterBeanDefinitionFactory {

    /**
     *
     * 创建 BeanDefinition
     *
     * @param clazz
     * @param annotation
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */

    AbstractWeaBeanDefinition creatWeaBeanDefinition(Class clazz, Annotation annotation) throws IllegalAccessException, ClassNotFoundException, InstantiationException;

    WeaCreateWeaBeanDefinitionPostProcessor getWeaCreateWeaBeanDefinitionPostProcessor() throws IllegalAccessException, InstantiationException;

    void setWeaCreateWeaBeanDefinitionPostProcessor(WeaCreateWeaBeanDefinitionPostProcessor weaCreateWeaBeanDefinitionPostProcessor);

}
