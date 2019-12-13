package com.weaverboot.frame.ioc.beans.bean.definition.impl;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaFactoryBean;

import java.lang.reflect.Method;

public class DefaultWeaFactoryBean extends AbstractWeaFactoryBean {

    public DefaultWeaFactoryBean(Class clazz, Method method){

        this.setConstructorClass(clazz);

        this.setConstructorMethod(method);

    }

}
