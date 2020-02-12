package com.weaverboot.frame.ioc.container;


import com.weaverboot.frame.aop.advisor.advisor.inte.WeaAopAdvisor;
import com.weaverboot.frame.aop.pointcut.inte.WeaAopPointCut;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.WeaBeanDefinition;
import com.weaverboot.frame.ioc.handler.replace.weaReplaceApiAdvice.WeaReplaceApiAdvice;
import com.weaverboot.frame.ioc.postProcessor.wired.inte.WeaWiredBeanPostProcessor;

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

    public volatile static List<WeaAopPointCut> WEA_AOP_ADVISOR_LIST = new ArrayList<>();

    private volatile static Map<String, List<WeaReplaceApiAdvice>> REPLACE_AFTER_API_MAP = new ConcurrentHashMap<>();

    private volatile static Map<String, List<WeaReplaceApiAdvice>> REPLACE_BEFORE_API_MAP = new ConcurrentHashMap<>();

    private volatile static List<AbstractWeaBeanDefinition> WIRED_BEAN_POST_PROCESSOR = new ArrayList<>();


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

    public static List<WeaReplaceApiAdvice> getReplaceAfter(String apiUrl){

        return REPLACE_AFTER_API_MAP.get(apiUrl);

    }

    public static void setReplaceAfter(String apiUrl,List<WeaReplaceApiAdvice> methodMap){

        REPLACE_AFTER_API_MAP.put(apiUrl,methodMap);

    }

    public static List<WeaReplaceApiAdvice> getReplaceBefore(String apiUrl){

        return REPLACE_BEFORE_API_MAP.get(apiUrl);

    }

    public static void setReplaceBefore(String apiUrl,List<WeaReplaceApiAdvice> methodMap){

        REPLACE_BEFORE_API_MAP.put(apiUrl,methodMap);

    }


    public static void cleanContainer(){

        BEANDEFINITION_MAP.clear();

        BEING_CREATE_BEANDEFINITION_MAP.clear();

        EARLY_BEANDEFINITION_MAP.clear();

        REPLACE_AFTER_API_MAP.clear();

    }

    public static Map<String, AbstractWeaBeanDefinition> getBeandefinitionMap() {
        return BEANDEFINITION_MAP;
    }

    public static void setBeandefinitionMap(Map<String, AbstractWeaBeanDefinition> beandefinitionMap) {
        BEANDEFINITION_MAP = beandefinitionMap;
    }

    public static Map<String, List<WeaReplaceApiAdvice>> getReplaceAfterApiMap() {
        return REPLACE_AFTER_API_MAP;
    }

    public static void setReplaceAfterApiMap(Map<String, List<WeaReplaceApiAdvice>> replaceAfterApiMap) {
        REPLACE_AFTER_API_MAP = replaceAfterApiMap;
    }

    public static Map<String, List<WeaReplaceApiAdvice>> getReplaceBeforeApiMap() {
        return REPLACE_BEFORE_API_MAP;
    }

    public static void setReplaceBeforeApiMap(Map<String, List<WeaReplaceApiAdvice>> replaceBeforeApiMap) {
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

    public static void addWiredBeanPostProcessor(AbstractWeaBeanDefinition weaWiredBeanPostProcessor){

        WIRED_BEAN_POST_PROCESSOR.add(weaWiredBeanPostProcessor);

    }

    public static List<AbstractWeaBeanDefinition> getWeaWiredBeanPostProcessorList(){

        return WIRED_BEAN_POST_PROCESSOR;

    }


}
