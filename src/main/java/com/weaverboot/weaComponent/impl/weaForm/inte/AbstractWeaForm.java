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

    private int viewAttr;

    private Object value;

    private boolean hide;


    /**
     * 布局参数
     */

    private int labelcol;

    private int fieldcol;


    /**
     * 定义一行显示条件数，默认值为2,当值为1时标识该条件单独占一行
     */

    private int colSpan;

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
     * 前端是否校验必填及显示信息 数据格式 required|string @梁勇
     */

    private String rules;

    private String helpfulTip;

    private Map<String, Object> helpfulTipProps = new HashMap<String, Object>();

    private int showOrder; //顺序

    private String display;

    private String placeholder;

    private boolean entSearch; //true 输入回车搜索 默认false

    private List<String> valueList = new ArrayList<String>();

    //移动端属性 是否移动端 0/1/2  (默认0)(0仅在PC端显示)(1仅在移动端显示)(2同时在PC及移动端显示)
    private BelongAttr belong = BelongAttr.PC;

    private String tipPosition = "bottom";

    private JSONObject compDef;

    public Object compValue;

    private boolean hasBorder = false;

    public Map<String,String> style;

    public AbstractWeaForm(){

        init();

    }

    public void copyCommonAttr(AbstractWeaForm abstractWeaForm){

        this.setBelong(abstractWeaForm.getBelong());

        this.setIsBase64(abstractWeaForm.getIsBase64());

        this.setColSpan(abstractWeaForm.getColSpan());

        this.setCompDef(abstractWeaForm.getCompDef());

        this.setCompValue(abstractWeaForm.getCompValue());

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

        this.setOtherParams(abstractWeaForm.getOtherParams());

        this.setParentDomkey(abstractWeaForm.getParentDomkey());

        this.setPlaceholder(abstractWeaForm.getPlaceholder());

        this.setIsQuickSearch(abstractWeaForm.getIsQuickSearch());

        this.setRelatekey(abstractWeaForm.getRelatekey());

        this.setRules(abstractWeaForm.getRules());

        this.setShowOrder(abstractWeaForm.getShowOrder());

        this.setTipPosition(abstractWeaForm.getTipPosition());

        this.setValue(abstractWeaForm.getValue());

        this.setValueList(abstractWeaForm.getValueList());

        this.setViewAttr(abstractWeaForm.getViewAttr());

        this.setStyle(abstractWeaForm.getStyle());

    }

    /**
     *
     * 组件部分属性初始化默认值
     *
     * 初始化的值为：
     *
     * 列宽：2
     *
     *
     *
     */

    private void init(){

        this.colSpan = 2;

        this.fieldcol = 10;

        this.labelcol = 6;

        this.viewAttr = 2;

    }

    public AbstractWeaForm addStyle(String key,String value){

        if (this.style == null){

            this.style = new HashMap<>();

        }

        style.put(key,value);

        return this;

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

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
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

    public boolean isHasBorder() {
        return hasBorder;
    }

    public void setHasBorder(boolean hasBorder) {
        this.hasBorder = hasBorder;
    }

    public Map<String, String> getStyle() {
        return style;
    }

    public void setStyle(Map<String, String> style) {
        this.style = style;
    }
}
