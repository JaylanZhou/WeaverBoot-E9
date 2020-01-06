package com.weaverboot.frame.ioc.handler.replace.inte;

import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

import java.io.IOException;

public interface WeaIocReplaceHandler {

    void getReplaceForm(AbstractWeaBeanDefinition abstractWeaBeanDefinition) throws IllegalAccessException, ClassNotFoundException, InstantiationException, IOException;

}
