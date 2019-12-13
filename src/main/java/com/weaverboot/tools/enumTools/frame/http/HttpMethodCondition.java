package com.weaverboot.tools.enumTools.frame.http;

/**
 *
 * Http 请求方式枚举类
 *
 * @Author : Jaylan Zhou
 *
 */

public enum HttpMethodCondition {

    GET("GET"),
    HEAD("HEAD"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    CONNECT("CONNECT"),
    OPTIONS("OPTIONS"),
    TRACE("TRACE"),
    PATCH("PATCH");

    private String result;

    private HttpMethodCondition(String result){

        this.result = result;

    }

    @Override
    public String toString(){

        return this.result;

    }

}
