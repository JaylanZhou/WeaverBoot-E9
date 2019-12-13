package com.weaverboot.frame.ioc.filter.util;

import com.alibaba.fastjson.JSONObject;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;
import com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaChildTree;
import com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaFatherTree;
import com.weaverboot.weaResultMsg.impl.formResult.inte.AbstractWeaFormReciveResultMsg;
import com.weaverboot.weaComponent.impl.weaTab.inte.AbstractWeaTab;

public class ComponentDataUtils {

    private ComponentDataUtils(){}

    public static  <T extends AbstractWeaForm>T getFormItem(AbstractWeaFormReciveResultMsg abstractWeaFormReciveResultMsg,int groupIndex,int itemIndex,Class<T> tClass) throws IllegalAccessException, InstantiationException {

        JSONObject jsonObject = (JSONObject) abstractWeaFormReciveResultMsg.getCondition().get(groupIndex).getItems().get(itemIndex);

        T abstractWeaForm = JSONObject.parseObject(jsonObject.toJSONString(),tClass);

        return abstractWeaForm;

    }

    public static void setFormItem(AbstractWeaFormReciveResultMsg abstractWeaFormReciveResultMsg,int groupIndex,int itemIndex,AbstractWeaForm abstractWeaForm){

        abstractWeaFormReciveResultMsg.getCondition().get(groupIndex).getItems().set(itemIndex,abstractWeaForm);

    }

    public static <T extends AbstractWeaFatherTree>T getFatherTreeNode(AbstractWeaFormReciveResultMsg abstractWeaFormReciveResultMsg, int groupIndex,Class<T> tClass){

        JSONObject jsonObject = (JSONObject) abstractWeaFormReciveResultMsg.getWeaTree().get(groupIndex);

        T abstractWeaFatherTree = JSONObject.parseObject(jsonObject.toJSONString(),tClass);

        return abstractWeaFatherTree;

    }

    public static void setFatherTreeNode(AbstractWeaFormReciveResultMsg abstractWeaFormReciveResultMsg,int groupIndex,AbstractWeaFatherTree abstractWeaFatherTree){

        abstractWeaFormReciveResultMsg.getWeaTree().set(groupIndex,abstractWeaFatherTree);

    }

    public static  <T extends AbstractWeaChildTree>T getChildTreeNode(AbstractWeaFatherTree abstractWeaFatherTree, int groupIndex, Class<T> tClass) throws IllegalAccessException, InstantiationException {

        JSONObject jsonObject = (JSONObject) abstractWeaFatherTree.getChilds().get(groupIndex);

        T abstractWeaChildTree = JSONObject.parseObject(jsonObject.toJSONString(),tClass);

        return abstractWeaChildTree;

    }

    public static void setChildTreeNode(AbstractWeaFatherTree abstractWeaFatherTree,int groupIndex,AbstractWeaChildTree abstractWeaChildTree){

        abstractWeaFatherTree.getChilds().set(groupIndex,abstractWeaChildTree);

    }

    public static <T extends com.weaverboot.weaComponent.impl.weaTab.inte.AbstractWeaTab>T getWeaTab(AbstractWeaFormReciveResultMsg abstractWeaFormReciveResultMsg, int itemIndex, Class<T> tClass){

        JSONObject jsonObject = (JSONObject) abstractWeaFormReciveResultMsg.getWeaTab().get(itemIndex);

        T abstractWeaTab = JSONObject.parseObject(jsonObject.toJSONString(),tClass);

        return abstractWeaTab;

    }

    public static void setWeaTab(AbstractWeaFormReciveResultMsg abstractWeaFormReciveResultMsg, int itemIndex, AbstractWeaTab abstractWeaTab){

        abstractWeaFormReciveResultMsg.getWeaTab().set(itemIndex,abstractWeaTab);

    }

}
