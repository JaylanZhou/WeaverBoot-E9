package com.weaverboot.frame.ioc.handler.wired.anno.inte;

import java.lang.reflect.Field;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-02-14 19:48
 * @Version : 1.0
 */
public interface WeaWiredColumnAnnoInovkeHandler {

    void invokeWiredColumn(Field field,Object object,String beanName);

}
