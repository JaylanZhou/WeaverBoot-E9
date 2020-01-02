package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * 颜色选择器 组件
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class ColorPickerWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.COLORPICKER;

    public ColorPickerWeaForm(String label,String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public ColorPickerWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
