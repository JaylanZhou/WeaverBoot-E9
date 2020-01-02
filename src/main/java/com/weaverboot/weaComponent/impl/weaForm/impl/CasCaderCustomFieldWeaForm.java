package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * CASCADER的子组件
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


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
