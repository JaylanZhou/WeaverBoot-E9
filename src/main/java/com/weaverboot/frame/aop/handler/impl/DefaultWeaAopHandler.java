package com.weaverboot.frame.aop.handler.impl;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.aop.handler.inte.AbstractWeaAopHandler;

public class DefaultWeaAopHandler extends AbstractWeaAopHandler {


    @Override
    public void initReplace(AbstractWeaBeanDefinition abstractWeaBeanDefinition) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        getWeaIocReplaceHandler().getReplaceForm(abstractWeaBeanDefinition);

    }

}
