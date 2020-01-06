package com.weaverboot.frame.ioc.postProcessor.register.inte;

import com.weaverboot.frame.ioc.handler.register.inte.WeaRegisterIocAnnoHandler;
import com.weaverboot.frame.ioc.prop.WeaIocProperties;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-01-06 11:43
 * @Version : 1.0
 */
public abstract class AbstractWeaCreateWeaBeanDefinitionPostProcessor implements WeaCreateWeaBeanDefinitionPostProcessor {

    //注册逻辑处理类
    private WeaRegisterIocAnnoHandler weaRegisterIocAnnoHandler;

    /**
     *
     * 获取默认逻辑处理类
     *
     * @return 对应的逻辑处理类
     * @throws IllegalAccessException
     * @throws InstantiationException
     */

    @Override
    public WeaRegisterIocAnnoHandler getWeaRegisterIocAnnoHandler() throws IllegalAccessException, InstantiationException {

        if (weaRegisterIocAnnoHandler == null){

            return WeaIocProperties.DEFAULT_WEA_REGISTER_IOC_ANNO_HANDLER.newInstance();

        }

        return weaRegisterIocAnnoHandler;
    }

    @Override
    public void setWeaRegisterIocAnnoHandler(WeaRegisterIocAnnoHandler weaRegisterIocAnnoHandler) {
        this.weaRegisterIocAnnoHandler = weaRegisterIocAnnoHandler;
    }

}
