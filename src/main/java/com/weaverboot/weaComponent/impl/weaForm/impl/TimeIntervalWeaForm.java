package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

public class TimeIntervalWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.TIME_INTERVAL;

    public TimeIntervalWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public TimeIntervalWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
