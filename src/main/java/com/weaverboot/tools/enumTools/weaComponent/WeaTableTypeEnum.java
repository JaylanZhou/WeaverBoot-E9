package com.weaverboot.tools.enumTools.weaComponent;

/**
 * @Author : Jaylan Zhou
 * @Date : 2019-12-24 10:21
 * @Version : 1.0
 */
public enum WeaTableTypeEnum {

    CHECKBOX("checkbox", 1),

    RADIO("radio", 2),

    NONE("none", 3),

    THUMBNAIL("thumbnail", 4),

    OFFICALDOC("officalDoc", 5);


    private String name;

    private int code;

    private WeaTableTypeEnum(String name, int code) {

        this.setName(name);

        this.setCode(code);

    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
