package com.weaverboot.weaComponent.impl.weaForm.weaFormGroup.inte;

import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.enumTools.weaComponent.WeaFormGroupConditionType;
import com.weaverboot.tools.logTools.LogTools;
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

    public AbstractWeaFormGroup addItem(AbstractWeaForm abstractWeaForm) {

        if (items == null) {

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

    public <T extends AbstractWeaForm> T readFormItem(int itemIndex, Class<T> tClass) {

        AbstractWeaForm abstractWeaForm = (AbstractWeaForm) this.getItems().get(itemIndex);

        try {

            T resultForm = tClass.newInstance();

            resultForm.copyCommonAttr(abstractWeaForm);

            this.getItems().set(itemIndex, resultForm);

            return resultForm;

        } catch (Exception e) {

            throw new RuntimeException("创建" + tClass + "对象发生错误");

        }

    }

    public <T extends AbstractWeaForm> T readFormItemWithLabel(String labelName, Class<T> tClass) {

        for (int i = 0; i < items.size(); i++) {

            AbstractWeaForm abstractWeaForm = (AbstractWeaForm) items.get(i);

            try {

                    if (BaseTools.notNullString(labelName) && abstractWeaForm.getLabel().equals(labelName)) {

                    T resultForm = tClass.newInstance();

                    resultForm.copyCommonAttr(abstractWeaForm);

                    this.getItems().set(i,resultForm);

                    return resultForm;

                }

            } catch (Exception e) {

                throw new RuntimeException("创建" + tClass + "对象发生错误");

            }

        }

        LogTools.writeLog("未找到label为" + labelName + "的form组件");

        return null;

    }

    public <T extends AbstractWeaForm> List<T> readFormItemWithLabelList(String labelName, Class<T> tClass) {

        List<T> tList = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {

            AbstractWeaForm abstractWeaForm = (AbstractWeaForm) items.get(i);

            try {

                    if (BaseTools.notNullString(labelName) && abstractWeaForm.getLabel().equals(labelName)) {

                    T resultForm = tClass.newInstance();

                    resultForm.copyCommonAttr(abstractWeaForm);

                    tList.add(resultForm);

                    this.getItems().set(i,resultForm);

                }

            } catch (Exception e) {

                throw new RuntimeException("创建" + tClass + "对象发生错误");

            }

        }

        if (BaseTools.notNullList(tList)){

            return tList;

        }

        LogTools.writeLog("未找到label为" + labelName + "的form组件");

        return null;

    }


    public void removeFormItem(int itemIndex) {

        this.getItems().remove(itemIndex);

    }

    public void removeFormItemWithLabel(String labelName) {

        for (int i = 0; i < items.size(); i++) {

            AbstractWeaForm abstractWeaForm = (AbstractWeaForm) items.get(i);

            if (BaseTools.notNullString(abstractWeaForm.getLabel()) && abstractWeaForm.getLabel().equals(labelName)) {

                this.getItems().remove(i);

                break;

            }

        }

    }

    public void removeFormItemWithLabelAll(String labelName) {

        for (int i = 0; i < items.size(); i++) {

            AbstractWeaForm abstractWeaForm = (AbstractWeaForm) items.get(i);

            if (BaseTools.notNullString(abstractWeaForm.getLabel()) && abstractWeaForm.getLabel().equals(labelName)) {

                this.getItems().remove(i);

            }

        }

    }

    public void addFormItem(int itemIndex, AbstractWeaForm abstractWeaForm) {

        this.getItems().add(itemIndex, abstractWeaForm);

    }

    public void addFormItem(AbstractWeaForm abstractWeaForm) {

        this.getItems().add(abstractWeaForm);

    }

    public WeaFormGroupConditionType getConditionType() {
        return conditionType;
    }

    public void setConditionType(WeaFormGroupConditionType conditionType) {
        this.conditionType = conditionType;
    }
}
