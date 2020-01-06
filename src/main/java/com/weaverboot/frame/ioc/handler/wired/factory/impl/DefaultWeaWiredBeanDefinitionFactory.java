package com.weaverboot.frame.ioc.handler.wired.factory.impl;

import com.weaverboot.frame.ioc.anno.classAnno.WeaIocComponent;
import com.weaverboot.frame.ioc.anno.classAnno.WeaIocConfiguration;
import com.weaverboot.frame.ioc.anno.classAnno.WeaIocReplaceComponent;
import com.weaverboot.frame.ioc.anno.classAnno.WeaIocService;
import com.weaverboot.frame.ioc.anno.methodAnno.WeaIocBean;
import com.weaverboot.frame.ioc.handler.wired.factory.inte.AbstractWeaWiredBeanDefinitionFactory;
import com.weaverboot.frame.ioc.handler.wired.impl.WeaIocComponentWiredBeanDefinitionHandler;
import com.weaverboot.frame.ioc.handler.wired.impl.WeaIocConfigurationWiredBeanDefinitionHandler;
import com.weaverboot.frame.ioc.handler.wired.impl.WeaIocReplaceComponentWiredBeanDefinitionHandler;
import com.weaverboot.frame.ioc.handler.wired.impl.WeaIocServiceWiredBeanDefinitionHandler;
import com.weaverboot.frame.ioc.handler.wired.inte.WeaWiredBeanDefinitionHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

import java.lang.annotation.Annotation;

public class DefaultWeaWiredBeanDefinitionFactory extends AbstractWeaWiredBeanDefinitionFactory {

    @Override
    public WeaWiredBeanDefinitionHandler getHandler(AbstractWeaBeanDefinition abstractWeaBeanDefinition) {

        Annotation annotation = abstractWeaBeanDefinition.getBeanAnnotation();

        if (annotation.annotationType().equals(WeaIocComponent.class)){

            return new WeaIocComponentWiredBeanDefinitionHandler();

        } else if (annotation.annotationType().equals(WeaIocService.class)){

            return new WeaIocServiceWiredBeanDefinitionHandler();

        } else if (annotation.annotationType().equals(WeaIocReplaceComponent.class)){

            return new WeaIocReplaceComponentWiredBeanDefinitionHandler();

        } else if (annotation.annotationType().equals(WeaIocConfiguration.class)){

            return new WeaIocConfigurationWiredBeanDefinitionHandler();

        } else if (annotation.annotationType().equals(WeaIocBean.class)){

            return new WeaIocComponentWiredBeanDefinitionHandler();

        } else{

            throw new RuntimeException("未找到对应的注册类型");

        }

    }

}
