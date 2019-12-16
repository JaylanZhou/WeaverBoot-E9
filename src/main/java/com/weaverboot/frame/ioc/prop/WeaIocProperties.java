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
import com.weaverboot.tools.baseTools.BaseTools;
import weaver.general.BaseBean;
import weaver.general.GCONST;

import java.io.*;
import java.util.Properties;

public class WeaIocProperties {

    private WeaIocProperties(){}

    public static Properties IOC_PROPERTIES;

    public static InputStream IOC_INPUTSTREAM;

    public static String SCAN_PACKAGE;

    private static String IOC_PROPERTIES_NAME = "weaverboot";

    private static String SCAN_PACKAGE_NAME = "scanPackage";

    private static String PROPERTIES_SUFFIX = ".properties";

    public static String CUSTOM_PROPERTIES_URL = "";

    public static Class<? extends WeaRegisterIocAnnoHandler> DEFAULT_WEA_REGISTER_IOC_ANNO_HANDLER = DefaultWeaRegisterIocAnnoHandler.class;

    public static Class<? extends WeaIocReplaceHandler> DEFAULT_WEA_IOC_REPLACE_AFTER_HANDLER = DefaultWeaIocReplaceHandler.class;

    public static Class<? extends WeaWiredBeanDefinitionFactory> DEFAULT_WEA_WIRED_BEAN_DEFINITION_FACTORY = DefaultWeaWiredBeanDefinitionFactory.class;

    public static Class<? extends WeaIocAutowiredHandler> DEFAULT_WEA_IOC_AUTOWIRED_HANDLER = DefaultWeaIocAutowiredHandler.class;

    public static Class<? extends WeaIocValueHandler> DEFAULT_WEA_IOC_VALUE_HANDLER = DefaultWeaIocValueHandler.class;

    static {

        IOC_PROPERTIES = new Properties();

        try {

            IOC_INPUTSTREAM = new FileInputStream(new File(GCONST.getPropertyPath() + IOC_PROPERTIES_NAME + PROPERTIES_SUFFIX));

            BufferedReader bf = new BufferedReader(new InputStreamReader(IOC_INPUTSTREAM));

            IOC_PROPERTIES.load(bf);

        } catch (FileNotFoundException e) {

                try {

                    InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("weaverboot.properties");

                    BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));

                    IOC_PROPERTIES.load(bf);

                } catch (IOException ex) {

                    throw new RuntimeException("未找到weaverboot.properties文件");

                }


        } catch (IOException e) {

            throw new RuntimeException("流读取配置文件失败，原因为:" + e.getMessage());

        }

        SCAN_PACKAGE = IOC_PROPERTIES.getProperty("scanPackage");

    }

}
