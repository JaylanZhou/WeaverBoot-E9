package com.weaverboot.weaComponent.impl.weaTable.table.impl;

import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.weaComponent.impl.weaTable.checkboxPopedom.inte.AbstractWeaCheckboxPopedom;
import com.weaverboot.weaComponent.impl.weaTable.column.inte.AbstractWeaTableColumn;
import com.weaverboot.weaComponent.impl.weaTable.operate.inte.AbstractWeaTableOperate;
import com.weaverboot.weaComponent.impl.weaTable.table.inte.AbstractWeaTable;

import java.util.ArrayList;
import java.util.List;

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
    public <T extends AbstractWeaTableColumn> List<T> readWeaTableColumnWithNameArray(String columnText, Class<T> tClass) {

        List<T> tList = new ArrayList<>();

        for (AbstractWeaTableColumn ab : this.getColumns()
        ) {

            if (BaseTools.notNullString(columnText) && columnText.equals(ab.getText())){

                tList.add((T) ab);

            }

        }

        if (BaseTools.notNullList(tList)){

            return tList;

        }

        throw new RuntimeException("在此table中没有找到名为" + columnText + "的列");

    }

    @Override
    public <T extends AbstractWeaTableColumn> T readWeaTableColumnWithColumn(String column, Class<T> tClass) {

        for (AbstractWeaTableColumn ab : this.getColumns()
        ) {

            if (BaseTools.notNullString(column) && column.equals(ab.getColumn())){

                return (T)ab;

            }

        }

        throw new RuntimeException("在此table中没有找到列名为" + column + "的列");

    }

    @Override
    public <T extends AbstractWeaTableColumn> List<T> readWeaTableColumnWithColumnArray(String column, Class<T> tClass) {

        List<T> tList = new ArrayList<>();

        for (AbstractWeaTableColumn ab : this.getColumns()
        ) {

            if (BaseTools.notNullString(column) && column.equals(ab.getColumn())){

                tList.add((T) ab);

            }

        }

        if (BaseTools.notNullList(tList)){

            return tList;

        }

        throw new RuntimeException("在此table中没有找到列名为" + column + "的列");

    }

    @Override
    public <T extends AbstractWeaTableColumn> void addWeaTableColumn(int index, T t) {

        this.getColumns().add(index,t);

    }

    @Override
    public <T extends AbstractWeaTableColumn> void addWeaTableColumn(T t) {

        this.getColumns().add(t);

    }

    @Override
    public void removeWeaTableColumn(int index) {

        this.getColumns().remove(index);

    }

    @Override
    public void removeWeaTableColumnWithName(String columnText) {

        for (int i = 0; i < this.getColumns().size(); i++) {

            AbstractWeaTableColumn abstractWeaTableColumn = this.getColumns().get(i);

            String text = abstractWeaTableColumn.getText();

            if (BaseTools.notNullString(text) && text.equals(columnText)){

                this.getColumns().remove(i);

                break;

            }

        }

    }

    @Override
    public void removeWeaTableColumnWithNameAll(String columnText) {

        for (int i = 0; i < this.getColumns().size(); i++) {

            AbstractWeaTableColumn abstractWeaTableColumn = this.getColumns().get(i);

            if (BaseTools.notNullString(abstractWeaTableColumn.getText()) && abstractWeaTableColumn.getText().equals(columnText)){

                this.getColumns().remove(i);

            }

        }

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
    public <T extends AbstractWeaTableOperate> List<T> readWeaTableOperateWithNameArray(String operateText, Class<T> tClass) {

        List<T> tList = new ArrayList<>();

        for (AbstractWeaTableOperate ab : this.getOperates().getOperate()
        ) {

            if (BaseTools.notNullString(operateText) && ab.getText().equals(operateText)){

                tList.add((T) ab);

            }

        }

        if (BaseTools.notNullList(tList)){

            return tList;

        }

        throw new RuntimeException("没有找到名为" + operateText + "的操作按钮");

    }

    @Override
    public <T extends AbstractWeaTableOperate> void addWeaTableOperate(int index, T t) {

        this.getOperates().getOperate().add(index,t);

    }

    @Override
    public <T extends AbstractWeaTableOperate> void addWeaTableOperate(T t) {

        this.getOperates().getOperate().add(t);

    }

    @Override
    public void removeWeaTableOperate(int index) {

        this.getOperates().getOperate().remove(index);

    }

    @Override
    public void removeWeaTableOperateWithName(String operateText) {

        List<AbstractWeaTableOperate> abstractWeaTableOperateList = this.getOperates().getOperate();

        for (int i = 0; i < abstractWeaTableOperateList.size(); i++) {

            String text = abstractWeaTableOperateList.get(i).getText();

            if (BaseTools.notNullString(text) && text.equals(operateText)){

                abstractWeaTableOperateList.remove(i);

                break;

            }

        }

    }

    @Override
    public void removeWeaTableOperateWithNameAll(String operateText) {

        List<AbstractWeaTableOperate> abstractWeaTableOperateList = this.getOperates().getOperate();

        for (int i = 0; i < abstractWeaTableOperateList.size(); i++) {

            String text = abstractWeaTableOperateList.get(i).getText();

            if (BaseTools.notNullString(text) && text.equals(operateText)){

                abstractWeaTableOperateList.remove(i);

            }

        }

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
