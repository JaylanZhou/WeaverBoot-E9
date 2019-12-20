package com.api;

import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;
import com.weaverboot.frame.ioc.anno.fieldAnno.WeaAutowired;
import com.weaverboot.frame.ioc.beans.context.impl.DefaultWeaApplicationContext;
import com.weaverboot.frame.ioc.beans.context.inte.WeaApplicationContext;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.logTools.LogTools;
import weaver.general.BaseBean;

import javax.ws.rs.ext.Provider;
import java.lang.reflect.Type;

/**
 * @Author : Jaylan Zhou
 * @Date : 2019-12-18 12:04
 * @Version : 1.0
 */

@Provider
public class WeaAutowiredJerseyInjector implements InjectableProvider<WeaAutowired, Type> {

    private WeaApplicationContext applicationContext;

    private BaseBean baseBean;

    private String beanId;

    public WeaAutowiredJerseyInjector(){

        baseBean = new BaseBean();

        applicationContext = new DefaultWeaApplicationContext();

        beanId = "";

    }


    @Override
    public ComponentScope getScope() {
        return ComponentScope.PerRequest;
    }

    @Override
    public Injectable getInjectable(ComponentContext componentContext, WeaAutowired weaAutowired, Type type) {

        String weaAutowiredValue = weaAutowired.value();

        if (BaseTools.notNullString(weaAutowiredValue)){

            this.beanId = weaAutowiredValue;

        } else {

            this.beanId = type.getTypeName();

        }

        return new Injectable() {

            @Override
            public Object getValue() {

                try {


                    return applicationContext.getBean(beanId);

                } catch (Exception e){

                    LogTools.writeLog("出错了，原因为:" + e.getMessage());

                    throw new RuntimeException(e.getMessage());

                }
            }

        };
    }

}
