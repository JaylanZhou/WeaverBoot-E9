package com.weaverboot.http.httpClient.handle.impl;

import com.weaverboot.http.httpClient.handle.inte.PutSendHandle;
import com.weaverboot.http.httpClient.tools.HttpClientBuildTools;
import com.weaverboot.http.httpClient.tools.HttpConnectionPoolTools;
import com.weaverboot.tools.enumTools.frame.EncodeCondition;
import com.weaverboot.tools.enumTools.frame.http.HttpContentTypeCondition;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import weaver.general.BaseBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DefaultPutSendHandle implements PutSendHandle {

    private Map<String, String> param = null;

    private Map<String, String> header = null;

    private CloseableHttpResponse response = null;

    private BaseBean baseBean = new BaseBean();


    @Override
    public String sendPut(String url, String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition) {

        HttpPut httpPut = null;

        try {

            httpPut = HttpClientBuildTools.getHttpPut(url,body,encodeCondition,httpContentTypeCondition,param,header);

            this.response = HttpConnectionPoolTools.getHttpClient().execute(httpPut);

            String resultString = HttpClientBuildTools.getReturnMessage(response,encodeCondition);

            HttpClientBuildTools.closeConnection(this.response,param,header);

            return resultString;

        } catch (IOException e) {

            baseBean.writeLog("框架报错，原因为:" + e.getMessage());

            throw new RuntimeException(e.getMessage());

        }

    }

    @Override
    public String sendPut(String url, String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition, String userName, String password) {

        HttpPut httpPut = null;

        try {

            httpPut = HttpClientBuildTools.getHttpPut(url,body,encodeCondition,httpContentTypeCondition,param,header);

            this.response = HttpConnectionPoolTools.getHttpClient(userName,password).execute(httpPut);

            String resultString = HttpClientBuildTools.getReturnMessage(response,encodeCondition);

            HttpClientBuildTools.closeConnection(this.response,param,header);

            return resultString;

        } catch (IOException e) {

            baseBean.writeLog("框架报错，原因为:" + e.getMessage());

            throw new RuntimeException(e.getMessage());

        }

    }

    @Override
    public void setParams(String name,String value) {

        if(this.param == null){

            this.param = new HashMap<String, String>();

        }

        this.param.put(name,value);

    }

    @Override
    public void setHeader(String name,String value) {

        if(this.header == null){

            this.header = new HashMap<String, String>();

        }

        this.header.put(name,value);

    }

    @Override
    public void cleanParams() {

        this.param = null;

    }

    @Override
    public void cleanHeader() {

        this.header = null;

    }

    @Override
    public void cleanAll() {

        this.param = null;

        this.header = null;

    }

}
