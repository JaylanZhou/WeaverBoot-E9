package com.weaverboot.weaComponent.impl.weaTable.column.factory.inte;

import com.weaverboot.weaComponent.impl.weaTable.column.inte.AbstractWeaTableColumn;
import com.weaverboot.weaComponent.impl.weaTable.table.inte.AbstractWeaTable;

/**
 *
 * WeaTableColumn 工厂类
 *
 * 此类可根据 AbstractWeaTable 类型生产对应类型的 AbstractWeaTableColumn
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */

public interface WeaTableColumnFactory {

    /**
     *
     * 根据 AbstractWeaTable 的具体类型生产 AbstractWeaTableColumn
     *
     * @param abstractWeaTable 表格类
     * @return 字段具体实现类
     *
     */

    AbstractWeaTableColumn buildWeaTableColumn(AbstractWeaTable abstractWeaTable);

}
