package com.weaverboot.frame.ioc.prop;

import com.weaverboot.frame.ioc.anno.classAnno.WeaIocWiredBeanPostProcessor;
import com.weaverboot.frame.ioc.beans.context.impl.DefaultWeaApplicationContext;
import com.weaverboot.frame.ioc.beans.context.inte.WeaApplicationContext;
import com.weaverboot.frame.ioc.handler.postProcessor.wired.impl.DefaultWeaIocWiredBeanPostProcessorHandler;
import com.weaverboot.frame.ioc.handler.postProcessor.wired.inte.WeaIocWiredBeanPostProcessorHandler;
import com.weaverboot.frame.ioc.handler.replace.impl.DefaultWeaIocReplaceHandler;
import com.weaverboot.frame.ioc.handler.replace.inte.WeaIocReplaceHandler;
import com.weaverboot.frame.ioc.handler.register.impl.DefaultWeaRegisterIocAnnoHandler;
import com.weaverboot.frame.ioc.handler.register.inte.WeaRegisterIocAnnoHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.autowired.impl.DefaultWeaIocAutowiredHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.autowired.inte.WeaIocAutowiredHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.impl.DefaultWeaWiredColumnAnnoInvokeHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.impl.DefaultWeaWiredMethodAnnoInvokeHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.inte.WeaWiredColumnAnnoInovkeHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.inte.WeaWiredFieldAnnoHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.inte.WeaWiredMethodAnnoInvokeHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.value.impl.DefaultWeaIocValueHandler;
import com.weaverboot.frame.ioc.handler.wired.anno.value.inte.WeaIocValueHandler;
import com.weaverboot.frame.ioc.handler.wired.factory.impl.DefaultWeaWiredBeanDefinitionFactory;
import com.weaverboot.frame.ioc.handler.wired.factory.inte.WeaWiredBeanDefinitionFactory;
import com.weaverboot.frame.ioc.postProcessor.register.impl.DefaultWeaCreateWeaBeanDefinitionPostProcessor;
import com.weaverboot.frame.ioc.postProcessor.register.inte.WeaCreateWeaBeanDefinitionPostProcessor;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.logTools.LogTools;
import weaver.general.GCONST;

import java.io.*;
import java.util.Properties;

public class WeaIocProperties {

    private WeaIocProperties(){}

    public static Properties IOC_PROPERTIES;

    public static InputStream IOC_INPUTSTREAM;

    public final static String BASIC_SCAN_PACKAGE = "com.weaverboot.frame.ioc.conf.**;";

    public static String SCAN_PACKAGE;

    private static String IOC_PROPERTIES_NAME = "weaverboot";

    private static String SCAN_PACKAGE_NAME = "scanPackage";

    private static String PROPERTIES_SUFFIX = ".properties";

    private static String EFFECT_PROPERTIES_NAME = "weaverboot.effect.properties.name";

    public static String CUSTOM_PROPERTIES_URL = "";

    public static Class<? extends WeaApplicationContext> DEFAULT_WEA_APPLICATION_CONTEXT = DefaultWeaApplicationContext.class;

    public static Class<? extends WeaRegisterIocAnnoHandler> DEFAULT_WEA_REGISTER_IOC_ANNO_HANDLER = DefaultWeaRegisterIocAnnoHandler.class;

    public static Class<? extends WeaIocReplaceHandler> DEFAULT_WEA_IOC_REPLACE_AFTER_HANDLER = DefaultWeaIocReplaceHandler.class;

    public static Class<? extends WeaWiredBeanDefinitionFactory> DEFAULT_WEA_WIRED_BEAN_DEFINITION_FACTORY = DefaultWeaWiredBeanDefinitionFactory.class;

    public static Class<? extends WeaIocAutowiredHandler> DEFAULT_WEA_IOC_AUTOWIRED_HANDLER = DefaultWeaIocAutowiredHandler.class;

    public static Class<? extends WeaIocValueHandler> DEFAULT_WEA_IOC_VALUE_HANDLER = DefaultWeaIocValueHandler.class;

    public static Class<? extends WeaCreateWeaBeanDefinitionPostProcessor> DEFAULT_WEA_CREATE_WEA_BEAN_DEFINITION_POST_PROCESSOR = DefaultWeaCreateWeaBeanDefinitionPostProcessor.class;

    public static Class<? extends WeaIocWiredBeanPostProcessorHandler> DEFAULT_WEA_IOC_WIRED_BEAN_POST_PROCESSOR_HANDLER = DefaultWeaIocWiredBeanPostProcessorHandler.class;

    public static Class<? extends WeaWiredColumnAnnoInovkeHandler> DEFAULT_WEA_WIRED_COLUMN_ANNO_INVOKE_HANDLER = DefaultWeaWiredColumnAnnoInvokeHandler.class;

    public static Class<? extends WeaWiredMethodAnnoInvokeHandler> DEFAULT_WEA_WIRED_METHOD_ANNO_INVOKE_HANDLER = DefaultWeaWiredMethodAnnoInvokeHandler.class;

    static {

        String effect = null;

        try {

           webConfigLoad();

            if (BaseTools.notNullString(effect = IOC_PROPERTIES.getProperty(EFFECT_PROPERTIES_NAME))){

                IOC_PROPERTIES_NAME = effect;

                webConfigLoad();

            }

        } catch (FileNotFoundException e) {

                try {

                    localConfigLoad();

                    if (BaseTools.notNullString(effect = IOC_PROPERTIES.getProperty(EFFECT_PROPERTIES_NAME))){

                        IOC_PROPERTIES_NAME = effect;

                        localConfigLoad();

                    }

                } catch (IOException ex) {

                    LogTools.error("未找到" + IOC_PROPERTIES_NAME + PROPERTIES_SUFFIX + "文件");

                    throw new RuntimeException("未找到" + IOC_PROPERTIES_NAME + PROPERTIES_SUFFIX + "文件");

                }


        } catch (IOException e) {

            LogTools.error("流读取配置文件失败，原因为:" + e.getMessage());

            throw new RuntimeException("流读取配置文件失败，原因为:" + e.getMessage());

        }

        SCAN_PACKAGE = BASIC_SCAN_PACKAGE + IOC_PROPERTIES.getProperty("scanPackage");

    }

    private static void webConfigLoad() throws IOException {

        IOC_INPUTSTREAM = new FileInputStream(new File(GCONST.getPropertyPath() + IOC_PROPERTIES_NAME + PROPERTIES_SUFFIX));

        BufferedReader bf = new BufferedReader(new InputStreamReader(IOC_INPUTSTREAM));

        IOC_PROPERTIES = new Properties();

        IOC_PROPERTIES.load(bf);

    }

    private static void localConfigLoad() throws IOException {

        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(IOC_PROPERTIES_NAME + PROPERTIES_SUFFIX);

        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));

        IOC_PROPERTIES = new Properties();

        IOC_PROPERTIES.load(bf);

    }


}
