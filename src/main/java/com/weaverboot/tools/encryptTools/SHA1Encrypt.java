package com.weaverboot.tools.encryptTools;

import com.weaverboot.tools.enumTools.frame.EncodeCondition;

import java.security.MessageDigest;

public class SHA1Encrypt {

    private SHA1Encrypt(){



    }

    public static String getSHA1(String str, EncodeCondition encodeCondition) {

        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };

        try {

            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");

            mdTemp.update(str.getBytes(encodeCondition.toString()));

            byte[] md = mdTemp.digest();

            int j = md.length;

            char buf[] = new char[j * 2];

            int k = 0;

            for (int i = 0; i < j; i++) {

                byte byte0 = md[i];

                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];

                buf[k++] = hexDigits[byte0 & 0xf];

            }

            return new String(buf);

        } catch (Exception e) {

            return null;

        }

    }

}
