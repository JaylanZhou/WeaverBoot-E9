package com.weaverboot.frame.aop.handler.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

import java.io.IOException;

public interface WeaAopHandler {

    void initReplace(AbstractWeaBeanDefinition abstractWeaBeanDefinition) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException;

}
