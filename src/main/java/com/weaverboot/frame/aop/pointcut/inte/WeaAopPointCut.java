package com.weaverboot.frame.aop.pointcut.inte;

import com.weaverboot.frame.aop.advisor.advisor.inte.WeaAopAdvisor;

import java.lang.reflect.Method;
import java.util.List;

public interface WeaAopPointCut {

    //匹配类
    boolean matchsClass(Class<?> targetClass);

    //匹配方法
    boolean matchsMethod(Method method, Class<?> targetClass);

    List<WeaAopAdvisor> getWeaAopAdvisorList();

    void addWeaAopAdvisor(WeaAopAdvisor weaAopAdvisor);

    int getOrder();

    String getExpression();

}
