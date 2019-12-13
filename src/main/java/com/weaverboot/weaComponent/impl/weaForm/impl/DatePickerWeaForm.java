package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

public class DatePickerWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.DATEPICKER;

    public DatePickerWeaForm(String label,String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public DatePickerWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
