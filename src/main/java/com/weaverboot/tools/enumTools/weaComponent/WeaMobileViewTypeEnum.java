package com.weaverboot.tools.enumTools.weaComponent;

/**
 * @Author : Jaylan Zhou
 * @Date : 2019-12-24 10:38
 * @Version : 1.0
 */
public enum WeaMobileViewTypeEnum {

    HIGHLIGHT("1"),

    DETAIL("2");

    private String stringVal;

    private WeaMobileViewTypeEnum(String stringVal) {
        this.stringVal = stringVal;
    }

    public String getStringVal() {
        return this.stringVal;
    }

    public void setStringVal(String stringVal) {
        this.stringVal = stringVal;
    }

}
