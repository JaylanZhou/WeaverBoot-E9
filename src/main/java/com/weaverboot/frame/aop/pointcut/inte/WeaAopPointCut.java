package com.weaverboot.frame.aop.pointcut.inte;

import java.lang.reflect.Method;

public interface WeaAopPointCut {

    //匹配类
    boolean matchsClass(Class<?> targetClass);

    //匹配方法
    boolean matchsMethod(Method method, Class<?> targetClass);

}
