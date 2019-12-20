package com.weaverboot.frame.ioc.beans.bean.definition.utils;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.beans.context.impl.DefaultWeaApplicationContext;
import com.weaverboot.frame.ioc.beans.context.inte.WeaApplicationContext;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.replace.weaReplaceParam.impl.WeaAfterReplaceParam;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.replace.weaReplaceParam.impl.WeaBeforeReplaceParam;
import weaver.general.BaseBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class WeaIocReplaceUriUtils {


    public static boolean checkAfterUrl(String apiUrl){

        return WeaIocContainer.getReplaceAfter(apiUrl) != null;

    }

    public static boolean checkBeforeUrl(String apiUrl){

        return WeaIocContainer.getReplaceBefore(apiUrl) != null;

    }

    public static String invokeReplaceApiAfterMethod(WeaAfterReplaceParam weaAfterReplaceParam) throws InvocationTargetException, IllegalAccessException {

        WeaApplicationContext weaApplicationContext = new DefaultWeaApplicationContext();

        Map<AbstractWeaBeanDefinition, Map<String,Method>> resultMap = WeaIocContainer.getReplaceAfter(weaAfterReplaceParam.getApiUrl());

        String replaceBody = weaAfterReplaceParam.getData();

        if (resultMap != null) {

            for (AbstractWeaBeanDefinition ab : resultMap.keySet()
            ) {

                Map<String,Method> methodMap = resultMap.get(ab);

                for (String s : methodMap.keySet()
                     ) {

                    Method method = methodMap.get(s);

                    weaAfterReplaceParam.setData((String) method.invoke(weaApplicationContext.getBean(ab.getBeanId()),weaAfterReplaceParam));

                }

            }

        }

        return weaAfterReplaceParam.getData();

    }

    public static void invokeReplaceApiBeforeMethod(WeaBeforeReplaceParam weaBeforeReplaceParam) throws InvocationTargetException, IllegalAccessException {

        Map<AbstractWeaBeanDefinition, Map<String,Method>> resultMap = WeaIocContainer.getReplaceBefore(weaBeforeReplaceParam.getApiUrl());

        WeaApplicationContext weaApplicationContext = new DefaultWeaApplicationContext();

        if (resultMap != null) {

            for (AbstractWeaBeanDefinition ab : resultMap.keySet()
            ) {

                Map<String,Method> methodMap = resultMap.get(ab);

                for (String s : methodMap.keySet()
                ) {

                    Method method = methodMap.get(s);

                    method.invoke(weaApplicationContext.getBean(ab.getBeanId()),weaBeforeReplaceParam);

                }

            }

        }


    }

}
