package com.weaverboot.frame.ioc.handler.wired.anno.value.utils;

import com.weaverboot.frame.ioc.prop.WeaIocProperties;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.logTools.LogTools;
import weaver.general.BaseBean;

import java.io.*;

public class WeaIocValueTransDataUtil {

    private WeaIocValueTransDataUtil(){}

    public static boolean checkIsSpEL(String value){

        if (value.startsWith("${") && value.endsWith("}")){

            return true;

        }

        return false;

    }

    public static String getPropertiesValue(String value) throws IOException {

        BaseBean baseBean = new BaseBean();

        value = value.substring(value.indexOf("${") + 2,value.lastIndexOf("}"));

        String valueTrans = WeaIocProperties.IOC_PROPERTIES.getProperty(value);

        if (!BaseTools.notNullString(valueTrans)){

            LogTools.writeLog("未找到对应属性" + value);

            return null;

        } else {

            String result = new String(WeaIocProperties.IOC_PROPERTIES.getProperty(value).getBytes(), "UTF-8");

            return result;
        }



    }

    public static Object transFieldDataToBasicType(String value, Class cl){

        String className = cl.getName();

        if (className.equals(Integer.class.getName()) || className.equals(int.class.getName())){

            return Integer.valueOf(value);

        } else if (className.equals(Boolean.class.getName()) || className.equals(boolean.class.getName())){

            return Boolean.valueOf(value);

        } else if (className.equals(Float.class.getName()) || className.equals(float.class.getName())){

            return Float.valueOf(value);

        } else if (className.equals(Long.class.getName()) || className.equals(long.class.getName())){

            return Long.valueOf(value);

        } else if (className.equals(Byte.class.getName()) || className.equals(byte.class.getName())){

            return Byte.valueOf(value);

        } else if (className.equals(Short.class.getName()) || className.equals(short.class.getName())){

            return Short.valueOf(value);

        } else if (className.equals(Double.class.getName()) || className.equals(double.class.getName())){

            return Double.valueOf(value);

        } else if (className.equals(Character.class.getName()) || className.equals(char.class.getName())){

            return value.toCharArray();

        } else {

            return value;

        }

    }

}
