package com.weaverboot.tools.enumTools.frame.http;

/**
 *
 * Http Content-type 枚举类
 *
 * @Author : Jaylan Zhou
 *
 */

public enum HttpContentTypeCondition {

    TEXT_HTML("text/html"),
    TEXT_PLAIN("text/plain"),
    TEXT_XML("text/xml"),
    IMAGE_GIF("image/gif"),
    IMAGE_JPEG("image/jpeg"),
    IMAGE_PNG("image/png"),
    XHTML("application/xhtml+xml"),
    APPLICATION_XML("application/xml"),
    ATOM_XML("application/atom+xml"),
    APPLICATION_JSON("application/json"),
    APPLICATION_PDF("application/pdf"),
    APPLICATION_MSWORD("application/msword"),
    APPLICATION_OCTET_STREAM("application/octet-stream"),
    APPLICATION_ENCTYPE_NORMAL("application/x-www-form-urlencoded"),
    MULTIPART_FORM_DATA("multipart/form-data");

    private String result;

    private HttpContentTypeCondition(String result){

        this.result = result;

    }

    @Override
    public String toString(){

        return this.result;

    }

}
