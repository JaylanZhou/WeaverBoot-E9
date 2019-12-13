package com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.factory.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.inte.WeaWiredBeanDefinitionHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

public interface WeaWiredBeanDefinitionFactory {

    WeaWiredBeanDefinitionHandler getHandler(AbstractWeaBeanDefinition abstractWeaBeanDefinition);

}
