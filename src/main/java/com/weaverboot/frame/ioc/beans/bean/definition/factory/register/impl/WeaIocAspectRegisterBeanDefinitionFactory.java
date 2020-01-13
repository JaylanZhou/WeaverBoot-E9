package com.weaverboot.frame.ioc.beans.bean.definition.factory.register.impl;

import com.weaverboot.frame.aop.advice.impl.*;
import com.weaverboot.frame.aop.advisor.advisor.impl.AdviceWeaAopAdvisor;
import com.weaverboot.frame.aop.advisor.advisor.inte.WeaAopAdvisor;
import com.weaverboot.frame.aop.anno.advice.*;
import com.weaverboot.frame.aop.anno.aspect.WeaAopAspect;
import com.weaverboot.frame.aop.pointcut.impl.AspectJWeaAopPointcut;
import com.weaverboot.frame.aop.pointcut.inte.WeaAopPointCut;
import com.weaverboot.frame.ioc.beans.bean.definition.factory.register.inte.AbstractWeaRegisterBeanDefinitionFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.impl.DefaultWeaBeanDefinition;
import com.weaverboot.frame.ioc.beans.bean.definition.impl.WeaRootBeanDefinition;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;
import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.classTools.ReflectTools;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * 建立AOP类的 BeanDefinition
 *
 * @Author : Jaylan Zhou
 * @Date : 2020-01-06 11:38
 * @Version : 1.0
 *
 */
public class WeaIocAspectRegisterBeanDefinitionFactory extends AbstractWeaRegisterBeanDefinitionFactory {

    @Override
    public AbstractWeaBeanDefinition creatWeaBeanDefinition(Class clazz, Annotation annotation) throws IllegalAccessException, ClassNotFoundException, InstantiationException {

        if (clazz == null){

            throw new RuntimeException("扫描的 class 不能是null");

        }

        AbstractWeaBeanDefinition weaBeanDefinition = new WeaRootBeanDefinition();

        getWeaCreateWeaBeanDefinitionPostProcessor().initWeaBeanDefinition(weaBeanDefinition,clazz,annotation);

        getWeaCreateWeaBeanDefinitionPostProcessor().postProcessBeforeRegister(weaBeanDefinition);

        String beanId = weaBeanDefinition.getBeanClassName();

        WeaAopAspect weaAopAspect = (WeaAopAspect) weaBeanDefinition.getBeanAnnotation();

        WeaAopPointCut weaAopPointCut = new AspectJWeaAopPointcut(weaAopAspect.pointCut(),weaAopAspect.order());

        if (BaseTools.notNullString(weaAopAspect.value())){

            beanId = weaAopAspect.value();

        }

        getWeaCreateWeaBeanDefinitionPostProcessor().initEarlyContainer(beanId,weaBeanDefinition);

        getWeaCreateWeaBeanDefinitionPostProcessor().postProcessAfterRegister(weaBeanDefinition);

        this.registeAopInfo(weaBeanDefinition,weaAopPointCut);

        WeaIocContainer.WEA_AOP_ADVISOR_LIST.add(weaAopPointCut);

        return weaBeanDefinition;

    }

    private void registeAopInfo(AbstractWeaBeanDefinition weaBeanDefinition,WeaAopPointCut weaAopPointCut){

        Class wClass = weaBeanDefinition.getBeanClass();

        List<Method> methodList = ReflectTools.getAllMethodForClass(wClass);

        String pointCut = weaAopPointCut.getExpression();

        int order = weaAopPointCut.getOrder();

        for (Method method : methodList
             ) {

            WeaAopAdvisor weaAopAdvisor;

            if (method.isAnnotationPresent(WeaAopBefore.class)){

                weaAopAdvisor = new AdviceWeaAopAdvisor(DefaultWeaAopBeforeAdvice.class.getName(),DefaultWeaAopBeforeAdvice.class,pointCut,order);

            } else if (method.isAnnotationPresent(WeaAopAfter.class)){

                weaAopAdvisor = new AdviceWeaAopAdvisor(DefaultWeaAopAfterAdvice.class.getName(), DefaultWeaAopAfterAdvice.class,pointCut,order);

            } else if (method.isAnnotationPresent(WeaAopAround.class)){

                weaAopAdvisor = new AdviceWeaAopAdvisor(DefaultWeaAopAroundAdvice.class.getName(),DefaultWeaAopAroundAdvice.class,pointCut,order);

            } else if (method.isAnnotationPresent(WeaAopAfterReturning.class)){

                weaAopAdvisor = new AdviceWeaAopAdvisor(DefaultWeaAopAfterReturnAdvice.class.getName(),DefaultWeaAopAfterReturnAdvice.class,pointCut,order);

            } else if (method.isAnnotationPresent(WeaAopException.class)){

                weaAopAdvisor = new AdviceWeaAopAdvisor(DefaultWeaAopExceptionAdvice.class.getName(), DefaultWeaAopExceptionAdvice.class,pointCut,order);

            }  else {

                continue;

            }

            weaAopPointCut.addWeaAopAdvisor(weaAopAdvisor);

        }

    }

}
