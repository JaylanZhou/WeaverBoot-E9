package com.weaverboot.frame.aop.advisor.pointcut.inte;

import com.weaverboot.frame.aop.advisor.advisor.inte.AbstractWeaAopAdvisor;
import com.weaverboot.frame.aop.pointcut.inte.WeaAopPointCut;

/**
 * @Author : Jaylan Zhou
 * @Date : 2019-12-20 13:32
 * @Version : 1.0
 */
public abstract class AbstractAspectJWeaAopPointCutAdvisor extends AbstractWeaAopAdvisor {

    protected WeaAopPointCut weaAopPointCut;

    public AbstractAspectJWeaAopPointCutAdvisor(String adviceBeanName, String expression,WeaAopPointCut weaAopPointCut) {

        super(adviceBeanName, expression);

        this.weaAopPointCut = weaAopPointCut;

    }

    public WeaAopPointCut getWeaAopPointCut(){

        return this.weaAopPointCut;

    }

}
