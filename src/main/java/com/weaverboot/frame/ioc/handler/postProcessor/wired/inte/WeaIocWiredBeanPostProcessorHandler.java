package com.weaverboot.frame.ioc.handler.postProcessor.wired.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.postProcessor.wired.inte.WeaWiredBeanPostProcessor;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-02-12 15:00
 * @Version : 1.0
 */
public interface WeaIocWiredBeanPostProcessorHandler {

    void handlePostProcessBeforeInitialization(Object object,String beanName);

    void handlePostProcessAfterInitialization(Object object,String beanName);

    WeaWiredBeanPostProcessor getInstanceOfBeanPostProcessor(AbstractWeaBeanDefinition abstractWeaBeanDefinition);

}
