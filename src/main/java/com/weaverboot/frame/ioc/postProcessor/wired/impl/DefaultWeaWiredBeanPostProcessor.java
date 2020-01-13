package com.weaverboot.frame.ioc.postProcessor.wired.impl;

import com.weaverboot.frame.aop.advisor.advisor.inte.WeaAopAdvisor;
import com.weaverboot.frame.aop.handler.aspectPointcut.impl.DefaultWeaAspectPointcutHandler;
import com.weaverboot.frame.aop.handler.aspectPointcut.inte.WeaAspectPointcutHandler;
import com.weaverboot.frame.aop.pointcut.inte.WeaAopPointCut;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.beans.bean.definition.utils.WeaIocCheckUtils;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.postProcessor.wired.inte.AbstractWeaWiredBeanPostProcessor;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.classTools.ReflectTools;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 注入前后操作拓展节点默认实现类
 *
 * @Author : Jaylan Zhou
 * @Date : 2020-01-06 09:20
 * @Version : 1.0
 */
public class DefaultWeaWiredBeanPostProcessor extends AbstractWeaWiredBeanPostProcessor {

    private WeaAspectPointcutHandler weaAspectPointcutHandler;

    public DefaultWeaWiredBeanPostProcessor(){

        this.weaAspectPointcutHandler = new DefaultWeaAspectPointcutHandler();

    }

    public DefaultWeaWiredBeanPostProcessor(WeaAspectPointcutHandler weaAspectPointcutHandler){

        this.weaAspectPointcutHandler = weaAspectPointcutHandler;

    }

    /**
     *
     * 注入后操作
     *
     * @param abstractWeaBeanDefinition
     */

    @Override
    public void postProcessAfterInitialization(AbstractWeaBeanDefinition abstractWeaBeanDefinition){

        String beanId = abstractWeaBeanDefinition.getBeanId();

        if (WeaIocCheckUtils.checkIsSingleton(abstractWeaBeanDefinition)){

            WeaIocContainer.getBeingCreateBeandefinitionMap().remove(beanId);

            WeaIocContainer.getBeandefinitionMap().put(beanId,abstractWeaBeanDefinition);

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

        if (WeaIocCheckUtils.checkIsSingleton(abstractWeaBeanDefinition)) { //检查是否是单例创建

            WeaIocContainer.getEarlyBeandefinitionMap().remove(beanId);

            WeaIocContainer.setBeingCreateBeandefinition(beanId, abstractWeaBeanDefinition);

        }

        this.weaAspectPointcutHandler.registerProxy(abstractWeaBeanDefinition);

    }

}
