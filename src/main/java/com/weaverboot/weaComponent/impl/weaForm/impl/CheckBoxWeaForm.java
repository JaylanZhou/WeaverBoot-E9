package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * check框 组件
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class CheckBoxWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.CHECKBOX;

    private String content;

    private boolean checkbox; //字段是否可以复选

    private boolean checkboxValue; //字段复选的值

    public CheckBoxWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public CheckBoxWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getCheckbox() {
        return checkbox;
    }

    @Override
    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public boolean getCheckboxValue() {
        return checkboxValue;
    }

    @Override
    public void setCheckboxValue(boolean checkboxValue) {
        this.checkboxValue = checkboxValue;
    }
}
