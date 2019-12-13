package com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

public interface WeaRBDFFactory {

    WeaRegisterBeanDefinitionFactory getFactory(Class<AbstractWeaBeanDefinition> abstractWeaBeanDefinitionClass);

}
