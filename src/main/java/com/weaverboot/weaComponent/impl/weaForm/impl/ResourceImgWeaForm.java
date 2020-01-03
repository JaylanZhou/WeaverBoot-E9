package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * 人力资源组件（暂未启用）
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class ResourceImgWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.RESOURCEIMG;

    public ResourceImgWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public ResourceImgWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
