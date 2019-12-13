package com.weaverboot.http.httpClient.tools;

import com.weaverboot.tools.enumTools.frame.http.HttpMethodCondition;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 此类专治各种不支持 Entity 的请求方法的疑难杂症
 *
 * @Author : Jaylan Zhou
 *
 */
public class HttpMethodWithEntity extends HttpEntityEnclosingRequestBase {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

    }

    public HttpMethodCondition httpMethodCondition;

    @Override
    public String getMethod() {

        Map<String,Object> map = new HashMap<String, Object>();

        return httpMethodCondition.toString();

    }

    public HttpMethodWithEntity(HttpMethodCondition httpMethodCondition){

        this.httpMethodCondition = httpMethodCondition;

    }

    public HttpMethodWithEntity(final String uri,HttpMethodCondition httpMethodCondition) {

        super();

        this.httpMethodCondition = httpMethodCondition;

        setURI(URI.create(uri));

    }

    public HttpMethodWithEntity(final URI uri,HttpMethodCondition httpMethodCondition) {

        super();

        this.httpMethodCondition = httpMethodCondition;

        setURI(uri);

    }

}
