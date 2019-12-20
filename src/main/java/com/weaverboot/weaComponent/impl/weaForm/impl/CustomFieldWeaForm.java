package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

public class CustomFieldWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.CUSTOMFIELD;

    public CustomFieldWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public CustomFieldWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
