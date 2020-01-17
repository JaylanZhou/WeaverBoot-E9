package com.weaverboot.tools.logTools;

import com.weaverboot.frame.ioc.prop.WeaIocProperties;
import com.weaverboot.tools.baseTools.BaseTools;
import weaver.general.BaseBean;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-01-16 17:49
 * @Version : 1.0
 */
public class WeaLogConfig {

    public static String logType = "basebean";

    public static String loggerName;



    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }
}
