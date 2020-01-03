package com.weaverboot.frame.ioc.filter.util;

import com.alibaba.fastjson.JSONObject;
import com.weaverboot.weaResultMsg.impl.formResult.inte.AbstractWeaFormReciveComponentResultMsg;
import com.weaverboot.weaComponent.impl.weaTab.inte.AbstractWeaTab;
@Deprecated
public class ComponentDataUtils {

    private ComponentDataUtils(){}

//    public static  <T extends AbstractWeaForm>T getFormItem(AbstractWeaFormReciveComponentResultMsg abstractWeaFormReciveResultMsg, int groupIndex, int itemIndex, Class<T> tClass) {
//
//        JSONObject jsonObject = (JSONObject) abstractWeaFormReciveResultMsg.getCondition().get(groupIndex).getItems().get(itemIndex);
//
//        T abstractWeaForm = JSONObject.parseObject(jsonObject.toJSONString(),tClass);
//
//        return abstractWeaForm;
//
//    }
//
//    public static void setFormItem(AbstractWeaFormReciveComponentResultMsg abstractWeaFormReciveResultMsg, int groupIndex, int itemIndex, AbstractWeaForm abstractWeaForm){
//
//        abstractWeaFormReciveResultMsg.getCondition().get(groupIndex).getItems().set(itemIndex,abstractWeaForm);
//
//    }

//    public static <T extends AbstractWeaFatherTree>T getFatherTreeNode(AbstractWeaFormReciveComponentResultMsg abstractWeaFormReciveResultMsg, int groupIndex, Class<T> tClass){
//
//        JSONObject jsonObject = (JSONObject) abstractWeaFormReciveResultMsg.getWeaTree().get(groupIndex);
//
//        T abstractWeaFatherTree = JSONObject.parseObject(jsonObject.toJSONString(),tClass);
//
//        return abstractWeaFatherTree;
//
//    }
//
//    public static void setFatherTreeNode(AbstractWeaFormReciveComponentResultMsg abstractWeaFormReciveResultMsg, int groupIndex, AbstractWeaFatherTree abstractWeaFatherTree){
//
//        abstractWeaFormReciveResultMsg.getWeaTree().set(groupIndex,abstractWeaFatherTree);
//
//    }
//
//    public static  <T extends AbstractWeaChildTree>T getChildTreeNode(AbstractWeaFatherTree abstractWeaFatherTree, int groupIndex, Class<T> tClass) throws IllegalAccessException, InstantiationException {
//
//        JSONObject jsonObject = (JSONObject) abstractWeaFatherTree.getChilds().get(groupIndex);
//
//        T abstractWeaChildTree = JSONObject.parseObject(jsonObject.toJSONString(),tClass);
//
//        return abstractWeaChildTree;
//
//    }
//
//    public static void setChildTreeNode(AbstractWeaFatherTree abstractWeaFatherTree,int groupIndex,AbstractWeaChildTree abstractWeaChildTree){
//
//        abstractWeaFatherTree.getChilds().set(groupIndex,abstractWeaChildTree);
//
//    }
//
//    public static <T extends AbstractWeaTab>T getWeaTab(AbstractWeaFormReciveComponentResultMsg abstractWeaFormReciveResultMsg, int itemIndex, Class<T> tClass){
//
//        JSONObject jsonObject = (JSONObject) abstractWeaFormReciveResultMsg.getWeaTab().get(itemIndex);
//
//        T abstractWeaTab = JSONObject.parseObject(jsonObject.toJSONString(),tClass);
//
//        return abstractWeaTab;
//
//    }
//
//    public static void setWeaTab(AbstractWeaFormReciveComponentResultMsg abstractWeaFormReciveResultMsg, int itemIndex, AbstractWeaTab abstractWeaTab){
//
//        abstractWeaFormReciveResultMsg.getWeaTab().set(itemIndex,abstractWeaTab);
//
//    }

}
