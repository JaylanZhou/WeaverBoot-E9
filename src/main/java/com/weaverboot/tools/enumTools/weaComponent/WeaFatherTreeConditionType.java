package com.weaverboot.tools.enumTools.weaComponent;

/**
 * @Author : Jaylan Zhou
 * @Date : 2019-12-27 16:18
 * @Version : 1.0
 */
public enum WeaFatherTreeConditionType {

    DEFAULT("0");

    private String stringVal;

    private WeaFatherTreeConditionType(String stringVal) {
        this.stringVal = stringVal;
    }

    public String getStringVal() {
        return this.stringVal;
    }

    public void setStringVal(String stringVal) {
        this.stringVal = stringVal;
    }


    @Override
    public String toString() {
        return this.getStringVal();
    }
}
