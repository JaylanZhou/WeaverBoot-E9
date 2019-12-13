package com.weaverboot.http.httpClient.handle.inte;

import com.weaverboot.tools.enumTools.frame.EncodeCondition;
import com.weaverboot.tools.enumTools.frame.http.HttpContentTypeCondition;

public interface OptionsSendHandle {

    String sendOptions(String url, String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition);

    String sendOptions(String url, String body, EncodeCondition encodeCondition, HttpContentTypeCondition httpContentTypeCondition, String userName, String password);

    void setParams(String name, String value);

    void setHeader(String name, String value);

    void cleanParams();

    void cleanHeader();

    void cleanAll();

}
