package com.weaverboot.http.httpClient.handle.inte;

import java.util.Map;

@Deprecated
public interface HttpClientTools {

    void setParams(Map<String, String> params);

    void setHeader(Map<String, String> header);

    void cleanParams();

    void cleanHeader();

    void cleanAll();

}
