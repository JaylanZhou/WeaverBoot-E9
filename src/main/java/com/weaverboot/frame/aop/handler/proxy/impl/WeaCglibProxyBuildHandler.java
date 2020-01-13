package com.weaverboot.frame.aop.handler.proxy.impl;

import com.weaverboot.frame.aop.handler.proxy.inte.AbstractWeaProxyBuildHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.tools.logTools.LogTools;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 *
 * CgLib 动态代理处理实现类
 *
 * @Author : Jaylan Zhou
 * @Date : 2020-01-13 10:59
 * @Version : 1.0
 */
public class WeaCglibProxyBuildHandler extends AbstractWeaProxyBuildHandler implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public WeaCglibProxyBuildHandler(AbstractWeaBeanDefinition abstractWeaBeanDefinition){

        this.abstractWeaBeanDefinition = abstractWeaBeanDefinition;

    }

    @Override
    public Object getProxy() {

        Class tClass = abstractWeaBeanDefinition.getBeanClass();

        enhancer.setSuperclass(tClass);

        enhancer.setInterfaces(tClass.getInterfaces());

        enhancer.setCallback(this);

        Constructor<?> constructor = null;

        try {

            constructor = tClass.getConstructor(new Class<?>[] {});

        } catch (NoSuchMethodException | SecurityException e) {

            LogTools.error("无法获取" + tClass + "的无参构造函数，原因为:" + e.getMessage());

        }

        if (constructor != null) {

            return enhancer.create();
        }


        //没有无参构造函数时,从BeanDefinition里面获取构造参数的类型和值进行增强
        else {


            //return enhancer.create(bd.getConstructor().getParameterTypes(), bd.getConstructorArgumentRealValues());

        }

        return null;

    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return null;
    }
}
