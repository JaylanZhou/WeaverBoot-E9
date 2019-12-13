package com.weaverboot.tools.enumTools.frame;

/**
 *
 * AND OR 枚举类，用于拼装 AND 或者 OR 的条件
 *
 * @Author : Jaylan Zhou
 *
 */
public enum SelectCondition {

    AND("AND"),OR("OR"),NULL(null);

    private String result;

    private SelectCondition(String result){

        this.result = result;

    }

    @Override
    public String toString(){

        return this.result;

    }


}
