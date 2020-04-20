package com.weaverboot.frame.ioc.handler.scan.impl;

import com.weaverboot.frame.ioc.beans.bean.definition.utils.WeaIocFormatUtils;
import com.weaverboot.frame.ioc.prop.WeaIocProperties;
import com.weaverboot.frame.ioc.resource.patcher.WeaAntPathMatcher;
import com.weaverboot.frame.ioc.resource.patcher.WeaPathMatcher;
import com.weaverboot.frame.ioc.handler.register.impl.DefaultWeaRegisterBeanDefinitionHandler;
import com.weaverboot.frame.ioc.handler.register.inte.WeaRegisterBeanDefinitionHandler;
import com.weaverboot.frame.ioc.handler.scan.inte.WeaScanBeanDefinitionHandler;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.logTools.LogTools;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class DefaultWeaScanBeanDefinitionHandler implements WeaScanBeanDefinitionHandler {

    private WeaPathMatcher weaPathMatcher;

    private WeaRegisterBeanDefinitionHandler weaRegisterBeanDefinitionHandler = new DefaultWeaRegisterBeanDefinitionHandler();

    public static String basePath = "";

    public static String baseLibPath = "";

    private static final String CLASS_SUFFIX = ".class";

    private static final String JAR_SUFFIX = ".jar";

    private static String SPLIT_SCANPACKAGE_FLAG = ";";

    public DefaultWeaScanBeanDefinitionHandler(){

        initBasePath();

        weaPathMatcher = new WeaAntPathMatcher();

    }

    public void scanBeanDefinition() {

        if (BaseTools.notNullString(WeaIocProperties.SCAN_PACKAGE)) {

            scanLogic(WeaIocProperties.SCAN_PACKAGE);

        } else {

            throw new RuntimeException("扫描包为空，请检查weaverboot.properties的scanPackage属性");

        }

    }

    private void scanLogic(String packageName){

       scanClassBean(packageName);

       scanJar(packageName);

    }

    private void scanJar(String packageName){

        File file = new File(baseLibPath);

        File[] jarList = file.listFiles();

        String[] scanJars = WeaIocProperties.SCAN_JAR.split(";");

        String[] packageNames = packageName.split(";");

        this.checkJar(jarList,scanJars,packageNames);

    }

    private void checkJar(File[] jarList,String[] scarJars,String[] packageNames){

        for (File jarFile : jarList
        ) {

            if (jarFile.isDirectory()){

                checkJar(jarFile.listFiles(),scarJars,packageNames);

            } else {

                String jarFileName = jarFile.getName();

                if (jarFileName.endsWith(JAR_SUFFIX)){

                    for (String scanJar : scarJars
                         ) {

                        if (weaPathMatcher.match(scanJar,jarFileName)){

                            loadJar(jarFile,packageNames);

                            break;

                        }

                    }

                }

            }

        }

    }

    private void loadJar(File jarFile,String[] packageNames){

        try {

            JarFile jarZip = new JarFile(jarFile.getPath());

            Enumeration<JarEntry> entrys = jarZip.entries();

            while (entrys.hasMoreElements()){

                JarEntry jarEntry = entrys.nextElement();

                if (jarEntry.getName().endsWith(CLASS_SUFFIX)){

                    String classPath = jarEntry.getName();

                    String classPackageName = classPath.substring(0,classPath.lastIndexOf("/")).replaceAll("/",".");

                    for (String packageName : packageNames
                         ) {

                        if (weaPathMatcher.match(packageName,classPackageName)){

                            Class tClass = this.getClass().getClassLoader().loadClass(classPath.replaceAll("/",".").replaceAll(".class",""));

                            this.weaRegisterBeanDefinitionHandler.registerBeanDefinition(tClass);

                            break;

                        }

                    }

                }

            }



        } catch (MalformedURLException e) {

            LogTools.error("URL资源加载失败，原因为:" + e.getMessage());

        } catch (IOException e) {

            LogTools.error("Jar包扫描IO错误，原因为:" + e.getMessage());

        } catch (ClassNotFoundException e) {

            LogTools.error("扫描jar包ClassNotFound,原因为:" + e.getMessage());

        } catch (IllegalAccessException e) {

            LogTools.error("扫描jar包参数错误，原因为:" + e.getMessage());

        } catch (InstantiationException e) {

            LogTools.error("注册jar包中的扫描类错误，原因为：" + e.getMessage());

        }


    }

    private void scanClassBean(String packageName){

        List<File> fileList = getScanPackageList(packageName);

        for (File f : fileList
        ) {

            for (File fa : f.listFiles()
            ) {

                String faName = fa.getPath();

                if (faName.endsWith(CLASS_SUFFIX)) {

                    try {

                        faName = WeaIocFormatUtils.formatClassName(faName, basePath);

                        Class clazz = this.getClass().getClassLoader().loadClass(faName);

                        weaRegisterBeanDefinitionHandler.registerBeanDefinition(clazz);

                    } catch (Exception e){

                        LogTools.error("扫描发生错误，名称:" + faName + ",原因为:" + e.getMessage());

                        continue;

                    } catch (Throwable t){

                        LogTools.error("扫描发生错误，名称:" + faName + ",原因为:" + t.getMessage());

                        continue;

                    }

                }

            }

        }

    }

    private List<File> getScanPackageList(String scanPackgeString){

        List<File> fileList = new ArrayList<>();

        scanPackgeString = WeaIocFormatUtils.formatScanPackage(scanPackgeString);

        String[] scanPackges = scanPackgeString.split(SPLIT_SCANPACKAGE_FLAG);




            File file = new File(basePath);

            File[] files = file.listFiles();

            if (files != null && files.length > 0) {

                for (int i = 0; i < files.length; i++) {

                    checkPath(fileList, files[i],scanPackges);

                }


        }

        return fileList;

    }

    private void checkPath(List<File> fileList,File file,String[] scanPackges){

            if (file.isDirectory()) {

                String path = WeaIocFormatUtils.formatFilePath(file.getPath());

                    for (String scanPackage : scanPackges
                    ) {
                        if (weaPathMatcher.match(scanPackage, path.replaceAll(basePath, ""))) {

                            fileList.add(file);

                            break;

                        }

                }

                File[] files = file.listFiles();

                for (int i = 0; i < files.length; i++) {

                    checkPath(fileList,files[i],scanPackges);

            }
        }

    }

    private void initBasePath() {

        if (!BaseTools.notNullString(basePath)) {

            basePath = this.getClass().getClassLoader().getResource("").getPath();

        } else {

            if (!basePath.startsWith("/")) {

                baseLibPath = basePath = "/" + basePath;

            } else {

                baseLibPath = basePath;

            }

            basePath = (basePath + "classbean/").replaceAll("\\\\", "/");

            baseLibPath = (baseLibPath + "WEB-INF/lib/").replaceAll("\\\\", "/");

        }

    }

}
