package com.weaverboot.frame.aop.advisor.advisor.inte;

/**
 * @Author : Jaylan Zhou
 * @Date : 2019-12-20 13:31
 * @Version : 1.0
 */
public abstract class AbstractWeaAopAdvisor implements WeaAopAdvisor {

    protected String adviceBeanName;

    protected String expression;

    public AbstractWeaAopAdvisor(String adviceBeanName,String expression){

        this.adviceBeanName = adviceBeanName;

        this.expression = expression;

    }

    @Override
    public String getAdviceBeanName() {

        return this.adviceBeanName;

    }

    @Override
    public String getExpression() {

        return this.getExpression();

    }
}
