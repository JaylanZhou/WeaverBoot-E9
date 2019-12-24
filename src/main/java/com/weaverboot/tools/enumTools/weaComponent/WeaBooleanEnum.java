package com.weaverboot.tools.enumTools.weaComponent;

public enum WeaBooleanEnum {

    TRUE("1", true),

    FALSE("0", false);

    private String stringVal;

    private Boolean boolVal;

    private WeaBooleanEnum(String stringVal, Boolean boolVal) {

        this.stringVal = stringVal;

        this.boolVal = boolVal;

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

    public String toString() {
        return this.boolVal.toString();
    }

}
