package com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.anno.autowired.impl.DefaultWeaIocAutowiredHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.anno.autowired.inte.WeaIocAutowiredHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.anno.value.inte.WeaIocValueHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.WeaFactoryBean;
import com.weaverboot.frame.ioc.beans.bean.definition.utils.WeaIocCheckUtils;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.prop.WeaIocProperties;

import java.io.IOException;
import java.lang.reflect.Field;

public abstract class AbstractWeaWiredBeanDefinitionHandler implements WeaWiredBeanDefinitionHandler {

    private WeaIocAutowiredHandler weaIocAutowiredHandler;

    private WeaIocValueHandler weaIocValueHandler;

    protected static Object lockObject = new Object();

    public WeaIocAutowiredHandler getWeaIocAutowiredHandler() throws IllegalAccessException, InstantiationException {

        if (weaIocAutowiredHandler == null){

            return WeaIocProperties.DEFAULT_WEA_IOC_AUTOWIRED_HANDLER.newInstance();

        }

        return weaIocAutowiredHandler;
    }

    public void setWeaIocAutowiredHandler(WeaIocAutowiredHandler weaIocAutowiredHandler) {
        this.weaIocAutowiredHandler = weaIocAutowiredHandler;
    }

    public WeaIocValueHandler getWeaIocValueHandler() throws IllegalAccessException, InstantiationException {

        if (weaIocValueHandler == null){

            return WeaIocProperties.DEFAULT_WEA_IOC_VALUE_HANDLER.newInstance();

        }

        return weaIocValueHandler;
    }

    public void setWeaIocValueHandler(WeaIocValueHandler weaIocValueHandler) {
        this.weaIocValueHandler = weaIocValueHandler;
    }

    protected void afterWiredOperate(AbstractWeaBeanDefinition abstractWeaBeanDefinition){

        String beanId = abstractWeaBeanDefinition.getBeanId();

        WeaIocContainer.getBeingCreateBeandefinitionMap().remove(beanId);

        if (WeaIocCheckUtils.checkIsSingleton(abstractWeaBeanDefinition)){

            WeaIocContainer.getBeandefinitionMap().put(beanId,abstractWeaBeanDefinition);

        }

    }

    protected void beforeWiredOperate(AbstractWeaBeanDefinition abstractWeaBeanDefinition) throws InstantiationException, IllegalAccessException {

        Object object = createBean(abstractWeaBeanDefinition);

        abstractWeaBeanDefinition.setBeanObject(object);

        String beanId = abstractWeaBeanDefinition.getBeanId();

        if (WeaIocCheckUtils.checkIsSingleton(abstractWeaBeanDefinition)) {

            WeaIocContainer.getEarlyBeandefinitionMap().remove(beanId);

            WeaIocContainer.setBeingCreateBeandefinition(beanId, abstractWeaBeanDefinition);

        }

    }

    protected Object createBean(AbstractWeaBeanDefinition abstractWeaBeanDefinition) throws IllegalAccessException, InstantiationException {

        WeaFactoryBean weaFactoryBean = abstractWeaBeanDefinition.getWeaFactoryBean();

        if (abstractWeaBeanDefinition.getWeaFactoryBean() != null){

            return weaFactoryBean.createBean();

        } else {

            return abstractWeaBeanDefinition.getBeanClass().newInstance();

        }

    }



    @Override
    public synchronized Object wiredBean(AbstractWeaBeanDefinition abstractWeaBeanDefinition) throws IllegalAccessException, ClassNotFoundException, InstantiationException, IOException {

        Object object = abstractWeaBeanDefinition.getBeanObject();

        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields
        ) {

            getWeaIocAutowiredHandler().autoWiredField(field,object);

            getWeaIocValueHandler().valueField(field,object);


        }

        return object;

    }

}
