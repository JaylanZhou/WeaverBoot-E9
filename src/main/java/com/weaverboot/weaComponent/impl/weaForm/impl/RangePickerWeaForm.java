package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * 日期区间选择组件
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class RangePickerWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.RANGEPICKER;

    private String startValue;

    private String endValue;

    private boolean textDecoration;

    private boolean dateGroup; //日期区间设置不限。

    public RangePickerWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public RangePickerWeaForm(){

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

    public boolean getTextDecoration() {
        return textDecoration;
    }

    public void setTextDecoration(boolean textDecoration) {
        this.textDecoration = textDecoration;
    }

    public boolean getDateGroup() {
        return dateGroup;
    }

    public void setDateGroup(boolean dateGroup) {
        this.dateGroup = dateGroup;
    }
}
