package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * 文本输入框 组件
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class InputWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.INPUT;

    public InputWeaForm(String label,String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public InputWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }


}
