package com.weaverboot.frame.ioc.beans.bean.definition.factory.register.impl;

import com.weaverboot.frame.ioc.anno.classAnno.WeaIocService;
import com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte.AbstractWeaRegisterBeanDefinitionFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.impl.DefaultWeaBeanDefinition;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.tools.baseTools.BaseTools;

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

public class WeaIocServiceRegisterBeanDefinitionFactory extends AbstractWeaRegisterBeanDefinitionFactory {

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
    public AbstractWeaBeanDefinition creatWeaBeanDefinition(Class clazz, Annotation annotation) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        if (clazz == null){

            throw new RuntimeException("扫描的 class 不能是null");

        }

        AbstractWeaBeanDefinition weaBeanDefinition = new DefaultWeaBeanDefinition();

        String beanId = super.beforeRegisrer(weaBeanDefinition,clazz,annotation);

        if (clazz.isAnnotationPresent(WeaIocService.class)) {

            WeaIocService weaIocService = (WeaIocService) clazz.getAnnotation(WeaIocService.class);

            weaBeanDefinition.setBeanAnnotation(weaIocService);

            if (BaseTools.notNullString(weaIocService.value())) {

                beanId = weaIocService.value();

            }

        }

        weaBeanDefinition.setBeanId(beanId);

        WeaIocContainer.setEarlyBeandefinition(beanId, weaBeanDefinition);

        WeaIocContainer.getEarlyBeandifinitionList().add(beanId);

        return weaBeanDefinition;

    }
}
