package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * check框 组件
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class CheckWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.CHECKBOX;

    public CheckWeaForm(String label,String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public CheckWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }
}
