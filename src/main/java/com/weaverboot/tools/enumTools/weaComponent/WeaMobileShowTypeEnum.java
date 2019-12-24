package com.weaverboot.tools.enumTools.weaComponent;

public enum WeaMobileShowTypeEnum {

    ListView("listview", 0),

    Table("table", 1);

    private String stringVal;

    private int intVal;

    private WeaMobileShowTypeEnum(String stringVal, int intVal) {

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
