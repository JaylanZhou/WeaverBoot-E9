package com.weaverboot.frame.aop.advisor.advisor.impl;

import com.weaverboot.frame.aop.advice.inte.WeaAopAdvice;
import com.weaverboot.frame.aop.advisor.advisor.inte.AbstractWeaAopAdvisor;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-01-06 14:29
 * @Version : 1.0
 */
public class AdviceWeaAopAdvisor extends AbstractWeaAopAdvisor {

    public AdviceWeaAopAdvisor(String adviceBeanName, Class<? extends WeaAopAdvice> adviceBeanClass, String expression){

        this.setAdviceBeanName(adviceBeanName);

        this.setAdviceBeanClass(adviceBeanClass);

        this.setExpression(expression);

    }

    public AdviceWeaAopAdvisor(String adviceBeanName, Class<? extends WeaAopAdvice> adviceBeanClass, String expression, int order){

        this.setAdviceBeanName(adviceBeanName);

        this.setAdviceBeanClass(adviceBeanClass);

        this.setExpression(expression);

        this.setOrder(order);

    }

}
