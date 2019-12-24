package com.weaverboot.weaResultMsg.impl.tableResult.inte;

import com.alibaba.fastjson.JSONObject;
import com.cloudstore.dev.api.util.Util_TableMap;
import com.cloudstore.eccom.constant.WeaResultDataType;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.weaComponent.impl.weaTable.table.inte.AbstractWeaTable;

/**
 * @Author : Jaylan Zhou
 * @Date : 2019-12-23 12:06
 * @Version : 1.0
 */
public abstract class AbstractWeaTableComponentReciveResultMsg extends AbstractWeaTableComponentResultMsg {

    @Override
    public String resultToSerialization() {

        Util_TableMap.setVal(this.getDatas(), super.toTableString());

        this.setType(WeaResultDataType.LIST_SPLIT_DATA.getTypeid());

        return JSONObject.toJSONString(this);

    }

    public abstract <T extends AbstractWeaTable>T parseAndGetWeaTable(Class<T> tClass) throws Exception;

}
