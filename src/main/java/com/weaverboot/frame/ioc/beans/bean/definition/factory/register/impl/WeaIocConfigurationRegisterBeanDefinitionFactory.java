package com.weaverboot.frame.ioc.beans.bean.definition.factory.register.impl;

import com.weaverboot.frame.ioc.anno.classAnno.WeaIocComponent;
import com.weaverboot.frame.ioc.anno.classAnno.WeaIocConfiguration;
import com.weaverboot.frame.ioc.anno.classAnno.WeaLazyInit;
import com.weaverboot.frame.ioc.anno.classAnno.WeaPrototype;
import com.weaverboot.frame.ioc.anno.methodAnno.WeaIocBean;
import com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte.AbstractWeaRegisterBeanDefinitionFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.impl.DefaultWeaBeanDefinition;
import com.weaverboot.frame.ioc.beans.bean.definition.impl.DefaultWeaFactoryBean;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.tools.baseTools.BaseTools;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class WeaIocConfigurationRegisterBeanDefinitionFactory extends AbstractWeaRegisterBeanDefinitionFactory {
    @Override
    public AbstractWeaBeanDefinition creatWeaBeanDefinition(Class clazz, Annotation annotation) throws IllegalAccessException, ClassNotFoundException, InstantiationException {

        if (clazz == null){

            throw new RuntimeException("扫描的 class 不能是null");

        }

        AbstractWeaBeanDefinition weaBeanDefinition = new DefaultWeaBeanDefinition();

        String beanId = super.beforeRegisrer(weaBeanDefinition,clazz,annotation);

        if (clazz.isAnnotationPresent(WeaIocConfiguration.class)) {

            WeaIocConfiguration weaIocConfiguration = (WeaIocConfiguration) clazz.getAnnotation(WeaIocConfiguration.class);

            weaBeanDefinition.setBeanAnnotation(weaIocConfiguration);

            if (BaseTools.notNullString(weaIocConfiguration.value())) {

                beanId = weaIocConfiguration.value();

            }

            registerConfigurationBean(clazz);

        }

        weaBeanDefinition.setBeanId(beanId);

        WeaIocContainer.setEarlyBeandefinition(beanId, weaBeanDefinition);

        WeaIocContainer.getEarlyBeandifinitionList().add(beanId);

        return weaBeanDefinition;

    }

    private void registerConfigurationBean(Class clazz) throws IllegalAccessException, ClassNotFoundException, InstantiationException {

        Method[] methods = clazz.getMethods();

        for (Method method : methods
             ) {

            if (method.isAnnotationPresent(WeaIocBean.class)){

                WeaIocBean weaIocBean = method.getAnnotation(WeaIocBean.class);

                Class registerBeanClass = method.getReturnType();

                AbstractWeaBeanDefinition abstractWeaBeanDefinition = new DefaultWeaBeanDefinition();

                abstractWeaBeanDefinition.setBeanClass(registerBeanClass);

                abstractWeaBeanDefinition.setBeanClassName(registerBeanClass.getName());

                abstractWeaBeanDefinition.setBeanAnnotation(weaIocBean);

                String beanId = registerBeanClass.getName();

                if (BaseTools.notNullString(weaIocBean.value())) {

                    beanId = weaIocBean.value();

                }

                abstractWeaBeanDefinition.setBeanId(beanId);

                if (BaseTools.notNullString(weaIocBean.destoryMethod())){

                    abstractWeaBeanDefinition.setDestoryMethod(weaIocBean.destoryMethod());

                }

                if (BaseTools.notNullString(weaIocBean.initMethod())){

                    abstractWeaBeanDefinition.setInitMethod(weaIocBean.initMethod());

                }

                if (method.isAnnotationPresent(WeaLazyInit.class)){

                    abstractWeaBeanDefinition.setLazyInit(true);

                }

                if (method.isAnnotationPresent(WeaPrototype.class)){

                    abstractWeaBeanDefinition.setSingleton(false);

                    abstractWeaBeanDefinition.setPrototype(true);

                }

                abstractWeaBeanDefinition.setWeaFactoryBean(new DefaultWeaFactoryBean(clazz,method));

                WeaIocContainer.setEarlyBeandefinition(beanId, abstractWeaBeanDefinition);

                WeaIocContainer.getEarlyBeandifinitionList().add(beanId);

            }

        }

    }

}
