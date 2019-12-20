package com.weaverboot.frame.aop.handler.impl;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.aop.handler.inte.AbstractWeaAopHandler;

import java.io.IOException;

public class DefaultWeaAopHandler extends AbstractWeaAopHandler {


    @Override
    public void initReplace(AbstractWeaBeanDefinition abstractWeaBeanDefinition,Object object) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {

        getWeaIocReplaceHandler().getReplaceForm(abstractWeaBeanDefinition,object);

    }

}
