package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * 时间区间组件
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class TimeIntervalWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.TIME_INTERVAL;

    public TimeIntervalWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public TimeIntervalWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
