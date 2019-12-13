package com.weaverboot.frame.ioc.beans.bean.definition.handler.replace.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

public interface WeaIocReplaceHandler {

    void getReplaceForm(AbstractWeaBeanDefinition abstractWeaBeanDefinition) throws IllegalAccessException, ClassNotFoundException, InstantiationException;

}
