package com.weaverboot.frame.ioc.postProcessor.register.impl;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.postProcessor.register.inte.AbstractWeaCreateWeaBeanDefinitionPostProcessor;

import java.lang.reflect.Method;


/**
 * @Author : Jaylan Zhou
 * @Date : 2020-01-06 11:49
 * @Version : 1.0
 */
public class DefaultWeaCreateWeaBeanDefinitionPostProcessor extends AbstractWeaCreateWeaBeanDefinitionPostProcessor {

    @Override
    public void postProcessBeforeRegister(AbstractWeaBeanDefinition abstractWeaBeanDefinition) {



    }

    @Override
    public void postProcessAfterRegister(AbstractWeaBeanDefinition abstractWeaBeanDefinition) {

        Method[] methods = abstractWeaBeanDefinition.getBeanClass().getMethods();



    }

}
