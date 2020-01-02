package com.weaverboot.weaComponent.impl.weaForm.weaFormGroup.inte;

import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.enumTools.weaComponent.WeaFormGroupConditionType;
import com.weaverboot.tools.logTools.LogTools;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Form组件分组 - 基类
 * 
 * @Author : Jaylan Zhou
 * 
 * @Date : 2020-01-02
 *
 */


public abstract class AbstractWeaFormGroup {

    private WeaFormGroupConditionType conditionType; //区分分组实现类的枚举类

    private boolean defaultshow = true; //是否显示，默认为true

    private String title; //分组的默认名称

    private String titleTip;

    private List<? super AbstractWeaForm> items; //此分组中包含的form组件列表

    private String idHide; //是否隐藏

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

    /**
     *
     * 根据索引获取组件，索引从0开始
     * 此方法会建立一个Class<T> 指定的form组件，并将原有组件的公有属性拷贝到新建的组件中
     *
     * @param itemIndex 组件所在的位置，从0开始计算
     * @param tClass 想要转换的组件的Class，此class必须为AbstractWeaForm的子类，即必须为一个组件
     * @param <T> 转换组件的类名，由Class指定
     * @return 转换的组件
     */

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

    /**
     *
     * 根据标签名获取组件
     * 此方法会建立一个Class<T> 指定的form组件，并将原有组件的公有属性拷贝到新建的组件中
     *
     * 注意：若方法中含有多个标签名相同的组件，此方法只会返回排序在先的第一个组件
     *
     * @param labelName 想要获取的组件的标签名
     * @param tClass 想要转换的组件的Class，此class必须为AbstractWeaForm的子类，即必须为一个组件
     * @param <T> 转换组件的类名，由Class指定
     * @return 转换后的组件
     */

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

    /**
     *
     * 根据标签名获取组件 （列表）
     * 此方法会建立多个Class<T> 指定的form组件，并将原有组件的公有属性拷贝到新建的组件中
     *
     * 注意：若方法中含有多个标签名相同的组件，此方法会返回这些组件的List
     *
     * @param labelName 想要获取的组件的标签名
     * @param tClass 想要转换的组件的Class，此class必须为AbstractWeaForm的子类，即必须为一个组件
     * @param <T> 转换组件的类名，由Class指定
     * @return 转换后的组件List
     */

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

    /**
     *
     * 根据索引移除组件
     *
     * @param itemIndex 想要移除的组件位置索引，从0开始计算
     *
     */

    public void removeFormItem(int itemIndex) {

        this.getItems().remove(itemIndex);

    }

    /**
     *
     * 根据标签名移除组件
     *
     * 注意：若有多个标签名相同的组件，此方法只会移除排序在先的第一个组件
     *
     * @param labelName 想要移除的组件标签名
     *
     */

    public void removeFormItemWithLabel(String labelName) {

        for (int i = 0; i < items.size(); i++) {

            AbstractWeaForm abstractWeaForm = (AbstractWeaForm) items.get(i);

            if (BaseTools.notNullString(abstractWeaForm.getLabel()) && abstractWeaForm.getLabel().equals(labelName)) {

                this.getItems().remove(i);

                break;

            }

        }

    }

    /**
     *
     * 根据标签名移除组件（列表）
     *
     * 注意：若分组中含有多个标签名相同的组件，此方法会移除此标签名的所有组件
     *
     * @param labelName 想要移除的组件标签名
     *
     */

    public void removeFormItemWithLabelAll(String labelName) {

        for (int i = 0; i < items.size(); i++) {

            AbstractWeaForm abstractWeaForm = (AbstractWeaForm) items.get(i);

            if (BaseTools.notNullString(abstractWeaForm.getLabel()) && abstractWeaForm.getLabel().equals(labelName)) {

                this.getItems().remove(i);

            }

        }

    }

    /**
     *
     * 在索引指定位置添加一个组件
     *
     * @param itemIndex 添加的位置索引，从0开始计算
     * @param abstractWeaForm 添加的组件
     *
     */

    public void addFormItem(int itemIndex, AbstractWeaForm abstractWeaForm) {

        this.getItems().add(itemIndex, abstractWeaForm);

    }

    /**
     *
     * 在分组末尾添加一个组件
     *
     * @param abstractWeaForm 添加的组件
     *
     */

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
