package com.weaverboot.http.httpClient.tools;

import com.weaverboot.tools.enumTools.frame.EncodeCondition;
import com.weaverboot.tools.enumTools.frame.http.HttpContentTypeCondition;
import com.weaverboot.tools.enumTools.frame.http.HttpMethodCondition;
import com.weaverboot.tools.logTools.LogTools;
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

public class HttpClientBuildTools {

    private HttpClientBuildTools(){



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

    public static StringEntity getStringEntity(String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition) {

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
     * 建立POST表单
     *
     * @return
     * @throws UnsupportedEncodingException
     */

    public static UrlEncodedFormEntity getUrlEncodedFormEntity(Map<String,String> param) throws UnsupportedEncodingException {

        if (param != null) {

            List<NameValuePair> paramList = new ArrayList();

            for (String key : param.keySet()) {

                paramList.add(new BasicNameValuePair(key, param.get(key)));

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
     * 建立GET请求连接
     *
     * @param url 地址
     * @return
     * @throws URISyntaxException
     */

    public static URI getUri(String url,Map<String,String> param) throws URISyntaxException {

        URIBuilder builder = new URIBuilder(url);

        if(param != null){

            for (String key : param.keySet()) {

                builder.addParameter(key, param.get(key));

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
    public static String setParamToHttp(String url,Map<String,String> param){

        if(param != null){

            StringBuilder urlBuilder = new StringBuilder(url);

            int i = 0;

            for (String key : param.keySet()
            ) {

                if (i == 0){

                    urlBuilder.append("?").append(key).append("=").append(param.get(key));

                } else {

                    urlBuilder.append("&").append(key).append("=").append(param.get(key));

                }

                i++;

            }

            url = urlBuilder.toString();

        }

        return url;

    }

    public static HttpRequestBase setHeaderToHttp(HttpRequestBase httpRequestBase, Map<String,String> header){

        if(header != null) {

            for (String key : header.keySet()) {

                httpRequestBase.setHeader(key, header.get(key));

            }

        }

        return httpRequestBase;

    }

    /**
     *
     * 关闭HttpClient的一系列操作
     *
     * @throws IOException
     */

    public static void closeConnection(CloseableHttpResponse response, Map<String, String> param, Map<String, String> header) throws IOException {

        if(response != null){

            response.close();

            response = null;

        }

        if(param != null){

            param = null;

        }

        if(header != null){

            header = null;

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

    public static String getReturnMessage(CloseableHttpResponse response,EncodeCondition encodeCondition) throws IOException {

        String resultString = "";

        BaseBean baseBean = new BaseBean();

        if(response.getStatusLine().getStatusCode() == 200){

            resultString = EntityUtils.toString(response.getEntity(), encodeCondition.toString());

            LogTools.info("返回成功:\n" + resultString);


        }else if(response.getStatusLine().getStatusCode() == 400){

            resultString = EntityUtils.toString(response.getEntity(), encodeCondition.toString());

            LogTools.error("400错误:\n" + resultString);


        }else if(response.getStatusLine().getStatusCode() == 404){

            resultString = EntityUtils.toString(response.getEntity(), encodeCondition.toString());

            LogTools.error("404错误:\n" + resultString);

        }else if(response.getStatusLine().getStatusCode() == 500){

            resultString = EntityUtils.toString(response.getEntity(), encodeCondition.toString());

            LogTools.error("500错误:\n" + resultString);

        }else{

            if (response.getEntity() != null) {

                resultString = EntityUtils.toString(response.getEntity(), encodeCondition.toString());

            }

            LogTools.error("返回信息:\n" + resultString);

            resultString = String.valueOf(response.getStatusLine().getStatusCode());

        }

        return resultString;

    }

    /**
     *
     * 获取HttpGet
     *
     * @param url 发送地址
     * @return
     * @throws URISyntaxException
     */

    public static HttpGet getHttpGet(String url, Map<String,String> param, Map<String,String> header) throws URISyntaxException {

        URI uri = getUri(url,param);

        HttpGet httpGet = new HttpGet(uri);

        if(header != null) {

            for (String key : header.keySet()) {

                httpGet.setHeader(key, header.get(key));

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

    public static HttpPost getHttpPost(String url, String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition, Map<String,String> param, Map<String,String> header) {

        url = setParamToHttp(url,param);

        StringEntity stringEntity = getStringEntity(body,encodeCondition,httpContentTypeCondition);

        HttpPost httpPost = new HttpPost(url);

        httpPost = (HttpPost) HttpConnectionPoolTools.setRequestConfig(httpPost);

        httpPost.setEntity(stringEntity);

        httpPost = (HttpPost) setHeaderToHttp(httpPost,header);

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

    public static HttpPatch getHttpPatch(String url, String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition, Map<String,String> param, Map<String,String> header) throws IOException {

        url = setParamToHttp(url,param);

        StringEntity stringEntity = getStringEntity(body,encodeCondition,httpContentTypeCondition);

        HttpPatch httpPatch = new HttpPatch(url);

        httpPatch = (HttpPatch) HttpConnectionPoolTools.setRequestConfig(httpPatch);

        httpPatch.setEntity(stringEntity);

        httpPatch = (HttpPatch) setHeaderToHttp(httpPatch,header);

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

    public static HttpPut getHttpPut(String url, String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition, Map<String, String> param, Map<String, String> header) throws IOException {

        url = setParamToHttp(url,param);

        StringEntity stringEntity = getStringEntity(body,encodeCondition,httpContentTypeCondition);

        HttpPut httpPut = new HttpPut(url);

        httpPut = (HttpPut) HttpConnectionPoolTools.setRequestConfig(httpPut);

        httpPut.setEntity(stringEntity);

        httpPut = (HttpPut) setHeaderToHttp(httpPut,header);

        return httpPut;

    }

    /**
     *
     * 获取HttpDelete
     *
     * @param url 发送地址
     * @param body 发送报文
     * @param encodeCondition 编码格式枚举类
     * @param httpContentTypeCondition 报文格式枚举类
     * @return
     * @throws IOException
     */

    public static HttpMethodWithEntity getHttpDelete(String url, String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition, Map<String, String> param, Map<String, String> header) throws IOException {

        url = setParamToHttp(url,param);

        StringEntity stringEntity = getStringEntity(body,encodeCondition,httpContentTypeCondition);

        HttpMethodWithEntity httpDelete = new HttpMethodWithEntity(url, HttpMethodCondition.DELETE);

        httpDelete = (HttpMethodWithEntity) HttpConnectionPoolTools.setRequestConfig(httpDelete);

        httpDelete.setEntity(stringEntity);

        httpDelete = (HttpMethodWithEntity) setHeaderToHttp(httpDelete,header);

        return httpDelete;

    }

    /**
     *
     * 获取HttpOptions
     *
     * @param url 发送地址
     * @param body 发送报文
     * @param encodeCondition 编码格式枚举类
     * @param httpContentTypeCondition 报文格式枚举类
     * @return
     * @throws IOException
     */

    public static HttpMethodWithEntity getHttpOptions(String url, String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition, Map<String, String> param, Map<String, String> header) throws IOException {

        url = setParamToHttp(url,param);

        StringEntity stringEntity = getStringEntity(body,encodeCondition,httpContentTypeCondition);

        HttpMethodWithEntity httpOption = new HttpMethodWithEntity(url, HttpMethodCondition.OPTIONS);

        httpOption = (HttpMethodWithEntity) HttpConnectionPoolTools.setRequestConfig(httpOption);

        httpOption.setEntity(stringEntity);

        httpOption = (HttpMethodWithEntity) setHeaderToHttp(httpOption,header);

        return httpOption;

    }

}
