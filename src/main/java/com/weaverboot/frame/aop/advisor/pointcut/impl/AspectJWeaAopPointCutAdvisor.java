package com.weaverboot.frame.aop.advisor.pointcut.impl;

import com.weaverboot.frame.aop.advisor.pointcut.inte.AbstractAspectJWeaAopPointCutAdvisor;
import com.weaverboot.frame.aop.pointcut.inte.WeaAopPointCut;

/**
 * @Author : Jaylan Zhou
 * @Date : 2019-12-20 13:29
 * @Version : 1.0
 */
public class AspectJWeaAopPointCutAdvisor extends AbstractAspectJWeaAopPointCutAdvisor {

    public AspectJWeaAopPointCutAdvisor(String adviceBeanName, String expression,WeaAopPointCut weaAopPointCut) {

        super(adviceBeanName, expression,weaAopPointCut);

    }

}
