package com.weaverboot.frame.ioc.beans.context.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

import java.lang.reflect.Method;
import java.util.Map;

public interface WeaApplicationContext {

    void refresh();

    Object getBean(String beanName);

    Map<AbstractWeaBeanDefinition, Map<String,Method>> getReplaceApi(String apiUrl);

}
