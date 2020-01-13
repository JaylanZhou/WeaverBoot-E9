package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;
import com.weaverboot.weaComponent.impl.weaForm.selectOptions.WeaDateOptions;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 日期组合 组件
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class DateGroupWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.DATE;

    private List<WeaDateOptions> datas;

    private boolean textDecoration;

    public DateGroupWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public DateGroupWeaForm(){

        super();

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    /**
     *
     * 为日期组合类添加选项
     *
     * @param name 选项名称
     * @param value 选项值
     * @return 返回本类，方便链式调用
     *
     */

    public DateGroupWeaForm addOption(String name, String value){

        if (this.datas == null){

            this.datas = new ArrayList<>();

        }

        datas.add(new WeaDateOptions(name,value));

        return this;

    }

    public List<WeaDateOptions> getDatas() {
        return datas;
    }

    public void setDatas(List<WeaDateOptions> datas) {
        this.datas = datas;
    }

    public boolean getTextDecoration() {
        return textDecoration;
    }

    public void setTextDecoration(boolean textDecoration) {
        this.textDecoration = textDecoration;
    }
}
