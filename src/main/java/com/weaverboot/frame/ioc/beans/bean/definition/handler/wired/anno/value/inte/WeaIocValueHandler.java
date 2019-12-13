package com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.anno.value.inte;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface WeaIocValueHandler {

    void valueField(Field field, Object object) throws IllegalAccessException, IOException;

    void methodField(Method method, Object object) throws IllegalAccessException, IOException, InvocationTargetException;

}
