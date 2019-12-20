package com.weaverboot.frame.ioc.beans.bean.definition.factory.register.impl;

import com.weaverboot.frame.ioc.anno.classAnno.WeaIocReplaceComponent;
import com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte.AbstractWeaRegisterBeanDefinitionFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.impl.DefaultWeaBeanDefinition;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.tools.baseTools.BaseTools;

import java.lang.annotation.Annotation;

/**
 *
 * WeaIocReplaceComponent 接口拦截类的默认注册工厂实现类
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2019-12-17
 *
 */

public class WeaIocReplaceComponentRegisterBeanDinitionFactory extends AbstractWeaRegisterBeanDefinitionFactory {

    /**
     *
     * 注册 WeaIocReplaceComponent 的方法
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

        if (clazz.isAnnotationPresent(WeaIocReplaceComponent.class)) {

            WeaIocReplaceComponent weaIocReplaceComponent = (WeaIocReplaceComponent) clazz.getAnnotation(WeaIocReplaceComponent.class);

            weaBeanDefinition.setBeanAnnotation(weaIocReplaceComponent);

            if (BaseTools.notNullString(weaIocReplaceComponent.value())) {

                beanId = weaIocReplaceComponent.value();

            }

        }

        weaBeanDefinition.setBeanId(beanId);

        WeaIocContainer.setEarlyBeandefinition(beanId, weaBeanDefinition);

        WeaIocContainer.getEarlyBeandifinitionList().add(beanId);

        return weaBeanDefinition;

    }

}
