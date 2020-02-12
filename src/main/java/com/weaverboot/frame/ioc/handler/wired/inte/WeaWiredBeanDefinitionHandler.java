package com.weaverboot.frame.ioc.handler.wired.inte;

import com.weaverboot.frame.aop.handler.aspectPointcut.inte.WeaAspectPointcutHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.handler.postProcessor.wired.inte.WeaIocWiredBeanPostProcessorHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.autowired.inte.WeaIocAutowiredHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.value.inte.WeaIocValueHandler;
import com.weaverboot.frame.ioc.postProcessor.wired.inte.WeaWiredBeanPostProcessor;

import java.io.IOException;

public interface WeaWiredBeanDefinitionHandler {

    Object wiredBean(AbstractWeaBeanDefinition abstractWeaBeanDefinition) throws IllegalAccessException, ClassNotFoundException, InstantiationException, IOException;

    Object createBean(AbstractWeaBeanDefinition abstractWeaBeanDefinition) throws IllegalAccessException, InstantiationException;

    WeaIocAutowiredHandler getWeaIocAutowiredHandler() throws IllegalAccessException, InstantiationException;

    void setWeaIocAutowiredHandler(WeaIocAutowiredHandler weaIocAutowiredHandler);

    WeaIocValueHandler getWeaIocValueHandler() throws IllegalAccessException, InstantiationException;

    void setWeaIocValueHandler(WeaIocValueHandler weaIocValueHandler);

    WeaAspectPointcutHandler getWeaAspectPointCutHandler() throws IllegalAccessException, InstantiationException;

    void setWeaAspectPointCutHandler(WeaAspectPointcutHandler weaAspectPointCutHandler);

    WeaIocWiredBeanPostProcessorHandler getWeaIocWiredBeanPostProcessorHandler() throws IllegalAccessException, InstantiationException;

    void setWeaIocWiredBeanPostProcessorHandler(WeaIocWiredBeanPostProcessorHandler weaIocWiredBeanPostProcessorHandler);

}
