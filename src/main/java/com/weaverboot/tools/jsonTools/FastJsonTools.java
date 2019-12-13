package com.weaverboot.tools.jsonTools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import weaver.general.BaseBean;

@Deprecated
public class FastJsonTools {

    private FastJsonTools(){



    }

    public final static JSONArray string2JSONArray(String message){

        try {

            JSONArray jsonArray = JSON.parseArray(message);

            return jsonArray;

        }catch (Exception e){

            BaseBean baseBean = new BaseBean();

            baseBean.writeLog("字符串转换为JSONArray出错，原因为：" + e.getMessage());

            throw new RuntimeException(e.getMessage());

        }

    }

    public final static JSONObject string2JSONObject(String message){

        try {

            JSONObject jsonObject = JSON.parseObject(message);

            return jsonObject;

        }catch (Exception e){

            BaseBean baseBean = new BaseBean();

            baseBean.writeLog("字符串转换为JSONObject出错，原因为：" + e.getMessage());

            throw new RuntimeException(e.getMessage());

        }

    }



}
