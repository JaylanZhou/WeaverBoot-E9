package com.weaverboot.frame.ioc.handler.replace.impl;

import com.weaverboot.frame.ioc.anno.methodAnno.WeaReplaceAfter;
import com.weaverboot.frame.ioc.anno.methodAnno.WeaReplaceBefore;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.anno.classAnno.WeaIocReplaceComponent;
import com.weaverboot.frame.ioc.handler.replace.inte.AbstractWeaIocReplaceHandler;
import com.weaverboot.frame.ioc.handler.replace.weaReplaceApiAdvice.WeaReplaceApiAdvice;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.logTools.LogTools;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public class DefaultWeaIocReplaceHandler extends AbstractWeaIocReplaceHandler {


    @Override
    public void getReplaceForm(AbstractWeaBeanDefinition abstractWeaBeanDefinition) {

        if (abstractWeaBeanDefinition.getBeanClass().isAnnotationPresent(WeaIocReplaceComponent.class)) {

            Method[] methods = abstractWeaBeanDefinition.getBeanClass().getMethods();

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

            apiUrl = apiUrl.startsWith("/") ? apiUrl : "/" + apiUrl;

            if (BaseTools.notNullString(apiUrl)) {

                List<WeaReplaceApiAdvice> weaReplaceApiAdviceList = WeaIocContainer.getReplaceAfter(apiUrl);

                if (BaseTools.notNullList(weaReplaceApiAdviceList)) {

                    addAfterAdvice(method,abstractWeaBeanDefinition,weaReplaceApiAdviceList);

                } else {

                    weaReplaceApiAdviceList = new ArrayList<>();

                    addAfterAdvice(method,abstractWeaBeanDefinition,weaReplaceApiAdviceList);

                    WeaIocContainer.setReplaceAfter(apiUrl, weaReplaceApiAdviceList);

                }

                LogTools.info("注册了url:" + apiUrl + "以及后置方法:" + method.getName());

            }

        }

    }

    public void checkReplaceBefore(Method method, AbstractWeaBeanDefinition abstractWeaBeanDefinition){

        if (method.isAnnotationPresent(WeaReplaceBefore.class)) {

            String apiUrl = method.getAnnotation(WeaReplaceBefore.class).value();

            List<WeaReplaceApiAdvice> weaReplaceApiAdviceList = WeaIocContainer.getReplaceBefore(apiUrl);

            if (BaseTools.notNullList(weaReplaceApiAdviceList)) {

                addBeforeAdvice(method,abstractWeaBeanDefinition,weaReplaceApiAdviceList);

            } else {

                weaReplaceApiAdviceList = new ArrayList<>();

                addBeforeAdvice(method,abstractWeaBeanDefinition,weaReplaceApiAdviceList);

                WeaIocContainer.setReplaceBefore(apiUrl, weaReplaceApiAdviceList);

            }

            LogTools.info("注册了url:" + apiUrl + "以及前置方法:" + method.getName());

        }

    }

    private void addAfterAdvice(Method method,AbstractWeaBeanDefinition abstractWeaBeanDefinition,List<WeaReplaceApiAdvice> apiAdviceList){

        WeaReplaceApiAdvice weaReplaceApiAdvice = new WeaReplaceApiAdvice();

        weaReplaceApiAdvice.setMethod(method);

        weaReplaceApiAdvice.setAbstractWeaBeanDefinition(abstractWeaBeanDefinition);

        weaReplaceApiAdvice.setOrder(method.getAnnotation(WeaReplaceAfter.class).order());

        apiAdviceList.add(weaReplaceApiAdvice);

    }

    private void addBeforeAdvice(Method method,AbstractWeaBeanDefinition abstractWeaBeanDefinition,List<WeaReplaceApiAdvice> apiAdviceList){

        WeaReplaceApiAdvice weaReplaceApiAdvice = new WeaReplaceApiAdvice();

        weaReplaceApiAdvice.setMethod(method);

        weaReplaceApiAdvice.setAbstractWeaBeanDefinition(abstractWeaBeanDefinition);

        weaReplaceApiAdvice.setOrder(method.getAnnotation(WeaReplaceBefore.class).order());

        apiAdviceList.add(weaReplaceApiAdvice);

    }

}
