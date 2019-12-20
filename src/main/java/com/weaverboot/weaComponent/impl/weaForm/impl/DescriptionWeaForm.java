package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

public class DescriptionWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.DESCRIPTION;

    public DescriptionWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public DescriptionWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
