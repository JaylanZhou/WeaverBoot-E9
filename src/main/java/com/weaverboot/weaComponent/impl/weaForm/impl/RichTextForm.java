package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * 富文本组件
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class RichTextForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.RICHTEXT;

    public RichTextForm(String label,String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public RichTextForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }


}
