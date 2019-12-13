package com.weaverboot.tools.encryptTools;

import com.weaverboot.tools.enumTools.frame.EncodeCondition;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * HMAC - SHA256 加解密工具类
 *
 * @Author : Jaylan Zhou
 *
 */

public class HMACSHA256Encrypt {


    private HMACSHA256Encrypt(){



    }

    /**
     *
     * String字符串加密
     *
     * 加密方式 : HMAC-SH256
     *
     * @param message 加密信息
     * @param secretKey 加密秘钥
     * @return
     */
    public static String sha256_HMAC(String message, String secretKey) {

        String hash = "";

        try {

            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");

            SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");

            sha256_HMAC.init(secret_key);

            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());

            hash = byteArrayToHexString(bytes);

        } catch (Exception e) {

            throw new RuntimeException("HMAC-SH256 加密失败，原因为: " + e.getMessage());

        }

        return hash;

    }

    /**
     *
     * String字符串加密（自定义数据格式）
     *
     * 加密方式 : HMAC-SH256
     *
     * @param message 加密信息
     * @param secretKey 加密秘钥
     * @param encodeCondition 编码格式枚举类
     * @return
     */
    public static String sha256_HMAC(String message, String secretKey, EncodeCondition encodeCondition) {

        String hash = "";

        try {

            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");

            SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(String.valueOf(encodeCondition)), "HmacSHA256");

            sha256_HMAC.init(secret_key);

            byte[] bytes = sha256_HMAC.doFinal(message.getBytes(String.valueOf(encodeCondition)));

            hash = byteArrayToHexString(bytes);

        } catch (Exception e) {

            throw new RuntimeException("HMAC-SH256 加密失败，原因为: " + e.getMessage());

        }

        return hash;

    }

    /**
     *
     * 加密后的数组转换成字符串
     *
     * @param encryptByte 加密后的数组
     * @return 数组转换的字符串
     *
     */
    private static String byteArrayToHexString(byte[] encryptByte){

        try {

            StringBuilder hs = new StringBuilder();

            String stmp;

            for (int n = 0; encryptByte != null && n < encryptByte.length; n++) {

                stmp = Integer.toHexString(encryptByte[n] & 0XFF);

                if (stmp.length() == 1) {

                    hs.append('0');

                }

                hs.append(stmp);

            }

            return hs.toString().toLowerCase();

        }catch (Exception e){

            throw new RuntimeException("加密数组转化为字符串失败，原因为 : " + e.getMessage());

        }

    }




}
