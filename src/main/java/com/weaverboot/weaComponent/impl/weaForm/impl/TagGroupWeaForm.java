package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

public class TagGroupWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.TAGGROUP;

    public TagGroupWeaForm(String label,String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public TagGroupWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
