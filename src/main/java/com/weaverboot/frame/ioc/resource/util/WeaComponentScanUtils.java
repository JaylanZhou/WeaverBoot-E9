package com.weaverboot.frame.ioc.resource.util;

import com.weaverboot.frame.ioc.beans.context.impl.DefaultWeaApplicationContext;
import com.weaverboot.frame.ioc.beans.context.inte.WeaApplicationContext;

import java.lang.reflect.InvocationTargetException;

public class WeaComponentScanUtils {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        WeaApplicationContext weaApplicationContext = new DefaultWeaApplicationContext();

        weaApplicationContext.refresh();

    }

}
