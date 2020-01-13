package com.weaverboot.tools.componentTools.form;
import com.alibaba.fastjson.JSONObject;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.weaComponent.impl.weaForm.impl.*;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;
import com.weaverboot.weaComponent.impl.weaForm.weaFormGroup.DefaultWeaFormGroup;
import com.weaverboot.weaComponent.impl.weaForm.weaFormGroup.inte.AbstractWeaFormGroup;
import com.weaverboot.weaComponent.impl.weaTab.impl.DefaultWeaTab;
import com.weaverboot.weaComponent.impl.weaTab.inte.AbstractWeaTab;
import com.weaverboot.weaComponent.impl.weaTree.impl.DefaultWeaTree;
import com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaTree;
import com.weaverboot.weaResultMsg.impl.formResult.inte.AbstractWeaFormReciveComponentResultMsg;
import weaver.wechat.util.Utils;
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

        parseWeaTree(t,t.getWeaTree());

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

    private static void parseWeaTree(AbstractWeaFormReciveComponentResultMsg abstractWeaFormReciveComponentResultMsg,List<? super AbstractWeaTree> weaFatherTreeJson){

        if (BaseTools.notNullList(weaFatherTreeJson)){

            abstractWeaFormReciveComponentResultMsg.setWeaTree(handleWeaTree(weaFatherTreeJson));

        }


    }

    private static List<? super AbstractWeaTree> handleWeaTree(List<? super AbstractWeaTree> weaTreeJson){

        List<? super AbstractWeaTree> fatherTreeList = new ArrayList<>();

        for (int i = 0; i < weaTreeJson.size(); i++) {

            JSONObject jsonObject = (JSONObject) weaTreeJson.get(i);

            AbstractWeaTree abstractWeaFatherTree = JSONObject.parseObject(jsonObject.toJSONString(), getAbstractWeaTreeClass(jsonObject));

            List<? super AbstractWeaTree> childTreeJsonArray = abstractWeaFatherTree.getChilds();

            if (BaseTools.notNullList(childTreeJsonArray)) {

                abstractWeaFatherTree.setChilds(handleWeaTree(childTreeJsonArray));

            }

            fatherTreeList.add(abstractWeaFatherTree);

        }

        return fatherTreeList;

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

        String conditionType = Utils.null2String(jsonObject.getString("conditionType"));

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

                return JSONObject.parseObject(jsonString, DateGroupWeaForm.class);

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

                return JSONObject.parseObject(jsonString, CheckBoxWeaForm.class);

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

    private static Class<? extends AbstractWeaFormGroup> getAbstractWeaFormGroupClass(JSONObject groupJsonObject){

        String type = Utils.null2String(groupJsonObject.getString("conditionType"));

        switch (type){

            case "0" :

                return DefaultWeaFormGroup.class;

            default :

                return DefaultWeaFormGroup.class;

        }

    }

    private static Class<? extends AbstractWeaTree> getAbstractWeaTreeClass(JSONObject weaFatherTreeJsonObject){

        String type = Utils.null2String(weaFatherTreeJsonObject.getString("conditionType"));

        switch (type){

            case "0" :

                return DefaultWeaTree.class;

            default :

                return DefaultWeaTree.class;

        }

    }

    private static Class<? extends AbstractWeaTab> getAbstractWeaTabClass(JSONObject weaTabJsonObject){

        String type = Utils.null2String(weaTabJsonObject.get("conditionType").toString());

        switch (type){

            case "0" :

                return DefaultWeaTab.class;

            default :

                return DefaultWeaTab.class;

        }

    }

}
