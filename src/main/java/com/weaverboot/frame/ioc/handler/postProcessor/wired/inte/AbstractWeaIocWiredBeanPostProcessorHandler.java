package com.weaverboot.frame.ioc.handler.postProcessor.wired.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.postProcessor.wired.inte.WeaWiredBeanPostProcessor;

import java.util.List;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-02-12 15:01
 * @Version : 1.0
 */
public abstract class AbstractWeaIocWiredBeanPostProcessorHandler implements WeaIocWiredBeanPostProcessorHandler {


    @Override
    public WeaWiredBeanPostProcessor getInstanceOfBeanPostProcessor(AbstractWeaBeanDefinition abstractWeaBeanDefinition) {

        WeaWiredBeanPostProcessor object = null;

        try {

            if (abstractWeaBeanDefinition.getBeanObject() == null) {

                object = (WeaWiredBeanPostProcessor) abstractWeaBeanDefinition.getBeanClass().newInstance();

            } else {

                object = (WeaWiredBeanPostProcessor) abstractWeaBeanDefinition.getBeanObject();

            }

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        }

        return object;
    }
}
