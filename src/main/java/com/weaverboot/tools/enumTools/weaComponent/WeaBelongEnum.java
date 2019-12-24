package com.weaverboot.tools.enumTools.weaComponent;

/**
 * @Author : Jaylan Zhou
 * @Date : 2019-12-24 10:41
 * @Version : 1.0
 */
public enum WeaBelongEnum {

    PC("0"),
    MOBILE("1"),
    PCMOBILE("2");

    private String stringVal;

    private WeaBelongEnum(String stringVal) {
        this.stringVal = stringVal;
    }

    public String getStringVal() {
        return this.stringVal;
    }

    public void setStringVal(String stringVal) {
        this.stringVal = stringVal;
    }

}
