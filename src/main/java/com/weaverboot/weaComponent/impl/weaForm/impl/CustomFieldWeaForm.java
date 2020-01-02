package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * 自定义组件(流程自定义查询相关) 组件
 * 
 * @Author : Jaylan Zhou
 * 
 * @Date : 2020-01-02
 *
 */


public class CustomFieldWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.CUSTOMFIELD;

    public CustomFieldWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public CustomFieldWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
