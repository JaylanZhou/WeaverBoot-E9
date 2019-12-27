package com.weaverboot.weaResultMsg.impl.formResult.inte;

import com.alibaba.fastjson.JSONObject;
import com.weaverboot.weaComponent.impl.weaForm.weaFormGroup.DefaultWeaFormGroup;
import com.weaverboot.weaComponent.impl.weaForm.weaFormGroup.inte.AbstractWeaFormGroup;
import com.weaverboot.weaResultMsg.inte.AbstractWeaComponentResultMsg;
import com.weaverboot.weaComponent.impl.weaTab.inte.AbstractWeaTab;
import com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaFatherTree;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWeaFormComponentResultMsg extends AbstractWeaComponentResultMsg {

    private List<? super AbstractWeaFormGroup> condition;

    private List<? super AbstractWeaFatherTree> weaTree;

    private List<? super AbstractWeaTab> weaTab;

    public List<? super AbstractWeaFormGroup> getCondition() {
        return condition;
    }

    public void setCondition(List<? super AbstractWeaFormGroup> condition) {
        this.condition = condition;
    }

    public AbstractWeaFormComponentResultMsg addItem(AbstractWeaFormGroup abstractWeaFormGroup){

        if (condition == null){

            condition = new ArrayList<>();

        }

        condition.add(abstractWeaFormGroup);

        return this;

    }

    public AbstractWeaFormComponentResultMsg addTree(AbstractWeaFatherTree abstractWeaFatherTree){

        if (weaTree == null){

            weaTree = new ArrayList<>();

        }

        weaTree.add(abstractWeaFatherTree);

        return this;

    }

    public AbstractWeaFormComponentResultMsg addTab(AbstractWeaTab abstractWeaTab){

        if (weaTab == null){

            weaTab = new ArrayList<>();

        }

        weaTab.add(abstractWeaTab);

        return this;

    }

    public List<? super AbstractWeaFatherTree> getWeaTree() {
        return weaTree;
    }

    public void setWeaTree(List<? super AbstractWeaFatherTree> weaTree) {
        this.weaTree = weaTree;
    }

    public List<? super AbstractWeaTab> getWeaTab() {
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
