package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

public class DateWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.DATE;

    public DateWeaForm(String label,String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public DateWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
