package com.weaverboot.frame.ioc.beans.bean.definition.factory.register.impl;

import com.weaverboot.frame.ioc.anno.classAnno.WeaIocComponent;
import com.weaverboot.frame.ioc.anno.classAnno.WeaIocConfiguration;
import com.weaverboot.frame.ioc.anno.classAnno.WeaLazyInit;
import com.weaverboot.frame.ioc.anno.classAnno.WeaPrototype;
import com.weaverboot.frame.ioc.anno.methodAnno.WeaIocBean;
import com.weaverboot.frame.ioc.anno.parameterAnno.WeaParam;
import com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte.AbstractWeaRegisterBeanDefinitionFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.impl.DefaultWeaBeanDefinition;
import com.weaverboot.frame.ioc.beans.bean.definition.impl.DefaultWeaFactoryBean;
import com.weaverboot.frame.ioc.beans.bean.definition.impl.WeaRootBeanDefinition;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.tools.baseTools.BaseTools;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * WeaIocConfiguration 配置注解的默认注册工厂类
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2019-12-17
 *
 */

public class WeaIocConfigurationRegisterBeanDefinitionFactory extends AbstractWeaRegisterBeanDefinitionFactory {

    /**
     *
     * WeaIocConfiguration的注册方法
     *
     * @param clazz
     * @param annotation
     * @return
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     */

    @Override
    public AbstractWeaBeanDefinition creatWeaBeanDefinition(Class clazz, Annotation annotation) throws IllegalAccessException, ClassNotFoundException, InstantiationException {

        if (clazz == null){

            throw new RuntimeException("扫描的 class 不能是null");

        }

        AbstractWeaBeanDefinition weaBeanDefinition = new WeaRootBeanDefinition();

        getWeaCreateWeaBeanDefinitionPostProcessor().initWeaBeanDefinition(weaBeanDefinition,clazz,annotation);

        getWeaCreateWeaBeanDefinitionPostProcessor().postProcessBeforeRegister(weaBeanDefinition);

        String beanId = weaBeanDefinition.getBeanClassName();

        if (clazz.isAnnotationPresent(WeaIocConfiguration.class)) {

            WeaIocConfiguration weaIocConfiguration = (WeaIocConfiguration) clazz.getAnnotation(WeaIocConfiguration.class);

            weaBeanDefinition.setBeanAnnotation(weaIocConfiguration);

            if (BaseTools.notNullString(weaIocConfiguration.value())) {

                beanId = weaIocConfiguration.value();

            }

            registerConfigurationBean(clazz);

        }

        getWeaCreateWeaBeanDefinitionPostProcessor().initEarlyContainer(beanId,weaBeanDefinition);

        getWeaCreateWeaBeanDefinitionPostProcessor().postProcessAfterRegister(weaBeanDefinition);

        return weaBeanDefinition;

    }

    /**
     *
     * 注册配置类中的Bean
     *
     * @param clazz
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     */

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

                Parameter[] parameters = method.getParameters();

                if (parameters == null || parameters.length == 0){

                    abstractWeaBeanDefinition.setWeaFactoryBean(new DefaultWeaFactoryBean(clazz,method,null));

                } else {

                    List<String> beanIds = new ArrayList<>();

                    for (Parameter parameter : parameters
                         ) {

                        if (parameter.isAnnotationPresent(WeaParam.class)){

                            WeaParam weaParam = parameter.getAnnotation(WeaParam.class);

                            beanIds.add(weaParam.name());

                        } else {

                            beanIds.add(parameter.getType().getName());

                        }

                    }

                    abstractWeaBeanDefinition.setWeaFactoryBean(new DefaultWeaFactoryBean(clazz,method,beanIds));

                }

                WeaIocContainer.setEarlyBeandefinition(beanId, abstractWeaBeanDefinition);

                WeaIocContainer.getEarlyBeandifinitionList().add(beanId);

            }

        }

    }

}
