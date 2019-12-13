package com.weaverboot.tools.enumTools.frame;

/**
 *
 * 时间格式枚举类
 *
 * @Author : Jaylan Zhou
 *
 */

public enum DateTypeCondition {

    NORMAL("yyyy-MM-dd HH:mm:sss"),
    ISO8601("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    private String result;

    private DateTypeCondition(String result){

        this.result = result;

    }

    @Override
    public String toString(){

        return this.result;

    }

}
