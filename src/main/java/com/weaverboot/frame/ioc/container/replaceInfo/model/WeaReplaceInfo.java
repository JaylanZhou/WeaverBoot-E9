package com.weaverboot.frame.ioc.container.replaceInfo.model;

import com.weaverboot.frame.ioc.handler.replace.weaReplaceApiAdvice.WeaReplaceApiAdvice;

import java.util.List;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020/4/16 5:19 下午
 * @Version : 1.0
 */
public class WeaReplaceInfo {

    private String apiAddress;

    private List<WeaReplaceApiAdvice> beforeReplaceInfo;

    private List<WeaReplaceApiAdvice> afterReplaceInfo;

    public String getApiAddress() {
        return apiAddress;
    }

    public void setApiAddress(String apiAddress) {
        this.apiAddress = apiAddress;
    }

    public List<WeaReplaceApiAdvice> getBeforeReplaceInfo() {
        return beforeReplaceInfo;
    }

    public void setBeforeReplaceInfo(List<WeaReplaceApiAdvice> beforeReplaceInfo) {
        this.beforeReplaceInfo = beforeReplaceInfo;
    }

    public List<WeaReplaceApiAdvice> getAfterReplaceInfo() {
        return afterReplaceInfo;
    }

    public void setAfterReplaceInfo(List<WeaReplaceApiAdvice> afterReplaceInfo) {
        this.afterReplaceInfo = afterReplaceInfo;
    }
}
