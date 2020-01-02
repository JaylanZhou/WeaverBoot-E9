package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * 单个数字输入框
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class InputNumberWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.INPUTNUMBER;

    public InputNumberWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public InputNumberWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }


}
