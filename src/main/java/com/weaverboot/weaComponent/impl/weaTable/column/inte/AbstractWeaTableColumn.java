package com.weaverboot.weaComponent.impl.weaTable.column.inte;

import com.weaverboot.tools.enumTools.weaComponent.WeaAlignEnum;
import com.weaverboot.tools.enumTools.weaComponent.WeaBelongEnum;
import com.weaverboot.tools.enumTools.weaComponent.WeaBooleanEnum;
import com.weaverboot.tools.enumTools.weaComponent.WeaMobileViewTypeEnum;
import com.weaverboot.weaComponent.inte.AbstractWeaComponent;

/**
 *
 * WeaTableColumn - 基类
 *
 * 所有的 WeaTableColumn 具体实现类均由此类拓展
 *
 * @Author : Jaylan Zhou
 * @Date : 2019-12-23 11:21
 * @Version : 1.0
 */

public abstract class AbstractWeaTableColumn extends AbstractWeaComponent {

    private WeaAlignEnum align = WeaAlignEnum.LEFT; //支持对齐方式   center,left,right

    private WeaAlignEnum dataalign = WeaAlignEnum.LEFT; //内容支持对齐方式   center,left,right

    private WeaBooleanEnum hide = WeaBooleanEnum.FALSE;//标识隐藏字段

    private String width;

    private String text;

    private String column;

    private String orderkey;

    private String transmethod;

    private String otherpara;

    private String otherpara2;

    private int showType = 0; //主要对当列表在多选浏览框使用时 0： 显示 1：高亮显示 2：不显示

    private WeaBooleanEnum isPrimarykey = WeaBooleanEnum.FALSE; //是否主键字段

    private WeaBooleanEnum isInputCol = WeaBooleanEnum.FALSE; //在输入框中显示的名称

    private String transMethodForce;//强制处理transMethod，即使hide为true也处理。

    private String collapse; //合并行

    private String labelid;

    private String thumbnail;

    private String customCol;//控制显示列定制（false 不显示）

    private String key;//消除列相同时显示错位问题（实际使用中传入不同的key即可，tablestring中表现为_key）

    private WeaBooleanEnum display = WeaBooleanEnum.TRUE; //默认显示

    //移动端属性 是否移动端 0/1/2  (默认0)(0仅在PC端显示)(1仅在移动端显示)(2同时在PC及移动端显示)
    private WeaBelongEnum belong = WeaBelongEnum.PC;

    //移动端显示方式1/2  (single模式下生效)(1高亮显示、2第二行次要信息展示)
    private WeaMobileViewTypeEnum mobileviewtype = WeaMobileViewTypeEnum.HIGHLIGHT;

    ///移动端 转换函数
    private String mobiletransmethod;

    ///移动端 转换函数 参数
    private String mobileotherpara;

    ///字段内容转换成base64格式，防止被拦截
    private WeaBooleanEnum isBase64 = WeaBooleanEnum.FALSE;

    ///字段固定列
    private String fixed=null;//left,right,none



    public WeaAlignEnum getAlign() {
        return align;
    }

    public void setAlign(WeaAlignEnum align) {
        this.align = align;
    }

    public WeaAlignEnum getDataalign() {
        return dataalign;
    }

    public void setDataalign(WeaAlignEnum dataalign) {
        this.dataalign = dataalign;
    }

    public WeaBooleanEnum getHide() {
        return hide;
    }

    public void setHide(WeaBooleanEnum hide) {
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

    public WeaBooleanEnum getIsPrimarykey() {
        return isPrimarykey;
    }

    public void setIsPrimarykey(WeaBooleanEnum isPrimarykey) {
        this.isPrimarykey = isPrimarykey;
    }

    public WeaBooleanEnum getIsInputCol() {
        return isInputCol;
    }

    public void setIsInputCol(WeaBooleanEnum isInputCol) {
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

    public WeaBooleanEnum getDisplay() {
        return display;
    }

    public void setDisplay(WeaBooleanEnum display) {
        this.display = display;
    }

    public WeaBelongEnum getBelong() {
        return belong;
    }

    public void setBelong(WeaBelongEnum belong) {
        this.belong = belong;
    }

    public WeaMobileViewTypeEnum getMobileviewtype() {
        return mobileviewtype;
    }

    public void setMobileviewtype(WeaMobileViewTypeEnum mobileviewtype) {
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

    public WeaBooleanEnum getIsBase64() {
        return isBase64;
    }

    public void setIsBase64(WeaBooleanEnum isBase64) {
        this.isBase64 = isBase64;
    }

    public String getFixed() {
        return fixed;
    }

    public void setFixed(String fixed) {
        this.fixed = fixed;
    }

    public String getLabelid() {
        return labelid;
    }

    public void setLabelid(String labelid) {
        this.labelid = labelid;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
