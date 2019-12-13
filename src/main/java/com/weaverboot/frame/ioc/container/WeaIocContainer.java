package com.weaverboot.frame.ioc.container;


import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WeaIocContainer {

    private WeaIocContainer(){}

    private volatile static Map<String, AbstractWeaBeanDefinition> BEANDEFINITION_MAP = new ConcurrentHashMap<>();

    private volatile static Map<String, AbstractWeaBeanDefinition> EARLY_BEANDEFINITION_MAP = new ConcurrentHashMap<>();

    private volatile static Map<String,AbstractWeaBeanDefinition> BEING_CREATE_BEANDEFINITION_MAP = new ConcurrentHashMap<>();

    private volatile static List<String> EARLY_BEANDIFINITION_LIST = new ArrayList<>();

    private volatile static Map<String, Map<AbstractWeaBeanDefinition, Map<String,Method>>> REPLACE_AFTER_API_MAP = new ConcurrentHashMap<>();

    private volatile static Map<String, Map<AbstractWeaBeanDefinition, Map<String,Method>>> REPLACE_BEFORE_API_MAP = new ConcurrentHashMap<>();


    public static AbstractWeaBeanDefinition getBeanDefinition(String beanId){

        return BEANDEFINITION_MAP.get(beanId);

    }

    public static void setBeandefinition(String beanId, AbstractWeaBeanDefinition abstractWeaBeanDefinition){

        BEANDEFINITION_MAP.put(abstractWeaBeanDefinition.getBeanId(), abstractWeaBeanDefinition);

    }

    public static AbstractWeaBeanDefinition getBeingCreateBeanDefinition(String beanId){

        return BEING_CREATE_BEANDEFINITION_MAP.get(beanId);

    }

    public static void setBeingCreateBeandefinition(String beanId, AbstractWeaBeanDefinition abstractWeaBeanDefinition){

        BEING_CREATE_BEANDEFINITION_MAP.put(abstractWeaBeanDefinition.getBeanId(), abstractWeaBeanDefinition);

    }

    public static AbstractWeaBeanDefinition getEarlyBeanDefinition(String beanId){

        return EARLY_BEANDEFINITION_MAP.get(beanId);

    }

    public static void setEarlyBeandefinition(String beanId, AbstractWeaBeanDefinition abstractWeaBeanDefinition){

        EARLY_BEANDEFINITION_MAP.put(beanId, abstractWeaBeanDefinition);

    }

    public void addEarlyBeanDifinitionList(String beanId){

        EARLY_BEANDIFINITION_LIST.add(beanId);

    }

    public static Map<AbstractWeaBeanDefinition,Map<String,Method>> getReplaceAfter(String apiUrl){

        return REPLACE_AFTER_API_MAP.get(apiUrl);

    }

    public static void setReplaceAfter(String apiUrl,Map<AbstractWeaBeanDefinition, Map<String,Method>> methodMap){

        REPLACE_AFTER_API_MAP.put(apiUrl,methodMap);

    }

    public static Map<AbstractWeaBeanDefinition,Map<String,Method>> getReplaceBefore(String apiUrl){

        return REPLACE_BEFORE_API_MAP.get(apiUrl);

    }

    public static void setReplaceBefore(String apiUrl,Map<AbstractWeaBeanDefinition, Map<String,Method>> methodMap){

        REPLACE_BEFORE_API_MAP.put(apiUrl,methodMap);

    }


    public static void cleanContainer(){

        BEANDEFINITION_MAP.clear();

        REPLACE_AFTER_API_MAP.clear();

    }

    public static Map<String, AbstractWeaBeanDefinition> getBeandefinitionMap() {
        return BEANDEFINITION_MAP;
    }

    public static void setBeandefinitionMap(Map<String, AbstractWeaBeanDefinition> beandefinitionMap) {
        BEANDEFINITION_MAP = beandefinitionMap;
    }

    public static Map<String, Map<AbstractWeaBeanDefinition, Map<String, Method>>> getReplaceAfterApiMap() {
        return REPLACE_AFTER_API_MAP;
    }

    public static void setReplaceAfterApiMap(Map<String, Map<AbstractWeaBeanDefinition, Map<String, Method>>> replaceAfterApiMap) {
        REPLACE_AFTER_API_MAP = replaceAfterApiMap;
    }

    public static Map<String, Map<AbstractWeaBeanDefinition, Map<String, Method>>> getReplaceBeforeApiMap() {
        return REPLACE_BEFORE_API_MAP;
    }

    public static void setReplaceBeforeApiMap(Map<String, Map<AbstractWeaBeanDefinition, Map<String, Method>>> replaceBeforeApiMap) {
        REPLACE_BEFORE_API_MAP = replaceBeforeApiMap;
    }

    public static Map<String, AbstractWeaBeanDefinition> getEarlyBeandefinitionMap() {
        return EARLY_BEANDEFINITION_MAP;
    }

    public static void setEarlyBeandefinitionMap(Map<String, AbstractWeaBeanDefinition> earlyBeandefinitionMap) {
        EARLY_BEANDEFINITION_MAP = earlyBeandefinitionMap;
    }

    public static List<String> getEarlyBeandifinitionList() {
        return EARLY_BEANDIFINITION_LIST;
    }

    public static void setEarlyBeandifinitionList(List<String> earlyBeandifinitionList) {
        EARLY_BEANDIFINITION_LIST = earlyBeandifinitionList;
    }

    public static Map<String, AbstractWeaBeanDefinition> getBeingCreateBeandefinitionMap() {
        return BEING_CREATE_BEANDEFINITION_MAP;
    }

    public static void setBeingCreateBeandefinitionMap(Map<String, AbstractWeaBeanDefinition> beingCreateBeandefinitionMap) {
        BEING_CREATE_BEANDEFINITION_MAP = beingCreateBeandefinitionMap;
    }
}
