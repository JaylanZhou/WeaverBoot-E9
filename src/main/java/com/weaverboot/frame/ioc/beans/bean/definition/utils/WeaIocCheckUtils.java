package com.weaverboot.frame.ioc.beans.bean.definition.utils;

import com.weaverboot.frame.ioc.anno.classAnno.WeaIocComponent;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

import java.lang.annotation.Annotation;

public class WeaIocCheckUtils {

    private WeaIocCheckUtils(){}

    public static Annotation checkAnnotationComponenet(Class clazz){

        if (clazz.isAnnotationPresent(com.weaverboot.frame.ioc.anno.classAnno.WeaIocComponent.class)){

            return clazz.getAnnotation(com.weaverboot.frame.ioc.anno.classAnno.WeaIocComponent.class);

        } else {

            Annotation[] annotations = clazz.getAnnotations();

            for (int i = 0; i < annotations.length; i++) {

                if (annotations[i].annotationType().isAnnotationPresent(WeaIocComponent.class)){

                    return annotations[i];

                }

            }

        }

        return null;

    }

    public static Object checkIsOrInitObject(Object object, AbstractWeaBeanDefinition abstractWeaBeanDefinition) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        if (object == null) {

            object = WeaIocCheckUtils.class.getClassLoader().loadClass(abstractWeaBeanDefinition.getBeanClassName()).newInstance();

            abstractWeaBeanDefinition.setBeanObject(object);

            System.out.println("注册了实体:" + object.getClass().getName());

        }

        return object;

    }

    public static boolean checkIsSingleton(AbstractWeaBeanDefinition abstractWeaBeanDefinition){

        return abstractWeaBeanDefinition.isSingleton() && !abstractWeaBeanDefinition.isPrototype();

    }

}
