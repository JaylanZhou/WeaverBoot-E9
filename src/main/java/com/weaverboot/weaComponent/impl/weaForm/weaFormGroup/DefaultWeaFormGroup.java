package com.weaverboot.weaComponent.impl.weaForm.weaFormGroup;

import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;
import com.weaverboot.weaComponent.impl.weaForm.weaFormGroup.inte.AbstractWeaFormGroup;

import java.util.ArrayList;
import java.util.List;

public class DefaultWeaFormGroup extends AbstractWeaFormGroup {

    private boolean defaultshow = true;

    private String title;

    private String titleTip;

    private List<? super com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm> items;

    public List<? super com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm> getItems() {
        return items;
    }

    public void setItems(List<? super com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm> items) {
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

    public DefaultWeaFormGroup addItem(AbstractWeaForm abstractWeaForm){

        if (items == null){

            this.items = new ArrayList<>();

        }

        this.items.add(abstractWeaForm);

        return this;

    }
}
