package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

public class ResourceImgWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.RESOURCEIMG;

    public ResourceImgWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public ResourceImgWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
