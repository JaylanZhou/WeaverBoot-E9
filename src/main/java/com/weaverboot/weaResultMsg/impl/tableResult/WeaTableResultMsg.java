package com.weaverboot.weaResultMsg.impl.tableResult;

import com.alibaba.fastjson.JSONObject;
import com.weaverboot.weaResultMsg.inte.AbstractWeaResultMsg;

public class WeaTableResultMsg extends AbstractWeaResultMsg {

    @Override
    public String resultToSerialization() {

        return JSONObject.toJSONString(this);

    }


}
