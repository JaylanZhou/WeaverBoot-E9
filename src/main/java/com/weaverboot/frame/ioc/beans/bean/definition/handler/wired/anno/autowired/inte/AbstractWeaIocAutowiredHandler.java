package com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.anno.autowired.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.factory.inte.WeaWiredBeanDefinitionFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.prop.WeaIocProperties;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 *
 * @WeaAutowired 注解注入对象属性 默认实现抽象类
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2019-12-20
 *
 */

public abstract class AbstractWeaIocAutowiredHandler implements WeaIocAutowiredHandler {

    private WeaWiredBeanDefinitionFactory weaWiredBeanDefinitionFactory;

    /**
     *
     * 获取默认属性对应类型注入工厂
     *
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */

    public WeaWiredBeanDefinitionFactory getWeaWiredBeanDefinitionFactory() throws IllegalAccessException, InstantiationException {

        if (weaWiredBeanDefinitionFactory == null){

            weaWiredBeanDefinitionFactory = WeaIocProperties.DEFAULT_WEA_WIRED_BEAN_DEFINITION_FACTORY.newInstance();

        }

        return weaWiredBeanDefinitionFactory;
    }

    public void setWeaWiredBeanDefinitionFactory(WeaWiredBeanDefinitionFactory weaWiredBeanDefinitionFactory) {
        this.weaWiredBeanDefinitionFactory = weaWiredBeanDefinitionFactory;
    }

    /**
     *
     * 依赖注入核心逻辑
     *
     * @param beingBeanDefinition
     * @param beanId 依赖的对象beanId
     * @param field 需要注入的属性field
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws IOException
     */

    protected Object checkDependcy(AbstractWeaBeanDefinition beingBeanDefinition,String beanId, Field field) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {

        if (WeaIocContainer.getBeanDefinition(beanId) == null){ //如果创建好的集合中没有，检查正在创建的集合

            if (WeaIocContainer.getBeingCreateBeanDefinition(beanId) == null) { //如果正在创建的集合中没有，检查初始化未创建集合

                if (WeaIocContainer.getEarlyBeanDefinition(beanId) == null) { //如果初始化未创建集合中还没有，检查是否是接口

                    return checkIsImplBeanAndWired(beingBeanDefinition,field); //检查是否是接口，是否有对应的实现类并注入，否则报错

                } else { //如果初始化未创建的集合中有，则优先注入此对象

                    AbstractWeaBeanDefinition abstractWeaBeanDefinition = WeaIocContainer.getEarlyBeanDefinition(beanId);

                    return getWeaWiredBeanDefinitionFactory().getHandler(abstractWeaBeanDefinition).wiredBean(abstractWeaBeanDefinition);

                }

            } else { //如果此依赖正在被创建，则先返回这个正在被创建的对象，hold到属性中

                return WeaIocContainer.getBeingCreateBeanDefinition(beanId).getBeanObject();

            }

        } //如果这个对象已经被创建，直接返回这个对象

            return WeaIocContainer.getBeanDefinition(beanId).getBeanObject();


    }

    /**
     *
     * 检查三个集合中都没有的注入属性是否为接口或抽象类，并找到对应的实现类注入，否则报错
     *
     * @param beingBeanDefinition
     * @param field 需要被检查的field
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws IOException
     */

    private Object checkIsImplBeanAndWired(AbstractWeaBeanDefinition beingBeanDefinition,Field field) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {

        Class fieldType = field.getType();

        //检查field是不是接口或抽象类
        if (fieldType.isInterface() || Modifier.isAbstract(fieldType.getModifiers())){

            //从初始化集合中寻找实现类
            for (String early : WeaIocContainer.getEarlyBeandefinitionMap().keySet()
                 ) {

                AbstractWeaBeanDefinition abstractWeaBeanDefinition = WeaIocContainer.getEarlyBeanDefinition(early);

                if (fieldType.isAssignableFrom(abstractWeaBeanDefinition.getBeanClass())) {

                    return getWeaWiredBeanDefinitionFactory().getHandler(abstractWeaBeanDefinition).wiredBean(abstractWeaBeanDefinition);

                }

            }

            //从正在创建的集合中寻找抽象类
            for (String being : WeaIocContainer.getBeingCreateBeandefinitionMap().keySet()
            ) {

                AbstractWeaBeanDefinition abstractWeaBeanDefinition = WeaIocContainer.getBeingCreateBeanDefinition(being);

                if (fieldType.isAssignableFrom(abstractWeaBeanDefinition.getBeanClass())) {

                    return abstractWeaBeanDefinition.getBeanObject();

                }

            }

            //从已创建的集合中寻找抽象类
            for (String has : WeaIocContainer.getBeandefinitionMap().keySet()
                 ) {

                AbstractWeaBeanDefinition abstractWeaBeanDefinition = WeaIocContainer.getBeanDefinition(has);

                if (fieldType.isAssignableFrom(abstractWeaBeanDefinition.getBeanClass())) {

                    return abstractWeaBeanDefinition.getBeanObject();

                }

            }

            //如果都找不到直接报错
            throw new RuntimeException("未找到:" + fieldType + "的实现类");

        } else {

            //如果其不是接口，直接报错
            throw new RuntimeException("未找到:" + fieldType + " 的注册类");

        }

    }

}
