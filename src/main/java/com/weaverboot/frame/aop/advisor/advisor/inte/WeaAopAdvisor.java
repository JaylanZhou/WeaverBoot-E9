package com.weaverboot.frame.aop.advisor.advisor.inte;

import com.weaverboot.frame.aop.advice.inte.WeaAopAdvice;

public interface WeaAopAdvisor {

    String getAdviceBeanName();

    String getExpression();

    Class<? extends WeaAopAdvice> getAdviceBeanClass();

    int getOrder();

}
