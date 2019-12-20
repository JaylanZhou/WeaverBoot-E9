package com.weaverboot.frame.ioc.beans.bean.definition.inte;

import com.weaverboot.tools.logTools.LogTools;
import weaver.general.BaseBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AbstractWeaFactoryBean implements WeaFactoryBean {

    private Class constructorClass;

    private Method constructorMethod;

    protected BaseBean baseBean = new BaseBean();

    @Override
    public Object createBean() {

        if (constructorClass != null && constructorMethod != null){

            //可以优化

            try {

                return constructorMethod.invoke(constructorClass.newInstance());

            } catch (IllegalAccessException e) {

                LogTools.writeLog("通过指定构造方法注入错误，原因为:" + e.getMessage());

                e.printStackTrace();

            } catch (InvocationTargetException e) {

                LogTools.writeLog("通过指定构造方法注入错误，原因为:" + e.getMessage());

                e.printStackTrace();

            } catch (InstantiationException e) {

                LogTools.writeLog("通过指定构造方法注入错误，原因为:" + e.getMessage());

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
}
