package com.weaverboot.frame.ioc.beans.bean.definition.utils;

public class WeaIocFormatUtils {

    private WeaIocFormatUtils(){}

    public static String formatScanPackage(String scanPackage){

        return scanPackage.replaceAll("\\.","/");

    }

    public static String formatClassName(String faName,String basePath){

        if (!faName.startsWith("/")){

            faName = "/" + faName;

        }

        return faName.replaceAll("\\\\","/").replaceAll("\\.class","").replaceAll(basePath,"").replaceAll("/","\\.");

    }

    public static String formatFilePath(String fileName){

        if (!fileName.startsWith("/")){

            fileName = "/" + fileName;

        }

        return fileName.replaceAll("\\\\","/");

    }

}
