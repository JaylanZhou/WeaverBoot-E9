package com.weaverboot.weaResultMsg.impl.tableResult.impl;

import com.weaverboot.tools.componentTools.table.WeaTableTools;
import com.weaverboot.weaComponent.impl.weaTable.table.inte.AbstractWeaTable;
import com.weaverboot.weaResultMsg.impl.tableResult.inte.AbstractWeaTableComponentReciveResultMsg;

/**
 *
 * table 默认接收类
 *
 * @Author : Jaylan Zhou
 * @Date : 2019-12-23 12:30
 * @Version : 1.0
 */
public class DefaultWeaTableComponentReciveResultMsg extends AbstractWeaTableComponentReciveResultMsg {


    @Override
    public <T extends AbstractWeaTable> T parseAndGetWeaTable(Class<T> tClass) throws Exception {

        String sessionKey = this.getDatas();

        T t = WeaTableTools.checkTableStringConfig(sessionKey,tClass);

        this.setWeaTable(t);

        return t;

    }
}
