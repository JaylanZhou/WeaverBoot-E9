package com.weaverboot.frame.ioc.beans.bean.definition.utils;

import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.replace.weaReplaceParam.impl.WeaAfterReplaceParam;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.replace.weaReplaceParam.impl.WeaBeforeReplaceParam;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import weaver.general.BaseBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class WeaIocReplaceUriUtils {

    private WeaIocReplaceUriUtils(){}

    public static boolean checkAfterUrl(String apiUrl){

        return WeaIocContainer.getReplaceAfter(apiUrl) != null;

    }

    public static boolean checkBeforeUrl(String apiUrl){

        return WeaIocContainer.getReplaceBefore(apiUrl) != null;

    }

    public static String invokeReplaceApiAfterMethod(WeaAfterReplaceParam weaAfterReplaceParam) throws InvocationTargetException, IllegalAccessException {

        Map<com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition, Map<String,Method>> resultMap = WeaIocContainer.getReplaceAfter(weaAfterReplaceParam.getApiUrl());

        String replaceBody = weaAfterReplaceParam.getData();

        if (resultMap != null) {

            for (com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition ab : resultMap.keySet()
            ) {

                Map<String,Method> methodMap = resultMap.get(ab);

                for (String s : methodMap.keySet()
                     ) {

                    Method method = methodMap.get(s);

                    weaAfterReplaceParam.setData((String) method.invoke(ab.getBeanObject(),weaAfterReplaceParam));

                }

            }

        }

        return weaAfterReplaceParam.getData();

    }

    public static void invokeReplaceApiBeforeMethod(WeaBeforeReplaceParam weaBeforeReplaceParam) throws InvocationTargetException, IllegalAccessException {

        Map<com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition, Map<String,Method>> resultMap = WeaIocContainer.getReplaceBefore(weaBeforeReplaceParam.getApiUrl());

        if (resultMap != null) {

            BaseBean baseBean = new BaseBean();

            for (AbstractWeaBeanDefinition ab : resultMap.keySet()
            ) {

                Map<String,Method> methodMap = resultMap.get(ab);

                for (String s : methodMap.keySet()
                ) {

                    Method method = methodMap.get(s);

                    method.invoke(ab.getBeanObject(),weaBeforeReplaceParam);

                }

            }

        }


    }

}
