package com.weaverboot.frame.ioc.container.replaceInfo.model;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020/4/16 5:51 下午
 * @Version : 1.0
 */
public class WeaReplaceInfoResultMsg {

    private Map<String,WeaReplaceInfo> resultContent;

    private boolean api_status;

    private String msg;

    public Map<String, WeaReplaceInfo> getResultContent() {
        return resultContent;
    }

    public void setResultContent(Map<String, WeaReplaceInfo> resultContent) {
        this.resultContent = resultContent;
    }

    public boolean getApi_status() {
        return api_status;
    }

    public void setApi_status(boolean api_status) {
        this.api_status = api_status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String resultToSerialization() {
        return JSONObject.toJSONString(this);
    }

}
