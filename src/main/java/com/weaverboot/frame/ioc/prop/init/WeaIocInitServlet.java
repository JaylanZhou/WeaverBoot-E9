package com.weaverboot.frame.ioc.prop.init;

import com.weaverboot.frame.ioc.handler.scan.impl.DefaultWeaScanBeanDefinitionHandler;
import com.weaverboot.frame.ioc.beans.context.impl.DefaultWeaApplicationContext;
import com.weaverboot.frame.ioc.beans.context.inte.WeaApplicationContext;
import com.weaverboot.tools.logTools.LogTools;
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

            LogTools.writeLog("IOC根目录:" + DefaultWeaScanBeanDefinitionHandler.basePath);

            weaApplicationContext.refresh();

        } catch (Exception e){

            LogTools.writeLog("IOC初始化失败，原因为:" + e.getMessage());

        }
    }

}
