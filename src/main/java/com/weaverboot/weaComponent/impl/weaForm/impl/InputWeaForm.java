package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

public class InputWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.INPUT;

    public InputWeaForm(String label,String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public InputWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }


}
