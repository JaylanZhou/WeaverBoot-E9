package com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.anno.autowired.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.factory.inte.WeaWiredBeanDefinitionFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.prop.WeaIocProperties;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public abstract class AbstractWeaIocAutowiredHandler implements WeaIocAutowiredHandler {

    private WeaWiredBeanDefinitionFactory weaWiredBeanDefinitionFactory;

    public WeaWiredBeanDefinitionFactory getWeaWiredBeanDefinitionFactory() throws IllegalAccessException, InstantiationException {

        if (weaWiredBeanDefinitionFactory == null){

            weaWiredBeanDefinitionFactory = WeaIocProperties.DEFAULT_WEA_WIRED_BEAN_DEFINITION_FACTORY.newInstance();

        }

        return weaWiredBeanDefinitionFactory;
    }

    public void setWeaWiredBeanDefinitionFactory(WeaWiredBeanDefinitionFactory weaWiredBeanDefinitionFactory) {
        this.weaWiredBeanDefinitionFactory = weaWiredBeanDefinitionFactory;
    }

    protected Object checkDependcy(String beanId, Field field) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {

        if (WeaIocContainer.getBeanDefinition(beanId) == null){

            if (WeaIocContainer.getBeingCreateBeanDefinition(beanId) == null) {

                if (WeaIocContainer.getEarlyBeanDefinition(beanId) == null) {

                    return checkIsImplBeanAndWired(field);

                } else {

                    AbstractWeaBeanDefinition abstractWeaBeanDefinition = WeaIocContainer.getEarlyBeanDefinition(beanId);

                    return getWeaWiredBeanDefinitionFactory().getHandler(abstractWeaBeanDefinition).wiredBean(abstractWeaBeanDefinition);

                }

            } else {

                return WeaIocContainer.getBeingCreateBeanDefinition(beanId).getBeanObject();

            }

        }

            return WeaIocContainer.getBeanDefinition(beanId).getBeanObject();


    }

    private Object checkIsImplBeanAndWired(Field field) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {

        Class fieldType = field.getType();

        if (fieldType.isInterface() || Modifier.isAbstract(fieldType.getModifiers())){

            for (String early : WeaIocContainer.getEarlyBeandefinitionMap().keySet()
                 ) {

                AbstractWeaBeanDefinition abstractWeaBeanDefinition = WeaIocContainer.getEarlyBeanDefinition(early);

                if (fieldType.isInstance(abstractWeaBeanDefinition.getBeanObject())) {

                    return getWeaWiredBeanDefinitionFactory().getHandler(abstractWeaBeanDefinition).wiredBean(abstractWeaBeanDefinition);

                }

            }

            for (String being : WeaIocContainer.getBeingCreateBeandefinitionMap().keySet()
            ) {

                AbstractWeaBeanDefinition abstractWeaBeanDefinition = WeaIocContainer.getBeingCreateBeanDefinition(being);

                if (fieldType.isInstance(abstractWeaBeanDefinition.getBeanObject())) {

                    return abstractWeaBeanDefinition.getBeanObject();

                }

            }

            for (String has : WeaIocContainer.getBeandefinitionMap().keySet()
                 ) {

                AbstractWeaBeanDefinition abstractWeaBeanDefinition = WeaIocContainer.getBeanDefinition(has);

                if (fieldType.isInstance(abstractWeaBeanDefinition.getBeanObject())) {

                    return abstractWeaBeanDefinition.getBeanObject();

                }

            }

            throw new RuntimeException("未找到:" + fieldType + "的实现类");

        } else {

            throw new RuntimeException("未找到:" + fieldType + " 的注册类");

        }

    }

}
