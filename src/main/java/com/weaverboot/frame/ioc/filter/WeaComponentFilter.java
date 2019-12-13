package com.weaverboot.frame.ioc.filter;

import com.engine.common.util.ParamUtil;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.replace.weaReplaceParam.impl.WeaAfterReplaceParam;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.replace.weaReplaceParam.impl.WeaBeforeReplaceParam;
import com.weaverboot.frame.ioc.beans.bean.definition.utils.WeaIocReplaceUriUtils;
import com.weaverboot.frame.ioc.filter.wrapper.ComponentResponseWrapper;
import weaver.general.BaseBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WeaComponentFilter implements Filter {

    private BaseBean baseBean = new BaseBean();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {



    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        Map<String,Object> requestMap = ParamUtil.request2Map(request);

        String apiUrl = request.getRequestURI();

        if (WeaIocReplaceUriUtils.checkBeforeUrl(apiUrl)) {

            try {

                WeaBeforeReplaceParam weaBeforeReplaceParam = new WeaBeforeReplaceParam();

                weaBeforeReplaceParam.setParamMap(requestMap);

                weaBeforeReplaceParam.setRequest(request);

                weaBeforeReplaceParam.setResponse(response);

                weaBeforeReplaceParam.setApiUrl(apiUrl);

                WeaIocReplaceUriUtils.invokeReplaceApiBeforeMethod(weaBeforeReplaceParam);

            } catch (Exception e) {

                baseBean.writeLog("替换路径:" + apiUrl + "的前置方法失败，原因为:" + e.getMessage());

            }

        }

        if (WeaIocReplaceUriUtils.checkAfterUrl(apiUrl)) {

            ComponentResponseWrapper componentResponseWrapper = new ComponentResponseWrapper(response);

            filterChain.doFilter(request, componentResponseWrapper);

            byte[] content = componentResponseWrapper.getBytes();

            String data = new String(content, "UTF-8");

            String result = data;

            try {

                WeaAfterReplaceParam weaAfterReplaceParam = new WeaAfterReplaceParam();

                weaAfterReplaceParam.setApiUrl(apiUrl);

                weaAfterReplaceParam.setData(data);

                weaAfterReplaceParam.setParamMap(requestMap);

                weaAfterReplaceParam.setRequest(request);

                weaAfterReplaceParam.setResponse(response);

                result = WeaIocReplaceUriUtils.invokeReplaceApiAfterMethod(weaAfterReplaceParam);

            } catch (Exception e) {

                baseBean.writeLog("替换路径:" + apiUrl + "的后置方法失败，原因为:" + e.getMessage());

            }

            PrintWriter out = response.getWriter();

            out.println(result);

            out.flush();

            out.close();



        } else {

            filterChain.doFilter(request,response);

        }

    }

    @Override
    public void destroy() {

    }
}
