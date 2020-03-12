package com.weaverboot.weaResultMsg.impl.generalResult;

import com.alibaba.fastjson.JSONObject;
import com.weaverboot.weaResultMsg.inte.AbstractWeaGeneralResultMsg;
import weaver.hrm.User;

public class WeaGeneralResultMsg extends AbstractWeaGeneralResultMsg {

    @Override
    public String resultToSerialization() {

        return JSONObject.toJSONString(this);

    }

}
