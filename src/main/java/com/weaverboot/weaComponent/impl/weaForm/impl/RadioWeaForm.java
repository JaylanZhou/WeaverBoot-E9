package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

public class RadioWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.RADIO;

    public RadioWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public RadioWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
