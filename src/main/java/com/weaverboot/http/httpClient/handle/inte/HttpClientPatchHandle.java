package com.weaverboot.http.httpClient.handle.inte;

import com.weaverboot.tools.enumTools.frame.EncodeCondition;
import com.weaverboot.tools.enumTools.frame.http.HttpContentTypeCondition;

import java.io.IOException;


@Deprecated
public interface HttpClientPatchHandle {

    /**
     *
     * 以自定义格式，Patch方法发送报文
     *
     * @param url 发送地址
     * @param body 报文内容
     * @param httpContentTypeCondition 数据格式枚举类
     * @param encodeCondition 编码格式枚举类
     * @return 服务端返回信息
     * @throws IOException
     */

    String sendPatch(String url, String body, HttpContentTypeCondition httpContentTypeCondition, EncodeCondition encodeCondition) throws IOException;

    /**
     *
     * 以自定义格式，Patch方法发送报文(basic验证)
     *
     * @param url 发送地址
     * @param body 报文内容
     * @param httpContentTypeCondition 数据格式枚举类
     * @param encodeCondition 编码格式枚举类
     * @param userName basic验证用户名
     * @param password basic验证密码
     * @return
     * @throws IOException
     */

    String sendPatch(String url, String body, HttpContentTypeCondition httpContentTypeCondition, EncodeCondition encodeCondition, String userName, String password) throws IOException;

}
