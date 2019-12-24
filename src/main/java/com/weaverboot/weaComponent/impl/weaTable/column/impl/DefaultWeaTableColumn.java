package com.weaverboot.weaComponent.impl.weaTable.column.impl;

import com.weaverboot.tools.enumTools.weaComponent.WeaAlignEnum;
import com.weaverboot.tools.enumTools.weaComponent.WeaBelongEnum;
import com.weaverboot.tools.enumTools.weaComponent.WeaBooleanEnum;
import com.weaverboot.tools.enumTools.weaComponent.WeaMobileViewTypeEnum;
import com.weaverboot.weaComponent.impl.weaTable.column.inte.AbstractWeaTableColumn;

/**
 * @Author : Jaylan Zhou
 * @Date : 2019-12-23 11:21
 * @Version : 1.0
 */
public class DefaultWeaTableColumn extends AbstractWeaTableColumn {

    public DefaultWeaTableColumn(String width, String text, String column, String orderkey, String transmethod) {
        super();
        this.setAlign(WeaAlignEnum.LEFT);
        this.setDataalign(WeaAlignEnum.LEFT);
        this.setHide(WeaBooleanEnum.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBooleanEnum.FALSE);
        this.setIsInputCol(WeaBooleanEnum.FALSE);
        this.setDisplay(WeaBooleanEnum.TRUE);
        this.setBelong(WeaBelongEnum.PC);
        this.setMobileviewtype(WeaMobileViewTypeEnum.HIGHLIGHT);
        this.setWidth(width);
        this.setText(text);
        this.setColumn(column);
        this.setOrderkey(orderkey);
        this.setTransmethod(transmethod);
    }

    public DefaultWeaTableColumn() {
        super();
        this.setAlign(WeaAlignEnum.LEFT);
        this.setDataalign(WeaAlignEnum.LEFT);
        this.setHide(WeaBooleanEnum.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBooleanEnum.FALSE);
        this.setIsInputCol(WeaBooleanEnum.FALSE);
        this.setDisplay(WeaBooleanEnum.TRUE);
        this.setBelong(WeaBelongEnum.PC);
        this.setMobileviewtype(WeaMobileViewTypeEnum.HIGHLIGHT);
    }

    public DefaultWeaTableColumn(String column) {
        this.setAlign(WeaAlignEnum.LEFT);
        this.setDataalign(WeaAlignEnum.LEFT);
        this.setHide(WeaBooleanEnum.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBooleanEnum.FALSE);
        this.setIsInputCol(WeaBooleanEnum.FALSE);
        this.setDisplay(WeaBooleanEnum.TRUE);
        this.setBelong(WeaBelongEnum.PC);
        this.setMobileviewtype(WeaMobileViewTypeEnum.HIGHLIGHT);
        this.setColumn(column);
    }

    public DefaultWeaTableColumn(WeaBooleanEnum hide, String column) {
        super();
        this.setAlign(WeaAlignEnum.LEFT);
        this.setDataalign(WeaAlignEnum.LEFT);
        this.setHide(WeaBooleanEnum.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBooleanEnum.FALSE);
        this.setIsInputCol(WeaBooleanEnum.FALSE);
        this.setDisplay(WeaBooleanEnum.TRUE);
        this.setBelong(WeaBelongEnum.PC);
        this.setMobileviewtype(WeaMobileViewTypeEnum.HIGHLIGHT);
        this.setHide(hide);
        this.setColumn(column);
    }

    public DefaultWeaTableColumn(String width, String text, String column, String orderkey) {
        super();
        this.setAlign(WeaAlignEnum.LEFT);
        this.setDataalign(WeaAlignEnum.LEFT);
        this.setHide(WeaBooleanEnum.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBooleanEnum.FALSE);
        this.setIsInputCol(WeaBooleanEnum.FALSE);
        this.setDisplay(WeaBooleanEnum.TRUE);
        this.setBelong(WeaBelongEnum.PC);
        this.setMobileviewtype(WeaMobileViewTypeEnum.HIGHLIGHT);
        this.setWidth(width);
        this.setText(text);
        this.setColumn(column);
        this.setOrderkey(orderkey);
    }

    public DefaultWeaTableColumn(String width, String text, String column) {
        super();
        this.setAlign(WeaAlignEnum.LEFT);
        this.setDataalign(WeaAlignEnum.LEFT);
        this.setHide(WeaBooleanEnum.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBooleanEnum.FALSE);
        this.setIsInputCol(WeaBooleanEnum.FALSE);
        this.setDisplay(WeaBooleanEnum.TRUE);
        this.setBelong(WeaBelongEnum.PC);
        this.setMobileviewtype(WeaMobileViewTypeEnum.HIGHLIGHT);
        this.setWidth(width);
        this.setText(text);
        this.setColumn(column);
    }

    public DefaultWeaTableColumn(String width, String text, String column, String orderkey, int showType) {
        super();
        this.setAlign(WeaAlignEnum.LEFT);
        this.setDataalign(WeaAlignEnum.LEFT);
        this.setHide(WeaBooleanEnum.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBooleanEnum.FALSE);
        this.setIsInputCol(WeaBooleanEnum.FALSE);
        this.setDisplay(WeaBooleanEnum.TRUE);
        this.setBelong(WeaBelongEnum.PC);
        this.setMobileviewtype(WeaMobileViewTypeEnum.HIGHLIGHT);
        this.setWidth(width);
        this.setText(text);
        this.setColumn(column);
        this.setOrderkey(orderkey);
        this.setShowType(showType);
    }

    public DefaultWeaTableColumn(String width, String text, String column, String orderkey, String transmethod, int showType) {
        super();
        this.setAlign(WeaAlignEnum.LEFT);
        this.setDataalign(WeaAlignEnum.LEFT);
        this.setHide(WeaBooleanEnum.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBooleanEnum.FALSE);
        this.setIsInputCol(WeaBooleanEnum.FALSE);
        this.setDisplay(WeaBooleanEnum.TRUE);
        this.setBelong(WeaBelongEnum.PC);
        this.setMobileviewtype(WeaMobileViewTypeEnum.HIGHLIGHT);
        this.setWidth(width);
        this.setText(text);
        this.setColumn(column);
        this.setOrderkey(orderkey);
        this.setTransmethod(transmethod);
        this.setShowType(showType);
    }

    public DefaultWeaTableColumn(String width, String text, String column, String orderkey, String transmethod, String otherpara) {
        super();
        this.setAlign(WeaAlignEnum.LEFT);
        this.setDataalign(WeaAlignEnum.LEFT);
        this.setHide(WeaBooleanEnum.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBooleanEnum.FALSE);
        this.setIsInputCol(WeaBooleanEnum.FALSE);
        this.setDisplay(WeaBooleanEnum.TRUE);
        this.setBelong(WeaBelongEnum.PC);
        this.setMobileviewtype(WeaMobileViewTypeEnum.HIGHLIGHT);
        this.setWidth(width);
        this.setText(text);
        this.setColumn(column);
        this.setOrderkey(orderkey);
        this.setTransmethod(transmethod);
        this.setOtherpara(otherpara);
    }

    public DefaultWeaTableColumn(String width, String text, String column, String orderkey, String transmethod, String otherpara, String customCol) {
        super();
        this.setAlign(WeaAlignEnum.LEFT);
        this.setDataalign(WeaAlignEnum.LEFT);
        this.setHide(WeaBooleanEnum.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBooleanEnum.FALSE);
        this.setIsInputCol(WeaBooleanEnum.FALSE);
        this.setDisplay(WeaBooleanEnum.TRUE);
        this.setBelong(WeaBelongEnum.PC);
        this.setMobileviewtype(WeaMobileViewTypeEnum.HIGHLIGHT);
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
        this.setAlign(WeaAlignEnum.LEFT);
        this.setDataalign(WeaAlignEnum.LEFT);
        this.setHide(WeaBooleanEnum.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBooleanEnum.FALSE);
        this.setIsInputCol(WeaBooleanEnum.FALSE);
        this.setDisplay(WeaBooleanEnum.TRUE);
        this.setBelong(WeaBelongEnum.PC);
        this.setMobileviewtype(WeaMobileViewTypeEnum.HIGHLIGHT);
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
        this.setAlign(WeaAlignEnum.LEFT);
        this.setDataalign(WeaAlignEnum.LEFT);
        this.setHide(WeaBooleanEnum.FALSE);
        this.setShowType(0);
        this.setIsPrimarykey(WeaBooleanEnum.FALSE);
        this.setIsInputCol(WeaBooleanEnum.FALSE);
        this.setDisplay(WeaBooleanEnum.TRUE);
        this.setBelong(WeaBelongEnum.PC);
        this.setMobileviewtype(WeaMobileViewTypeEnum.HIGHLIGHT);
        this.setColumn(column);
        this.setTransmethod(transmethod);
        this.setOtherpara(otherpara);
        this.setShowType(showType);
    }

}
