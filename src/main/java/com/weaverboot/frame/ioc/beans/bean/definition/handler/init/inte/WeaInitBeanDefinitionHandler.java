package com.weaverboot.frame.ioc.beans.bean.definition.handler.init.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface WeaInitBeanDefinitionHandler {

    void initBeanDefinition() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException;


}
