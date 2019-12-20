package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

public class DateIntervalWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.DATE_INTERVAL;

    public DateIntervalWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public DateIntervalWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
