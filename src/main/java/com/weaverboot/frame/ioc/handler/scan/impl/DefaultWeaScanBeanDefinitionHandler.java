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
import java.util.ArrayList;
import java.util.List;

public class DefaultWeaScanBeanDefinitionHandler implements WeaScanBeanDefinitionHandler {

    private WeaPathMatcher weaPathMatcher;

    private WeaRegisterBeanDefinitionHandler weaRegisterBeanDefinitionHandler = new DefaultWeaRegisterBeanDefinitionHandler();

    public static String basePath = "";

    private static String CLASS_SUFFIX = ".class";

    private static String SPLIT_SCANPACKAGE_FLAG = ";";

    public DefaultWeaScanBeanDefinitionHandler(){

        initBasePath();

        weaPathMatcher = new WeaAntPathMatcher();

    }

    public void scanBeanDefinition() {

        if (BaseTools.notNullString(WeaIocProperties.SCAN_PACKAGE)) {

            List<File> fileList = getScanPackageList(WeaIocProperties.SCAN_PACKAGE);

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

                            LogTools.error("扫描发生错误，原因为:" + e.getMessage());

                        } catch (Throwable t){

                            LogTools.error("扫描发生错误，原因为:" + t.getMessage());

                        }

                    }

                }

            }

        } else {

            throw new RuntimeException("扫描包为空，请检查weaverboot.properties的scanPackage属性");

        }

    }

    private List<File> getScanPackageList(String scanPackgeString){

        List<File> fileList = new ArrayList<>();

        scanPackgeString = WeaIocFormatUtils.formatScanPackage(scanPackgeString);

        String[] scanPackges = scanPackgeString.split(SPLIT_SCANPACKAGE_FLAG);

        for (String scanPackage : scanPackges
             ) {


            File file = new File(basePath);

            File[] files = file.listFiles();

            if (files != null && files.length > 0) {

                for (int i = 0; i < files.length; i++) {

                    checkPath(fileList, files[i], scanPackage);

                }

            }

        }

        return fileList;

    }

    private void checkPath(List<File> fileList,File file,String scanPackge){

        if (file.isDirectory()) {

            String path = WeaIocFormatUtils.formatFilePath(file.getPath());

            if (weaPathMatcher.match(scanPackge, path.replaceAll(basePath, ""))) {

                fileList.add(file);

            }

            File[] files = file.listFiles();

            for (int i = 0; i < files.length; i++) {

                checkPath(fileList,files[i],scanPackge);

            }

        }

    }

    private void initBasePath() {

        if (!BaseTools.notNullString(basePath)) {

            basePath = this.getClass().getClassLoader().getResource("").getPath();

        } else {

            if (!basePath.startsWith("/")) {

                basePath = "/" + basePath;

            }

            basePath = (basePath + "classbean/").replaceAll("\\\\", "/");

        }

    }

}
