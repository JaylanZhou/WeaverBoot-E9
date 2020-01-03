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


public class CustomWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.CUSTOM;

    private String customType; //自定义类型

    public CustomWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public CustomWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    @Override
    public String getCustomType() {
        return customType;
    }

    @Override
    public void setCustomType(String customType) {
        this.customType = customType;
    }
}
