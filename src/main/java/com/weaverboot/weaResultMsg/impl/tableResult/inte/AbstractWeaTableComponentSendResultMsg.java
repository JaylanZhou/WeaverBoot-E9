package com.weaverboot.weaResultMsg.impl.tableResult.inte;

import com.alibaba.fastjson.JSONObject;
import com.cloudstore.dev.api.util.Util_TableMap;
import com.cloudstore.eccom.constant.WeaResultDataType;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.weaResultMsg.impl.tableResult.inte.AbstractWeaTableComponentResultMsg;

/**
 * @Author : Jaylan Zhou
 * @Date : 2019-12-23 12:06
 * @Version : 1.0
 */
public abstract class AbstractWeaTableComponentSendResultMsg extends AbstractWeaTableComponentResultMsg {

    @Override
    public String resultToSerialization() {

        String sessionKey = BaseTools.createUUID();

        Util_TableMap.setVal(sessionKey, this.toTableString());

        this.setType(WeaResultDataType.LIST_SPLIT_DATA.getTypeid());

        this.setDatas(sessionKey);

        return JSONObject.toJSONString(this);
    }
}
