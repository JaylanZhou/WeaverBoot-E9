package com.weaverboot.frame.ioc.beans.bean.definition.handler.register.impl;

import com.weaverboot.frame.ioc.anno.classAnno.WeaLazyInit;
import com.weaverboot.frame.ioc.anno.classAnno.WeaPrototype;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.register.inte.WeaRegisterIocAnnoHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

public class DefaultWeaRegisterIocAnnoHandler implements WeaRegisterIocAnnoHandler {

    @Override
    public void handleLazyInit(AbstractWeaBeanDefinition abstractWeaBeanDefinition) {

        if (abstractWeaBeanDefinition.getBeanClass().isAnnotationPresent(WeaLazyInit.class)){

            abstractWeaBeanDefinition.setLazyInit(true);

        }

    }

    @Override
    public void handlePrototype(AbstractWeaBeanDefinition abstractWeaBeanDefinition) {


        if (abstractWeaBeanDefinition.getBeanClass().isAnnotationPresent(WeaPrototype.class)){

            abstractWeaBeanDefinition.setSingleton(false);

            abstractWeaBeanDefinition.setPrototype(true);

        }

    }

}
