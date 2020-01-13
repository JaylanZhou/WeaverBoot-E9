package com.weaverboot.frame.aop.handler.proxy.impl;

import com.weaverboot.frame.aop.handler.proxy.inte.AbstractWeaProxyBuildHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * jdk 动态代理基本处理实现类
 *
 * @Author : Jaylan Zhou
 * @Date : 2020-01-13 10:59
 * @Version : 1.0
 */
public class WeaJDKProxyBuildHandler extends AbstractWeaProxyBuildHandler implements InvocationHandler {

    public WeaJDKProxyBuildHandler(AbstractWeaBeanDefinition abstractWeaBeanDefinition){

        this.abstractWeaBeanDefinition = abstractWeaBeanDefinition;

    }

    @Override
    public Object getProxy() {

        return Proxy.newProxyInstance(abstractWeaBeanDefinition.getClass().getClassLoader(),abstractWeaBeanDefinition.getBeanClass().getInterfaces(),this);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return null;

    }
}
