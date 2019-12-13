package com.weaverboot.http.httpClient.tools;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 *
 * 绕过 SSL 证书安全验证
 *
 * 作者HttpClient为：
 *
 * <dependency>
 *       <groupId>org.apache.httpcomponents</groupId>
 *       <artifactId>httpclient</artifactId>
 *       <version>4.5.6</version>
 * </dependency>
 *
 * @Author : Jaylan Zhou
 *
 * @Ref : https://blog.csdn.net/hu9645313573/article/details/75649741
 *
 */

public class HttpClientSSLProperties {

    /**
     *
     * 创建X509TrustManager，用于绕过SSL证书验证
     *
     * @return
     */
        private static X509TrustManager createX509TrustManager(){

        // 实现一个X509TrustManager接口，用于绕过验证
        X509TrustManager x509TrustManager = new X509TrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {

                return null;

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType){

            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType){

            }

        };

        return x509TrustManager;
    }


    /**
     *
     * SSL - 信任所有连接
     *
     * @return
     */

    public static SSLConnectionSocketFactory trustAllHttpsCertificates() {

        SSLConnectionSocketFactory socketFactory = null;

        TrustManager[] trustAllCerts = new TrustManager[1];

        TrustManager x509TrustManager = HttpClientSSLProperties.createX509TrustManager();

        trustAllCerts[0] = x509TrustManager;

        SSLContext sslContext = null;

        try {

            sslContext = SSLContext.getInstance("TLS");//sc = SSLContext.getInstance("TLS")

            sslContext.init(null, trustAllCerts, null);

            socketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

            //HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();

        } catch (KeyManagementException e) {

            e.printStackTrace();

        }

        return socketFactory;

    }

}
