package com.weaverboot.frame.ioc.beans.bean.definition.factory.register.impl;

import com.weaverboot.frame.ioc.anno.classAnno.WeaIocCustomAnnoation;
import com.weaverboot.frame.ioc.anno.classAnno.WeaIocWiredBeanPostProcessor;
import com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte.AbstractWeaRegisterBeanDefinitionFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.impl.WeaRootBeanDefinition;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.handler.wired.anno.inte.WeaWiredFieldAnnoHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.inte.WeaWiredMethodAnnoHandler;
import com.weaverboot.frame.ioc.postProcessor.wired.inte.WeaWiredBeanPostProcessor;
import com.weaverboot.tools.enumTools.frame.ioc.WeaAnnoTargetEnum;
import com.weaverboot.tools.logTools.LogTools;

import java.lang.annotation.Annotation;

/**
 *
 * 默认 WeaIocService 注册逻辑处理类
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2019-12-17
 *
 */

public class WeaIocCustomAnnoationFactory extends AbstractWeaRegisterBeanDefinitionFactory {

    /**
     *
     * 注册默认的WeaIocService类
     *
     * @param clazz
     * @param annotation
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */

    @Override
    public AbstractWeaBeanDefinition creatWeaBeanDefinition(Class clazz, Annotation annotation) throws InstantiationException, IllegalAccessException {

        if (clazz == null){

            throw new RuntimeException("扫描的 class 不能是null");

        }

        AbstractWeaBeanDefinition weaBeanDefinition = new WeaRootBeanDefinition();

        getWeaCreateWeaBeanDefinitionPostProcessor().initWeaBeanDefinition(weaBeanDefinition,clazz,annotation);

        getWeaCreateWeaBeanDefinitionPostProcessor().postProcessBeforeRegister(weaBeanDefinition);

        WeaIocCustomAnnoation weaIocCustomAnnoation = (WeaIocCustomAnnoation) clazz.getAnnotation(WeaIocCustomAnnoation.class);

        WeaAnnoTargetEnum weaAnnoTargetEnum = weaIocCustomAnnoation.target();

        if (weaAnnoTargetEnum == WeaAnnoTargetEnum.FIELD){

            if (WeaWiredFieldAnnoHandler.class.isAssignableFrom(clazz)){

                if (WeaIocContainer.getWiredFieldAnnoHandlerMap().containsKey(weaIocCustomAnnoation.customAnnoation())){

                    LogTools.error("自定义注解" + weaIocCustomAnnoation.customAnnoation().getName() + "已存在，无法再次注入！");

                } else {

                    WeaIocContainer.getWiredFieldAnnoHandlerMap().put(weaIocCustomAnnoation.customAnnoation(), clazz);

                }

            } else {

                LogTools.error("字段自定义注解" + weaIocCustomAnnoation.customAnnoation().getName() + "的逻辑类" + clazz.getName() + "需要实现WeaWiredFieldAnnoHandler类");

            }


        } else if (weaAnnoTargetEnum == WeaAnnoTargetEnum.METHOD){

            if (WeaWiredMethodAnnoHandler.class.isAssignableFrom(clazz)){

                if (WeaIocContainer.getWiredMethodAnnoHandlerMap().containsKey(weaIocCustomAnnoation.customAnnoation())){

                    LogTools.error("自定义注解" + weaIocCustomAnnoation.customAnnoation().getName() + "已存在，无法再次注入！");

                } else {

                    WeaIocContainer.getWiredMethodAnnoHandlerMap().put(weaIocCustomAnnoation.customAnnoation(), clazz);

                }

            } else {

                LogTools.error("方法自定义注解" + weaIocCustomAnnoation.customAnnoation().getName() + "的逻辑类" + clazz.getName() + "需要实现WeaWiredMethodAnnoHandler类");

            }

        }

        //getWeaCreateWeaBeanDefinitionPostProcessor().initEarlyContainer(beanId,weaBeanDefinition);

        getWeaCreateWeaBeanDefinitionPostProcessor().postProcessAfterRegister(weaBeanDefinition);

        return weaBeanDefinition;

    }
}
