package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

public class SwitchWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.SWITCH;

    public SwitchWeaForm(String label,String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public ConditionType getConditionType() {
        return conditionType;
    }
}
