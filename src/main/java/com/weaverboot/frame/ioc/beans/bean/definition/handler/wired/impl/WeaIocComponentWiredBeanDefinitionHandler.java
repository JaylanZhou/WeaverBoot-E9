package com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.impl;

import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.inte.AbstractWeaWiredBeanDefinitionHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

import java.io.IOException;

public class WeaIocComponentWiredBeanDefinitionHandler extends AbstractWeaWiredBeanDefinitionHandler {

    @Override
    public Object wiredBean(AbstractWeaBeanDefinition abstractWeaBeanDefinition) throws IllegalAccessException, ClassNotFoundException, InstantiationException, IOException {

        synchronized (lockObject){

            beforeWiredOperate(abstractWeaBeanDefinition);

            Object object = super.wiredBean(abstractWeaBeanDefinition);

            afterWiredOperate(abstractWeaBeanDefinition);

            return object;

        }

    }

}
