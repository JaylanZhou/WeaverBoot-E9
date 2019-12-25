package com.weaverboot.weaComponent.impl.weaTable.column.factory.inte;

import com.weaverboot.weaComponent.impl.weaTable.column.inte.AbstractWeaTableColumn;
import com.weaverboot.weaComponent.impl.weaTable.table.inte.AbstractWeaTable;

public interface WeaTableColumnFactory {

    AbstractWeaTableColumn buildWeaTableColumn(AbstractWeaTable abstractWeaTable);

}
