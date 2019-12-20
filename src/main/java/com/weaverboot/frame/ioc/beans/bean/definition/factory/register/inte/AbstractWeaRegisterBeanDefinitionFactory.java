package com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.prop.WeaIocProperties;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.register.inte.WeaRegisterIocAnnoHandler;

import java.lang.annotation.Annotation;

/**
 *
 * BeanDefinition 创建工厂默认实现
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2019-12-17
 *
 */

public abstract class AbstractWeaRegisterBeanDefinitionFactory implements WeaRegisterBeanDefinitionFactory {

    //注册逻辑处理类
    private WeaRegisterIocAnnoHandler weaRegisterIocAnnoHandler;

    //类加载器
    protected ClassLoader classLoader = this.getClass().getClassLoader();

    /**
     *
     * 获取默认逻辑处理类
     *
     * @return 对应的逻辑处理类
     * @throws IllegalAccessException
     * @throws InstantiationException
     */

    public WeaRegisterIocAnnoHandler getWeaRegisterIocAnnoHandler() throws IllegalAccessException, InstantiationException {

        if (weaRegisterIocAnnoHandler == null){

            return WeaIocProperties.DEFAULT_WEA_REGISTER_IOC_ANNO_HANDLER.newInstance();

        }

        return weaRegisterIocAnnoHandler;
    }

    public void setWeaRegisterIocAnnoHandler(WeaRegisterIocAnnoHandler weaRegisterIocAnnoHandler) {
        this.weaRegisterIocAnnoHandler = weaRegisterIocAnnoHandler;
    }

    /**
     *
     * 默认前注册操作
     *
     * @param abstractWeaBeanDefinition
     * @param clazz
     * @param annotation
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */

    protected String beforeRegisrer(AbstractWeaBeanDefinition abstractWeaBeanDefinition, Class clazz, Annotation annotation) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        abstractWeaBeanDefinition.setBeanClassName(clazz.getName());

        abstractWeaBeanDefinition.setBeanClass(clazz);

        abstractWeaBeanDefinition.setBeanAnnotation(annotation);

        WeaRegisterIocAnnoHandler weaRegisterIocAnnoHandler = getWeaRegisterIocAnnoHandler();

        weaRegisterIocAnnoHandler.handleLazyInit(abstractWeaBeanDefinition);

        weaRegisterIocAnnoHandler.handlePrototype(abstractWeaBeanDefinition);

        String beanId = abstractWeaBeanDefinition.getBeanClassName();

        return beanId;

    }

}
