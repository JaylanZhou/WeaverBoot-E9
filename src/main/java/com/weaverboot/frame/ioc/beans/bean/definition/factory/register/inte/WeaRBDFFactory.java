package com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

/**
 *
 * 获取注册BeanDefinition创建工厂的接口
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2019-12-17
 *
 */


public interface WeaRBDFFactory {

    /**
     *
     * 获取BeanDefinition创建工厂
     *
     * @param abstractWeaBeanDefinitionClass
     * @return
     */

    WeaRegisterBeanDefinitionFactory getFactory(Class<AbstractWeaBeanDefinition> abstractWeaBeanDefinitionClass);

}
