package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;
import com.weaverboot.weaComponent.impl.weaForm.selectOptions.DefaultSelectOption;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectLinkageWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.SELECT_LINKAGE;

    /**
     * 选择框联动数据
     */
    private Map<String, AbstractWeaForm> selectLinkageDatas;
    /**
     * 选择框联动时定义select所占宽度 支持百分比、固定宽度
     */
    private String selectWidth;

    private List<DefaultSelectOption> options;

    /**
     * select组件detailtype属性 1:正常显示，2:checkbox多选，3:radio单选
     */
    private int detailtype = 1;

    private boolean supportCancel;

    private boolean multiple; //支持select多选

    public SelectLinkageWeaForm(String label, String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public SelectLinkageWeaForm(){

        super();

    }

    public SelectLinkageWeaForm putLinkageDatas(String key,AbstractWeaForm abstractWeaForm){

        if (this.selectLinkageDatas == null){

            this.selectLinkageDatas = new HashMap<>();

        }

        this.selectLinkageDatas.put(key,abstractWeaForm);

        return this;

    }

    public DefaultSelectOption addOption(String showname, String key){

        if (options == null){

            options = new ArrayList<>();

        }

        DefaultSelectOption defaultSelectOption = new DefaultSelectOption(showname,key);

        options.add(defaultSelectOption);

        return defaultSelectOption;

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    public Map<String, AbstractWeaForm> getSelectLinkageDatas() {
        return selectLinkageDatas;
    }

    public void setSelectLinkageDatas(Map<String, AbstractWeaForm> selectLinkageDatas) {
        this.selectLinkageDatas = selectLinkageDatas;
    }

    public String getSelectWidth() {
        return selectWidth;
    }

    public void setSelectWidth(String selectWidth) {
        this.selectWidth = selectWidth;
    }

    public List<DefaultSelectOption> getOptions() {
        return options;
    }

    public void setOptions(List<DefaultSelectOption> options) {
        this.options = options;
    }

    public int getDetailtype() {
        return detailtype;
    }

    public void setDetailtype(int detailtype) {
        this.detailtype = detailtype;
    }

    public boolean isSupportCancel() {
        return supportCancel;
    }

    public void setSupportCancel(boolean supportCancel) {
        this.supportCancel = supportCancel;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }
}
