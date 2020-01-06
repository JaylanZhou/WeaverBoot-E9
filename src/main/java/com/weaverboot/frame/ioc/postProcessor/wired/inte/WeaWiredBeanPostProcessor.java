package com.weaverboot.frame.ioc.postProcessor.wired.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-01-06 09:17
 * @Version : 1.0
 */
public interface WeaWiredBeanPostProcessor {

    //bean初始化前增强
    void postProcessBeforeInitialization(AbstractWeaBeanDefinition abstractWeaBeanDefinition);

    //bean初始化后增强
    void postProcessAfterInitialization(AbstractWeaBeanDefinition abstractWeaBeanDefinition);

}
