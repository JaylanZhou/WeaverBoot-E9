package com.weaverboot.tools.enumTools.frame;

/**
 *
 * 编码格式枚举类
 *
 * @Author : Jaylan Zhou
 *
 */

public enum EncodeCondition {

    UTF_8("UTF-8"),
    GBK("GBK"),
    BIG5("BIG5"),
    GB2312("GB2312"),
    ASCII("ASCII"),
    ISO_8859_1("ISO-8859-1"),
    GB18030("GB18030"),
    UCS("UCS"),
    UNICODE("UNICODE");

    private String result;

    private EncodeCondition(String result){

        this.result = result;

    }

    @Override
    public String toString(){

        return this.result;

    }

}
