package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * 开关 组件
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class SwitchWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.SWITCH;

    public SwitchWeaForm(String label,String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public SwitchWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }
}
