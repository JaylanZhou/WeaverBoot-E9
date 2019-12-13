package com.weaverboot.tools.enumTools.frame;

/**
 *
 * ORDER BY 枚举类，用于拼装 ORDER BY 语句
 *
 * @Author : Jaylan Zhou
 *
 */

public enum OrderByCondition {

    NORMAL(""),DESC("DESC");

    private String result;

    private OrderByCondition(String result){

        this.result = result;

    }

    @Override
    public String toString(){

        return this.result;

    }

}
