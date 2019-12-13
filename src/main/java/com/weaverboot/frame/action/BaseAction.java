package com.weaverboot.frame.action;

import com.weaverboot.frame.service.BaseService;
import weaver.general.BaseBean;

import java.lang.reflect.ParameterizedType;

public class BaseAction<S extends BaseService> extends BaseBean{

    protected S baseService;

    {
        try {
            baseService = this.getSClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("BaseService初始化错误，原因为:" + e.getMessage());
        }
    }


    /**
     * 通过反射来获取泛型的Class
     * <p>
     * 主要实现机制：
     */

    private Class<S> getSClass() {

        //获取泛型的类型
        Class<S> entityClass = (Class<S>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];

        return entityClass;


    }



}
