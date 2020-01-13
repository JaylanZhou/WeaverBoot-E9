package com.weaverboot.frame.ioc.postProcessor.register.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.handler.register.inte.WeaRegisterIocAnnoHandler;
import com.weaverboot.frame.ioc.prop.WeaIocProperties;

import java.lang.annotation.Annotation;

/**
 *
 * 默认创建BeanDefinition时的前后置方法类
 *
 * @Author : Jaylan Zhou
 * @Date : 2020-01-06 11:43
 * @Version : 1.0
 */
public abstract class AbstractWeaCreateWeaBeanDefinitionPostProcessor implements WeaCreateWeaBeanDefinitionPostProcessor {

    //注册逻辑处理类
    private WeaRegisterIocAnnoHandler weaRegisterIocAnnoHandler;

    /**
     *
     * 获取默认逻辑处理类
     *
     * @return 对应的逻辑处理类
     * @throws IllegalAccessException
     * @throws InstantiationException
     */

    @Override
    public WeaRegisterIocAnnoHandler getWeaRegisterIocAnnoHandler() throws IllegalAccessException, InstantiationException {

        if (weaRegisterIocAnnoHandler == null){

            return WeaIocProperties.DEFAULT_WEA_REGISTER_IOC_ANNO_HANDLER.newInstance();

        }

        return weaRegisterIocAnnoHandler;
    }

    @Override
    public void setWeaRegisterIocAnnoHandler(WeaRegisterIocAnnoHandler weaRegisterIocAnnoHandler) {
        this.weaRegisterIocAnnoHandler = weaRegisterIocAnnoHandler;
    }

    /**
     *
     * 初始化BeanDefinition属性
     *
     * @param abstractWeaBeanDefinition
     * @param clazz
     * @param annotation
     * @throws InstantiationException
     * @throws IllegalAccessException
     */

    @Override
    public void initWeaBeanDefinition(AbstractWeaBeanDefinition abstractWeaBeanDefinition, Class clazz, Annotation annotation) throws InstantiationException, IllegalAccessException {

        abstractWeaBeanDefinition.setBeanClassName(clazz.getName());

        abstractWeaBeanDefinition.setBeanClass(clazz);

        abstractWeaBeanDefinition.setBeanAnnotation(annotation);

        WeaRegisterIocAnnoHandler weaRegisterIocAnnoHandler = getWeaRegisterIocAnnoHandler();

        weaRegisterIocAnnoHandler.handleLazyInit(abstractWeaBeanDefinition);

        weaRegisterIocAnnoHandler.handlePrototype(abstractWeaBeanDefinition);

    }

    /**
     *
     * 向待创建容器中加入此beandifition
     *
     * @param beanId
     * @param weaBeanDefinition
     */

    @Override
    public void initEarlyContainer(String beanId, AbstractWeaBeanDefinition weaBeanDefinition) {

        weaBeanDefinition.setBeanId(beanId);

        WeaIocContainer.setEarlyBeandefinition(beanId, weaBeanDefinition);

        WeaIocContainer.getEarlyBeandifinitionList().add(beanId);

    }

}
