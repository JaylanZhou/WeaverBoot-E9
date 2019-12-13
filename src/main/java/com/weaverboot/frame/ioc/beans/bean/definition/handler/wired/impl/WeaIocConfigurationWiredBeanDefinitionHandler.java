package com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.impl;

import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.inte.AbstractWeaWiredBeanDefinitionHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

import java.io.IOException;

public class WeaIocConfigurationWiredBeanDefinitionHandler extends AbstractWeaWiredBeanDefinitionHandler {

    @Override
    public synchronized Object wiredBean(AbstractWeaBeanDefinition abstractWeaBeanDefinition) throws IllegalAccessException, ClassNotFoundException, InstantiationException, IOException {

        beforeWiredOperate(abstractWeaBeanDefinition);

        Object object = super.wiredBean(abstractWeaBeanDefinition);

        afterWiredOperate(abstractWeaBeanDefinition);

        return object;

    }
}
