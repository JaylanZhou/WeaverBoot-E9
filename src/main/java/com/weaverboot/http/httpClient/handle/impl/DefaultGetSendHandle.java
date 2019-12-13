package com.weaverboot.http.httpClient.handle.impl;

import com.weaverboot.http.httpClient.handle.inte.GetSendHandle;
import com.weaverboot.http.httpClient.tools.HttpClientBuildTools;
import com.weaverboot.http.httpClient.tools.HttpConnectionPoolTools;
import com.weaverboot.tools.enumTools.frame.EncodeCondition;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import weaver.general.BaseBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DefaultGetSendHandle implements GetSendHandle {

    private Map<String, String> param = null;

    private Map<String, String> header = null;

    private CloseableHttpResponse response = null;

    private BaseBean baseBean = new BaseBean();

    @Override
    public String sendGet(String url, EncodeCondition encodeCondition) throws Exception {

        try {

            HttpGet httpGet = HttpClientBuildTools.getHttpGet(url,param,header);

            response = HttpConnectionPoolTools.getHttpClient().execute(httpGet);

            String resultString = HttpClientBuildTools.getReturnMessage(response,encodeCondition);

            return resultString;

        }catch (Exception e){

            baseBean.writeLog("框架报错，原因为:" + e.getMessage());

            throw new RuntimeException(e.getMessage());

        }finally {

            try {

                HttpClientBuildTools.closeConnection(response,param,header);

            } catch (IOException e) {

                baseBean.writeLog("框架报错：关闭连接失败，原因为 " + e.getMessage());

                throw new RuntimeException(e.getMessage());

            }

        }

    }

    @Override
    public String sendGet(String url, EncodeCondition encodeCondition, String userName, String password) throws Exception {

        try {

            HttpGet httpGet = HttpClientBuildTools.getHttpGet(url,param,header);

            this.response = HttpConnectionPoolTools.getHttpClient(userName,password).execute(httpGet);

            String resultString = HttpClientBuildTools.getReturnMessage(this.response,encodeCondition);

            return resultString;

        }catch (Exception e){

            baseBean.writeLog("框架报错，原因为:" + e.getMessage());

            throw new RuntimeException(e.getMessage());

        }finally {

            try {

                HttpClientBuildTools.closeConnection(this.response,param,header);

            } catch (IOException e) {

                baseBean.writeLog("框架报错，原因为:" + e.getMessage());

                throw new RuntimeException(e.getMessage());

            }

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
