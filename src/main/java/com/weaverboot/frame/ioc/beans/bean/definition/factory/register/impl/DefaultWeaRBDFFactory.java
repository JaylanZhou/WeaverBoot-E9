package com.weaverboot.frame.ioc.beans.bean.definition.factory.register.impl;

import com.weaverboot.frame.ioc.anno.classAnno.WeaIocComponent;
import com.weaverboot.frame.ioc.anno.classAnno.WeaIocConfiguration;
import com.weaverboot.frame.ioc.anno.classAnno.WeaIocReplaceComponent;
import com.weaverboot.frame.ioc.anno.classAnno.WeaIocService;
import com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte.AbstractWeaRBDFFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte.WeaRegisterBeanDefinitionFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

/**
 *
 * 获取不同注解的注册工厂 默认实现类
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2019-12-17
 *
 */

public class DefaultWeaRBDFFactory extends AbstractWeaRBDFFactory {

    /**
     *
     * 获取不同种类的注册工厂
     *
     * @param abstractWeaBeanDefinitionClass
     * @return
     */

    @Override
    public WeaRegisterBeanDefinitionFactory getFactory(Class<AbstractWeaBeanDefinition> abstractWeaBeanDefinitionClass) {

        if (abstractWeaBeanDefinitionClass.isAnnotationPresent(WeaIocComponent.class)){

            return new WeaIocComponentRegisterBeanDefinitionFactory();

        } else if (abstractWeaBeanDefinitionClass.isAnnotationPresent(WeaIocService.class)){

            return new WeaIocServiceRegisterBeanDefinitionFactory();

        } else if (abstractWeaBeanDefinitionClass.isAnnotationPresent(WeaIocReplaceComponent.class)){

            return new WeaIocReplaceComponentRegisterBeanDinitionFactory();

        } else if (abstractWeaBeanDefinitionClass.isAnnotationPresent(WeaIocConfiguration.class)){

            return new WeaIocConfigurationRegisterBeanDefinitionFactory();

        } else {

            throw new RuntimeException("未找到对应的注册类型");

        }

    }

}
