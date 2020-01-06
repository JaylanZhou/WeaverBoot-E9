package com.weaverboot.frame.ioc.beans.context.impl;

import com.weaverboot.frame.ioc.handler.init.inte.WeaInitBeanDefinitionHandler;
import com.weaverboot.frame.ioc.handler.wired.factory.inte.WeaWiredBeanDefinitionFactory;
import com.weaverboot.frame.ioc.handler.wired.inte.WeaWiredBeanDefinitionHandler;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.handler.init.impl.DefaultWeaInitBeanDefinitionHandler;
import com.weaverboot.frame.ioc.handler.scan.impl.DefaultWeaScanBeanDefinitionHandler;
import com.weaverboot.frame.ioc.handler.scan.inte.WeaScanBeanDefinitionHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.beans.context.inte.WeaApplicationContext;
import com.weaverboot.frame.ioc.prop.WeaIocProperties;
import com.weaverboot.tools.logTools.LogTools;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DefaultWeaApplicationContext implements WeaApplicationContext {

    private WeaScanBeanDefinitionHandler weaScanBeanDefinitionHandler;

    private WeaInitBeanDefinitionHandler weaInitBeanDefinitionHandler;

    public DefaultWeaApplicationContext() {

        weaInitBeanDefinitionHandler = new DefaultWeaInitBeanDefinitionHandler();

        weaScanBeanDefinitionHandler = new DefaultWeaScanBeanDefinitionHandler();

    }

    @Override
    public void refresh() {

        try {

            WeaIocContainer.cleanContainer();

            weaScanBeanDefinitionHandler.scanBeanDefinition();

            weaInitBeanDefinitionHandler.initBeanDefinition();

        } catch (Exception e) {

            LogTools.writeLog("获取bean发生错误，原因为:" + e.getMessage());

        }

    }

    @Override
    public Object getBean(String beanId) {

        if (WeaIocContainer.getBeanDefinition(beanId) == null) {

            if (WeaIocContainer.getBeingCreateBeanDefinition(beanId) == null){

                if (WeaIocContainer.getEarlyBeanDefinition(beanId) == null) {

                    return null;

                } else {

                    AbstractWeaBeanDefinition abstractWeaBeanDefinition;

                    if (WeaIocContainer.getBeingCreateBeanDefinition(beanId) != null) {

                        abstractWeaBeanDefinition = WeaIocContainer.getBeingCreateBeanDefinition(beanId);

                    } else {

                        abstractWeaBeanDefinition = WeaIocContainer.getEarlyBeanDefinition(beanId);

                    }

                    try {

                        WeaWiredBeanDefinitionFactory weaWiredBeanDefinitionFactory = WeaIocProperties.DEFAULT_WEA_WIRED_BEAN_DEFINITION_FACTORY.newInstance();

                        WeaWiredBeanDefinitionHandler weaWiredBeanDefinitionHandler = weaWiredBeanDefinitionFactory.getHandler(abstractWeaBeanDefinition);

                        Object object = weaWiredBeanDefinitionHandler.wiredBean(abstractWeaBeanDefinition);

                        return object;

                    } catch (Exception e) {

                        LogTools.writeLog("获取bean发生错误，原因为:" + e.getMessage());

                        throw new RuntimeException("获取bean发生错误，原因为:" + e.getMessage());

                    }

                }

             } else {

                return WeaIocContainer.getBeingCreateBeanDefinition(beanId);

            }

        } else {

            return WeaIocContainer.getBeanDefinition(beanId).getBeanObject();

        }

    }

    @Override
    public <T> T getBean(Class<T> tClass) {

        String beanId = tClass.getName();

        Object object = getBean(beanId);

        return (T) object;
    }

    @Override
    public Map<String, Object> getBeansWithAnnotation(Class<Annotation> annotationClass) {

        try {

            WeaWiredBeanDefinitionFactory weaWiredBeanDefinitionFactory = WeaIocProperties.DEFAULT_WEA_WIRED_BEAN_DEFINITION_FACTORY.newInstance();

            Map<String,Object> resultMap = new HashMap<>();

            for (String beanId : WeaIocContainer.getEarlyBeandefinitionMap().keySet()
            ) {

                AbstractWeaBeanDefinition abstractWeaBeanDefinition = WeaIocContainer.getEarlyBeandefinitionMap().get(beanId);

                if (abstractWeaBeanDefinition.getBeanClass().isAnnotationPresent(annotationClass)) {

                    Object object = weaWiredBeanDefinitionFactory.getHandler(abstractWeaBeanDefinition).wiredBean(abstractWeaBeanDefinition);

                    resultMap.put(beanId,object);

                }

            }

            for (String beanId : WeaIocContainer.getBeandefinitionMap().keySet()
                 ) {

                AbstractWeaBeanDefinition abstractWeaBeanDefinition = WeaIocContainer.getBeanDefinition(beanId);

                if (abstractWeaBeanDefinition.getBeanClass().isAnnotationPresent(annotationClass)) {

                    Object object = abstractWeaBeanDefinition.getBeanObject();

                    resultMap.put(beanId,object);

                }

            }

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public Map<AbstractWeaBeanDefinition, Map<String, Method>> getReplaceAfterApi(String apiUrl) {

        return WeaIocContainer.getReplaceAfter(apiUrl);

    }

    @Override
    public Map<AbstractWeaBeanDefinition, Map<String, Method>> getReplaceBeforeApi(String apiUrl) {

        return WeaIocContainer.getReplaceBefore(apiUrl);

    }

}
