package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * 时间格式组件 不带区间
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class TimePickerWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.TIMEPICKER;

    private String format;

    private int formatPattern;

    private boolean textDecoration;

    private boolean noInput;

    public TimePickerWeaForm(String label,String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public TimePickerWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getFormatPattern() {
        return formatPattern;
    }

    public void setFormatPattern(int formatPattern) {
        this.formatPattern = formatPattern;
    }

    public boolean getTextDecoration() {
        return textDecoration;
    }

    public void setTextDecoration(boolean textDecoration) {
        this.textDecoration = textDecoration;
    }

    public boolean getNoInput() {
        return noInput;
    }

    public void setNoInput(boolean noInput) {
        this.noInput = noInput;
    }
}
