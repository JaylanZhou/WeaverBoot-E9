package com.weaverboot.frame.ioc.handler.init.impl;

import com.weaverboot.frame.ioc.handler.init.inte.AbstractWeaInitBeanDefinitionHandler;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.tools.logTools.LogTools;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class DefaultWeaInitBeanDefinitionHandler extends AbstractWeaInitBeanDefinitionHandler {

    private ClassLoader classLoader;

    public DefaultWeaInitBeanDefinitionHandler(){

        classLoader = this.getClass().getClassLoader();

    }

    @Override
    public void initBeanDefinition() {

        classLoader = this.getClass().getClassLoader();

        for (String beanId : WeaIocContainer.getEarlyBeandifinitionList()
             ) {

            AbstractWeaBeanDefinition abstractWeaBeanDefinition = WeaIocContainer.getEarlyBeanDefinition(beanId);

            if (abstractWeaBeanDefinition != null && !abstractWeaBeanDefinition.isLazyInit()){

                try {

                    getWeaWiredBeanDefinitionFactory().getHandler(abstractWeaBeanDefinition).wiredBean(abstractWeaBeanDefinition);

                } catch (Exception e){

                    LogTools.writeLog(abstractWeaBeanDefinition.getBeanClassName() + "注册错误，原因为:" + e.getMessage());

                    continue;

                }

            } else {

                continue;

            }

        }

    }

}
