package com.weaverboot.frame.aop.advisor.advisor.inte;

import com.weaverboot.frame.aop.advice.inte.WeaAopAdvice;

/**
 * @Author : Jaylan Zhou
 * @Date : 2019-12-20 13:31
 * @Version : 1.0
 */
public abstract class AbstractWeaAopAdvisor implements WeaAopAdvisor {

    private String adviceBeanName;

    private Class<? extends WeaAopAdvice> adviceBeanClass;

    private String expression;

    private int order;


    @Override
    public String getAdviceBeanName() {

        return this.adviceBeanName;

    }

    @Override
    public String getExpression() {

        return this.expression;

    }


    public void setAdviceBeanName(String adviceBeanName) {
        this.adviceBeanName = adviceBeanName;
    }

    @Override
    public Class<? extends WeaAopAdvice> getAdviceBeanClass() {
        return adviceBeanClass;
    }


    public void setAdviceBeanClass(Class<? extends WeaAopAdvice> adviceBeanClass) {
        this.adviceBeanClass = adviceBeanClass;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
