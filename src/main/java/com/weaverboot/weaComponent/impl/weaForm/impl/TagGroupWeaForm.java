package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * 标签组 组件
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class TagGroupWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.TAGGROUP;

    public TagGroupWeaForm(String label,String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public TagGroupWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
