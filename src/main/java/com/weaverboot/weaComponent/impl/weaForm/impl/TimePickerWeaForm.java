package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * 时间格式组件 不带区间
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class TimePickerWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.TIMEPICKER;

    public TimePickerWeaForm(String label,String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public TimePickerWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
