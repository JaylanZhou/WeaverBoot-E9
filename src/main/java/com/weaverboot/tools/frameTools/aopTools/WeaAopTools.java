package com.weaverboot.tools.frameTools.aopTools;

import com.weaverboot.tools.classTools.ClassTools;
import com.weaverboot.tools.classTools.ReflectTools;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * Weaver Boot AOP 工具类
 *
 * @Author : Jaylan Zhou
 * @Date : 2019-12-20 14:10
 * @Version : 1.0
 */
public class WeaAopTools {

    private WeaAopTools(){

    }

    public List<Method> getAllMethodForClass(Class<?> beanClass) {

        List<Method> allMethods = new LinkedList<>();

        //获取beanClass的所有接口
        Set<Class<?>> classes = new LinkedHashSet<>(ClassTools.getAllInterfacesForClassAsSet(beanClass));

        classes.add(beanClass);

        //遍历所有的类和接口反射获取到所有的方法
        for (Class<?> clazz : classes) {

            Method[] methods = ReflectTools.getAllDeclaredMethods(clazz);

            for (Method m : methods) {

                allMethods.add(m);

            }

        }

        return allMethods;

    }

}
