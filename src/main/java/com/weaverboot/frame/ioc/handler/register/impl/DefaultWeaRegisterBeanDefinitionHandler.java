package com.weaverboot.frame.ioc.handler.register.impl;

import com.weaverboot.frame.ioc.beans.bean.definition.factory.register.impl.DefaultWeaRBDFFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte.WeaRBDFFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte.WeaRegisterBeanDefinitionFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.utils.WeaIocCheckUtils;
import com.weaverboot.frame.ioc.handler.register.inte.WeaRegisterBeanDefinitionHandler;
import com.weaverboot.tools.logTools.LogTools;

import java.lang.annotation.Annotation;

public class DefaultWeaRegisterBeanDefinitionHandler implements WeaRegisterBeanDefinitionHandler {

    private WeaRBDFFactory weaRBDFFactory;

    public DefaultWeaRegisterBeanDefinitionHandler(){

        weaRBDFFactory = new DefaultWeaRBDFFactory();

    }


    /**
     *
     * 注册 BeanDefinition
     *
     * @param clazz
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */

    @Override
    public void registerBeanDefinition(Class clazz) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        Annotation annotation = WeaIocCheckUtils.checkAnnotationComponenet(clazz);

        if (annotation != null){

            LogTools.info("注册了：" + clazz.getName());

            WeaRegisterBeanDefinitionFactory weaRegisterBeanDefinitionFactory = weaRBDFFactory.getFactory(clazz);

            weaRegisterBeanDefinitionFactory.creatWeaBeanDefinition(clazz,annotation);

        }

    }

}
