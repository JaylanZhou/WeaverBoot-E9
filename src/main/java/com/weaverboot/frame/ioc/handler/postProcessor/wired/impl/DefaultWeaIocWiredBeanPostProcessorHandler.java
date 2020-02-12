package com.weaverboot.frame.ioc.handler.postProcessor.wired.impl;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.handler.postProcessor.wired.inte.AbstractWeaIocWiredBeanPostProcessorHandler;
import com.weaverboot.frame.ioc.postProcessor.wired.inte.WeaWiredBeanPostProcessor;

import java.util.List;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-02-12 15:02
 * @Version : 1.0
 */
public class DefaultWeaIocWiredBeanPostProcessorHandler extends AbstractWeaIocWiredBeanPostProcessorHandler {

    private List<AbstractWeaBeanDefinition> weaWiredBeanPostProcessorList = WeaIocContainer.getWeaWiredBeanPostProcessorList();

    @Override
    public void handlePostProcessBeforeInitialization(Object bean,String beanName) {


        for (AbstractWeaBeanDefinition ab : weaWiredBeanPostProcessorList
        ) {

            WeaWiredBeanPostProcessor weaWiredBeanPostProcessor = this.getInstanceOfBeanPostProcessor(ab);

            weaWiredBeanPostProcessor.postProcessBeforeInitialization(bean,beanName);

        }

    }

    @Override
    public void handlePostProcessAfterInitialization(Object bean,String beanName) {

        for (AbstractWeaBeanDefinition ab : weaWiredBeanPostProcessorList
        ) {

            WeaWiredBeanPostProcessor weaWiredBeanPostProcessor = this.getInstanceOfBeanPostProcessor(ab);

            weaWiredBeanPostProcessor.postProcessAfterInitialization(bean,beanName);

        }


    }

}
