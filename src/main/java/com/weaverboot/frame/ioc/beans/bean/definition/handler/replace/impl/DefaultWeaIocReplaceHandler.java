package com.weaverboot.frame.ioc.beans.bean.definition.handler.replace.impl;

import com.weaverboot.frame.ioc.anno.methodAnno.WeaReplaceAfter;
import com.weaverboot.frame.ioc.anno.methodAnno.WeaReplaceBefore;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.anno.classAnno.WeaIocReplaceComponent;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.replace.inte.AbstractWeaIocReplaceHandler;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DefaultWeaIocReplaceHandler extends AbstractWeaIocReplaceHandler {


    @Override
    public void getReplaceForm(AbstractWeaBeanDefinition abstractWeaBeanDefinition,Object object) throws IllegalAccessException, ClassNotFoundException, InstantiationException, IOException {

        if (abstractWeaBeanDefinition.getBeanClass().isAnnotationPresent(WeaIocReplaceComponent.class)) {

            Method[] methods = object.getClass().getMethods();

            for (Method method : methods
            ) {

                this.checkReplaceBefore(method,abstractWeaBeanDefinition);

                this.checkReplaceAfter(method,abstractWeaBeanDefinition);

            }

        }

    }

    public void checkReplaceAfter(Method method, AbstractWeaBeanDefinition abstractWeaBeanDefinition){

        if (method.isAnnotationPresent(WeaReplaceAfter.class)) {

            String apiUrl = method.getAnnotation(WeaReplaceAfter.class).value();

            Map<AbstractWeaBeanDefinition, Map<String,Method>> abstractWeaBeanDefinitionMap = WeaIocContainer.getReplaceAfter(apiUrl);

            if (abstractWeaBeanDefinitionMap != null) {

                Map<String,Method> methodMap = abstractWeaBeanDefinitionMap.get(abstractWeaBeanDefinition);

                if (methodMap != null){

                    methodMap.put(method.getAnnotation(WeaReplaceAfter.class).order(),method);

                } else {

                    methodMap = new TreeMap<>();

                    methodMap.put(method.getAnnotation(WeaReplaceAfter.class).order(),method);

                    abstractWeaBeanDefinitionMap.put(abstractWeaBeanDefinition,methodMap);

                }

            } else {

                abstractWeaBeanDefinitionMap = new HashMap<>();

                Map<String,Method> methodMap = new TreeMap<>();

                methodMap.put(method.getAnnotation(WeaReplaceAfter.class).order(),method);

                abstractWeaBeanDefinitionMap.put(abstractWeaBeanDefinition, methodMap);

                WeaIocContainer.setReplaceAfter(apiUrl, abstractWeaBeanDefinitionMap);

            }

            System.out.println("注册了url:" + apiUrl + "以及方法:" + method.getName());

        }

    }

    public void checkReplaceBefore(Method method, AbstractWeaBeanDefinition abstractWeaBeanDefinition){

        if (method.isAnnotationPresent(WeaReplaceBefore.class)) {

            String apiUrl = method.getAnnotation(WeaReplaceBefore.class).value();

            Map<AbstractWeaBeanDefinition, Map<String,Method>> abstractWeaBeanDefinitionMap = WeaIocContainer.getReplaceBefore(apiUrl);

            if (abstractWeaBeanDefinitionMap != null) {

                Map<String,Method> methodMap = abstractWeaBeanDefinitionMap.get(abstractWeaBeanDefinition);

                if (methodMap != null){

                    methodMap.put(method.getAnnotation(WeaReplaceBefore.class).order(),method);

                } else {

                    methodMap = new TreeMap<>();

                    methodMap.put(method.getAnnotation(WeaReplaceBefore.class).order(),method);

                    abstractWeaBeanDefinitionMap.put(abstractWeaBeanDefinition,methodMap);

                }

            } else {

                abstractWeaBeanDefinitionMap = new HashMap<>();

                Map<String,Method> methodMap = new TreeMap<>();

                methodMap.put(method.getAnnotation(WeaReplaceBefore.class).order(),method);

                abstractWeaBeanDefinitionMap.put(abstractWeaBeanDefinition, methodMap);

                WeaIocContainer.setReplaceBefore(apiUrl, abstractWeaBeanDefinitionMap);

            }

            System.out.println("注册了url:" + apiUrl + "以及方法:" + method.getName());

        }

    }

}
