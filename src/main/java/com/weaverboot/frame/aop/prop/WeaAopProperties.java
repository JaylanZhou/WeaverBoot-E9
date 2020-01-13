package com.weaverboot.frame.aop.prop;

import com.weaverboot.frame.aop.handler.aspectPointcut.impl.DefaultWeaAspectPointcutHandler;
import com.weaverboot.frame.aop.handler.aspectPointcut.inte.AbstractWeaAspectPointcutHandler;
import com.weaverboot.frame.aop.handler.proxy.inte.AbstractWeaProxyBuildHandler;
import com.weaverboot.frame.aop.handler.replace.impl.WeaAopReplaceReplaceHandler;
import com.weaverboot.frame.aop.handler.replace.inte.AbstractWeaAopReplaceHandler;

public class WeaAopProperties {

    private WeaAopProperties(){}

    public static Class<? extends AbstractWeaAopReplaceHandler> DEFAULT_WEA_AOP_HANDLER = WeaAopReplaceReplaceHandler.class;

    public static Class<? extends AbstractWeaAspectPointcutHandler> DEFAULT_WEA_ASPECT_POINTCUT_HANDLER = DefaultWeaAspectPointcutHandler.class;

}
