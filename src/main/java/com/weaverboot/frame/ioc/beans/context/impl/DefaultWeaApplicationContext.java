package com.weaverboot.frame.ioc.beans.context.impl;

import com.weaverboot.frame.ioc.beans.bean.definition.handler.init.inte.WeaInitBeanDefinitionHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.factory.inte.WeaWiredBeanDefinitionFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.inte.WeaWiredBeanDefinitionHandler;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.init.impl.DefaultWeaInitBeanDefinitionHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.scan.impl.DefaultWeaScanBeanDefinitionHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.scan.inte.WeaScanBeanDefinitionHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.beans.context.inte.WeaApplicationContext;
import com.weaverboot.frame.ioc.prop.WeaIocProperties;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class DefaultWeaApplicationContext implements WeaApplicationContext {

    private WeaScanBeanDefinitionHandler weaScanBeanDefinitionHandler;

    private WeaInitBeanDefinitionHandler weaInitBeanDefinitionHandler;

    public DefaultWeaApplicationContext(){

        weaInitBeanDefinitionHandler = new DefaultWeaInitBeanDefinitionHandler();

        weaScanBeanDefinitionHandler = new DefaultWeaScanBeanDefinitionHandler();

    }

    @Override
    public void refresh() {

        try {

            WeaIocContainer.cleanContainer();

            weaScanBeanDefinitionHandler.scanBeanDefinition();

            weaInitBeanDefinitionHandler.initBeanDefinition();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (InvocationTargetException e) {

            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object getBean(String beanId) {

        if (WeaIocContainer.getBeanDefinition(beanId) == null){

            if (WeaIocContainer.getEarlyBeanDefinition(beanId) == null){

                throw new RuntimeException("在容器中未找到:" + beanId);

            } else {

                AbstractWeaBeanDefinition abstractWeaBeanDefinition = WeaIocContainer.getEarlyBeanDefinition(beanId);

                try {

                    WeaWiredBeanDefinitionFactory weaWiredBeanDefinitionFactory = WeaIocProperties.DEFAULT_WEA_WIRED_BEAN_DEFINITION_FACTORY.newInstance();

                    WeaWiredBeanDefinitionHandler weaWiredBeanDefinitionHandler = weaWiredBeanDefinitionFactory.getHandler(abstractWeaBeanDefinition);

                    Object object = weaWiredBeanDefinitionHandler.wiredBean(abstractWeaBeanDefinition);

                    return object;

                } catch (InstantiationException e) {

                    throw new RuntimeException("获取bean发生错误，原因为:" + e.getMessage());

                } catch (IllegalAccessException e) {

                    throw new RuntimeException("获取bean发生错误，原因为:" + e.getMessage());

                } catch (ClassNotFoundException e) {

                    throw new RuntimeException("获取bean发生错误，原因为:" + e.getMessage());

                } catch (IOException e) {

                    throw new RuntimeException("获取bean发生错误，原因为:" + e.getMessage());

                }

            }

        } else {

            return WeaIocContainer.getBeanDefinition(beanId).getBeanObject();

        }

    }

    @Override
    public Map<AbstractWeaBeanDefinition, Map<String,Method>> getReplaceApi(String apiUrl) {

        return WeaIocContainer.getReplaceAfter(apiUrl);

    }

}
