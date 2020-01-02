package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * 级联组件
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class CasCaderWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.CASCADER;

    public CasCaderWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public CasCaderWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
