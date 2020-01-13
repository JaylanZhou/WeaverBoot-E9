package com.weaverboot.frame.aop.handler.aspectPointcut.impl;

import com.weaverboot.frame.aop.handler.aspectPointcut.inte.AbstractWeaAspectPointcutHandler;
import com.weaverboot.frame.aop.handler.proxy.impl.WeaCglibProxyBuildHandler;
import com.weaverboot.frame.aop.handler.proxy.impl.WeaJDKProxyBuildHandler;
import com.weaverboot.frame.aop.handler.proxy.inte.WeaProxyBuildHandler;
import com.weaverboot.frame.aop.pointcut.inte.WeaAopPointCut;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.tools.baseTools.BaseTools;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-01-13 11:01
 * @Version : 1.0
 */
public class DefaultWeaAspectPointcutHandler extends AbstractWeaAspectPointcutHandler {

    private WeaProxyBuildHandler weaProxyBuildHandler;

    @Override
    public Object registerProxy(AbstractWeaBeanDefinition abstractWeaBeanDefinition) {

        if (BaseTools.notNullList(WeaIocContainer.WEA_AOP_ADVISOR_LIST)){

            List<WeaAopPointCut> weaAopAdvisorList = new ArrayList<>();

            Class tClass = abstractWeaBeanDefinition.getBeanClass();

            for (WeaAopPointCut weaAopPointCut : WeaIocContainer.WEA_AOP_ADVISOR_LIST
            ) {

                for (Method method : abstractWeaBeanDefinition.getBeanClass().getMethods()
                ) {

                    if (weaAopPointCut.matchsMethod(method,tClass)){

                        weaAopAdvisorList.add(weaAopPointCut);

                    }

                }

            }

            abstractWeaBeanDefinition.setWeaAopPointCutList(weaAopAdvisorList);

            if (BaseTools.notNullList(weaAopAdvisorList)){

                //建立动态代理
                if (tClass.getInterfaces().length == 0){

                    weaProxyBuildHandler = new WeaJDKProxyBuildHandler(abstractWeaBeanDefinition);

                } else {

                    weaProxyBuildHandler = new WeaCglibProxyBuildHandler(abstractWeaBeanDefinition);

                }

                return weaProxyBuildHandler.getProxy();

            }


        }

        return null;

    }
}
