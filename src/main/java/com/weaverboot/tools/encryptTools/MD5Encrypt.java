package com.weaverboot.tools.encryptTools;

import com.weaverboot.tools.enumTools.frame.EncodeCondition;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 加密工具类
 *
 * @Author : Jaylan Zhou
 */

public class MD5Encrypt {


    private MD5Encrypt() {


    }

    /**
     * 32位MD5加密
     *
     * @param plainText 加密文本
     * @return
     */

    public static String get32bitMd5(String plainText) throws NoSuchAlgorithmException {


        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(plainText.getBytes());

        byte b[] = md.digest();

        int i;

        StringBuffer buf = new StringBuffer("");

        for (int offset = 0; offset < b.length; offset++) {

            i = b[offset];

            if (i < 0) {
                i += 256;
            }

            if (i < 16) {
                buf.append("0");
            }

            buf.append(Integer.toHexString(i));

        }
        // 32位加密

        return buf.toString().toUpperCase();


    }

    /**
     * 16位MD5加密
     *
     * @param plainText 加密文本
     * @return
     */

    public static String get16bitMd5(String plainText) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(plainText.getBytes());

        byte b[] = md.digest();

        int i;

        StringBuffer buf = new StringBuffer("");

        for (int offset = 0; offset < b.length; offset++) {

            i = b[offset];

            if (i < 0) {
                i += 256;
            }

            if (i < 16) {
                buf.append("0");
            }

            buf.append(Integer.toHexString(i));

        }

        // 16位的加密
        return buf.toString().substring(8, 24).toUpperCase();


    }

    /**
     * 32位MD5加密(自定义格式)
     *
     * @param plainText 加密文本
     * @param layout    数据格式
     * @return
     */

    public static String get32bitMd5(String plainText, EncodeCondition encodeCondition) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(plainText.getBytes(String.valueOf(encodeCondition)));

        byte b[] = md.digest();

        int i;

        StringBuffer buf = new StringBuffer("");

        for (int offset = 0; offset < b.length; offset++) {

            i = b[offset];

            if (i < 0) {
                i += 256;
            }

            if (i < 16) {
                buf.append("0");
            }

            buf.append(Integer.toHexString(i));

        }
        // 32位加密

        return buf.toString().toUpperCase();


    }

    /**
     * 16位MD5加密
     *
     * @param plainText 加密文本
     * @param encodeCondition 编码格式枚举类
     * @return
     */

    public static String get16bitMd5(String plainText, EncodeCondition encodeCondition) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(plainText.getBytes(String.valueOf(encodeCondition)));

        byte b[] = md.digest();

        int i;

        StringBuffer buf = new StringBuffer("");

        for (int offset = 0; offset < b.length; offset++) {

            i = b[offset];

            if (i < 0) {
                i += 256;
            }

            if (i < 16) {
                buf.append("0");
            }

            buf.append(Integer.toHexString(i));

        }

        // 16位的加密
        return buf.toString().substring(8, 24).toUpperCase();


    }

}
