package com.weaverboot.frame.ioc.beans.context.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.handler.replace.weaReplaceApiAdvice.WeaReplaceApiAdvice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public interface WeaApplicationContext {

    void refresh();

    Object getBean(String beanName);

    <T>T getBean(Class<T> tClass);

    Map<String,Object> getBeansWithAnnotation(Class<Annotation> annotationClass);

    List<WeaReplaceApiAdvice> getReplaceAfterApi(String apiUrl);

    List<WeaReplaceApiAdvice> getReplaceBeforeApi(String apiUrl);

}
