package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * 多行文本域组件
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class TextAreaWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.TEXTAREA;

    /**
     * @description ：多行文本框行数控制
     */

    private int minRows;

    private int maxRows;

    private String regExp;

    private int length;

    private int stringLength;

    private boolean disabled;

    private boolean textDecoration;

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

    public int getMinRows() {
        return minRows;
    }

    public void setMinRows(int minRows) {
        this.minRows = minRows;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getStringLength() {
        return stringLength;
    }

    public void setStringLength(int stringLength) {
        this.stringLength = stringLength;
    }

    public boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean getTextDecoration() {
        return textDecoration;
    }

    public void setTextDecoration(boolean textDecoration) {
        this.textDecoration = textDecoration;
    }

    public String getRegExp() {
        return regExp;
    }

    public void setRegExp(String regExp) {
        this.regExp = regExp;
    }
}
