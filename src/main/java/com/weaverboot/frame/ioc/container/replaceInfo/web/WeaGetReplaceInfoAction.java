package com.weaverboot.frame.ioc.container.replaceInfo.web;

import com.weaverboot.frame.ioc.anno.parameterAnno.WeaParamBean;
import com.weaverboot.frame.ioc.container.replaceInfo.model.WeaReplaceInfo;
import com.weaverboot.frame.ioc.container.replaceInfo.model.WeaReplaceInfoResultMsg;
import com.weaverboot.frame.ioc.container.replaceInfo.service.impl.WeaReplaceInfoServiceImpl;
import com.weaverboot.frame.ioc.container.replaceInfo.service.inte.WeaReplaceInfoService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020/4/16 5:10 下午
 * @Version : 1.0
 */
public class WeaGetReplaceInfoAction {

    private WeaReplaceInfoService weaReplaceInfoService = new WeaReplaceInfoServiceImpl();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getInfoList")
    public String replaceInfo(@WeaParamBean WeaReplaceInfo weaReplaceInfo){

        WeaReplaceInfoResultMsg weaReplaceInfoResultMsg = this.weaReplaceInfoService.getReplaceInfo(weaReplaceInfo);

        return weaReplaceInfoResultMsg.resultToSerialization();

    }


}
