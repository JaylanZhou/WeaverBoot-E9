package com.weaverboot.frame.aop.handler.aspectPointcut.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

public interface WeaAspectPointcutHandler {

    Object registerProxy(AbstractWeaBeanDefinition abstractWeaBeanDefinition);

}
