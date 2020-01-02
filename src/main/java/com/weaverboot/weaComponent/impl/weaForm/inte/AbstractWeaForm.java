package com.weaverboot.weaComponent.impl.weaForm.inte;

import com.alibaba.fastjson.JSONObject;
import com.api.browser.util.BelongAttr;
import com.weaverboot.weaComponent.inte.AbstractWeaComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 组件 - 公共基类
 *
 * 此类含有所有组件的共有属性及共有方法
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public abstract class AbstractWeaForm extends AbstractWeaComponent {


    /**
     * 自定义类型，当conditionType为custom时使用
     **/

    private String customType;

    /**
     * 不同类型组件可能需要不同参数，扩展个Map存储，前端可合并到父级传输到组件
     **/

    private Map<String, Object> otherParams;

    /**
     * 标识参数
     */

    private String key;

    /**
     * 显示、提交参数
     */

    private String label;

    /**
     * 条件联动
     */

    private String relatekey;

    private String[] domkey;

    private String[] parentDomkey;//在查询列表 选项存在于父选项时,con_父选项fildid qc:376384

    /**
     * 浏览数据定义相关
     */

    private int viewAttr = 2;

    private Object value;

    private boolean hide;

    private int length;

    private int stringLength;

    /**
     * 布局参数
     */

    private int labelcol = 6;

    private int fieldcol;


    /**
     * 定义一行显示条件数，默认值为2,当值为1时标识该条件单独占一行
     */

    private int colSpan = 2;

    /**
     * isQuickSearch
     **/

    private boolean isQuickSearch;

    /***
     * 多语言小地球
     */

    private boolean isBase64;

    private String inputType;

    /**
     * 数字框精度设置   2:保留2位
     */

    private int precision;

    private String min;

    private String max;

    private String step;

    /**
     * 前端是否校验必填及显示信息 数据格式 required|string @梁勇
     */

    private String rules;

    private String regExp;

    private String helpfulTip;

    private Map<String, Object> helpfulTipProps = new HashMap<String, Object>();

    private int showOrder; //顺序

    private boolean checkbox; //字段是否可以复选

    private boolean checkboxValue; //字段复选的值

    private String tip; //inputnumber组件里文字说明

    private String display;

    private String placeholder;

    private boolean entSearch; //true 输入回车搜索 默认false

    private boolean dateGroup; //日期区间设置不限。

    private List<String> valueList = new ArrayList<String>();

    //移动端属性 是否移动端 0/1/2  (默认0)(0仅在PC端显示)(1仅在移动端显示)(2同时在PC及移动端显示)
    private BelongAttr belong = BelongAttr.PC;

    private String tipPosition = "bottom";

    private JSONObject compDef;

    public Object compValue;

    private String className;

    private boolean hasBorder = false;

    public AbstractWeaForm(){

        init();

    }

    public void copyCommonAttr(AbstractWeaForm abstractWeaForm){

        this.setBelong(abstractWeaForm.getBelong());

        this.setIsBase64(abstractWeaForm.getIsBase64());

        this.setCheckbox(abstractWeaForm.isCheckbox());

        this.setCheckboxValue(abstractWeaForm.isCheckboxValue());

        this.setClassName(abstractWeaForm.getClassName());

        this.setColSpan(abstractWeaForm.getColSpan());

        this.setCompDef(abstractWeaForm.getCompDef());

        this.setCompValue(abstractWeaForm.getCompValue());

        this.setCustomType(abstractWeaForm.getCustomType());

        this.setDateGroup(abstractWeaForm.isDateGroup());

        this.setDisplay(abstractWeaForm.getDisplay());

        this.setDomkey(abstractWeaForm.getDomkey());

        this.setEntSearch(abstractWeaForm.isEntSearch());

        this.setFieldcol(abstractWeaForm.getFieldcol());

        this.setHasBorder(abstractWeaForm.isHasBorder());

        this.setHelpfulTip(abstractWeaForm.getHelpfulTip());

        this.setHelpfulTipProps(abstractWeaForm.getHelpfulTipProps());

        this.setHide(abstractWeaForm.isHide());

        this.setInputType(abstractWeaForm.getInputType());

        this.setKey(abstractWeaForm.getKey());

        this.setLabel(abstractWeaForm.getLabel());

        this.setLabelcol(abstractWeaForm.getLabelcol());

        this.setLength(abstractWeaForm.getLength());

        this.setMax(abstractWeaForm.getMax());

        this.setMin(abstractWeaForm.getMin());

        this.setOtherParams(abstractWeaForm.getOtherParams());

        this.setParentDomkey(abstractWeaForm.getParentDomkey());

        this.setPlaceholder(abstractWeaForm.getPlaceholder());

        this.setPrecision(abstractWeaForm.getPrecision());

        this.setIsQuickSearch(abstractWeaForm.getIsQuickSearch());

        this.setRegExp(abstractWeaForm.getRegExp());

        this.setRelatekey(abstractWeaForm.getRelatekey());

        this.setRules(abstractWeaForm.getRules());

        this.setShowOrder(abstractWeaForm.getShowOrder());

        this.setStep(abstractWeaForm.getStep());

        this.setStringLength(abstractWeaForm.getStringLength());

        this.setTip(abstractWeaForm.getTip());

        this.setTipPosition(abstractWeaForm.getTipPosition());

        this.setValue(abstractWeaForm.getValue());

        this.setValueList(abstractWeaForm.getValueList());

        this.setViewAttr(abstractWeaForm.getViewAttr());

    }

    private void init(){

        this.colSpan = 2;

        this.fieldcol = 10;

        this.viewAttr = 2;

    }

    public String getCustomType() {
        return customType;
    }

    public void setCustomType(String customType) {
        this.customType = customType;
    }

    public Map<String, Object> getOtherParams() {
        return otherParams;
    }

    public void setOtherParams(Map<String, Object> otherParams) {
        this.otherParams = otherParams;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRelatekey() {
        return relatekey;
    }

    public void setRelatekey(String relatekey) {
        this.relatekey = relatekey;
    }

    public String[] getDomkey() {
        return domkey;
    }

    public void setDomkey(String ... domkey) {
        this.domkey = domkey;
    }

    public String[] getParentDomkey() {
        return parentDomkey;
    }

    public void setParentDomkey(String[] parentDomkey) {
        this.parentDomkey = parentDomkey;
    }

    public int getViewAttr() {
        return viewAttr;
    }

    public void setViewAttr(int viewAttr) {
        this.viewAttr = viewAttr;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getStringLength() {
        return stringLength;
    }

    public void setStringLength(int stringLength) {
        this.stringLength = stringLength;
    }

    public int getLabelcol() {
        return labelcol;
    }

    public void setLabelcol(int labelcol) {
        this.labelcol = labelcol;
    }

    public int getFieldcol() {
        return fieldcol;
    }

    public void setFieldcol(int fieldcol) {
        this.fieldcol = fieldcol;
    }

    public int getColSpan() {
        return colSpan;
    }

    public void setColSpan(int colSpan) {
        this.colSpan = colSpan;
    }

    public boolean getIsQuickSearch() {
        return isQuickSearch;
    }

    public void setIsQuickSearch(boolean quickSearch) {
        isQuickSearch = quickSearch;
    }

    public boolean getIsBase64() {
        return isBase64;
    }

    public void setIsBase64(boolean base64) {
        isBase64 = base64;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }


    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getRegExp() {
        return regExp;
    }

    public void setRegExp(String regExp) {
        this.regExp = regExp;
    }

    public String getHelpfulTip() {
        return helpfulTip;
    }

    public void setHelpfulTip(String helpfulTip) {
        this.helpfulTip = helpfulTip;
    }

    public Map<String, Object> getHelpfulTipProps() {
        return helpfulTipProps;
    }

    public void setHelpfulTipProps(Map<String, Object> helpfulTipProps) {
        this.helpfulTipProps = helpfulTipProps;
    }

    public int getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(int showOrder) {
        this.showOrder = showOrder;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public boolean isCheckboxValue() {
        return checkboxValue;
    }

    public void setCheckboxValue(boolean checkboxValue) {
        this.checkboxValue = checkboxValue;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public boolean isEntSearch() {
        return entSearch;
    }

    public void setEntSearch(boolean entSearch) {
        this.entSearch = entSearch;
    }

    public boolean isDateGroup() {
        return dateGroup;
    }

    public void setDateGroup(boolean dateGroup) {
        this.dateGroup = dateGroup;
    }

    public List<String> getValueList() {
        return valueList;
    }

    public void setValueList(List<String> valueList) {
        this.valueList = valueList;
    }

    public BelongAttr getBelong() {
        return belong;
    }

    public void setBelong(BelongAttr belong) {
        this.belong = belong;
    }

    public String getTipPosition() {
        return tipPosition;
    }

    public void setTipPosition(String tipPosition) {
        this.tipPosition = tipPosition;
    }

    public JSONObject getCompDef() {
        return compDef;
    }

    public void setCompDef(JSONObject compDef) {
        this.compDef = compDef;
    }

    public Object getCompValue() {
        return compValue;
    }

    public void setCompValue(Object compValue) {
        this.compValue = compValue;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isHasBorder() {
        return hasBorder;
    }

    public void setHasBorder(boolean hasBorder) {
        this.hasBorder = hasBorder;
    }
}
