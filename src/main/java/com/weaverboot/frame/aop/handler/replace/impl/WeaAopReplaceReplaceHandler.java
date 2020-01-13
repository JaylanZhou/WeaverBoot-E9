package com.weaverboot.frame.aop.handler.replace.impl;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.aop.handler.replace.inte.AbstractWeaAopReplaceHandler;

import java.io.IOException;

public class WeaAopReplaceReplaceHandler extends AbstractWeaAopReplaceHandler {


    @Override
    public void initReplace(AbstractWeaBeanDefinition abstractWeaBeanDefinition) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {

        getWeaIocReplaceHandler().getReplaceForm(abstractWeaBeanDefinition);

    }

}
