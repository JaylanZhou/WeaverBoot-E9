package com.weaverboot.frame.aop.pointcut.impl;

import com.weaverboot.frame.aop.pointcut.inte.WeaAopPointCut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;

/**
 * @Author : Jaylan Zhou
 * @Date : 2019-12-20 12:53
 * @Version : 1.0
 */
public class AspectJWeaAopPointcut implements WeaAopPointCut {

    private PointcutParser pointcutParser;

    private PointcutExpression pointcutExpression;

    public AspectJWeaAopPointcut(String expression){

        pointcutParser = PointcutParser.getPointcutParserSupportingAllPrimitivesAndUsingContextClassloaderForResolution();

        pointcutExpression = pointcutParser.parsePointcutExpression(expression);

    }

    @Override
    public boolean matchsClass(Class<?> targetClass) {

        System.out.println(pointcutExpression.getPointcutExpression());

        return pointcutExpression.couldMatchJoinPointsInType(targetClass);

    }

    @Override
    public boolean matchsMethod(Method method, Class<?> targetClass) {

        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);

        return shadowMatch.alwaysMatches();

    }

}
