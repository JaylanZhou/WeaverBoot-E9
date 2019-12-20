package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

public class ScopeWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.SCOPE;

    private String startValue;

    private String endValue;

    public ScopeWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public ScopeWeaForm(){

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
