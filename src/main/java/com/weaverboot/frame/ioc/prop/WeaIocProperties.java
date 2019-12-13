package com.weaverboot.frame.ioc.prop;

import com.weaverboot.frame.ioc.anno.fieldAnno.WeaIocValue;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.init.inte.WeaInitBeanDefinitionHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.replace.impl.DefaultWeaIocReplaceHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.replace.inte.WeaIocReplaceHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.init.impl.DefaultWeaInitBeanDefinitionHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.register.impl.DefaultWeaRegisterBeanDefinitionHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.register.impl.DefaultWeaRegisterIocAnnoHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.register.inte.WeaRegisterBeanDefinitionHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.register.inte.WeaRegisterIocAnnoHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.anno.autowired.impl.DefaultWeaIocAutowiredHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.anno.autowired.inte.WeaIocAutowiredHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.anno.value.impl.DefaultWeaIocValueHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.anno.value.inte.WeaIocValueHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.factory.impl.DefaultWeaWiredBeanDefinitionFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.factory.inte.WeaWiredBeanDefinitionFactory;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.impl.WeaIocReplaceComponentWiredBeanDefinitionHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.wired.inte.WeaWiredBeanDefinitionHandler;
import weaver.general.BaseBean;
import weaver.general.GCONST;

import java.io.*;
import java.util.Properties;

public class WeaIocProperties {

    private WeaIocProperties(){}

    public static Properties IOC_PROPERTIES;

    public static InputStream IOC_INPUTSTREAM;

    public static String SCAN_PACKAGE;

    private static String BASIC_SCAN_PACKAGE = "com.**.Impl;com.**.impl";

    private static String IOC_PROPERTIES_NAME = "weaverboot";

    private static String SCAN_PACKAGE_NAME = "scanPackage";

    private static String PROPERTIES_SUFFIX = ".properties";

    public static Class<? extends WeaRegisterIocAnnoHandler> DEFAULT_WEA_REGISTER_IOC_ANNO_HANDLER = DefaultWeaRegisterIocAnnoHandler.class;

    public static Class<? extends WeaIocReplaceHandler> DEFAULT_WEA_IOC_REPLACE_AFTER_HANDLER = DefaultWeaIocReplaceHandler.class;

    public static Class<? extends WeaWiredBeanDefinitionFactory> DEFAULT_WEA_WIRED_BEAN_DEFINITION_FACTORY = DefaultWeaWiredBeanDefinitionFactory.class;

    public static Class<? extends WeaIocAutowiredHandler> DEFAULT_WEA_IOC_AUTOWIRED_HANDLER = DefaultWeaIocAutowiredHandler.class;

    public static Class<? extends WeaIocValueHandler> DEFAULT_WEA_IOC_VALUE_HANDLER = DefaultWeaIocValueHandler.class;

    static {

        BaseBean baseBean = new BaseBean();

        SCAN_PACKAGE = baseBean.getPropValue(IOC_PROPERTIES_NAME,SCAN_PACKAGE_NAME);

        if (SCAN_PACKAGE == null || SCAN_PACKAGE.equals("")){

            SCAN_PACKAGE = BASIC_SCAN_PACKAGE;

        }

        IOC_PROPERTIES = new Properties();

        try {

            IOC_INPUTSTREAM = new FileInputStream(new File(GCONST.getPropertyPath() + IOC_PROPERTIES_NAME + PROPERTIES_SUFFIX));

            BufferedReader bf = new BufferedReader(new InputStreamReader(IOC_INPUTSTREAM));

            IOC_PROPERTIES.load(bf);

        } catch (FileNotFoundException e) {

            baseBean.writeLog("未找到weaver_ioc.properties文件");

            e.printStackTrace();

        } catch (IOException e) {

            baseBean.writeLog("流读取配置文件失败，原因为:" + e.getMessage());

            e.printStackTrace();
        }

    }

}
