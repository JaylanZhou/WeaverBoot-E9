package com.weaverboot.frame.aop.handler.inte;

import com.weaverboot.frame.ioc.handler.replace.inte.WeaIocReplaceHandler;
import com.weaverboot.frame.ioc.prop.WeaIocProperties;

public abstract class AbstractWeaAopHandler implements WeaAopHandler {

    private WeaIocReplaceHandler weaIocReplaceHandler;

    public WeaIocReplaceHandler getWeaIocReplaceHandler() throws IllegalAccessException, InstantiationException {

        if (weaIocReplaceHandler == null){

            return WeaIocProperties.DEFAULT_WEA_IOC_REPLACE_AFTER_HANDLER.newInstance();

        }

        return weaIocReplaceHandler;
    }

    public void setWeaIocReplaceHandler(WeaIocReplaceHandler weaIocReplaceHandler) {
        this.weaIocReplaceHandler = weaIocReplaceHandler;
    }

}
