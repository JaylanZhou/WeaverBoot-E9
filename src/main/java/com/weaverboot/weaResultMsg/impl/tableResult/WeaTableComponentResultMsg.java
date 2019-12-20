package com.weaverboot.weaResultMsg.impl.tableResult;

import com.alibaba.fastjson.JSONObject;
import com.weaverboot.weaResultMsg.inte.AbstractWeaComponentResultMsg;

public class WeaTableComponentResultMsg extends AbstractWeaComponentResultMsg {

    @Override
    public String resultToSerialization() {

        return JSONObject.toJSONString(this);

    }


}
