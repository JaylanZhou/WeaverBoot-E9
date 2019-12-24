package com.weaverboot.weaComponent.impl.weaTable.tablePopedom.inte;

import com.cloudstore.eccom.pc.table.WeaTablePopedom;

/**
 * @Author : Jaylan Zhou
 * @Date : 2019-12-24 10:16
 * @Version : 1.0
 */
public abstract class AbstractWeaTablePopedom {

    private boolean async;
    private String transmethod;
    private String otherpara;
    private String otherpara2;
    private String column;

    public AbstractWeaTablePopedom() {
    }

    public AbstractWeaTablePopedom(String transmethod, String otherpara, String column) {
        this.transmethod = transmethod;
        this.otherpara = otherpara;
        this.column = column;
    }

    public boolean isAsync() {
        return this.async;
    }

    public AbstractWeaTablePopedom setAsync(boolean async) {
        this.async = async;
        return this;
    }

    public String getTransmethod() {
        return this.transmethod;
    }

    public AbstractWeaTablePopedom setTransmethod(String transmethod) {
        this.transmethod = transmethod;
        return this;
    }

    public String getOtherpara() {
        return this.otherpara;
    }

    public AbstractWeaTablePopedom setOtherpara(String otherpara) {
        this.otherpara = otherpara;
        return this;
    }

    public String getOtherpara2() {
        return this.otherpara2;
    }

    public AbstractWeaTablePopedom setOtherpara2(String otherpara2) {
        this.otherpara2 = otherpara2;
        return this;
    }

    public String getColumn() {
        return this.column;
    }

    public AbstractWeaTablePopedom setColumn(String column) {
        this.column = column;
        return this;
    }

}
