package com.weaverboot.frame.ioc.beans.bean.definition.factory.register.impl;

import com.weaverboot.frame.ioc.anno.classAnno.WeaIocReplaceComponent;
import com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte.AbstractWeaRegisterBeanDefinitionFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.impl.DefaultWeaBeanDefinition;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.tools.baseTools.BaseTools;

import java.lang.annotation.Annotation;

public class WeaIocReplaceComponentRegisterBeanDinitionFactory extends AbstractWeaRegisterBeanDefinitionFactory {

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
