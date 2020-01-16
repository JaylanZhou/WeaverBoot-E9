package com.weaverboot.frame.ioc.beans.bean.definition.impl;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaFactoryBean;
import com.weaverboot.frame.ioc.beans.context.inte.WeaApplicationContext;

import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * 默认 IOC Bean 注解创建类的创建工厂实现类
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-16
 *
 */

public class DefaultWeaFactoryBean extends AbstractWeaFactoryBean {

    public DefaultWeaFactoryBean(Class clazz, Method method, List<Object> beanIds){

        super();

        this.setConstructorClass(clazz);

        this.setConstructorMethod(method);

        this.setBeanIds(beanIds);

    }

    public DefaultWeaFactoryBean(Class clazz, Method method, List<Object> beanIds, WeaApplicationContext weaApplicationContext){

        super(weaApplicationContext);

        this.setConstructorClass(clazz);

        this.setConstructorMethod(method);

        this.setBeanIds(beanIds);

    }

}
