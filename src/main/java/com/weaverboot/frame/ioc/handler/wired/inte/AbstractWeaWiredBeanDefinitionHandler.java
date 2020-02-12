package com.weaverboot.frame.ioc.handler.wired.inte;

import com.weaverboot.frame.aop.handler.aspectPointcut.inte.WeaAspectPointcutHandler;
import com.weaverboot.frame.aop.prop.WeaAopProperties;
import com.weaverboot.frame.ioc.beans.bean.definition.impl.WeaRootBeanDefinition;
import com.weaverboot.frame.ioc.beans.bean.definition.utils.WeaIocCheckUtils;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.handler.postProcessor.wired.inte.WeaIocWiredBeanPostProcessorHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.autowired.inte.WeaIocAutowiredHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.value.inte.WeaIocValueHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.WeaFactoryBean;
import com.weaverboot.frame.ioc.postProcessor.wired.inte.WeaWiredBeanPostProcessor;
import com.weaverboot.frame.ioc.prop.WeaIocProperties;
import com.weaverboot.tools.classTools.ReflectTools;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 *
 * 属性注入 - 标准基类
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */

public abstract class AbstractWeaWiredBeanDefinitionHandler implements WeaWiredBeanDefinitionHandler {

    private WeaIocAutowiredHandler weaIocAutowiredHandler;

    private WeaIocValueHandler weaIocValueHandler;

    private WeaAspectPointcutHandler weaAspectPointcutHandler;

    private WeaIocWiredBeanPostProcessorHandler weaIocWiredBeanPostProcessorHandler;

    protected static Object lockObject = new Object();

    /**
     *
     * 获取Autowired处理类，如不指定则为默认类
     *
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */

    public WeaIocAutowiredHandler getWeaIocAutowiredHandler() throws IllegalAccessException, InstantiationException {

        if (weaIocAutowiredHandler == null){

            weaIocAutowiredHandler = WeaIocProperties.DEFAULT_WEA_IOC_AUTOWIRED_HANDLER.newInstance();

        }

        return weaIocAutowiredHandler;
    }

    public void setWeaIocAutowiredHandler(WeaIocAutowiredHandler weaIocAutowiredHandler) {
        this.weaIocAutowiredHandler = weaIocAutowiredHandler;
    }

    /**
     *
     * 获取Value处理类，如不指定则为默认类
     *
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */

    public WeaIocValueHandler getWeaIocValueHandler() throws IllegalAccessException, InstantiationException {

        if (weaIocValueHandler == null){

            weaIocValueHandler = WeaIocProperties.DEFAULT_WEA_IOC_VALUE_HANDLER.newInstance();

        }

        return weaIocValueHandler;
    }

    public void setWeaIocValueHandler(WeaIocValueHandler weaIocValueHandler) {
        this.weaIocValueHandler = weaIocValueHandler;
    }

    @Override
    public WeaAspectPointcutHandler getWeaAspectPointCutHandler() throws IllegalAccessException, InstantiationException {

        if (weaAspectPointcutHandler == null){

            weaAspectPointcutHandler = WeaAopProperties.DEFAULT_WEA_ASPECT_POINTCUT_HANDLER.newInstance();

        }

        return weaAspectPointcutHandler;

    }

    @Override
    public void setWeaAspectPointCutHandler(WeaAspectPointcutHandler weaAspectPointCutHandler) {

        this.weaAspectPointcutHandler = weaAspectPointCutHandler;


    }

    @Override
    public WeaIocWiredBeanPostProcessorHandler getWeaIocWiredBeanPostProcessorHandler() throws IllegalAccessException, InstantiationException {

        if (this.weaIocWiredBeanPostProcessorHandler == null){

            weaIocWiredBeanPostProcessorHandler = WeaIocProperties.DEFAULT_WEA_IOC_WIRED_BEAN_POST_PROCESSOR_HANDLER.newInstance();

        }

        return weaIocWiredBeanPostProcessorHandler;
    }

    @Override
    public void setWeaIocWiredBeanPostProcessorHandler(WeaIocWiredBeanPostProcessorHandler weaIocWiredBeanPostProcessorHandler) {
        this.weaIocWiredBeanPostProcessorHandler = weaIocWiredBeanPostProcessorHandler;
    }

    /**
     *
     * 创建实体类
     *
     * @param abstractWeaBeanDefinition
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */

    @Override
    public Object createBean(AbstractWeaBeanDefinition abstractWeaBeanDefinition) throws IllegalAccessException, InstantiationException {

        WeaFactoryBean weaFactoryBean = abstractWeaBeanDefinition.getWeaFactoryBean();

        if (!(abstractWeaBeanDefinition instanceof WeaRootBeanDefinition)) { //日后用作是否开启动态代理配置用

            Object object = this.getWeaAspectPointCutHandler().registerProxy(abstractWeaBeanDefinition);

            if (object != null){

                return object;

            }

        }

        if (abstractWeaBeanDefinition.getWeaFactoryBean() != null){

            return weaFactoryBean.createBean();

        } else {

            return abstractWeaBeanDefinition.getBeanClass().newInstance();

        }

    }

    /**
     *
     * 核心：注入属性 - 基本方法
     *
     * @param abstractWeaBeanDefinition
     * @return
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IOException
     */

    @Override
    public Object wiredBean(AbstractWeaBeanDefinition abstractWeaBeanDefinition) throws IllegalAccessException, ClassNotFoundException, InstantiationException, IOException {

        Object object = createBean(abstractWeaBeanDefinition);

        abstractWeaBeanDefinition.setBeanObject(object);

        String beanId = abstractWeaBeanDefinition.getBeanId();

        if (WeaIocCheckUtils.checkIsSingleton(abstractWeaBeanDefinition)) { //检查是否是单例创建

            WeaIocContainer.getEarlyBeandefinitionMap().remove(beanId);

            WeaIocContainer.setBeingCreateBeandefinition(beanId, abstractWeaBeanDefinition);

        }

        this.getWeaIocWiredBeanPostProcessorHandler().handlePostProcessBeforeInitialization(object,beanId);

        List<Field> fields = ReflectTools.getAllFields(abstractWeaBeanDefinition.getBeanClass());

        for (Field field : fields
        ) {

            getWeaIocAutowiredHandler().autoWiredField(field,object);

            getWeaIocValueHandler().valueField(field,object);

        }

        if (WeaIocCheckUtils.checkIsSingleton(abstractWeaBeanDefinition)){

            WeaIocContainer.getBeingCreateBeandefinitionMap().remove(beanId);

            WeaIocContainer.getBeandefinitionMap().put(beanId,abstractWeaBeanDefinition);

        }

        this.getWeaIocWiredBeanPostProcessorHandler().handlePostProcessAfterInitialization(object,beanId);

        return object;

    }

}
