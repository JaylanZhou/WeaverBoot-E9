package com.weaverboot.tools.parseTools;

import com.weaverboot.frame.model.BaseModel;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.logTools.LogTools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModelTransTableParserTool {

    private ModelTransTableParserTool(){}

    private final static String DOLLAR_PREFIX = "${";

    private final static String POINT_PREFIX = "#{";

    private final static String GET = "get";

    public static String parseRegex(String text, Map<String,String> conditionMap, BaseModel baseModel, Class baseModelClass){

        text = parseDollar(text,conditionMap,baseModel,baseModelClass);

        text = parsePoint(text,conditionMap,baseModel,baseModelClass);

        return text;

    }

    public static String parseDollar(String text, Map<String,String> conditionMap, BaseModel baseModel, Class baseModelClass){

        boolean hasMap = conditionMap != null;

        Pattern regexWithDollar = Pattern.compile("\\$\\{(.*?)\\}");

        int countDollar = text.split("\\$\\{").length - 1;

        if (hasMap) {

            text = parseHasMap(text,countDollar,regexWithDollar,DOLLAR_PREFIX,conditionMap,baseModel,baseModelClass);

        } else {

            text = parseNoMap(text,countDollar,regexWithDollar,DOLLAR_PREFIX,baseModel,baseModelClass);

        }

        return text;

    }

    public static String parsePoint(String text, Map<String,String> conditionMap, BaseModel baseModel, Class baseModelClass){

        boolean hasMap = conditionMap != null;

        Pattern regexWithDollar = Pattern.compile("\\#\\{(.*?)\\}");

        int countDollar = text.split("\\#\\{").length - 1;

        if (hasMap) {

            text = parseHasMap(text,countDollar,regexWithDollar,POINT_PREFIX,conditionMap,baseModel,baseModelClass);

        } else {

            text = parseNoMap(text,countDollar,regexWithDollar,POINT_PREFIX,baseModel,baseModelClass);

        }

        return text;

    }

    private static String parseHasMap(String text,int count,Pattern regex,String prefix,Map<String,String> conditionMap,BaseModel baseModel,Class baseModelClass){


        for (int i = 0; i < count; i++) {

            Matcher matcher = regex.matcher(text);

            boolean flag = matcher.find();

            if (!flag) {

                break;

            }

            String key = matcher.group(0);

            String keyFormat = key.substring(2,key.length() - 1);

            if (conditionMap.containsKey(keyFormat)){

                text = replaceRegex(text,key,conditionMap.get(keyFormat),prefix);

            } else {

                text = parseNoMapLogic(text,baseModel,baseModelClass,keyFormat,key,prefix);

            }

        }

        return text;

    }

    private static String parseNoMap(String text,int count,Pattern regex,String prefix,BaseModel baseModel,Class baseModelClass){

        for (int i = 0; i < count; i++) {

            Matcher matcher = regex.matcher(text);

            boolean flag = matcher.find();

            if (!flag) {

                break;

            }

            String key = matcher.group(0);

            String keyFormat = key.substring(2,key.length() - 1);

            text = parseNoMapLogic(text,baseModel,baseModelClass,keyFormat,key,prefix);

        }

        return text;

    }


    private static String parseNoMapLogic(String text,BaseModel baseModel,Class baseModelClass,String keyFormat,String key,String prefix){

        try {

            Method method = baseModelClass.getMethod(GET + BaseTools.toUpperCaseFirstOne(keyFormat));

            if (method != null){

                text = replaceRegex(text,key, (String) method.invoke(baseModel),prefix);

            }

        } catch (NoSuchMethodException e) {

            LogTools.error("转换占位符失败，未找到方法");

        } catch (IllegalAccessException e) {

            LogTools.error("转换占位符失败，方法参数不符合");

        } catch (InvocationTargetException e) {

            LogTools.error("转换占位符失败，方法执行错误，原因为:" + e.getMessage());

        }

        return text;

    }

    private static String replaceRegex(String text,String key,String value,String prefix){

        if (prefix.equals(DOLLAR_PREFIX)){

            text = text.replace(key,value);

        } else if (prefix.equals(POINT_PREFIX)){

            StringBuilder valueBuilder = new StringBuilder("'").append(value).append("'");

            text = text.replace(key,valueBuilder.toString());

        }

        return text;

    }

}
