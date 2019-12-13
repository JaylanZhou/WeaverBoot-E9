package com.weaverboot.http.httpClient.handle.inte;

import com.weaverboot.tools.enumTools.frame.EncodeCondition;

import java.io.IOException;

public interface GetSendHandle {

    /**
     *
     * 以自定义格式，Get方法发送报文
     *
     * @param url 发送地址
     * @param encodeCondition 编码格式枚举类
     * @return 服务端返回信息
     * @throws IOException
     */

    String sendGet(String url, EncodeCondition encodeCondition) throws Exception;

    /**
     *
     * 以自定义格式，Get方法发送报文(basic验证)
     *
     * @param url 发送地址
     * @param encodeCondition 编码格式枚举类
     * @param userName basic验证用户名
     * @param password basic验证密码
     * @return
     * @throws IOException
     */

    String sendGet(String url, EncodeCondition encodeCondition, String userName, String password) throws Exception;

    void setParams(String name, String value);

    void setHeader(String name, String value);

    void cleanParams();

    void cleanHeader();

    void cleanAll();

}
