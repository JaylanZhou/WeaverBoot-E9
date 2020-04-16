package com.weaverboot.frame.ioc.handler.replace.weaReplaceApiAdvice;

import com.alibaba.fastjson.annotation.JSONField;
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

    @JSONField(serialize = false)
    private AbstractWeaBeanDefinition abstractWeaBeanDefinition;

    @JSONField(serialize = false)
    private Method method;

    private String methodName;

    private String description;

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

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
