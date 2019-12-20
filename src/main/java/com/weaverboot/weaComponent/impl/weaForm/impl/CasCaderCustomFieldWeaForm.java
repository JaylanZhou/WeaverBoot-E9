package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

public class CasCaderCustomFieldWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.CASCADERCUSTOMFIELD;

    public CasCaderCustomFieldWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public CasCaderCustomFieldWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
