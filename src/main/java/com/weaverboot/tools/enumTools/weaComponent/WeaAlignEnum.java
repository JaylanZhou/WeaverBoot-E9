package com.weaverboot.tools.enumTools.weaComponent;

public enum WeaAlignEnum {

    CENTER("center", 0),
    LEFT("left", 1),
    RIGHT("right", 2);

    private String stringVal;
    private int intVal;

    private WeaAlignEnum(String stringVal, int intVal) {
        this.stringVal = stringVal;
        this.setIntVal(intVal);
    }

    public String getStringVal() {
        return this.stringVal;
    }

    public void setStringVal(String stringVal) {
        this.stringVal = stringVal;
    }

    public String toString() {
        return this.stringVal.toString();
    }

    public int getIntVal() {
        return this.intVal;
    }

    public void setIntVal(int intVal) {
        this.intVal = intVal;
    }

}
