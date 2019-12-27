package com.weaverboot.tools.componentTools.form;
import com.alibaba.fastjson.JSONObject;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.weaComponent.impl.weaForm.impl.*;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;
import com.weaverboot.weaComponent.impl.weaForm.weaFormGroup.DefaultWeaFormGroup;
import com.weaverboot.weaComponent.impl.weaForm.weaFormGroup.inte.AbstractWeaFormGroup;
import com.weaverboot.weaComponent.impl.weaTab.impl.DefaultWeaTab;
import com.weaverboot.weaComponent.impl.weaTab.inte.AbstractWeaTab;
import com.weaverboot.weaComponent.impl.weaTree.impl.DefaultWeaChildTree;
import com.weaverboot.weaComponent.impl.weaTree.impl.DefaultWeaFatherTree;
import com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaChildTree;
import com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaFatherTree;
import com.weaverboot.weaResultMsg.impl.formResult.inte.AbstractWeaFormReciveComponentResultMsg;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Form 消息体反序列化类
 *
 * @Author : Jaylan Zhou
 * @Date : 2019-12-27 14:41
 * @Version : 1.0
 */
public class WeaFormParseTools {

    private WeaFormParseTools(){}

    public static <T extends AbstractWeaFormReciveComponentResultMsg> T parseWeaReciveComponentResultMsg(String data,Class<T> tClass){

        T t = JSONObject.parseObject(data,tClass);

        parseWeaFormGroup(t,t.getCondition());

        parseWeaFatherTree(t,t.getWeaTree());

        parseWeaTab(t,t.getWeaTab());

        return t;

    }

    /**
     *
     * 解析WeaFormGroup
     *
     * @param abstractWeaFormReciveComponentResultMsg
     * @param abstractWeaFormGroups
     */

    private static void parseWeaFormGroup(AbstractWeaFormReciveComponentResultMsg abstractWeaFormReciveComponentResultMsg,List<? super AbstractWeaFormGroup> abstractWeaFormGroups){


        List<AbstractWeaFormGroup> abstractWeaFormGroupList = new ArrayList<>();

        for (int i = 0; i < abstractWeaFormGroups.size(); i++) {

            JSONObject jsonObject = (JSONObject) abstractWeaFormGroups.get(i);

            AbstractWeaFormGroup abstractWeaFormGroup = JSONObject.parseObject(jsonObject.toJSONString(),getAbstractWeaFormGroupClass(jsonObject)); //解析基本内容,暂时使用DefaultGroup

            parseWeaForm(abstractWeaFormGroup); //解析weaform的条目

            abstractWeaFormGroupList.add(abstractWeaFormGroup);

        }

        abstractWeaFormReciveComponentResultMsg.setCondition(abstractWeaFormGroupList);

    }

    /**
     *
     * 解析Form组件
     *
     * @param abstractWeaFormGroup
     *
     */

    private static void parseWeaForm(AbstractWeaFormGroup abstractWeaFormGroup){

        List<? super AbstractWeaForm> abstractWeaFormJsonList =  abstractWeaFormGroup.getItems();

        if (BaseTools.notNullList(abstractWeaFormJsonList)) {

            List<AbstractWeaForm> abstractWeaFormList = new ArrayList<>();


            for (int i = 0; i < abstractWeaFormJsonList.size(); i++) {

                abstractWeaFormList.add(getAbstractWeaForm((JSONObject) abstractWeaFormJsonList.get(i))); //判断每一条的类型并生成

            }

            abstractWeaFormGroup.setItems(abstractWeaFormList);

        }

    }

    /**
     *
     * 解析WeaFatherTree
     *
     * @param abstractWeaFormReciveComponentResultMsg
     * @param weaFatherTreeJson
     */

    private static void parseWeaFatherTree(AbstractWeaFormReciveComponentResultMsg abstractWeaFormReciveComponentResultMsg,List<? super AbstractWeaFatherTree> weaFatherTreeJson){

        List<AbstractWeaFatherTree> abstractWeaFatherTreeList = new ArrayList<>();

        if (BaseTools.notNullList(weaFatherTreeJson)){

        for (int i = 0; i < weaFatherTreeJson.size(); i++) {

            JSONObject jsonObject = (JSONObject) weaFatherTreeJson.get(i);

            AbstractWeaFatherTree abstractWeaFatherTree = JSONObject.parseObject(jsonObject.toJSONString(), getAbstractWeaFatherTreeClass(jsonObject));

            List<? super AbstractWeaChildTree> childTreeJsonArray = abstractWeaFatherTree.getChilds();

                if (BaseTools.notNullList(childTreeJsonArray)) {

                    List<AbstractWeaChildTree> abstractWeaChildTreeList = new ArrayList<>();

                    for (int j = 0; j < childTreeJsonArray.size(); j++) {

                        abstractWeaChildTreeList.add(getAbstractWeaChildTree((JSONObject) childTreeJsonArray.get(j)));

                    }

                    abstractWeaFatherTree.setChilds(abstractWeaChildTreeList);

                }

                abstractWeaFatherTreeList.add(abstractWeaFatherTree);

            }


        }

        abstractWeaFormReciveComponentResultMsg.setWeaTree(abstractWeaFatherTreeList);

    }

    /**
     *
     * 解析WeaTab
     *
     * @param abstractWeaFormReciveComponentResultMsg
     * @param weaTabJson
     * @return
     */

    private static void parseWeaTab(AbstractWeaFormReciveComponentResultMsg abstractWeaFormReciveComponentResultMsg,List<? super AbstractWeaTab> weaTabJson){

        if (BaseTools.notNullList(weaTabJson)) {

            List<AbstractWeaTab> abstractWeaTabList = new ArrayList<>();

            for (int i = 0; i < weaTabJson.size(); i++) {

                JSONObject jsonObject = (JSONObject) weaTabJson.get(i);

                AbstractWeaTab abstractWeaTab = JSONObject.parseObject(jsonObject.toJSONString(), getAbstractWeaTabClass(jsonObject));

                abstractWeaTabList.add(abstractWeaTab);

            }

            abstractWeaFormReciveComponentResultMsg.setWeaTab(abstractWeaTabList);

        }

    }

    /**
     *
     * 获取WeaForm组件
     *
     * @param jsonObject
     * @return
     */

    private static AbstractWeaForm getAbstractWeaForm(JSONObject jsonObject){

        String conditionType = jsonObject.getString("conditionType");

        if (!BaseTools.notNullString(conditionType)){

            throw new RuntimeException("组件:" + jsonObject.getString("label") + "的conditionType为空");

        }

        String jsonString = jsonObject.toJSONString();

        switch (conditionType){

            case "INPUT" :

                return JSONObject.parseObject(jsonString,InputWeaForm.class);

            case "TEXTAREA" :

                return JSONObject.parseObject(jsonString, TextAreaWeaForm.class);

            case "SELECT" :

                return JSONObject.parseObject(jsonString, SelectWeaForm.class); //需要改进

            case "DATE" :

                return JSONObject.parseObject(jsonString, DateWeaForm.class);

            case "DATEPICKER" :

                return JSONObject.parseObject(jsonString, DatePickerWeaForm.class);

            case "TIMEPICKER" :

                return JSONObject.parseObject(jsonString,TimePickerWeaForm.class);

            case "RANGEPICKER" :

                return JSONObject.parseObject(jsonString,RangePickerWeaForm.class);

            case "DATE_INTERVAL" :

                return JSONObject.parseObject(jsonString,DateIntervalWeaForm.class);

            case "TIME_INTERVAL" :

                return JSONObject.parseObject(jsonString,TimeIntervalWeaForm.class);

            case "BROWSER" :

                return JSONObject.parseObject(jsonString,BrowserWeaForm.class);

            case "SELECT_LINKAGE" :

                return JSONObject.parseObject(jsonString, SelectLinkageWeaForm.class); //待改进

            case "INPUT_INTERVAL" :

                return null; //暂未实现

            case "SCOPE" :

                return JSONObject.parseObject(jsonString,ScopeWeaForm.class);

            case "CHECKBOX" :

                return JSONObject.parseObject(jsonString,CheckWeaForm.class);

            case "SWITCH" :

                return JSONObject.parseObject(jsonString,SwitchWeaForm.class);

            case "CUSTOM" :

                return null; //暂未实现

            case "RESOURCEIMG" :

                return JSONObject.parseObject(jsonString,ResourceImgWeaForm.class);

            case "COLORPICKER" :

                return JSONObject.parseObject(jsonString,ColorPickerWeaForm.class);

            case "CASCADER" :

                return JSONObject.parseObject(jsonString,CasCaderWeaForm.class);

            case "INPUTNUMBER" :

                return JSONObject.parseObject(jsonString,InputNumberWeaForm.class);

            case "CUSTOMFIELD" :

                return JSONObject.parseObject(jsonString,CustomFieldWeaForm.class);

            case "TIMERANGEPICKER" :

                return JSONObject.parseObject(jsonString,TimeRangePickerWeaForm.class);

            case "UPLOAD" :

                return JSONObject.parseObject(jsonString,UploadWeaForm.class);

            case "RICHTEXT" :

                return JSONObject.parseObject(jsonString,RichTextForm.class);

            case "TAGGROUP" :

                return JSONObject.parseObject(jsonString,TagGroupWeaForm.class);

            case "TEXT" :

                return JSONObject.parseObject(jsonString,TextWeaForm.class);

            case "CASCADERCUSTOMFIELD" :

                return JSONObject.parseObject(jsonString,CasCaderCustomFieldWeaForm.class);

            case "PASSWORD" :

                return JSONObject.parseObject(jsonString,PasswordWeaForm.class);

            case "DESCRIPTION" :

                return JSONObject.parseObject(jsonString,DescriptionWeaForm.class);

            case "RADIO" :

                return JSONObject.parseObject(jsonString,RadioWeaForm.class);

            case "INPUTSEARCH" :

                return null; //暂未实现

            case "PERIOD" :

                return null; //暂未实现

            default :

                throw new RuntimeException("ConditionType:" + conditionType + "目前组件还不支持解析");

        }

    }

    private static AbstractWeaChildTree getAbstractWeaChildTree(JSONObject weaChildTreeJsonObject){

        String conditionType = weaChildTreeJsonObject.getString("conditionType");

        switch (conditionType){

            case "0" :

                return weaChildTreeJsonObject.parseObject(weaChildTreeJsonObject.toJSONString(),DefaultWeaChildTree.class);

            default :

                return weaChildTreeJsonObject.parseObject(weaChildTreeJsonObject.toJSONString(),DefaultWeaChildTree.class);

        }

    }

    private static Class<? extends AbstractWeaFormGroup> getAbstractWeaFormGroupClass(JSONObject groupJsonObject){

        String type = groupJsonObject.getString("conditionType");

        switch (type){

            case "0" :

                return DefaultWeaFormGroup.class;

            default :

                return DefaultWeaFormGroup.class;

        }

    }

    private static Class<? extends AbstractWeaFatherTree> getAbstractWeaFatherTreeClass(JSONObject weaFatherTreeJsonObject){

        String type = weaFatherTreeJsonObject.getString("conditionType");

        switch (type){

            case "0" :

                return DefaultWeaFatherTree.class;

            default :

                return DefaultWeaFatherTree.class;

        }

    }

    private static Class<? extends AbstractWeaTab> getAbstractWeaTabClass(JSONObject weaTabJsonObject){

        String type = weaTabJsonObject.get("conditionType").toString();

        switch (type){

            case "0" :

                return DefaultWeaTab.class;

            default :

                return DefaultWeaTab.class;

        }

    }

}
