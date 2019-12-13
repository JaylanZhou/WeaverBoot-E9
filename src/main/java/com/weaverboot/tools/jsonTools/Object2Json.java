package com.weaverboot.tools.jsonTools;

import com.weaverboot.tools.baseTools.BaseTools;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * 将 对象 和 List 转换为 JSONObject 或 JSONArray
 *
 * @Author : Jaylan Zhou
 *
 */

@Deprecated
public class Object2Json {

    /**
     *
     * 将list转成JSONArray
     *
     * @param list 需要转换的list
     * @return
     * @throws Exception
     */

    public static JSONArray modelList2JsonArray(List list) throws Exception {

        JSONArray jsonArray = new JSONArray();

        for(int i = 0; i < list.size(); i++){

            Object object = list.get(i);

            Field[] fields = object.getClass().getDeclaredFields();

            JSONObject jsonObject = new JSONObject();

            for (Field field : fields
            ) {

                jsonObject.put(field.getName(),getMethodName(field,object).invoke(object));

            }

            jsonArray.put(jsonObject);

        }

        return jsonArray;

    }

    /**
     *
     * 将对象转换成JSONObject
     *
     * @param object 需要转换的object
     * @return
     * @throws Exception
     */

    public static JSONObject model2JsonObject(Object object) throws Exception {

        JSONObject jsonObject = new JSONObject();

        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields
        ) {

            jsonObject.put(field.getName(),getMethodName(field,object).invoke(object));

        }

        return jsonObject;

    }

    private static Method getMethodName(Field column, Object object) throws Exception{

        return object.getClass().getMethod("get" + BaseTools.toUpperCaseFirstOne(column.getName()));

    }

}
