package com.weaverboot.frame.ioc.beans.bean.definition.utils;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.beans.context.impl.DefaultWeaApplicationContext;
import com.weaverboot.frame.ioc.beans.context.inte.WeaApplicationContext;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.handler.replace.weaReplaceApiAdvice.WeaReplaceApiAdvice;
import com.weaverboot.frame.ioc.handler.replace.weaReplaceApiAdvice.WeaReplaceApiAdviceComparator;
import com.weaverboot.frame.ioc.handler.replace.weaReplaceParam.impl.WeaAfterReplaceParam;
import com.weaverboot.frame.ioc.handler.replace.weaReplaceParam.impl.WeaBeforeReplaceParam;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.logTools.LogTools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class WeaIocReplaceUriUtils {


    public static boolean checkAfterUrl(String apiUrl) {

        return WeaIocContainer.getReplaceAfter(apiUrl) != null;

    }

    public static boolean checkBeforeUrl(String apiUrl) {

        return WeaIocContainer.getReplaceBefore(apiUrl) != null;

    }

    public static String invokeReplaceApiAfterMethod(WeaAfterReplaceParam weaAfterReplaceParam) throws InvocationTargetException, IllegalAccessException {

        WeaApplicationContext weaApplicationContext = new DefaultWeaApplicationContext();

        List<WeaReplaceApiAdvice> weaReplaceApiAdviceList = WeaIocContainer.getReplaceAfter(weaAfterReplaceParam.getApiUrl());

        if (BaseTools.notNullList(weaReplaceApiAdviceList)) {

            for (WeaReplaceApiAdvice weaReplaceApiAdvice : weaReplaceApiAdviceList
            ) {

                try {

                    Method method = weaReplaceApiAdvice.getMethod();

                    weaAfterReplaceParam.setData((String) method.invoke(weaApplicationContext.getBean(weaReplaceApiAdvice.getAbstractWeaBeanDefinition().getBeanId()), weaAfterReplaceParam));

                } catch (Exception e) {

                    LogTools.writeLog("执行方法" + weaReplaceApiAdvice.getMethod().getName() + "时发生错误，原因为:" + e.getMessage());

                    continue;

                }

            }

        }

        return weaAfterReplaceParam.getData();

    }

    public static void invokeReplaceApiBeforeMethod(WeaBeforeReplaceParam weaBeforeReplaceParam) throws InvocationTargetException, IllegalAccessException {

        List<WeaReplaceApiAdvice> weaReplaceApiAdviceList = WeaIocContainer.getReplaceBefore(weaBeforeReplaceParam.getApiUrl());

        WeaApplicationContext weaApplicationContext = new DefaultWeaApplicationContext();

        if (BaseTools.notNullList(weaReplaceApiAdviceList)) {

            for (WeaReplaceApiAdvice weaReplaceApiAdvice : weaReplaceApiAdviceList
            ) {

                try {

                    Method method = weaReplaceApiAdvice.getMethod();

                    method.invoke(weaApplicationContext.getBean(weaReplaceApiAdvice.getAbstractWeaBeanDefinition().getBeanId()), weaBeforeReplaceParam);

                } catch (Exception e) {

                    LogTools.writeLog("执行方法" + weaReplaceApiAdvice.getMethod().getName() + "时发生错误，原因为:" + e.getMessage());

                    continue;

                }

            }

        }

    }

    public static void orderReplaceApiAdvice(){

        Comparator comparator = new WeaReplaceApiAdviceComparator();

        for (String key : WeaIocContainer.getReplaceBeforeApiMap().keySet()
             ) {

            List<WeaReplaceApiAdvice> weaReplaceApiAdviceList = WeaIocContainer.getReplaceBeforeApiMap().get(key);

            Collections.sort(weaReplaceApiAdviceList,comparator);

        }

        for (String key : WeaIocContainer.getReplaceAfterApiMap().keySet()
        ) {

            List<WeaReplaceApiAdvice> weaReplaceApiAdviceList = WeaIocContainer.getReplaceAfterApiMap().get(key);

            Collections.sort(weaReplaceApiAdviceList,comparator);

        }

    }

}
