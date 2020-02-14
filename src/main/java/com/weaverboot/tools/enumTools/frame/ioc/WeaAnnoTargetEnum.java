package com.weaverboot.tools.enumTools.frame.ioc;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-02-14 18:33
 * @Version : 1.0
 */
public enum  WeaAnnoTargetEnum {

    TYPE("TYPE"),METHOD("METHOD"),FIELD("FIELD");

    private String result;

    WeaAnnoTargetEnum(String result){

        this.result = result;

    }

    @Override
    public String toString(){

        return this.result;

    }

}
