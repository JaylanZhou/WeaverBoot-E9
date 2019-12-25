package com.weaverboot.weaComponent.impl.weaTable.table.impl;

import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.weaComponent.impl.weaTable.checkboxPopedom.inte.AbstractWeaCheckboxPopedom;
import com.weaverboot.weaComponent.impl.weaTable.column.inte.AbstractWeaTableColumn;
import com.weaverboot.weaComponent.impl.weaTable.operate.inte.AbstractWeaTableOperate;
import com.weaverboot.weaComponent.impl.weaTable.table.inte.AbstractWeaTable;

/**
 * @Author : Jaylan Zhou
 * @Date : 2019-12-25 16:27
 * @Version : 1.0
 */
public class DocWeaTable extends AbstractWeaTable {
    @Override
    public <T extends AbstractWeaTableColumn> T readWeaTableColumn(int index, Class<T> tClass) {

        if (this.getColumns().size() < index){

            throw new RuntimeException("此table的最大字段数量为:" + this.getColumns().size() + ",小于您输入的" + index);

        } else {

            return (T)this.getColumns().get(index);

        }

    }

    @Override
    public <T extends AbstractWeaTableColumn> T readWeaTableColumnWithName(String columnText, Class<T> tClass) {

        for (AbstractWeaTableColumn ab : this.getColumns()
        ) {

            if (BaseTools.notNullString(columnText) && columnText.equals(ab.getText())){

                return (T)ab;

            }

        }

        throw new RuntimeException("在此table中没有找到名为" + columnText + "的列");

    }

    @Override
    public <T extends AbstractWeaTableOperate> T readWeaTableOperate(int index, Class<T> tClass) {

        int operateNum = this.getOperates().getOperate().size();

        if (index > operateNum){

            throw new RuntimeException("您所输入的" + index + "大于当前最大操作按钮数");

        }

        return (T) this.getOperates().getOperate().get(index);
    }

    @Override
    public <T extends AbstractWeaTableOperate> T readWeaTableOperateWithName(String operateText, Class<T> tClass) {

        for (AbstractWeaTableOperate ab : this.getOperates().getOperate()
        ) {

            if (BaseTools.notNullString(operateText) && ab.getText().equals(operateText)){

                return (T) ab;

            }

        }

        throw new RuntimeException("没有找到名为" + operateText + "的操作按钮");

    }

    @Override
    public <T extends AbstractWeaCheckboxPopedom> T readWeaCheckboxPopedom(int index, Class<T> tClass) {

        if (this.getCheckboxList().size() < index){

            throw new RuntimeException("此table的最大checkboxlist大小为:" + this.getCheckboxList().size() + ",小于您输入的" + index);

        } else {

            return (T)this.getCheckboxList().get(index);

        }

    }
}
