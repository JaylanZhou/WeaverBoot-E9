package com.weaverboot.frame.ioc.beans.bean.definition.factory.register.impl;

import com.weaverboot.frame.ioc.anno.classAnno.WeaIocService;
import com.weaverboot.frame.ioc.anno.classAnno.WeaIocWiredBeanPostProcessor;
import com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte.AbstractWeaRegisterBeanDefinitionFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.impl.DefaultWeaBeanDefinition;
import com.weaverboot.frame.ioc.beans.bean.definition.impl.WeaRootBeanDefinition;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.postProcessor.wired.inte.WeaWiredBeanPostProcessor;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.logTools.LogTools;

import java.lang.annotation.Annotation;

/**
 *
 * 默认 WeaIocService 注册逻辑处理类
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2019-12-17
 *
 */

public class WeaIocWiredBeanPostProcessorDefinitionFactory extends AbstractWeaRegisterBeanDefinitionFactory {

    /**
     *
     * 注册默认的WeaIocService类
     *
     * @param clazz
     * @param annotation
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */

    @Override
    public AbstractWeaBeanDefinition creatWeaBeanDefinition(Class clazz, Annotation annotation) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        if (clazz == null){

            throw new RuntimeException("扫描的 class 不能是null");

        }

        if (WeaWiredBeanPostProcessor.class.isAssignableFrom(clazz)) {

            AbstractWeaBeanDefinition weaBeanDefinition = new WeaRootBeanDefinition();

            getWeaCreateWeaBeanDefinitionPostProcessor().initWeaBeanDefinition(weaBeanDefinition, clazz, annotation);

            getWeaCreateWeaBeanDefinitionPostProcessor().postProcessBeforeRegister(weaBeanDefinition);

            if (clazz.isAnnotationPresent(WeaIocWiredBeanPostProcessor.class)) {

                if (weaBeanDefinition.isSingleton() && !weaBeanDefinition.isPrototype()) {

                    try {

                        weaBeanDefinition.setBeanObject(clazz.newInstance());

                    } catch (Exception e) {

                        LogTools.error("初始化postprocessor" + clazz.getName() + "失败，至少有一个无参构造方法！具体原因为:" + e.getMessage());

                    }
                }

                WeaIocContainer.addWiredBeanPostProcessor(weaBeanDefinition);

            }

            //getWeaCreateWeaBeanDefinitionPostProcessor().initEarlyContainer(beanId,weaBeanDefinition);

            getWeaCreateWeaBeanDefinitionPostProcessor().postProcessAfterRegister(weaBeanDefinition);

            return weaBeanDefinition;

        } else {

            LogTools.error("postprocessor类" + clazz.getName() + "未注册WeaWireBeanPostProcessor接口");

            return null;

        }

    }
}
