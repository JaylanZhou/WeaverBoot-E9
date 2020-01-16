package com.weaverboot.frame.ioc.handler.replace.weaReplaceApiAdvice;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

import java.lang.reflect.Method;

/**
 *
 * Replace API 通知类
 *
 * @Author : Jaylan Zhou
 * @Date : 2020-01-16 15:26
 * @Version : 1.0
 */
public class WeaReplaceApiAdvice {

    private AbstractWeaBeanDefinition abstractWeaBeanDefinition;

    private Method method;

    private int order;

    public AbstractWeaBeanDefinition getAbstractWeaBeanDefinition() {
        return abstractWeaBeanDefinition;
    }

    public void setAbstractWeaBeanDefinition(AbstractWeaBeanDefinition abstractWeaBeanDefinition) {
        this.abstractWeaBeanDefinition = abstractWeaBeanDefinition;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
