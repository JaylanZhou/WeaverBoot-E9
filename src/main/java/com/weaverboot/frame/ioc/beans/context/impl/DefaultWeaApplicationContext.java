package com.weaverboot.frame.ioc.beans.context.impl;

import com.weaverboot.frame.ioc.beans.bean.definition.utils.WeaIocReplaceUriUtils;
import com.weaverboot.frame.ioc.handler.init.inte.WeaInitBeanDefinitionHandler;
import com.weaverboot.frame.ioc.handler.replace.weaReplaceApiAdvice.WeaReplaceApiAdvice;
import com.weaverboot.frame.ioc.handler.wired.anno.autowired.impl.DefaultWeaIocAutowiredHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.autowired.inte.WeaIocAutowiredHandler;
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
import java.util.List;
import java.util.Map;

public class DefaultWeaApplicationContext implements WeaApplicationContext {

    private WeaScanBeanDefinitionHandler weaScanBeanDefinitionHandler;

    private WeaInitBeanDefinitionHandler weaInitBeanDefinitionHandler;

    private WeaIocAutowiredHandler weaIocAutowiredHandler;

    public DefaultWeaApplicationContext() {

        weaInitBeanDefinitionHandler = new DefaultWeaInitBeanDefinitionHandler();

        weaScanBeanDefinitionHandler = new DefaultWeaScanBeanDefinitionHandler();

        weaIocAutowiredHandler = new DefaultWeaIocAutowiredHandler();

    }

    @Override
    public void refresh() {

        try {

            WeaIocContainer.cleanContainer();

            weaScanBeanDefinitionHandler.scanBeanDefinition();

            weaInitBeanDefinitionHandler.initBeanDefinition();

            WeaIocReplaceUriUtils.orderReplaceApiAdvice();

        } catch (Exception e) {

            LogTools.writeLog("获取bean发生错误，原因为:" + e.getMessage());

        }

    }

    @Override
    public Object getBean(String beanId) {

        try {

            if (WeaIocContainer.getBeanDefinition(beanId) == null) {

                if (WeaIocContainer.getBeingCreateBeanDefinition(beanId) == null) {

                    if (WeaIocContainer.getEarlyBeanDefinition(beanId) == null) {

                        return null;

                    } else {

                        AbstractWeaBeanDefinition abstractWeaBeanDefinition;

                        if (WeaIocContainer.getBeingCreateBeanDefinition(beanId) != null) {

                            abstractWeaBeanDefinition = WeaIocContainer.getBeingCreateBeanDefinition(beanId);

                        } else {

                            abstractWeaBeanDefinition = WeaIocContainer.getEarlyBeanDefinition(beanId);

                        }

                        WeaWiredBeanDefinitionFactory weaWiredBeanDefinitionFactory = WeaIocProperties.DEFAULT_WEA_WIRED_BEAN_DEFINITION_FACTORY.newInstance();

                        WeaWiredBeanDefinitionHandler weaWiredBeanDefinitionHandler = weaWiredBeanDefinitionFactory.getHandler(abstractWeaBeanDefinition);

                        return weaWiredBeanDefinitionHandler.wiredBean(abstractWeaBeanDefinition);

                    }

                } else {

                    return WeaIocContainer.getBeingCreateBeanDefinition(beanId);

                }

            } else {

                return WeaIocContainer.getBeanDefinition(beanId).getBeanObject();

            }

        } catch (Exception e){

            LogTools.error("获取bean发生错误，原因为:" + e.getMessage());

            return null;

        }

    }

    @Override
    public <T> T getBean(Class<T> tClass) {

        String beanId = tClass.getName();

        try {

            if (WeaIocContainer.getBeanDefinition(beanId) == null) {

                if (WeaIocContainer.getBeingCreateBeanDefinition(beanId) == null) {

                    if (WeaIocContainer.getEarlyBeanDefinition(beanId) == null) {

                        return (T) weaIocAutowiredHandler.checkIsImplBeanAndWired(tClass, false);

                    } else {

                        AbstractWeaBeanDefinition abstractWeaBeanDefinition;

                        if (WeaIocContainer.getBeingCreateBeanDefinition(beanId) != null) {

                            abstractWeaBeanDefinition = WeaIocContainer.getBeingCreateBeanDefinition(beanId);

                        } else {

                            abstractWeaBeanDefinition = WeaIocContainer.getEarlyBeanDefinition(beanId);

                        }

                        WeaWiredBeanDefinitionFactory weaWiredBeanDefinitionFactory = WeaIocProperties.DEFAULT_WEA_WIRED_BEAN_DEFINITION_FACTORY.newInstance();

                        WeaWiredBeanDefinitionHandler weaWiredBeanDefinitionHandler = weaWiredBeanDefinitionFactory.getHandler(abstractWeaBeanDefinition);

                        return (T) weaWiredBeanDefinitionHandler.wiredBean(abstractWeaBeanDefinition);

                    }

                } else {

                    return (T) WeaIocContainer.getBeingCreateBeanDefinition(beanId);

                }

            } else {

                return (T) WeaIocContainer.getBeanDefinition(beanId).getBeanObject();

            }

        } catch (Exception e){

            LogTools.error("获取bean发生错误，原因为:" + e.getMessage());

            return null;

        }

    }

    @Override
    @Deprecated
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

        } catch (Exception e) {

            LogTools.error("未找到标有该注解的类");

        }

        return null;
    }

    @Override
    public List<WeaReplaceApiAdvice> getReplaceAfterApi(String apiUrl) {

        return WeaIocContainer.getReplaceAfter(apiUrl);

    }

    @Override
    public List<WeaReplaceApiAdvice> getReplaceBeforeApi(String apiUrl) {

        return WeaIocContainer.getReplaceBefore(apiUrl);

    }

}
