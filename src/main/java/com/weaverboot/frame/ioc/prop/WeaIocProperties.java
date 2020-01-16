package com.weaverboot.frame.ioc.prop;

import com.weaverboot.frame.ioc.beans.context.impl.DefaultWeaApplicationContext;
import com.weaverboot.frame.ioc.beans.context.inte.WeaApplicationContext;
import com.weaverboot.frame.ioc.handler.replace.impl.DefaultWeaIocReplaceHandler;
import com.weaverboot.frame.ioc.handler.replace.inte.WeaIocReplaceHandler;
import com.weaverboot.frame.ioc.handler.register.impl.DefaultWeaRegisterIocAnnoHandler;
import com.weaverboot.frame.ioc.handler.register.inte.WeaRegisterIocAnnoHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.autowired.impl.DefaultWeaIocAutowiredHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.autowired.inte.WeaIocAutowiredHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.value.impl.DefaultWeaIocValueHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.value.inte.WeaIocValueHandler;
import com.weaverboot.frame.ioc.handler.wired.factory.impl.DefaultWeaWiredBeanDefinitionFactory;
import com.weaverboot.frame.ioc.handler.wired.factory.inte.WeaWiredBeanDefinitionFactory;
import com.weaverboot.frame.ioc.postProcessor.register.impl.DefaultWeaCreateWeaBeanDefinitionPostProcessor;
import com.weaverboot.frame.ioc.postProcessor.register.inte.WeaCreateWeaBeanDefinitionPostProcessor;
import com.weaverboot.frame.ioc.postProcessor.wired.impl.DefaultWeaWiredBeanPostProcessor;
import com.weaverboot.frame.ioc.postProcessor.wired.inte.WeaWiredBeanPostProcessor;
import weaver.general.GCONST;

import java.io.*;
import java.util.Properties;

public class WeaIocProperties {

    private WeaIocProperties(){}

    public static Properties IOC_PROPERTIES;

    public static InputStream IOC_INPUTSTREAM;

    private final static String BASIC_SCAN_PACKAGE = "com.weaverboot.frame.ioc.conf;";

    public static String SCAN_PACKAGE;

    private static String IOC_PROPERTIES_NAME = "weaverboot";

    private static String SCAN_PACKAGE_NAME = "scanPackage";

    private static String PROPERTIES_SUFFIX = ".properties";

    public static String CUSTOM_PROPERTIES_URL = "";

    public static Class<? extends WeaApplicationContext> DEFAULT_WEA_APPLICATION_CONTEXT = DefaultWeaApplicationContext.class;

    public static Class<? extends WeaRegisterIocAnnoHandler> DEFAULT_WEA_REGISTER_IOC_ANNO_HANDLER = DefaultWeaRegisterIocAnnoHandler.class;

    public static Class<? extends WeaIocReplaceHandler> DEFAULT_WEA_IOC_REPLACE_AFTER_HANDLER = DefaultWeaIocReplaceHandler.class;

    public static Class<? extends WeaWiredBeanDefinitionFactory> DEFAULT_WEA_WIRED_BEAN_DEFINITION_FACTORY = DefaultWeaWiredBeanDefinitionFactory.class;

    public static Class<? extends WeaIocAutowiredHandler> DEFAULT_WEA_IOC_AUTOWIRED_HANDLER = DefaultWeaIocAutowiredHandler.class;

    public static Class<? extends WeaIocValueHandler> DEFAULT_WEA_IOC_VALUE_HANDLER = DefaultWeaIocValueHandler.class;

    public static Class<? extends WeaWiredBeanPostProcessor> DEFAULT_WEA_BEAN_POST_PROCESSOR = DefaultWeaWiredBeanPostProcessor.class;

    public static Class<? extends WeaCreateWeaBeanDefinitionPostProcessor> DEFAULT_WEA_CREATE_WEA_BEAN_DEFINITION_POST_PROCESSOR = DefaultWeaCreateWeaBeanDefinitionPostProcessor.class;

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

        SCAN_PACKAGE = BASIC_SCAN_PACKAGE + IOC_PROPERTIES.getProperty("scanPackage");

    }

}
