package com.weaverboot.http.httpClient.tools;

import com.weaverboot.tools.enumTools.frame.EncodeCondition;
import com.weaverboot.tools.enumTools.frame.http.HttpContentTypeCondition;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import weaver.general.BaseBean;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * HttpClient 封装属性类
 *
 * @Author : Jaylan Zhou
 *
 */

@Deprecated
public class HttpClientProperties extends BaseBean {

    private CloseableHttpResponse response = null;

    private Map<String, String> param = null;

    private Map<String, String> header = null;


    /**
     *
     * 关闭HttpClient的一系列操作
     *
     * @throws IOException
     */

    protected void closeConnection() throws IOException {

        if(this.response != null){

            this.response.close();

            this.response = null;

        }

        if(this.param != null){

            this.param = null;

        }

        if(this.header != null){

            this.header = null;

        }

    }

    /**
     *
     * 存储传输参数
     *
     * @param param 传输参数Map
     */

    public void setParam(Map<String, String> param){

        this.param = param;

    }

    /**
     *
     * 存储头部数据
     *
     * @param header 头部数据Map
     */

    public void setHeader(Map<String, String> header){

        this.header = header;

    }

    /**
     *
     * 建立GET请求连接
     *
     * @param url 地址
     * @return
     * @throws URISyntaxException
     */

    private URI getUri(String url) throws URISyntaxException {

        URIBuilder builder = new URIBuilder(url);

        if(this.param != null){

            for (String key : this.param.keySet()) {

                builder.addParameter(key, this.param.get(key));

            }

        }

        URI uri = builder.build();

        return uri;

    }

    /**
     *
     * 获取url
     *
     * @param url 原先的url地址
     * @return
     */
    private String setParamToHttp(String url){

        if(param != null){

            StringBuilder urlBuilder = new StringBuilder(url);

            int i = 0;

            for (String key : this.param.keySet()
            ) {

                if (i == 0){

                    urlBuilder.append("?").append(key).append("=").append(this.param.get(key));

                } else {

                    urlBuilder.append("&").append(key).append("=").append(this.param.get(key));

                }

                i++;

            }

            url = urlBuilder.toString();

        }

        return url;

    }

    public HttpRequestBase setHeaderToHttp(HttpRequestBase httpRequestBase){

        if(header != null) {

            for (String key : this.header.keySet()) {

                httpRequestBase.setHeader(key, this.header.get(key));

            }

        }

        return httpRequestBase;

    }

    /**
     *
     * 建立POST表单
     *
     * @return
     * @throws UnsupportedEncodingException
     */

    private UrlEncodedFormEntity getUrlEncodedFormEntity() throws UnsupportedEncodingException {

        if (this.param != null) {

            List<NameValuePair> paramList = new ArrayList();

            for (String key : this.param.keySet()) {

                paramList.add(new BasicNameValuePair(key, this.param.get(key)));

            }

            // 模拟表单
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);

            return entity;

        }else {

            return null;

        }

    }

    /**
     *
     * 处理返回信息
     *
     * @param response
     * @return
     * @throws IOException
     */

    private String getReturnMessage(CloseableHttpResponse response,EncodeCondition encodeCondition) throws IOException {

        String resultString = "";

        if(response.getStatusLine().getStatusCode() == 200){

            resultString = EntityUtils.toString(response.getEntity(), encodeCondition.toString());

            this.writeLog("返回成功:\n" + resultString);


        }else if(response.getStatusLine().getStatusCode() == 400){

            resultString = EntityUtils.toString(response.getEntity(), encodeCondition.toString());

            this.writeLog("400错误:\n" + resultString);


        }else if(response.getStatusLine().getStatusCode() == 404){

            resultString = EntityUtils.toString(response.getEntity(), encodeCondition.toString());

            this.writeLog("404错误:\n" + resultString);

        }else if(response.getStatusLine().getStatusCode() == 500){

            resultString = EntityUtils.toString(response.getEntity(), encodeCondition.toString());

            this.writeLog("500错误:\n" + resultString);

        }else{

            if (response.getEntity() != null) {

                resultString = EntityUtils.toString(response.getEntity(), encodeCondition.toString());

            }

            this.writeLog("返回信息:\n" + resultString);

            resultString = String.valueOf(response.getStatusLine().getStatusCode());

        }

        return resultString;

    }

    /**
     *
     * GET方式发送信息
     *
     * @param url 发送地址
     * @param encodeCondition 编码格式枚举类
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */

    public String sendGet(String url,EncodeCondition encodeCondition) throws IOException {

        try {

            HttpGet httpGet = this.getHttpGet(url);

            this.response = HttpConnectionPoolTools.getHttpClient().execute(httpGet);

            String resultString = this.getReturnMessage(this.response,encodeCondition);

            return resultString;

        }catch (Exception e){

            this.writeLog("框架报错，原因为:" + e.getMessage());

            throw new RuntimeException(e.getMessage());

        }finally {

            this.closeConnection();

        }

    }

    /**
     *
     * GET方式发送信息
     *
     * @param url 发送地址
     * @param encodeCondition 编码格式枚举类
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */

    public String sendGet(String url,EncodeCondition encodeCondition,String userName,String password) throws IOException {

        try {

            HttpGet httpGet = this.getHttpGet(url);

            this.response = HttpConnectionPoolTools.getHttpClient(userName,password).execute(httpGet);

            String resultString = this.getReturnMessage(this.response,encodeCondition);

            return resultString;

        }catch (Exception e){

            this.writeLog("框架报错，原因为:" + e.getMessage());

            throw new RuntimeException(e.getMessage());

        }finally {

            this.closeConnection();

        }

    }

    /**
     *
     * POST方式发送信息(RESTful形式)
     *
     * @param url 发送地址
     * @param encodeCondition 编码格式枚举类
     * @return
     * @throws IOException
     */

    public String sendPost(String url,EncodeCondition encodeCondition) throws IOException {


        try {

            HttpPost httpPost = new HttpPost(url);

            httpPost = (HttpPost) this.setHeaderToHttp(httpPost);

            httpPost.setEntity(this.getUrlEncodedFormEntity());

            httpPost = (HttpPost) HttpConnectionPoolTools.setRequestConfig(httpPost);

            this.response = HttpConnectionPoolTools.getHttpClient().execute(httpPost);

            String resultString = this.getReturnMessage(response,encodeCondition);

            return resultString;

        }catch (Exception e){

            this.writeLog("框架报错，原因为:" + e.getMessage());

            throw new RuntimeException(e.getMessage());

        }finally {

            this.closeConnection();

        }

    }

    /**
     *
     * 获得StringEntity
     *
     * @param body
     * @param encodeCondition
     * @param httpContentTypeCondition
     * @return
     * @throws IOException
     */

    private StringEntity getStringEntity(String body,EncodeCondition encodeCondition,HttpContentTypeCondition httpContentTypeCondition) throws IOException {

        if(body == null){

            body = "";

        }

        if (encodeCondition == null){

            throw new RuntimeException("编码格式不能为空");

        }

        if (encodeCondition == null){

            throw new RuntimeException("报文格式不能为空");

        }

        StringEntity stringEntity = new StringEntity(body,String.valueOf(encodeCondition));

        stringEntity.setContentEncoding(String.valueOf(encodeCondition));

        stringEntity.setContentType(String.valueOf(httpContentTypeCondition));

        return stringEntity;

    }

    /**
     *
     * 获取HttpGet
     *
     * @param url 发送地址
     * @return
     * @throws URISyntaxException
     */

    public HttpGet getHttpGet(String url) throws URISyntaxException {

        url = this.setParamToHttp(url);

        URI uri = this.getUri(url);

        HttpGet httpGet = new HttpGet(uri);

        if(this.header != null) {

            for (String key : this.header.keySet()) {

                httpGet.setHeader(key, this.header.get(key));

            }

        }

        httpGet = (HttpGet) HttpConnectionPoolTools.setRequestConfig(httpGet);

        return httpGet;

    }

    /**
     *
     * 获取HttpPost
     *
     * @param url 发送地址
     * @param body 发送报文
     * @param encodeCondition 编码格式枚举类
     * @param httpContentTypeCondition 报文格式枚举类
     * @return
     * @throws IOException
     */

    private HttpPost getHttpPost(String url, String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition) throws IOException {

        url = this.setParamToHttp(url);

        StringEntity stringEntity = this.getStringEntity(body,encodeCondition,httpContentTypeCondition);

        HttpPost httpPost = new HttpPost(url);

        httpPost = (HttpPost) HttpConnectionPoolTools.setRequestConfig(httpPost);

        httpPost.setEntity(stringEntity);

        httpPost = (HttpPost) setHeaderToHttp(httpPost);

        return httpPost;

    }

    /**
     *
     * 获取HttpPatch
     *
     * @param url 发送地址
     * @param body 发送报文
     * @param encodeCondition 编码格式枚举类
     * @param httpContentTypeCondition 报文格式枚举类
     * @return
     * @throws IOException
     */

    private HttpPatch getHttpPatch(String url, String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition) throws IOException {

        url = this.setParamToHttp(url);

        StringEntity stringEntity = this.getStringEntity(body,encodeCondition,httpContentTypeCondition);

        HttpPatch httpPatch = new HttpPatch(url);

        httpPatch = (HttpPatch) HttpConnectionPoolTools.setRequestConfig(httpPatch);

        httpPatch.setEntity(stringEntity);

        httpPatch = (HttpPatch) setHeaderToHttp(httpPatch);

        return httpPatch;

    }

    /**
     *
     * 获取HttpPut
     *
     * @param url 发送地址
     * @param body 发送报文
     * @param encodeCondition 编码格式枚举类
     * @param httpContentTypeCondition 报文格式枚举类
     * @return
     * @throws IOException
     */

    private HttpPut getHttpPut(String url, String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition) throws IOException {

        url = this.setParamToHttp(url);

        StringEntity stringEntity = this.getStringEntity(body,encodeCondition,httpContentTypeCondition);

        HttpPut httpPut = new HttpPut(url);

        httpPut = (HttpPut) HttpConnectionPoolTools.setRequestConfig(httpPut);

        httpPut.setEntity(stringEntity);

        httpPut = (HttpPut) setHeaderToHttp(httpPut);

        return httpPut;

    }

    /**
     *
     * POST方式发送信息(发送body，免授权)
     *
     * @param url 发送地址
     * @param body 报文字符
     * @param encodeCondition 编码格式枚举类
     * @param httpContentTypeCondition 报文格式枚举类
     * @return
     * @throws IOException
     */

    public String sendPost(String url, String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition) throws IOException {

        HttpPost httpPost = this.getHttpPost(url,body,encodeCondition,httpContentTypeCondition);

        this.response = HttpConnectionPoolTools.getHttpClient().execute(httpPost);

        String resultString = this.getReturnMessage(response,encodeCondition);

        this.closeConnection();

        return resultString;

    }

    /**
     *
     * POST方式发送信息(发送body，需要授权)
     *
     * @param url 发送地址
     * @param body 报文字符串
     * @param encodeCondition 编码格式枚举类
     * @param httpContentTypeCondition 报文格式枚举类
     * @param userName basic验证 - 用户名
     * @param password basic验证 - 密码
     * @return
     * @throws IOException
     */

    public String sendPost(String url, String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition ,String userName,String password) throws IOException {

        HttpPost httpPost = this.getHttpPost(url,body,encodeCondition,httpContentTypeCondition);

        this.response = HttpConnectionPoolTools.getHttpClient(userName,password).execute(httpPost);

        String resultString = this.getReturnMessage(response,encodeCondition);

        this.closeConnection();

        return resultString;

    }

    /**
     *
     * PATCH方式发送信息(发送body，免授权)
     *
     * @param url 发送地址
     * @param body 报文字符
     * @param encodeCondition 编码格式枚举类
     * @param httpContentTypeCondition 报文格式枚举类
     * @return
     * @throws IOException
     */

    public String sendPatch(String url, String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition) throws IOException {

        HttpPatch httpPatch = this.getHttpPatch(url,body,encodeCondition,httpContentTypeCondition);

        this.response = HttpConnectionPoolTools.getHttpClient().execute(httpPatch);

        String resultString = this.getReturnMessage(response,encodeCondition);

        this.closeConnection();

        return resultString;

    }

    /**
     *
     * PATCH方式发送信息(发送body，需要授权)
     *
     * @param url 发送地址
     * @param body 报文字符串
     * @param encodeCondition 编码格式枚举类
     * @param httpContentTypeCondition 报文格式枚举类
     * @param userName basic验证 - 用户名
     * @param password basic验证 - 密码
     * @return
     * @throws IOException
     */

    public String sendPatch(String url, String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition ,String userName,String password) throws IOException {

        HttpPatch httpPatch = this.getHttpPatch(url,body,encodeCondition,httpContentTypeCondition);

        this.response = HttpConnectionPoolTools.getHttpClient(userName,password).execute(httpPatch);

        String resultString = this.getReturnMessage(response,encodeCondition);

        this.closeConnection();

        return resultString;

    }

    /**
     *
     * PUT方式发送信息(发送body，免授权)
     *
     * @param url 发送地址
     * @param body 报文字符
     * @param encodeCondition 编码格式枚举类
     * @param httpContentTypeCondition 报文格式枚举类
     * @return
     * @throws IOException
     */

    public String sendPut(String url, String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition) throws IOException {

        HttpPut httpPut = this.getHttpPut(url,body,encodeCondition,httpContentTypeCondition);

        this.response = HttpConnectionPoolTools.getHttpClient().execute(httpPut);

        String resultString = this.getReturnMessage(response,encodeCondition);

        this.closeConnection();

        return resultString;

    }

    /**
     *
     * PUT方式发送信息(发送body，需要授权)
     *
     * @param url 发送地址
     * @param body 报文字符串
     * @param encodeCondition 编码格式枚举类
     * @param httpContentTypeCondition 报文格式枚举类
     * @param userName basic验证 - 用户名
     * @param password basic验证 - 密码
     * @return
     * @throws IOException
     */

    public String sendPut(String url, String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition ,String userName,String password) throws IOException {

        HttpPut httpPut = this.getHttpPut(url,body,encodeCondition,httpContentTypeCondition);

        this.response = HttpConnectionPoolTools.getHttpClient(userName,password).execute(httpPut);

        String resultString = this.getReturnMessage(response,encodeCondition);

        this.closeConnection();

        return resultString;

    }

}
