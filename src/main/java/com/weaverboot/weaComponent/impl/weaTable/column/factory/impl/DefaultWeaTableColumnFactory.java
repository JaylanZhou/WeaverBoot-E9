package com.weaverboot.weaComponent.impl.weaTable.column.factory.impl;

import com.weaverboot.weaComponent.impl.weaTable.column.factory.inte.WeaTableColumnFactory;
import com.weaverboot.weaComponent.impl.weaTable.column.impl.DefaultWeaTableColumn;
import com.weaverboot.weaComponent.impl.weaTable.column.impl.DocWeaTableColumn;
import com.weaverboot.weaComponent.impl.weaTable.column.inte.AbstractWeaTableColumn;
import com.weaverboot.weaComponent.impl.weaTable.table.impl.DocWeaTable;
import com.weaverboot.weaComponent.impl.weaTable.table.inte.AbstractWeaTable;

/**
 * @Author : Jaylan Zhou
 * @Date : 2019-12-25 16:38
 * @Version : 1.0
 */
public class DefaultWeaTableColumnFactory implements WeaTableColumnFactory {

    @Override
    public AbstractWeaTableColumn buildWeaTableColumn(AbstractWeaTable abstractWeaTable) {

        if (abstractWeaTable instanceof DocWeaTable){

            return new DocWeaTableColumn();

        }

        return new DefaultWeaTableColumn();

    }

}
