package com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.postProcessor.register.inte.WeaCreateWeaBeanDefinitionPostProcessor;
import com.weaverboot.frame.ioc.prop.WeaIocProperties;
import com.weaverboot.frame.ioc.handler.register.inte.WeaRegisterIocAnnoHandler;

import java.lang.annotation.Annotation;

/**
 *
 * BeanDefinition 创建工厂默认实现
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2019-12-17
 *
 */

public abstract class AbstractWeaRegisterBeanDefinitionFactory implements WeaRegisterBeanDefinitionFactory {

    //类加载器
    protected ClassLoader classLoader = this.getClass().getClassLoader();

    private WeaCreateWeaBeanDefinitionPostProcessor weaCreateWeaBeanDefinitionPostProcessor;

    @Override
    public WeaCreateWeaBeanDefinitionPostProcessor getWeaCreateWeaBeanDefinitionPostProcessor() throws IllegalAccessException, InstantiationException {

        if (weaCreateWeaBeanDefinitionPostProcessor == null){

            return WeaIocProperties.DEFAULT_WEA_CREATE_WEA_BEAN_DEFINITION_POST_PROCESSOR.newInstance();

        }

        return weaCreateWeaBeanDefinitionPostProcessor;
    }

    @Override
    public void setWeaCreateWeaBeanDefinitionPostProcessor(WeaCreateWeaBeanDefinitionPostProcessor weaCreateWeaBeanDefinitionPostProcessor) {
        this.weaCreateWeaBeanDefinitionPostProcessor = weaCreateWeaBeanDefinitionPostProcessor;
    }

}
