package com.weaverboot.http.httpClient.handle.impl;

import com.weaverboot.http.httpClient.handle.inte.*;
import com.weaverboot.http.httpClient.tools.HttpClientProperties;
import com.weaverboot.tools.enumTools.frame.EncodeCondition;
import com.weaverboot.tools.enumTools.frame.http.HttpContentTypeCondition;

import java.io.IOException;
import java.util.Map;

/**
 *
 * HttpClient 默认方法接口 - 默认实现类
 *
 * @Author : Jaylan Zhou
 *
 */

@Deprecated
public class DefaultHttpClientHandle implements HttpClientPostHandle, HttpClientPatchHandle, HttpClientGetHandle, HttpClientPutHandle,HttpClientTools {

    private HttpClientProperties httpClientProperties = new HttpClientProperties();


    /**
     *
     * 以自定义格式，Post方法发送报文
     *
     * @param url 发送地址
     * @param body 报文内容
     * @param httpContentTypeCondition 报文格式枚举类
     * @param encodeCondition 编码格式枚举类
     * @return 服务端返回信息
     * @throws IOException
     */

    @Override
    public String sendPost(String url, String body, HttpContentTypeCondition httpContentTypeCondition, EncodeCondition encodeCondition) throws IOException {

        String result = httpClientProperties.sendPost(url,body,encodeCondition,httpContentTypeCondition);

        return result;

    }

    /**
     *
     * 以自定义格式，Post方法发送报文(basic验证)
     *
     * @param url 发送地址
     * @param body 报文内容
     * @param httpContentTypeCondition 报文格式枚举类
     * @param encodeCondition 编码格式枚举类
     * @param userName basic验证用户名
     * @param password basic验证密码
     * @return
     * @throws IOException
     */

    @Override
    public String sendPost(String url, String body, HttpContentTypeCondition httpContentTypeCondition, EncodeCondition encodeCondition, String userName, String password) throws IOException {

        String result = httpClientProperties.sendPost(url,body,encodeCondition,httpContentTypeCondition,userName,password);

        return result;

    }

    @Override
    public String sendGet(String url,EncodeCondition encodeCondition) throws IOException {

        String result = httpClientProperties.sendGet(url,encodeCondition);

        return result;

    }

    @Override
    public String sendGet(String url, EncodeCondition encodeCondition, String userName, String password) throws IOException {

        String result = httpClientProperties.sendGet(url,encodeCondition,userName,password);

        return result;

    }

    @Override
    public String sendPatch(String url, String body, HttpContentTypeCondition httpContentTypeCondition, EncodeCondition encodeCondition) throws IOException {

        String result = httpClientProperties.sendPatch(url,body,encodeCondition,httpContentTypeCondition);

        return result;

    }

    @Override
    public String sendPatch(String url, String body, HttpContentTypeCondition httpContentTypeCondition, EncodeCondition encodeCondition, String userName, String password) throws IOException {

        String result = httpClientProperties.sendPatch(url,body,encodeCondition,httpContentTypeCondition,userName,password);

        return result;
    }

    @Override
    public String sendPut(String url, String body, HttpContentTypeCondition httpContentTypeCondition, EncodeCondition encodeCondition) throws IOException {

        String result = httpClientProperties.sendPut(url,body,encodeCondition,httpContentTypeCondition);

        return result;

    }

    @Override
    public String sendPut(String url, String body, HttpContentTypeCondition httpContentTypeCondition, EncodeCondition encodeCondition, String userName, String password) throws IOException {

        String result = httpClientProperties.sendPut(url,body,encodeCondition,httpContentTypeCondition,userName,password);

        return result;

    }

    @Override
    public void setParams(Map<String,String> params){

        this.httpClientProperties.setParam(params);

    }

    @Override
    public void setHeader(Map<String,String> header){

        this.httpClientProperties.setHeader(header);

    }

    @Override
    public void cleanParams(){

        this.httpClientProperties.setParam(null);

    }

    @Override
    public void cleanHeader(){

        this.httpClientProperties.setHeader(null);

    }

    @Override
    public void cleanAll(){

        this.httpClientProperties.setHeader(null);

        this.httpClientProperties.setParam(null);

    }

}
