package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

public class TextAreaWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.TEXTAREA;

    /**
     * @description ：多行文本框行数控制
     */

    private String minRows;

    private String maxRows;

    public TextAreaWeaForm(String label,String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public TextAreaWeaForm(){

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
