package com.weaverboot.http.httpClient.tools;

import com.weaverboot.tools.baseTools.BaseTools;
import weaver.general.BaseBean;

/**
 *
 * HttpClient 配置属性
 *
 * @Author : Jaylan
 *
 */

public class HttpClientConfig {

    private HttpClientConfig(){



    }

    public static int CONNECT_TIMEOUT = 100000; //连接超时时间（毫秒），默认为100秒

    public static int SOCKET_TIMEOUT = 100000; // Socket 超时时间 （毫秒）默认为100秒

    public static int MAX_CONN = 10; // 最大连接数，默认为10

    public static int MAX_PRE_ROUTE = 10; //最大预备路由数，默认为10

    public static int MAX_ROUTE = 10; //最大路由数，默认为10

    public static int MAX_LEISURE_TIME = 5000; // 最大空闲时间（毫秒），默认为5秒

    public static int TIMETASK_INITDELAY_TIME = 1000; // 监控进程初始化延迟时间（毫秒），默认为1秒

    public static int TIMETASK_INTERVAL_TIME = 2000; // 监控进程执行间隔时间（毫秒），默认为2秒

    public static boolean IGNORE_SSL_VERIFY = true; // 是否绕过SSL验证，是为true，否为false

    static {

        BaseBean baseBean = new BaseBean();

        String connect_timeout = baseBean.getPropValue("weaverboot","http.connect_timeout");

        String socket_timeout = baseBean.getPropValue("weaverboot","http.socket_timeout");

        String max_conn = baseBean.getPropValue("weaverboot","http.max_conn");

        String max_pre_route = baseBean.getPropValue("weaverboot","http.max_pre_route");

        String max_route = baseBean.getPropValue("weaverboot","http.max_route");

        String max_leisure_time = baseBean.getPropValue("weaverboot","http.max_leisure_time");

        String timetask_initdelay_time = baseBean.getPropValue("weaverboot","http.timetask_initdelay_time");

        String timetask_interval_time = baseBean.getPropValue("weaverboot","http.timetask_interval_time");

        String ignore_ssl_verify = baseBean.getPropValue("weaverboot","ignore_ssl_verify");

        if(BaseTools.notNullString(connect_timeout)){

            CONNECT_TIMEOUT = Integer.parseInt(connect_timeout);

        }

        if(BaseTools.notNullString(socket_timeout)){

            SOCKET_TIMEOUT = Integer.parseInt(socket_timeout);

        }

        if(BaseTools.notNullString(max_conn)){

            MAX_CONN = Integer.parseInt(max_conn);

        }

        if(BaseTools.notNullString(max_pre_route)){

            MAX_PRE_ROUTE = Integer.parseInt(max_pre_route);

        }

        if(BaseTools.notNullString(max_route)){

            MAX_ROUTE = Integer.parseInt(max_route);

        }

        if(BaseTools.notNullString(max_leisure_time)){

            MAX_LEISURE_TIME = Integer.parseInt(max_leisure_time);

        }

        if(BaseTools.notNullString(timetask_initdelay_time)){

            TIMETASK_INITDELAY_TIME = Integer.parseInt(timetask_initdelay_time);

        }

        if(BaseTools.notNullString(timetask_interval_time)){

            TIMETASK_INTERVAL_TIME = Integer.parseInt(timetask_interval_time);

        }

        if(BaseTools.notNullString(ignore_ssl_verify)){

            if (ignore_ssl_verify.equals("true")) {

                IGNORE_SSL_VERIFY = true;

            } else if(ignore_ssl_verify.equals("false")){

                IGNORE_SSL_VERIFY = false;

            }

        }


    }

}
