package com.weaverboot.weaComponent.impl.weaTable.column.impl;

import com.cloudstore.eccom.constant.WeaAlignAttr;
import com.cloudstore.eccom.constant.WeaBelongType;
import com.cloudstore.eccom.constant.WeaBoolAttr;
import com.cloudstore.eccom.constant.WeaMobileViewType;
import com.weaverboot.weaComponent.impl.weaTable.column.inte.AbstractWeaTableColumn;

/**
 * @Author : Jaylan Zhou
 * @Date : 2019-12-23 11:21
 * @Version : 1.0
 */
public class DefaultWeaTableColumn extends AbstractWeaTableColumn {

    public DefaultWeaTableColumn(String width, String text, String column, String orderkey, String transmethod) {
        super();
        this.setAlign(WeaAlignAttr.LEFT);
        this.setDataalign(WeaAlignAttr.LEFT);
        this.setHide(WeaBoolAttr.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBoolAttr.FALSE);
        this.setIsInputCol(WeaBoolAttr.FALSE);
        this.setDisplay(WeaBoolAttr.TRUE);
        this.setBelong(WeaBelongType.PC);
        this.setMobileviewtype(WeaMobileViewType.HIGHLIGHT);
        this.setWidth(width);
        this.setText(text);
        this.setColumn(column);
        this.setOrderkey(orderkey);
        this.setTransmethod(transmethod);
    }

    public DefaultWeaTableColumn() {
        super();
        this.setAlign(WeaAlignAttr.LEFT);
        this.setDataalign(WeaAlignAttr.LEFT);
        this.setHide(WeaBoolAttr.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBoolAttr.FALSE);
        this.setIsInputCol(WeaBoolAttr.FALSE);
        this.setDisplay(WeaBoolAttr.TRUE);
        this.setBelong(WeaBelongType.PC);
        this.setMobileviewtype(WeaMobileViewType.HIGHLIGHT);
    }

    public DefaultWeaTableColumn(String column) {
        this.setAlign(WeaAlignAttr.LEFT);
        this.setDataalign(WeaAlignAttr.LEFT);
        this.setHide(WeaBoolAttr.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBoolAttr.FALSE);
        this.setIsInputCol(WeaBoolAttr.FALSE);
        this.setDisplay(WeaBoolAttr.TRUE);
        this.setBelong(WeaBelongType.PC);
        this.setMobileviewtype(WeaMobileViewType.HIGHLIGHT);
        this.setColumn(column);
    }

    public DefaultWeaTableColumn(WeaBoolAttr hide, String column) {
        super();
        this.setAlign(WeaAlignAttr.LEFT);
        this.setDataalign(WeaAlignAttr.LEFT);
        this.setHide(WeaBoolAttr.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBoolAttr.FALSE);
        this.setIsInputCol(WeaBoolAttr.FALSE);
        this.setDisplay(WeaBoolAttr.TRUE);
        this.setBelong(WeaBelongType.PC);
        this.setMobileviewtype(WeaMobileViewType.HIGHLIGHT);
        this.setHide(hide);
        this.setColumn(column);
    }

    public DefaultWeaTableColumn(String width, String text, String column, String orderkey) {
        super();
        this.setAlign(WeaAlignAttr.LEFT);
        this.setDataalign(WeaAlignAttr.LEFT);
        this.setHide(WeaBoolAttr.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBoolAttr.FALSE);
        this.setIsInputCol(WeaBoolAttr.FALSE);
        this.setDisplay(WeaBoolAttr.TRUE);
        this.setBelong(WeaBelongType.PC);
        this.setMobileviewtype(WeaMobileViewType.HIGHLIGHT);
        this.setWidth(width);
        this.setText(text);
        this.setColumn(column);
        this.setOrderkey(orderkey);
    }

    public DefaultWeaTableColumn(String width, String text, String column) {
        super();
        this.setAlign(WeaAlignAttr.LEFT);
        this.setDataalign(WeaAlignAttr.LEFT);
        this.setHide(WeaBoolAttr.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBoolAttr.FALSE);
        this.setIsInputCol(WeaBoolAttr.FALSE);
        this.setDisplay(WeaBoolAttr.TRUE);
        this.setBelong(WeaBelongType.PC);
        this.setMobileviewtype(WeaMobileViewType.HIGHLIGHT);
        this.setWidth(width);
        this.setText(text);
        this.setColumn(column);
    }

    public DefaultWeaTableColumn(String width, String text, String column, String orderkey, int showType) {
        super();
        this.setAlign(WeaAlignAttr.LEFT);
        this.setDataalign(WeaAlignAttr.LEFT);
        this.setHide(WeaBoolAttr.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBoolAttr.FALSE);
        this.setIsInputCol(WeaBoolAttr.FALSE);
        this.setDisplay(WeaBoolAttr.TRUE);
        this.setBelong(WeaBelongType.PC);
        this.setMobileviewtype(WeaMobileViewType.HIGHLIGHT);
        this.setWidth(width);
        this.setText(text);
        this.setColumn(column);
        this.setOrderkey(orderkey);
        this.setShowType(showType);
    }

    public DefaultWeaTableColumn(String width, String text, String column, String orderkey, String transmethod, int showType) {
        super();
        this.setAlign(WeaAlignAttr.LEFT);
        this.setDataalign(WeaAlignAttr.LEFT);
        this.setHide(WeaBoolAttr.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBoolAttr.FALSE);
        this.setIsInputCol(WeaBoolAttr.FALSE);
        this.setDisplay(WeaBoolAttr.TRUE);
        this.setBelong(WeaBelongType.PC);
        this.setMobileviewtype(WeaMobileViewType.HIGHLIGHT);
        this.setWidth(width);
        this.setText(text);
        this.setColumn(column);
        this.setOrderkey(orderkey);
        this.setTransmethod(transmethod);
        this.setShowType(showType);
    }

    public DefaultWeaTableColumn(String width, String text, String column, String orderkey, String transmethod, String otherpara) {
        super();
        this.setAlign(WeaAlignAttr.LEFT);
        this.setDataalign(WeaAlignAttr.LEFT);
        this.setHide(WeaBoolAttr.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBoolAttr.FALSE);
        this.setIsInputCol(WeaBoolAttr.FALSE);
        this.setDisplay(WeaBoolAttr.TRUE);
        this.setBelong(WeaBelongType.PC);
        this.setMobileviewtype(WeaMobileViewType.HIGHLIGHT);
        this.setWidth(width);
        this.setText(text);
        this.setColumn(column);
        this.setOrderkey(orderkey);
        this.setTransmethod(transmethod);
        this.setOtherpara(otherpara);
    }

    public DefaultWeaTableColumn(String width, String text, String column, String orderkey, String transmethod, String otherpara, String customCol) {
        super();
        this.setAlign(WeaAlignAttr.LEFT);
        this.setDataalign(WeaAlignAttr.LEFT);
        this.setHide(WeaBoolAttr.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBoolAttr.FALSE);
        this.setIsInputCol(WeaBoolAttr.FALSE);
        this.setDisplay(WeaBoolAttr.TRUE);
        this.setBelong(WeaBelongType.PC);
        this.setMobileviewtype(WeaMobileViewType.HIGHLIGHT);
        this.setWidth(width);
        this.setText(text);
        this.setColumn(column);
        this.setOrderkey(orderkey);
        this.setTransmethod(transmethod);
        this.setOtherpara(otherpara);
        this.setCustomCol(customCol);
    }

    public DefaultWeaTableColumn(String width, String text, String column, String orderkey, String transmethod, String otherpara, int showType) {
        super();
        this.setAlign(WeaAlignAttr.LEFT);
        this.setDataalign(WeaAlignAttr.LEFT);
        this.setHide(WeaBoolAttr.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBoolAttr.FALSE);
        this.setIsInputCol(WeaBoolAttr.FALSE);
        this.setDisplay(WeaBoolAttr.TRUE);
        this.setBelong(WeaBelongType.PC);
        this.setMobileviewtype(WeaMobileViewType.HIGHLIGHT);
        this.setWidth(width);
        this.setText(text);
        this.setColumn(column);
        this.setOrderkey(orderkey);
        this.setTransmethod(transmethod);
        this.setOtherpara(otherpara);
        this.setShowType(showType);
    }

    public DefaultWeaTableColumn(String column, String transmethod, String otherpara, int showType) {
        super();
        this.setAlign(WeaAlignAttr.LEFT);
        this.setDataalign(WeaAlignAttr.LEFT);
        this.setHide(WeaBoolAttr.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBoolAttr.FALSE);
        this.setIsInputCol(WeaBoolAttr.FALSE);
        this.setDisplay(WeaBoolAttr.TRUE);
        this.setBelong(WeaBelongType.PC);
        this.setMobileviewtype(WeaMobileViewType.HIGHLIGHT);
        this.setColumn(column);
        this.setTransmethod(transmethod);
        this.setOtherpara(otherpara);
        this.setShowType(showType);
    }

}
