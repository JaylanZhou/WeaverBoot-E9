package com.weaverboot.frame.ioc.container.replaceInfo.service.impl;

import com.weaverboot.frame.ioc.container.WeaIocContainer;
import com.weaverboot.frame.ioc.container.replaceInfo.model.WeaReplaceInfo;
import com.weaverboot.frame.ioc.container.replaceInfo.model.WeaReplaceInfoResultMsg;
import com.weaverboot.frame.ioc.container.replaceInfo.service.inte.WeaReplaceInfoService;
import com.weaverboot.frame.ioc.handler.replace.weaReplaceApiAdvice.WeaReplaceApiAdvice;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.logTools.LogTools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020/4/16 5:26 下午
 * @Version : 1.0
 */
public class WeaReplaceInfoServiceImpl implements WeaReplaceInfoService {

    private static final String BEFORE = "BEFORE";

    private static final String AFTER = "AFTER";

    @Override
    public WeaReplaceInfoResultMsg getReplaceInfo(WeaReplaceInfo weaReplaceInfo) {

        WeaReplaceInfoResultMsg weaReplaceInfoResultMsg = new WeaReplaceInfoResultMsg();

        try {

            String apiAddress = weaReplaceInfo.getApiAddress();

            Map<String,WeaReplaceInfo> result = new HashMap<>();

            if (BaseTools.notNullString(apiAddress)){

                if (WeaIocContainer.getReplaceBefore(apiAddress) != null || WeaIocContainer.getReplaceAfter(apiAddress) != null) {

                    WeaReplaceInfo weaReplaceInfoResult = new WeaReplaceInfo();

                    weaReplaceInfoResult.setApiAddress(apiAddress);

                    weaReplaceInfoResult.setBeforeReplaceInfo(WeaIocContainer.getReplaceBefore(apiAddress));

                    weaReplaceInfoResult.setAfterReplaceInfo(WeaIocContainer.getReplaceAfter(apiAddress));

                    result.put(apiAddress, weaReplaceInfoResult);

                }

            } else {

                this.setReplaceInfoToMap(result,WeaIocContainer.getReplaceBeforeApiMap(),BEFORE);

                this.setReplaceInfoToMap(result,WeaIocContainer.getReplaceAfterApiMap(),AFTER);

            }

            weaReplaceInfoResultMsg.setResultContent(result);

            weaReplaceInfoResultMsg.setApi_status(true);

            weaReplaceInfoResultMsg.setMsg("请求成功");

        } catch (Exception e){

            weaReplaceInfoResultMsg.setApi_status(false);

            weaReplaceInfoResultMsg.setMsg("请求错误，原因:" + e.getMessage());

        }

        return weaReplaceInfoResultMsg;

    }

    private void setReplaceInfoToMap(Map<String,WeaReplaceInfo> result, Map<String, List<WeaReplaceApiAdvice>> aimMap,String aimMapType){

        for (String apiAddress : aimMap.keySet()
        ) {

            List<WeaReplaceApiAdvice> adviceList = aimMap.get(apiAddress);

            WeaReplaceInfo weaReplaceInfo;

            if (result.containsKey(apiAddress)){

                weaReplaceInfo = result.get(apiAddress);

            } else {

                weaReplaceInfo = new WeaReplaceInfo();

                result.put(apiAddress,weaReplaceInfo);

            }

            if (aimMapType.equals(BEFORE)){

                weaReplaceInfo.setBeforeReplaceInfo(adviceList);

            } else if (aimMapType.equals(AFTER)){

                weaReplaceInfo.setAfterReplaceInfo(adviceList);

            } else {

                LogTools.warn("aimMapType的参数有问题:" + aimMapType);

            }

        }

    }

}
