package com.weaverboot.frame.ioc.container.replaceInfo.service.inte;

import com.weaverboot.frame.ioc.container.replaceInfo.model.WeaReplaceInfo;
import com.weaverboot.frame.ioc.container.replaceInfo.model.WeaReplaceInfoResultMsg;

import java.util.Map;

public interface WeaReplaceInfoService {

    WeaReplaceInfoResultMsg getReplaceInfo(WeaReplaceInfo weaReplaceInfo);

}
