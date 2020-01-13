package com.weaverboot.frame.aop.pointcut.impl;

import com.weaverboot.frame.aop.advisor.advisor.inte.WeaAopAdvisor;
import com.weaverboot.frame.aop.pointcut.inte.WeaAopPointCut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 切点默认实现类
 *
 * @Author : Jaylan Zhou
 * @Date : 2019-12-20 12:53
 * @Version : 1.0
 */
public class AspectJWeaAopPointcut implements WeaAopPointCut {

    private PointcutParser pointcutParser;

    private PointcutExpression pointcutExpression;

    private String expression;

    private int order;

    private List<WeaAopAdvisor> weaAopAdvisorList;

    public AspectJWeaAopPointcut(String expression,int order){

        pointcutParser = PointcutParser.getPointcutParserSupportingAllPrimitivesAndUsingContextClassloaderForResolution();

        pointcutExpression = pointcutParser.parsePointcutExpression(expression);

        this.setExpression(expression);

        this.setOrder(order);

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

    @Override
    public List<WeaAopAdvisor> getWeaAopAdvisorList() {
        return this.weaAopAdvisorList;
    }

    @Override
    public void addWeaAopAdvisor(WeaAopAdvisor weaAopAdvisor) {

        if (weaAopAdvisorList == null){

            weaAopAdvisorList = new ArrayList<>();

        }

        this.weaAopAdvisorList.add(weaAopAdvisor);

    }

    @Override
    public String getExpression() {
        return expression;
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
