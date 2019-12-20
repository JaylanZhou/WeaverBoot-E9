package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

public class TextWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.TEXT;

    /**
     * @description ：多行文本框行数控制
     */

    private String minRows;

    private String maxRows;

    public TextWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public TextWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }


    public String getMinRows() {
        return minRows;
    }

    public void setMinRows(String minRows) {
        this.minRows = minRows;
    }

    public String getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(String maxRows) {
        this.maxRows = maxRows;
    }

}
