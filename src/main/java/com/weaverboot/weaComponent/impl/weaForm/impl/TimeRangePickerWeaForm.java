package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * 暂未启用
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class TimeRangePickerWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.TIMERANGEPICKER;

    private String startValue;

    private String endValue;

    private String format;

    private int formatPattern;

    private boolean textDecoration;

    private boolean noInput;

    public TimeRangePickerWeaForm(String label,String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public TimeRangePickerWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    public String getStartValue() {
        return startValue;
    }

    public void setStartValue(String startValue) {
        this.startValue = startValue;
    }

    public String getEndValue() {
        return endValue;
    }

    public void setEndValue(String endValue) {
        this.endValue = endValue;
    }

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

    public int getFormatPattern() {
        return formatPattern;
    }

    public void setFormatPattern(int formatPattern) {
        this.formatPattern = formatPattern;
    }

    public boolean getNoInput() {
        return noInput;
    }

    public void setNoInput(boolean noInput) {
        this.noInput = noInput;
    }

}
