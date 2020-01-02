package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * 日期格式 不带区间
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class DatePickerWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.DATEPICKER;

    private String format;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public DatePickerWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public DatePickerWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

}
