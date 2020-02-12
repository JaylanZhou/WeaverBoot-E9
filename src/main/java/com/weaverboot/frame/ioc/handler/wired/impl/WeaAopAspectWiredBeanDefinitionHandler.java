package com.weaverboot.frame.ioc.handler.wired.impl;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.handler.wired.inte.AbstractWeaWiredBeanDefinitionHandler;

import java.io.IOException;

public class WeaAopAspectWiredBeanDefinitionHandler extends AbstractWeaWiredBeanDefinitionHandler {

    @Override
    public Object wiredBean(AbstractWeaBeanDefinition abstractWeaBeanDefinition) throws IllegalAccessException, ClassNotFoundException, InstantiationException, IOException {

        synchronized (lockObject) {

            Object object = super.wiredBean(abstractWeaBeanDefinition);

            return object;

        }

    }
}
