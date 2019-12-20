package com.weaverboot.frame.aop.handler.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

import java.io.IOException;

public interface WeaAopHandler {

    void initReplace(AbstractWeaBeanDefinition abstractWeaBeanDefinition,Object object) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException;

}
