package com.weaverboot.weaComponent.impl.weaTable.checkboxPopedom.inte;

import com.weaverboot.weaComponent.inte.AbstractWeaComponent;

/**
 * @Author : Jaylan Zhou
 * @Date : 2019-12-24 09:39
 * @Version : 1.0
 */
public abstract class AbstractWeaCheckboxPopedom extends AbstractWeaComponent {

    private String id;

    private String showmethod;

    private String popedompara;

    public String getId() {
        return this.id;
    }

    public AbstractWeaCheckboxPopedom setId(String id) {
        this.id = id;
        return this;
    }

    public String getShowmethod() {

        return this.showmethod;

    }

    public AbstractWeaCheckboxPopedom setShowmethod(String showmethod) {

        this.showmethod = showmethod;

        return this;

    }

    public String getPopedompara() {

        return this.popedompara;

    }

    public AbstractWeaCheckboxPopedom setPopedompara(String popedompara) {

        this.popedompara = popedompara;

        return this;

    }

}
