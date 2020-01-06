package com.weaverboot.frame.ioc.postProcessor.register.impl;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.handler.register.inte.WeaRegisterIocAnnoHandler;
import com.weaverboot.frame.ioc.postProcessor.register.inte.AbstractWeaCreateWeaBeanDefinitionPostProcessor;

import java.lang.annotation.Annotation;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-01-06 11:49
 * @Version : 1.0
 */
public class DefaultWeaCreateWeaBeanDefinitionPostProcessor extends AbstractWeaCreateWeaBeanDefinitionPostProcessor {

    @Override
    public void postProcessBeforeRegister(AbstractWeaBeanDefinition abstractWeaBeanDefinition) throws InstantiationException, IllegalAccessException {

    }

    @Override
    public void postProcessAfterRegister(AbstractWeaBeanDefinition abstractWeaBeanDefinition) {

    }

    @Override
    public void initWeaBeanDefinition(AbstractWeaBeanDefinition abstractWeaBeanDefinition, Class clazz, Annotation annotation) throws InstantiationException, IllegalAccessException {

        abstractWeaBeanDefinition.setBeanClassName(clazz.getName());

        abstractWeaBeanDefinition.setBeanClass(clazz);

        abstractWeaBeanDefinition.setBeanAnnotation(annotation);

        WeaRegisterIocAnnoHandler weaRegisterIocAnnoHandler = getWeaRegisterIocAnnoHandler();

        weaRegisterIocAnnoHandler.handleLazyInit(abstractWeaBeanDefinition);

        weaRegisterIocAnnoHandler.handlePrototype(abstractWeaBeanDefinition);

    }

    @Override
    public void initEarlyContainer(String beanId, AbstractWeaBeanDefinition weaBeanDefinition) {

        weaBeanDefinition.setBeanId(beanId);

        WeaIocContainer.setEarlyBeandefinition(beanId, weaBeanDefinition);

        WeaIocContainer.getEarlyBeandifinitionList().add(beanId);

    }
}
