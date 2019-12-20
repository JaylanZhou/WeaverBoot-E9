package com.weaverboot.http.httpClient.tools;

import com.weaverboot.tools.logTools.LogTools;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import weaver.general.BaseBean;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * HttpClient 连接池管理类
 *
 * @Author : Jaylan Zhou
 *
 * @Ref : https://www.jianshu.com/p/c852cbcf3d68
 *
 */

public class HttpConnectionPoolTools {


    private static CloseableHttpClient httpClient; // 发送请求的客户端单例

    private static PoolingHttpClientConnectionManager manager; //连接池管理类

    private static ScheduledExecutorService monitorExecutor;

    private final static Object syncLock = new Object(); // 相当于线程锁,用于线程安全

    /**
     * 对http请求进行基本设置
     *
     * @param httpRequestBase http请求
     *
     *
     */

    public static HttpRequestBase setRequestConfig(HttpRequestBase httpRequestBase){

        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(HttpClientConfig.CONNECT_TIMEOUT)
                .setConnectTimeout(HttpClientConfig.CONNECT_TIMEOUT)
                .setSocketTimeout(HttpClientConfig.SOCKET_TIMEOUT).build();

        httpRequestBase.setConfig(requestConfig);

        return httpRequestBase;

    }

    /**
     *
     * 获取连接（无需授权）
     *
     * @return
     */

    public static CloseableHttpClient getHttpClient(){

        final BaseBean baseBean = new BaseBean();

        if (httpClient == null){

            //多线程下多个线程同时调用getHttpClient容易导致重复创建httpClient对象的问题,所以加上了同步锁
            synchronized (syncLock){

                if (httpClient == null){

                    httpClient = createHttpClient();

                    //开启监控线程,对异常和空闲线程进行关闭
                    monitorExecutor = Executors.newScheduledThreadPool(1);

                    monitorExecutor.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {

                            //关闭异常连接
                            manager.closeExpiredConnections();

                            //关闭5s空闲的连接
                            manager.closeIdleConnections(5000, TimeUnit.MILLISECONDS);

                        }

                    }, HttpClientConfig.TIMETASK_INITDELAY_TIME, HttpClientConfig.TIMETASK_INTERVAL_TIME, TimeUnit.MILLISECONDS);

                }

            }

        }

        return httpClient;

    }

    /**
     *
     * 获取连接（用户授权）
     *
     * @param userName
     * @param password
     * @return
     */

    public static CloseableHttpClient getHttpClient(String userName,String password){

        final BaseBean baseBean = new BaseBean();

        if (httpClient == null){

            //多线程下多个线程同时调用getHttpClient容易导致重复创建httpClient对象的问题,所以加上了同步锁
            synchronized (syncLock){

                if (httpClient == null){

                    httpClient = createHttpClient(userName,password);

                    //开启监控线程,对异常和空闲线程进行关闭
                    monitorExecutor = Executors.newScheduledThreadPool(1);

                    monitorExecutor.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {

                            //关闭异常连接
                            manager.closeExpiredConnections();

                            //关闭5s空闲的连接
                            manager.closeIdleConnections(5000, TimeUnit.MILLISECONDS);

                            LogTools.writeLog("关闭空闲连接");

                        }

                    }, HttpClientConfig.TIMETASK_INITDELAY_TIME, HttpClientConfig.TIMETASK_INTERVAL_TIME, TimeUnit.MILLISECONDS);

                }

            }

        }

        return httpClient;

    }

    /**
     *
     * 连接属性初始化
     *
     * @return
     */

    private static HttpRequestRetryHandler buildInit() {

        ConnectionSocketFactory plainSocketFactory = PlainConnectionSocketFactory.getSocketFactory();

        LayeredConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactory.getSocketFactory();

        Registry<ConnectionSocketFactory> registry;

        if (HttpClientConfig.IGNORE_SSL_VERIFY == true){

            registry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", plainSocketFactory)
                .register("https", HttpClientSSLProperties.trustAllHttpsCertificates()).build();

        } else {

            registry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", plainSocketFactory)
                .register("https", sslSocketFactory).build();

        }

        manager = new PoolingHttpClientConnectionManager(registry);

        //设置连接参数
        manager.setMaxTotal(HttpClientConfig.MAX_CONN); // 最大连接数

        manager.setDefaultMaxPerRoute(HttpClientConfig.MAX_ROUTE); // 路由最大连接数

        //HttpHost httpHost = new HttpHost(host, port);

        //manager.setMaxPerRoute(new HttpRoute(httpHost), MAX_ROUTE);

        //请求失败时,进行请求重试
        HttpRequestRetryHandler handler = new HttpRequestRetryHandler() {

            @Override
            public boolean retryRequest(IOException e, int i, HttpContext httpContext) {

                BaseBean baseBean = new BaseBean();

                if (i > 3){

                    //重试超过3次,放弃请求
                    LogTools.writeLog("连接请求失败超过3次，放弃请求");

                    return false;

                }

                if (e instanceof NoHttpResponseException){

                    //服务器没有响应,可能是服务器断开了连接,应该重试
                    LogTools.writeLog("连接失败，服务器无响应");

                    return true;

                }

                if (e instanceof SSLHandshakeException){

                    // SSL握手异常
                    LogTools.writeLog("连接失败，SSL握手异常");

                    return false;

                }

                if (e instanceof InterruptedIOException){

                    //超时
                    LogTools.writeLog("连接失败，请求超时");

                    return false;

                }

                if (e instanceof UnknownHostException){

                    // 服务器不可达
                    LogTools.writeLog("连接失败，无法找到服务器地址");

                    return false;

                }

                if (e instanceof ConnectTimeoutException){

                    // 连接超时
                    LogTools.writeLog("连接超时");

                    return false;

                }

                if (e instanceof SSLException){

                    LogTools.writeLog("连接失败，SSL异常");

                    return false;

                }

                HttpClientContext context = HttpClientContext.adapt(httpContext);

                HttpRequest request = context.getRequest();

                if (!(request instanceof HttpEntityEnclosingRequest)){

                    //如果请求不是关闭连接的请求
                    return true;

                }

                return false;
            }

        };

        return handler;

    }

    /**
     *
     * 构建httpclient实例（无需授权）
     *
     * @return
     */

    public static CloseableHttpClient createHttpClient(){

        HttpRequestRetryHandler handler = HttpConnectionPoolTools.buildInit();

        CloseableHttpClient client = HttpClients.custom().setConnectionManager(manager).setRetryHandler(handler).build();

        return client;
    }

    /**
     *
     * 构建httpclient实例（用户授权）
     *
     * @return
     */

    public static CloseableHttpClient createHttpClient(String userName,String password){

        HttpRequestRetryHandler handler = HttpConnectionPoolTools.buildInit();

        CredentialsProvider provider = new BasicCredentialsProvider();

        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(userName, password);

        provider.setCredentials(AuthScope.ANY, credentials);

        CloseableHttpClient client = HttpClients.custom().setConnectionManager(manager).setRetryHandler(handler).setDefaultCredentialsProvider(provider).build();

        return client;
    }


    /**
     *
     * 关闭连接池
     *
     */

    public static void closeConnectionPool(){

        try {

            httpClient.close();

            manager.close();

            monitorExecutor.shutdown();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
