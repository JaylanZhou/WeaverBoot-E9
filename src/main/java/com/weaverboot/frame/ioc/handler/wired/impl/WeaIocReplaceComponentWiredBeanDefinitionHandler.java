package com.weaverboot.frame.ioc.handler.wired.impl;

import com.weaverboot.frame.aop.handler.replace.inte.WeaAopReplaceHandler;
import com.weaverboot.frame.aop.prop.WeaAopProperties;
import com.weaverboot.frame.ioc.handler.wired.inte.AbstractWeaWiredBeanDefinitionHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

import java.io.IOException;

public class WeaIocReplaceComponentWiredBeanDefinitionHandler extends AbstractWeaWiredBeanDefinitionHandler {


    @Override
    public Object wiredBean(AbstractWeaBeanDefinition abstractWeaBeanDefinition) throws IllegalAccessException, ClassNotFoundException, InstantiationException, IOException {

        synchronized (lockObject) {

            Object object = super.wiredBean(abstractWeaBeanDefinition);

            return object;

        }

    }

}
