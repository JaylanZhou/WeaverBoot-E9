package com.weaverboot.weaComponent.impl.weaTable.column.impl;

import com.weaverboot.tools.enumTools.weaComponent.WeaAlignEnum;
import com.weaverboot.tools.enumTools.weaComponent.WeaBelongEnum;
import com.weaverboot.tools.enumTools.weaComponent.WeaBooleanEnum;
import com.weaverboot.tools.enumTools.weaComponent.WeaMobileViewTypeEnum;
import com.weaverboot.weaComponent.impl.weaTable.column.inte.AbstractWeaTableColumn;

/**
 *
 * AbstractWeaTableColumn - 默认实现类
 *
 * @Author : Jaylan Zhou
 * @Date : 2019-12-23 11:21
 * @Version : 1.0
 */
public class DefaultWeaTableColumn extends AbstractWeaTableColumn {

    /**
     *
     * 设置 column 默认值
     *
     */

    private void init(){

        this.setAlign(WeaAlignEnum.LEFT); //默认左对齐

        this.setDataalign(WeaAlignEnum.LEFT); //默认数据左对齐

        this.setHide(WeaBooleanEnum.FALSE); //默认不隐藏

        this.setShowType(0);

        this.setIsPrimarykey(WeaBooleanEnum.FALSE); //默认字段不为主键

        this.setIsInputCol(WeaBooleanEnum.FALSE); //默认不可输入

        this.setDisplay(WeaBooleanEnum.TRUE); //默认为显示

        this.setBelong(WeaBelongEnum.PC); //默认为PC展示

        this.setMobileviewtype(WeaMobileViewTypeEnum.HIGHLIGHT);

    }

    public DefaultWeaTableColumn(String width, String text, String column, String orderkey, String transmethod) {

        super();

        init();

        this.setWidth(width);

        this.setText(text);

        this.setColumn(column);

        this.setOrderkey(orderkey);

        this.setTransmethod(transmethod);

    }

    public DefaultWeaTableColumn() {

        super();

        init();

    }

    public DefaultWeaTableColumn(String column) {

        super();

        init();

        this.setColumn(column);

    }

    public DefaultWeaTableColumn(WeaBooleanEnum hide, String column) {

        super();

        init();

        this.setHide(hide);

        this.setColumn(column);

    }

    public DefaultWeaTableColumn(String width, String text, String column, String orderkey) {

        super();

        init();

        this.setWidth(width);

        this.setText(text);

        this.setColumn(column);


        this.setOrderkey(orderkey);

    }

    public DefaultWeaTableColumn(String width, String text, String column) {

        super();

        init();

        this.setWidth(width);

        this.setText(text);

        this.setColumn(column);

    }

    public DefaultWeaTableColumn(String width, String text, String column, String orderkey, int showType) {

        super();

        init();

        this.setWidth(width);

        this.setText(text);

        this.setColumn(column);

        this.setOrderkey(orderkey);

        this.setShowType(showType);

    }

    public DefaultWeaTableColumn(String width, String text, String column, String orderkey, String transmethod, int showType) {

        super();

        init();

        this.setWidth(width);

        this.setText(text);

        this.setColumn(column);

        this.setOrderkey(orderkey);

        this.setTransmethod(transmethod);

        this.setShowType(showType);

    }

    public DefaultWeaTableColumn(String width, String text, String column, String orderkey, String transmethod, String otherpara) {

        super();

        init();

        this.setWidth(width);

        this.setText(text);

        this.setColumn(column);

        this.setOrderkey(orderkey);

        this.setTransmethod(transmethod);

        this.setOtherpara(otherpara);

    }

    public DefaultWeaTableColumn(String width, String text, String column, String orderkey, String transmethod, String otherpara, String customCol) {

        super();

        init();

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

        init();

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

        init();

        this.setColumn(column);

        this.setTransmethod(transmethod);

        this.setOtherpara(otherpara);

        this.setShowType(showType);

    }

}
