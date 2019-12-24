package com.weaverboot.tools.enumTools.weaComponent;

public enum WeaBooleanEnum {

    TRUE("1", true,"true"),

    FALSE("0", false,"false");

    private String stringVal;

    private String booleanStringVal;

    private Boolean boolVal;

    private WeaBooleanEnum(String stringVal, Boolean boolVal,String booleanStringVal) {

        this.stringVal = stringVal;

        this.boolVal = boolVal;

        this.booleanStringVal = booleanStringVal;

    }

    public String getStringVal() {
        return this.stringVal;
    }

    public void setStringVal(String stringVal) {
        this.stringVal = stringVal;
    }

    public Boolean getBoolVal() {
        return this.boolVal;
    }

    public void setBoolVal(Boolean boolVal) {
        this.boolVal = boolVal;
    }

    public String getBooleanStringVal() {
        return booleanStringVal;
    }

    public void setBooleanStringVal(String booleanStringVal) {
        this.booleanStringVal = booleanStringVal;
    }

    public String toString() {
        return this.boolVal.toString();
    }

}
