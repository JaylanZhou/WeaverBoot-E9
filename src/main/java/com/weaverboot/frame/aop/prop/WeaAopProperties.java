package com.weaverboot.frame.aop.prop;

import com.weaverboot.frame.aop.handler.impl.DefaultWeaAopHandler;
import com.weaverboot.frame.aop.handler.inte.AbstractWeaAopHandler;

public class WeaAopProperties {

    private WeaAopProperties(){};

    public static Class<? extends AbstractWeaAopHandler> DEFAULT_WEA_AOP_HANDLER = DefaultWeaAopHandler.class;

}
