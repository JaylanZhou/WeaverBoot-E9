package com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.anno.autowired.impl;

import com.weaverboot.frame.ioc.anno.fieldAnno.WeaAutowired;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.anno.autowired.inte.AbstractWeaIocAutowiredHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.tools.baseTools.BaseTools;
import weaver.general.BaseBean;

import java.io.IOException;
import java.lang.reflect.Field;

public class DefaultWeaIocAutowiredHandler extends AbstractWeaIocAutowiredHandler {

    private BaseBean baseBean = new BaseBean();


    @Override
    public void autoWiredField(AbstractWeaBeanDefinition abstractWeaBeanDefinition,Field field,Object object) throws IllegalAccessException, ClassNotFoundException, InstantiationException, IOException {

        if (field.isAnnotationPresent(WeaAutowired.class)){

            WeaAutowired weaAutowired = field.getAnnotation(WeaAutowired.class);

            String beanId = BaseTools.toLowerCaseFirstOne(field.getType().getTypeName());

            if (BaseTools.notNullString(weaAutowired.value())){

                beanId = weaAutowired.value();

            }

            Object resultObject = checkDependcy(abstractWeaBeanDefinition,beanId,field);

            field.setAccessible(true);

            try {

                field.set(object, resultObject);

            } catch (Exception e){

                baseBean.writeLog("无法将:" + object.getClass().getName() + " 注册为:" + field.getType().getTypeName() + "，请检查注入类型");

            }

        }

    }



}
