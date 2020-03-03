package com.weaverboot.weaComponent.impl.weaTable.column.comparator;

import com.weaverboot.frame.ioc.handler.replace.weaReplaceApiAdvice.WeaReplaceApiAdvice;
import com.weaverboot.weaComponent.impl.weaTable.column.inte.AbstractWeaTableColumn;

import java.util.Comparator;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-01-16 16:05
 * @Version : 1.0
 */
public class WeaTableColumnComparator implements Comparator<AbstractWeaTableColumn> {

    @Override
    public int compare(AbstractWeaTableColumn o1, AbstractWeaTableColumn o2) {

        if (o1.getOrder() > o2.getOrder()){

            return 1;

        } else if (o1.getOrder() < o2.getOrder()){

            return -1;

        }

        return 0;

    }
}
