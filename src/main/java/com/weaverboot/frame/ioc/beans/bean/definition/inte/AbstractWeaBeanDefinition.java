package com.weaverboot.frame.ioc.beans.bean.definition.inte;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

public abstract class AbstractWeaBeanDefinition implements WeaBeanDefinition {

    private String beanId;

    public static final int AUTOWIRE_NO = 0;

    private String beanClassName;

    private Annotation beanAnnotation;

    private volatile Object beanObject;

    private Class beanClass;

    private String scope;

    private boolean singleton = true;

    private boolean prototype = false;

    private boolean abstractClass = false;

    private boolean lazyInit = false;

    private String destoryMethod;

    private String initMethod;

    private WeaFactoryBean weaFactoryBean;

    private int autowiredMode = AUTOWIRE_NO;

    private String description;

    private Annotation componentType;


    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public Object getBeanObject() {
        return beanObject;
    }

    public void setBeanObject(Object beanObject) {
        this.beanObject = beanObject;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public boolean isPrototype() {
        return prototype;
    }

    public void setPrototype(boolean prototype) {
        this.prototype = prototype;
    }

    public boolean isAbstractClass() {
        return abstractClass;
    }

    public void setAbstractClass(boolean abstractClass) {
        this.abstractClass = abstractClass;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public int getAutowiredMode() {
        return autowiredMode;
    }

    public void setAutowiredMode(int autowiredMode) {
        this.autowiredMode = autowiredMode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Annotation getBeanAnnotation() {
        return beanAnnotation;
    }

    public void setBeanAnnotation(Annotation beanAnnotation) {
        this.beanAnnotation = beanAnnotation;
    }

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public String getDestoryMethod() {
        return destoryMethod;
    }

    public void setDestoryMethod(String destoryMethod) {
        this.destoryMethod = destoryMethod;
    }

    public String getInitMethod() {
        return initMethod;
    }

    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }

    public WeaFactoryBean getWeaFactoryBean() {
        return weaFactoryBean;
    }

    public void setWeaFactoryBean(WeaFactoryBean weaFactoryBean) {
        this.weaFactoryBean = weaFactoryBean;
    }

    public Annotation getComponentType() {
        return componentType;
    }

    public void setComponentType(Annotation componentType) {
        this.componentType = componentType;
    }
}
