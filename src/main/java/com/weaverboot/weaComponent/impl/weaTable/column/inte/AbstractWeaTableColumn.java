package com.weaverboot.weaComponent.impl.weaTable.column.inte;

import com.cloudstore.eccom.constant.WeaAlignAttr;
import com.cloudstore.eccom.constant.WeaBelongType;
import com.cloudstore.eccom.constant.WeaBoolAttr;
import com.cloudstore.eccom.constant.WeaMobileViewType;
import com.weaverboot.weaComponent.inte.AbstractWeaComponent;

/**
 *
 * 默认 WeaTable 实现组件
 *
 * @Author : Jaylan Zhou
 * @Date : 2019-12-23 11:21
 * @Version : 1.0
 */

public abstract class AbstractWeaTableColumn extends AbstractWeaComponent {

    private WeaAlignAttr align = WeaAlignAttr.LEFT; //支持对齐方式   center,left,right

    private WeaAlignAttr dataalign = WeaAlignAttr.LEFT; //内容支持对齐方式   center,left,right

    private WeaBoolAttr hide = WeaBoolAttr.FALSE;;//标识隐藏字段

    private String width;

    private String text;

    private String column;

    private String orderkey;

    private String transmethod;

    private String otherpara;

    private String otherpara2;

    private int showType = 0; //主要对当列表在多选浏览框使用时 0： 显示 1：高亮显示 2：不显示

    private WeaBoolAttr isPrimarykey = WeaBoolAttr.FALSE; //是否主键字段

    private WeaBoolAttr isInputCol = WeaBoolAttr.FALSE; //在输入框中显示的名称

    private String transMethodForce;//强制处理transMethod，即使hide为true也处理。

    private String collapse; //合并行

    private String customCol;//控制显示列定制（false 不显示）

    private String key;//消除列相同时显示错位问题（实际使用中传入不同的key即可，tablestring中表现为_key）

    private WeaBoolAttr display=WeaBoolAttr.TRUE; //默认显示

    //移动端属性 是否移动端 0/1/2  (默认0)(0仅在PC端显示)(1仅在移动端显示)(2同时在PC及移动端显示)
    private WeaBelongType belong=WeaBelongType.PC;

    //移动端显示方式1/2  (single模式下生效)(1高亮显示、2第二行次要信息展示)
    private WeaMobileViewType mobileviewtype=WeaMobileViewType.HIGHLIGHT;

    ///移动端 转换函数
    private String mobiletransmethod;

    ///移动端 转换函数 参数
    private String mobileotherpara;

    ///字段内容转换成base64格式，防止被拦截
    private WeaBoolAttr isBase64=WeaBoolAttr.FALSE;

    ///字段固定列
    private String fixed=null;//left,right,none



    public WeaAlignAttr getAlign() {
        return align;
    }

    public void setAlign(WeaAlignAttr align) {
        this.align = align;
    }

    public WeaAlignAttr getDataalign() {
        return dataalign;
    }

    public void setDataalign(WeaAlignAttr dataalign) {
        this.dataalign = dataalign;
    }

    public WeaBoolAttr getHide() {
        return hide;
    }

    public void setHide(WeaBoolAttr hide) {
        this.hide = hide;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getOrderkey() {
        return orderkey;
    }

    public void setOrderkey(String orderkey) {
        this.orderkey = orderkey;
    }

    public String getTransmethod() {
        return transmethod;
    }

    public void setTransmethod(String transmethod) {
        this.transmethod = transmethod;
    }

    public String getOtherpara() {
        return otherpara;
    }

    public void setOtherpara(String otherpara) {
        this.otherpara = otherpara;
    }

    public String getOtherpara2() {
        return otherpara2;
    }

    public void setOtherpara2(String otherpara2) {
        this.otherpara2 = otherpara2;
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public WeaBoolAttr getIsPrimarykey() {
        return isPrimarykey;
    }

    public void setIsPrimarykey(WeaBoolAttr isPrimarykey) {
        this.isPrimarykey = isPrimarykey;
    }

    public WeaBoolAttr getIsInputCol() {
        return isInputCol;
    }

    public void setIsInputCol(WeaBoolAttr isInputCol) {
        this.isInputCol = isInputCol;
    }

    public String getTransMethodForce() {
        return transMethodForce;
    }

    public void setTransMethodForce(String transMethodForce) {
        this.transMethodForce = transMethodForce;
    }

    public String getCollapse() {
        return collapse;
    }

    public void setCollapse(String collapse) {
        this.collapse = collapse;
    }

    public String getCustomCol() {
        return customCol;
    }

    public void setCustomCol(String customCol) {
        this.customCol = customCol;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public WeaBoolAttr getDisplay() {
        return display;
    }

    public void setDisplay(WeaBoolAttr display) {
        this.display = display;
    }

    public WeaBelongType getBelong() {
        return belong;
    }

    public void setBelong(WeaBelongType belong) {
        this.belong = belong;
    }

    public WeaMobileViewType getMobileviewtype() {
        return mobileviewtype;
    }

    public void setMobileviewtype(WeaMobileViewType mobileviewtype) {
        this.mobileviewtype = mobileviewtype;
    }

    public String getMobiletransmethod() {
        return mobiletransmethod;
    }

    public void setMobiletransmethod(String mobiletransmethod) {
        this.mobiletransmethod = mobiletransmethod;
    }

    public String getMobileotherpara() {
        return mobileotherpara;
    }

    public void setMobileotherpara(String mobileotherpara) {
        this.mobileotherpara = mobileotherpara;
    }

    public WeaBoolAttr getIsBase64() {
        return isBase64;
    }

    public void setIsBase64(WeaBoolAttr isBase64) {
        this.isBase64 = isBase64;
    }

    public String getFixed() {
        return fixed;
    }

    public void setFixed(String fixed) {
        this.fixed = fixed;
    }
}
