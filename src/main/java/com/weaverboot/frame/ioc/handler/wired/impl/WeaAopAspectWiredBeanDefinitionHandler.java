package com.weaverboot.frame.ioc.handler.wired.impl;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.handler.wired.inte.AbstractWeaWiredBeanDefinitionHandler;
import com.weaverboot.frame.ioc.postProcessor.wired.impl.WeaAopAspectWiredBeanPostProcessor;

import java.io.IOException;

public class WeaAopAspectWiredBeanDefinitionHandler extends AbstractWeaWiredBeanDefinitionHandler {

    public WeaAopAspectWiredBeanDefinitionHandler(){

        this.setWeaBeanPostProcessor(new WeaAopAspectWiredBeanPostProcessor());

    }

    @Override
    public Object wiredBean(AbstractWeaBeanDefinition abstractWeaBeanDefinition) throws IllegalAccessException, ClassNotFoundException, InstantiationException, IOException {

        synchronized (lockObject) {

            Object object = super.wiredBean(abstractWeaBeanDefinition);

            return object;

        }

    }
}
