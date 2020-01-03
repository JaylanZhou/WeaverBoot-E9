package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * 日期格式 不带区间
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class DatePickerWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.DATEPICKER;

    private String format;

    private boolean textDecoration;

    private String formatPattern;

    private boolean noInput;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean getTextDecoration() {
        return textDecoration;
    }

    public void setTextDecoration(boolean textDecoration) {
        this.textDecoration = textDecoration;
    }

    public String getFormatPattern() {
        return formatPattern;
    }

    public void setFormatPattern(String formatPattern) {
        this.formatPattern = formatPattern;
    }

    public DatePickerWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public DatePickerWeaForm(){

        super();

    }

    public boolean getNoInput() {
        return noInput;
    }

    public void setNoInput(boolean noInput) {
        this.noInput = noInput;
    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
