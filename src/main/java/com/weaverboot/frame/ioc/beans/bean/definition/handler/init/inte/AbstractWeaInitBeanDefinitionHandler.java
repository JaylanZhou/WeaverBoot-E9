package com.weaverboot.frame.ioc.beans.bean.definition.handler.init.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.factory.inte.WeaWiredBeanDefinitionFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.inte.WeaWiredBeanDefinitionHandler;
import com.weaverboot.frame.ioc.prop.WeaIocProperties;
import com.weaverboot.frame.aop.handler.inte.WeaAopHandler;
import com.weaverboot.frame.aop.prop.WeaAopProperties;

public abstract class AbstractWeaInitBeanDefinitionHandler implements WeaInitBeanDefinitionHandler {

    private WeaWiredBeanDefinitionFactory weaWiredBeanDefinitionFactory;

    public WeaWiredBeanDefinitionFactory getWeaWiredBeanDefinitionFactory() throws IllegalAccessException, InstantiationException {

        if (weaWiredBeanDefinitionFactory == null){

            weaWiredBeanDefinitionFactory = WeaIocProperties.DEFAULT_WEA_WIRED_BEAN_DEFINITION_FACTORY.newInstance();

        }

        return weaWiredBeanDefinitionFactory;
    }

    public void setWeaWiredBeanDefinitionFactory(WeaWiredBeanDefinitionFactory weaWiredBeanDefinitionFactory) {
        this.weaWiredBeanDefinitionFactory = weaWiredBeanDefinitionFactory;
    }


}
