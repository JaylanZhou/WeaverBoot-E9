package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * 密码组件
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class PasswordWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.PASSWORD;

    public PasswordWeaForm(String label,String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public PasswordWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
