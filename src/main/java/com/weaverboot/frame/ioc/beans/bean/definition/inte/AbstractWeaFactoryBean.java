package com.weaverboot.frame.ioc.beans.bean.definition.inte;

import com.weaverboot.frame.ioc.beans.context.impl.DefaultWeaApplicationContext;
import com.weaverboot.frame.ioc.beans.context.inte.WeaApplicationContext;
import com.weaverboot.frame.ioc.prop.WeaIocProperties;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.logTools.LogTools;
import weaver.general.BaseBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public abstract class AbstractWeaFactoryBean implements WeaFactoryBean {

    private Class constructorClass;

    private Method constructorMethod;

    private List<String> beanIds;

    private WeaApplicationContext weaApplicationContext;

    public AbstractWeaFactoryBean(){

        try {

            this.weaApplicationContext = WeaIocProperties.DEFAULT_WEA_APPLICATION_CONTEXT.newInstance();

        } catch (Exception e) {

            LogTools.error("获取上下文错误，原因为:" + e.getMessage());

        }

    }

    public AbstractWeaFactoryBean(WeaApplicationContext weaApplicationContext){

        this.weaApplicationContext = weaApplicationContext;

    }

    @Override
    public Object createBean() {

        if (constructorClass != null && constructorMethod != null){

            //可以优化

            try {

                if(BaseTools.notNullList(beanIds)){

                    Object[] args = new Object[beanIds.size()];

                    for (int i = 0; i < beanIds.size(); i++) {

                        args[i] = weaApplicationContext.getBean(beanIds.get(i));

                    }

                    return constructorMethod.invoke(constructorClass.newInstance(),args);

                } else {

                    return constructorMethod.invoke(constructorClass.newInstance());

                }

            } catch (IllegalAccessException e) {

                LogTools.error("通过指定构造方法注入错误，原因为:" + e.getMessage());

                e.printStackTrace();

            } catch (InvocationTargetException e) {

                LogTools.error("通过指定构造方法注入错误，原因为:" + e.getMessage());

                e.printStackTrace();

            } catch (InstantiationException e) {

                LogTools.error("通过指定构造方法注入错误，原因为:" + e.getMessage());

                e.printStackTrace();

            }

        }

        return null;

    }

    public Class getConstructorClass() {
        return constructorClass;
    }

    public void setConstructorClass(Class constructorClass) {
        this.constructorClass = constructorClass;
    }

    public Method getConstructorMethod() {
        return constructorMethod;
    }

    public void setConstructorMethod(Method constructorMethod) {
        this.constructorMethod = constructorMethod;
    }

    public List<String> getBeanIds() {
        return beanIds;
    }

    public void setBeanIds(List<String> beanIds) {
        this.beanIds = beanIds;
    }
}
