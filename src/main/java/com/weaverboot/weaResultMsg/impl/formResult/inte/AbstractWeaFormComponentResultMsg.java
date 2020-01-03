package com.weaverboot.weaResultMsg.impl.formResult.inte;

import com.alibaba.fastjson.JSONObject;
import com.weaverboot.weaComponent.impl.weaForm.weaFormGroup.inte.AbstractWeaFormGroup;
import com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaTree;
import com.weaverboot.weaResultMsg.inte.AbstractWeaComponentResultMsg;
import com.weaverboot.weaComponent.impl.weaTab.inte.AbstractWeaTab;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Form 消息体类 - 公共基类
 *
 * 此类为接收消息体与发送消息体的公共基类
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */

public abstract class AbstractWeaFormComponentResultMsg extends AbstractWeaComponentResultMsg {

    private List<? super AbstractWeaFormGroup> condition; //分组列表List

    private List<? super AbstractWeaTree> weaTree; //父级树列表List

    private List<? super AbstractWeaTab> weaTab; //标签页列表List

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

    public AbstractWeaFormComponentResultMsg addTree(AbstractWeaTree AbstractWeaTree){

        if (weaTree == null){

            weaTree = new ArrayList<>();

        }

        weaTree.add(AbstractWeaTree);

        return this;

    }

    public AbstractWeaFormComponentResultMsg addTab(AbstractWeaTab abstractWeaTab){

        if (weaTab == null){

            weaTab = new ArrayList<>();

        }

        weaTab.add(abstractWeaTab);

        return this;

    }

    public List<? super AbstractWeaTree> getWeaTree() {
        return weaTree;
    }

    public void setWeaTree(List<? super AbstractWeaTree> weaTree) {
        this.weaTree = weaTree;
    }

    public List<? super AbstractWeaTab> getWeaTab() {
        return weaTab;
    }

    public void setWeaTab(List<? super AbstractWeaTab> weaTab) {
        this.weaTab = weaTab;
    }

    /**
     *
     * Form 消息体的共有方法 - 序列化
     *
     * 此方法指定将消息体类通过 fastjson 序列化成json报文
     *
     * @return 消息体类序列化后的json报文
     */

    @Override
    public String resultToSerialization() {
        return JSONObject.toJSONString(this);
    }
}
