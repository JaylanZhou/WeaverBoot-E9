package com.api;

import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;
import com.weaverboot.frame.ioc.anno.fieldAnno.WeaAutowired;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.anno.autowired.inte.WeaIocAutowiredHandler;
import com.weaverboot.frame.ioc.beans.context.impl.DefaultWeaApplicationContext;
import com.weaverboot.frame.ioc.beans.context.inte.WeaApplicationContext;
import com.weaverboot.frame.ioc.prop.WeaIocProperties;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.logTools.LogTools;
import weaver.general.BaseBean;

import javax.ws.rs.ext.Provider;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/**
 * @Author : Jaylan Zhou
 * @Date : 2019-12-18 12:04
 * @Version : 1.0
 */

@Provider
public class WeaAutowiredJerseyInjector implements InjectableProvider<WeaAutowired, Type> {

    private WeaApplicationContext applicationContext;

    private WeaIocAutowiredHandler autowiredHandler;

    private String beanId;

    public WeaAutowiredJerseyInjector(){

        applicationContext = new DefaultWeaApplicationContext();

        beanId = "";

        try {

            autowiredHandler = WeaIocProperties.DEFAULT_WEA_IOC_AUTOWIRED_HANDLER.newInstance();

        } catch (Exception e) {

            LogTools.writeLog("初始化 Weaver Boot 的 Jersey 注解解析器失败，原因为:" + e.getMessage());

        }

    }


    @Override
    public ComponentScope getScope() {
        return ComponentScope.PerRequest;
    }

    @Override
    public Injectable getInjectable(ComponentContext componentContext, WeaAutowired weaAutowired, Type type) {

        String weaAutowiredValue = weaAutowired.value();

        Class clazz = (Class)type;

        return new Injectable() {

            @Override
            public Object getValue() {

                Object object = null;

                boolean isCustomBeanId = false;

                try {

                    if (BaseTools.notNullString(weaAutowiredValue)){

                        isCustomBeanId = true;

                        beanId = weaAutowiredValue;

                    } else {

                        beanId = type.getTypeName();

                    }

                    if ((object = applicationContext.getBean(beanId)) == null){

                        object = autowiredHandler.checkIsImplBeanAndWired(clazz,isCustomBeanId);

                    }

                    if (object == null){

                        throw new RuntimeException("未在容器中找到:" + clazz.getName());

                    }

                    return object;

                } catch (Exception e){

                    LogTools.writeLog("出错了，原因为:" + e.getMessage());

                    throw new RuntimeException(e.getMessage());

                }
            }

        };
    }

}
