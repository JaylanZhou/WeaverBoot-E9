package com.weaverboot.weaComponent.impl.weaForm.weaFormGroup.inte;

import com.alibaba.fastjson.JSONObject;
import com.weaverboot.tools.enumTools.weaComponent.WeaFormGroupConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWeaFormGroup {

    private WeaFormGroupConditionType conditionType;

    private boolean defaultshow = true;

    private String title;

    private String titleTip;

    private List<? super AbstractWeaForm> items;

    private String idHide;

    public List<? super AbstractWeaForm> getItems() {
        return items;
    }

    public void setItems(List<? super AbstractWeaForm> items) {
        this.items = items;
    }

    public boolean isDefaultshow() {
        return defaultshow;
    }

    public void setDefaultshow(boolean defaultshow) {
        this.defaultshow = defaultshow;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleTip() {
        return titleTip;
    }

    public void setTitleTip(String titleTip) {
        this.titleTip = titleTip;
    }

    public AbstractWeaFormGroup addItem(AbstractWeaForm abstractWeaForm){

        if (items == null){

            this.items = new ArrayList<>();

        }

        this.items.add(abstractWeaForm);

        return this;

    }

    public String getIdHide() {
        return idHide;
    }

    public void setIdHide(String idHide) {
        this.idHide = idHide;
    }

    public <T extends AbstractWeaForm>T transFormItem(int itemIndex, Class<T> tClass) {

        AbstractWeaForm abstractWeaForm = (AbstractWeaForm) this.getItems().get(itemIndex);

        try {

            T resultForm = tClass.newInstance();

            resultForm.copyCommonAttr(abstractWeaForm);

            this.getItems().set(itemIndex,resultForm);

            return resultForm;

        } catch (Exception e) {

            throw new RuntimeException("创建" + tClass + "对象发生错误");

        }

    }

    public <T extends AbstractWeaForm>T readFormItem(int itemIndex, Class<T> tClass) {

        try {

            return (T) this.getItems().get(itemIndex);

        } catch (Exception e) {

            throw new RuntimeException("创建" + tClass + "对象发生错误");

        }

    }

    public void removeFormItem(int itemIndex) {

        this.getItems().remove(itemIndex);

    }

    public void addFormItem(int itemIndex,AbstractWeaForm abstractWeaForm){

        this.getItems().add(itemIndex,abstractWeaForm);

    }

    public void addFormItem(AbstractWeaForm abstractWeaForm){

        this.getItems().add(abstractWeaForm);

    }

    public WeaFormGroupConditionType getConditionType() {
        return conditionType;
    }

    public void setConditionType(WeaFormGroupConditionType conditionType) {
        this.conditionType = conditionType;
    }
}
