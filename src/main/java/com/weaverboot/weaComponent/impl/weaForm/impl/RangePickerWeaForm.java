package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

public class RangePickerWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.RANGEPICKER;

    private String startValue;

    private String endValue;

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
}
