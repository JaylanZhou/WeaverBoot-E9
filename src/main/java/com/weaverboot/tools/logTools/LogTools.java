package com.weaverboot.tools.logTools;

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

    private final static Logger logger = null;

    public static void writeLog(String value){

        if (logger != null){

            logger.debug(value);

        } else {

            baseBean.writeLog(value);

        }

    }

}
