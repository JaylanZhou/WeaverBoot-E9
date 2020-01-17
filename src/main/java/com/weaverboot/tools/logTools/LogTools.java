package com.weaverboot.tools.logTools;

import com.weaverboot.frame.ioc.prop.WeaIocProperties;
import com.weaverboot.tools.baseTools.BaseTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weaver.general.BaseBean;

/**
 * @Author : Jaylan Zhou
 * @Date : 2019-12-20 16:08
 * @Version : 1.0
 */
public class LogTools {

    private static BaseBean baseBean = new BaseBean();

    private static Logger logger = null;

    private final static String LOG4J_TYPE = "log4j";

    private final static String SOUT_TYPE = "systemout";
    
    private static String logType = "basebean";
    
    private static String loggerName;

    static {

        try {

            BaseBean baseBean = new BaseBean();

            String type = null;

            if (BaseTools.notNullString(type = WeaIocProperties.IOC_PROPERTIES.getProperty("weaverboot.logger.logType"))){

                logType = type;

            }

            loggerName = WeaIocProperties.IOC_PROPERTIES.getProperty("weaverboot.logger.loggerName");

            baseBean.writeLog("初始化日志操作类，当前操作类型为:" + logType);

            System.out.println("初始化日志操作类，当前操作类型为:" + logType);

            if (BaseTools.notNullString(loggerName)){

                baseBean.writeLog("logger名称为:" + loggerName);

                System.out.println("logger名称为:" + loggerName);

            }

        } catch (Exception e) {

            LogTools.error("创建日志配置类错误，原因为:" + e.getMessage());

        }

        if (BaseTools.notNullString(logType) && logType.equals(LOG4J_TYPE)){

            logger = LoggerFactory.getLogger(loggerName);

        }

    }

    public static void writeLog(String value){

        if (logger != null && logType.equals(LOG4J_TYPE)){

            logger.debug(value);

        } else if (logType.equals(SOUT_TYPE)){

            System.out.println(value);

        } else {

            baseBean.writeLog(value);

        }

    }

    public static void info(String value){

        if (logger != null && logType.equals(LOG4J_TYPE)){

            logger.info(value);

        } else if (logType.equals(SOUT_TYPE)){

            System.out.println(value);

        } else {

            baseBean.writeLog(value);

        }

    }

    public static void debug(String value){

        if (logger != null && logType.equals(LOG4J_TYPE)){

            logger.debug(value);

        } else if (logType.equals(SOUT_TYPE)){

            System.out.println(value);

        } else {

            baseBean.writeLog(value);

        }

    }

    public static void error(String value){

        if (logger != null && logType.equals(LOG4J_TYPE)){

            logger.error(value);

        } else if (logType.equals(SOUT_TYPE)){

            System.out.println(value);

        } else {

            baseBean.writeLog(value);

        }

    }

    public static void warn(String value){

        if (logger != null && logType.equals(LOG4J_TYPE)){

            logger.warn(value);

        } else if (logType.equals(SOUT_TYPE)){

            System.out.println(value);

        } else {

            baseBean.writeLog(value);

        }

    }

}
