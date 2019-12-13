package com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.prop.WeaIocProperties;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.register.inte.WeaRegisterIocAnnoHandler;

import java.lang.annotation.Annotation;

public abstract class AbstractWeaRegisterBeanDefinitionFactory implements WeaRegisterBeanDefinitionFactory {

    private WeaRegisterIocAnnoHandler weaRegisterIocAnnoHandler;

    protected ClassLoader classLoader = this.getClass().getClassLoader();

    public WeaRegisterIocAnnoHandler getWeaRegisterIocAnnoHandler() throws IllegalAccessException, InstantiationException {

        if (weaRegisterIocAnnoHandler == null){

            return WeaIocProperties.DEFAULT_WEA_REGISTER_IOC_ANNO_HANDLER.newInstance();

        }

        return weaRegisterIocAnnoHandler;
    }

    public void setWeaRegisterIocAnnoHandler(WeaRegisterIocAnnoHandler weaRegisterIocAnnoHandler) {
        this.weaRegisterIocAnnoHandler = weaRegisterIocAnnoHandler;
    }

    protected String beforeRegisrer(AbstractWeaBeanDefinition abstractWeaBeanDefinition, Class clazz, Annotation annotation) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        abstractWeaBeanDefinition.setBeanClassName(clazz.getName());

        abstractWeaBeanDefinition.setBeanClass(clazz);

        abstractWeaBeanDefinition.setBeanAnnotation(annotation);

        WeaRegisterIocAnnoHandler weaRegisterIocAnnoHandler = getWeaRegisterIocAnnoHandler();

        weaRegisterIocAnnoHandler.handleLazyInit(abstractWeaBeanDefinition);

        weaRegisterIocAnnoHandler.handlePrototype(abstractWeaBeanDefinition);

        String beanId = abstractWeaBeanDefinition.getBeanClassName();

        return beanId;

    }

}
