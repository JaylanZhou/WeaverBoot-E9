package com.weaverboot.http.httpClient.handle.impl;

import com.weaverboot.http.httpClient.handle.inte.DeleteSendHandle;
import com.weaverboot.http.httpClient.tools.HttpClientBuildTools;
import com.weaverboot.http.httpClient.tools.HttpConnectionPoolTools;
import com.weaverboot.http.httpClient.tools.HttpMethodWithEntity;
import com.weaverboot.http.resultMsg.WeaHttpResultMsg;
import com.weaverboot.tools.enumTools.frame.EncodeCondition;
import com.weaverboot.tools.enumTools.frame.http.HttpContentTypeCondition;
import com.weaverboot.tools.logTools.LogTools;
import org.apache.http.client.methods.CloseableHttpResponse;
import weaver.general.BaseBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DefaultDeleteSendHandle implements DeleteSendHandle {

    private Map<String, String> param = null;

    private Map<String, String> header = null;

    private CloseableHttpResponse response = null;

    private BaseBean baseBean = new BaseBean();

    @Override
    public WeaHttpResultMsg sendDelete(String url, String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition) {

        HttpMethodWithEntity httpDelete = null;

        try {

            httpDelete = HttpClientBuildTools.getHttpDelete(url,body,encodeCondition,httpContentTypeCondition,param,header);

            this.response = HttpConnectionPoolTools.getHttpClient().execute(httpDelete);

            WeaHttpResultMsg weaHttpResultMsg = HttpClientBuildTools.getReturnMessage(response,encodeCondition);

            HttpClientBuildTools.closeConnection(this.response,param,header);

            return weaHttpResultMsg;

        } catch (IOException e) {

            LogTools.error("框架报错，原因为:" + e.getMessage());

            throw new RuntimeException(e.getMessage());

        }

    }

    @Override
    public WeaHttpResultMsg sendDelete(String url, String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition, String userName, String password) {

        HttpMethodWithEntity httpDelete = null;

        try {

            httpDelete = HttpClientBuildTools.getHttpDelete(url,body,encodeCondition,httpContentTypeCondition,param,header);

            this.response = HttpConnectionPoolTools.getHttpClient(userName,password).execute(httpDelete);

            WeaHttpResultMsg weaHttpResultMsg = HttpClientBuildTools.getReturnMessage(response,encodeCondition);

            HttpClientBuildTools.closeConnection(this.response,param,header);

            return weaHttpResultMsg;

        } catch (IOException e) {

            LogTools.error("框架报错，原因为:" + e.getMessage());

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
