package com.weaverboot.weaResultMsg.impl.formResult.inte;

import com.alibaba.fastjson.JSONObject;
import com.weaverboot.weaComponent.impl.weaForm.weaFormGroup.DefaultWeaFormGroup;
import com.weaverboot.weaResultMsg.inte.AbstractWeaComponentResultMsg;
import com.weaverboot.weaComponent.impl.weaTab.inte.AbstractWeaTab;
import com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaFatherTree;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWeaFormComponentResultMsg extends AbstractWeaComponentResultMsg {

    private List<DefaultWeaFormGroup> condition;

    private List<? super com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaFatherTree> weaTree;

    private List<? super com.weaverboot.weaComponent.impl.weaTab.inte.AbstractWeaTab> weaTab;

    public List<DefaultWeaFormGroup> getCondition() {
        return condition;
    }

    public void setCondition(List<DefaultWeaFormGroup> condition) {
        this.condition = condition;
    }

    public AbstractWeaFormComponentResultMsg addItem(DefaultWeaFormGroup defaultWeaFormGroup){

        if (condition == null){

            condition = new ArrayList<>();

        }

        condition.add(defaultWeaFormGroup);

        return this;

    }

    public AbstractWeaFormComponentResultMsg addTree(com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaFatherTree abstractWeaFatherTree){

        if (weaTree == null){

            weaTree = new ArrayList<>();

        }

        weaTree.add(abstractWeaFatherTree);

        return this;

    }

    public AbstractWeaFormComponentResultMsg addTab(com.weaverboot.weaComponent.impl.weaTab.inte.AbstractWeaTab abstractWeaTab){

        if (weaTab == null){

            weaTab = new ArrayList<>();

        }

        weaTab.add(abstractWeaTab);

        return this;

    }

    public List<? super com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaFatherTree> getWeaTree() {
        return weaTree;
    }

    public void setWeaTree(List<? super AbstractWeaFatherTree> weaTree) {
        this.weaTree = weaTree;
    }

    public List<? super com.weaverboot.weaComponent.impl.weaTab.inte.AbstractWeaTab> getWeaTab() {
        return weaTab;
    }

    public void setWeaTab(List<? super AbstractWeaTab> weaTab) {
        this.weaTab = weaTab;
    }

    @Override
    public String resultToSerialization() {
        return JSONObject.toJSONString(this);
    }
}
