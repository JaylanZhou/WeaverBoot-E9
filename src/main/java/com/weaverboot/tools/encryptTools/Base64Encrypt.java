package com.weaverboot.tools.encryptTools;

import com.weaverboot.tools.enumTools.frame.EncodeCondition;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 *
 * BASE64 - 加解密工具类
 *
 * @Author : Jaylan Zhou
 *
 */

public class Base64Encrypt {

    private Base64Encrypt(){



    }

    /**
     *
     * BASE64 文本加密
     *
     * @param text
     * @return
     */

    public static String encodeForBASE64(String text) {

        BASE64Encoder encoder = new BASE64Encoder();

        byte[] textByte = text.getBytes();

        String encodedText = encoder.encode(textByte).replaceAll("[\\s*\t\n\r]", "");

        return encodedText;

    }

    /**
     *
     * BASE64 文本解密
     *
     * @param text 解密文本
     * @return
     */

    public static String decodeBASE64(String text) throws IOException {

        BASE64Decoder decoder = new BASE64Decoder();

            byte[] b = decoder.decodeBuffer(text);

            return new String(b);

    }

    /**
     *
     * BASE64 文本加密(自定义格式)
     *
     * @param text 加密文本
     * @param encodeCondition 编码格式枚举类
     * @return
     */

    public static String encodeForBASE64(String text,EncodeCondition encodeCondition) throws UnsupportedEncodingException {

        BASE64Encoder encoder = new BASE64Encoder();

        byte[] textByte = text.getBytes(String.valueOf(encodeCondition));

        String encodedText = encoder.encode(textByte).replaceAll("[\\s*\t\n\r]", "");

        return encodedText;

    }

    /**
     *
     * BASE64 文本解密（自定义格式）
     *
     * @param text 解密文本
     * @param encodeCondition 编码格式枚举类
     * @return
     */

    public static String decodeBASE64(String text, EncodeCondition encodeCondition) throws IOException {

        BASE64Decoder decoder = new BASE64Decoder();

        byte[] result = decoder.decodeBuffer(text);

        return new String(result,String.valueOf(encodeCondition));

    }

}
