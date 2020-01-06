package com.weaverboot.frame.ioc.postProcessor.wired.impl;

import com.weaverboot.frame.aop.handler.inte.WeaAopHandler;
import com.weaverboot.frame.aop.prop.WeaAopProperties;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.beans.bean.definition.utils.WeaIocCheckUtils;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.postProcessor.wired.inte.AbstractWeaWiredBeanPostProcessor;
import com.weaverboot.tools.logTools.LogTools;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-01-06 10:00
 * @Version : 1.0
 */
public class WeaReplaceComponentWiredBeanPostProcessor extends AbstractWeaWiredBeanPostProcessor {

    /**
     *
     * 注入后操作
     *
     * @param abstractWeaBeanDefinition
     */

    @Override
    public void postProcessAfterInitialization(AbstractWeaBeanDefinition abstractWeaBeanDefinition){

        try {

            WeaAopHandler weaAopHandler = WeaAopProperties.DEFAULT_WEA_AOP_HANDLER.newInstance();

            weaAopHandler.initReplace(abstractWeaBeanDefinition);

            String beanId = abstractWeaBeanDefinition.getBeanId();

            if (WeaIocCheckUtils.checkIsSingleton(abstractWeaBeanDefinition)) {

                WeaIocContainer.getBeingCreateBeandefinitionMap().remove(beanId);

                WeaIocContainer.getBeandefinitionMap().put(beanId, abstractWeaBeanDefinition);

            }

        } catch (Exception e){

            LogTools.error("注入替换接口错误，原因为：" + e.getMessage());

        }

    }

    /**
     *
     * 注入前操作
     *
     * @param abstractWeaBeanDefinition
     * @throws InstantiationException
     * @throws IllegalAccessException
     */

    @Override
    public void postProcessBeforeInitialization(AbstractWeaBeanDefinition abstractWeaBeanDefinition) {

        String beanId = abstractWeaBeanDefinition.getBeanId();

        if (WeaIocCheckUtils.checkIsSingleton(abstractWeaBeanDefinition)) {

            WeaIocContainer.getEarlyBeandefinitionMap().remove(beanId);

            WeaIocContainer.setBeingCreateBeandefinition(beanId, abstractWeaBeanDefinition);

        }

    }

}
