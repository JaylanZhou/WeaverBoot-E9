package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

/**
 *
 * 文本输入框 组件
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class InputWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.INPUT;

    private String regExp; //过滤数据的正则表达式

    private boolean textDecoration; //是否有下划线

    private String tip; //文字提示

    public InputWeaForm(String label,String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public InputWeaForm(){

        super();

    }

    public boolean getTextDecoration() {
        return textDecoration;
    }

    public void setTextDecoration(boolean textDecoration) {
        this.textDecoration = textDecoration;
    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    public String getRegExp() {
        return regExp;
    }

    public void setRegExp(String regExp) {
        this.regExp = regExp;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

}
