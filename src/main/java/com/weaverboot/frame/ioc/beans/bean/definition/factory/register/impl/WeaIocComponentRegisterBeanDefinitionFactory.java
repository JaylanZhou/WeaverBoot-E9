package com.weaverboot.frame.ioc.beans.bean.definition.factory.register.impl;

import com.weaverboot.frame.ioc.anno.classAnno.WeaIocComponent;
import com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte.AbstractWeaRegisterBeanDefinitionFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.register.inte.WeaRegisterIocAnnoHandler;
import com.weaverboot.frame.ioc.beans.context.impl.DefaultWeaApplicationContext;
import com.weaverboot.frame.ioc.beans.context.inte.WeaApplicationContext;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.beans.bean.definition.impl.DefaultWeaBeanDefinition;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.tools.baseTools.BaseTools;

import java.lang.annotation.Annotation;

/**
 *
 * 注册标准注解WeaIocComponent的注册工厂实现类
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2019-12-17
 *
 */

public class WeaIocComponentRegisterBeanDefinitionFactory extends AbstractWeaRegisterBeanDefinitionFactory {

    /**
     *
     * WeaIocComponent的注册方法
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

        if (clazz.isAnnotationPresent(WeaIocComponent.class)) {

            WeaIocComponent weaIocComponent = (WeaIocComponent) clazz.getAnnotation(WeaIocComponent.class);

            weaBeanDefinition.setBeanAnnotation(weaIocComponent);

            if (BaseTools.notNullString(weaIocComponent.value())) {

                beanId = weaIocComponent.value();

            }

        }

        weaBeanDefinition.setBeanId(beanId);

        WeaIocContainer.setEarlyBeandefinition(beanId, weaBeanDefinition);

        WeaIocContainer.getEarlyBeandifinitionList().add(beanId);

        return weaBeanDefinition;

    }

}
