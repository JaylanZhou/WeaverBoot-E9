package com.weaverboot.frame.ioc.beans.bean.definition.factory.register.impl;

import com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte.AbstractWeaRegisterBeanDefinitionFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.impl.DefaultWeaBeanDefinition;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

import java.lang.annotation.Annotation;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-01-06 11:38
 * @Version : 1.0
 */
public class WeaIocAspectRegisterBeanDefinitionFactory extends AbstractWeaRegisterBeanDefinitionFactory {

    @Override
    public AbstractWeaBeanDefinition creatWeaBeanDefinition(Class clazz, Annotation annotation) throws IllegalAccessException, ClassNotFoundException, InstantiationException {

        if (clazz == null){

            throw new RuntimeException("扫描的 class 不能是null");

        }

        AbstractWeaBeanDefinition weaBeanDefinition = new DefaultWeaBeanDefinition();

        getWeaCreateWeaBeanDefinitionPostProcessor().initWeaBeanDefinition(weaBeanDefinition,clazz,annotation);

        getWeaCreateWeaBeanDefinitionPostProcessor().postProcessBeforeRegister(weaBeanDefinition);

        String beanId = weaBeanDefinition.getBeanClassName();

        getWeaCreateWeaBeanDefinitionPostProcessor().initEarlyContainer(beanId,weaBeanDefinition);

        getWeaCreateWeaBeanDefinitionPostProcessor().postProcessAfterRegister(weaBeanDefinition);

        return null;

    }

}
