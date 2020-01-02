package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * 日期区间组件
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class DateIntervalWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.DATE_INTERVAL;

    public DateIntervalWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public DateIntervalWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
