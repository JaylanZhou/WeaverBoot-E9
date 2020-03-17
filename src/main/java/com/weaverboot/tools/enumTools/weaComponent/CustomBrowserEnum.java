package com.weaverboot.tools.enumTools.weaComponent;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020/3/17 4:29 下午
 * @Version : 1.0
 */
public enum CustomBrowserEnum {

    RADIO("161"),
    MULTIPLE("162"),
    TREERADIO("256"),
    TREEMULTIPLE("257");

    private String value;

    CustomBrowserEnum(String value){

        this.value = value;

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
