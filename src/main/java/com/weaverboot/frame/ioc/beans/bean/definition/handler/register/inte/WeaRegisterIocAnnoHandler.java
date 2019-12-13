package com.weaverboot.frame.ioc.beans.bean.definition.handler.register.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

public interface WeaRegisterIocAnnoHandler {

    void handleLazyInit(com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition abstractWeaBeanDefinition);

    void handlePrototype(AbstractWeaBeanDefinition abstractWeaBeanDefinition);

}
