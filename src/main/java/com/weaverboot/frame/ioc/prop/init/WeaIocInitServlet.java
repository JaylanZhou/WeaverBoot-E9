package com.weaverboot.frame.ioc.prop.init;

import com.weaverboot.frame.ioc.beans.bean.definition.handler.scan.impl.DefaultWeaScanBeanDefinitionHandler;
import com.weaverboot.frame.ioc.beans.context.impl.DefaultWeaApplicationContext;
import com.weaverboot.frame.ioc.beans.context.inte.WeaApplicationContext;
import weaver.general.BaseBean;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class WeaIocInitServlet extends HttpServlet {

    private BaseBean baseBean = new BaseBean();

    @Override
    public void init() throws ServletException {

        try {

            ServletContext s1 = this.getServletContext();

            DefaultWeaScanBeanDefinitionHandler.basePath = s1.getRealPath("/");

            WeaApplicationContext weaApplicationContext = new DefaultWeaApplicationContext();

            weaApplicationContext.refresh();

        } catch (Exception e){

            baseBean.writeLog("IOC初始化失败，原因为:" + e.getMessage());

        }
    }

}
