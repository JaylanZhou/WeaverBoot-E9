package com.weaverboot.frame.ioc.beans.bean.definition.handler.init.impl;

import com.weaverboot.frame.ioc.beans.bean.definition.handler.init.inte.AbstractWeaInitBeanDefinitionHandler;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class DefaultWeaInitBeanDefinitionHandler extends AbstractWeaInitBeanDefinitionHandler {

    private ClassLoader classLoader;

    public DefaultWeaInitBeanDefinitionHandler(){

        classLoader = this.getClass().getClassLoader();

    }

    @Override
    public void initBeanDefinition() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {

        classLoader = this.getClass().getClassLoader();

        for (String beanId : WeaIocContainer.getEarlyBeandifinitionList()
             ) {

            AbstractWeaBeanDefinition abstractWeaBeanDefinition = WeaIocContainer.getEarlyBeanDefinition(beanId);

            if (abstractWeaBeanDefinition != null && !abstractWeaBeanDefinition.isLazyInit()){

                getWeaWiredBeanDefinitionFactory().getHandler(abstractWeaBeanDefinition).wiredBean(abstractWeaBeanDefinition);

            } else {

                continue;

            }

        }

    }

}
