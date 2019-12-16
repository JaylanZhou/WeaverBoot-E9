package com.weaverboot.frame.ioc.beans.bean.definition.handler.scan.impl;

import com.weaver.general.BaseBean;
import com.weaverboot.frame.ioc.beans.bean.definition.utils.WeaIocFormatUtils;
import com.weaverboot.frame.ioc.prop.WeaIocProperties;
import com.weaverboot.frame.ioc.resource.patcher.WeaAntPathMatcher;
import com.weaverboot.frame.ioc.resource.patcher.WeaPathMatcher;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.register.impl.DefaultWeaRegisterBeanDefinitionHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.register.inte.WeaRegisterBeanDefinitionHandler;
import com.weaverboot.frame.ioc.beans.bean.definition.handler.scan.inte.WeaScanBeanDefinitionHandler;
import com.weaverboot.tools.baseTools.BaseTools;

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

    public void scanBeanDefinition() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        if (BaseTools.notNullString(WeaIocProperties.SCAN_PACKAGE)) {

            List<File> fileList = getScanPackageList(WeaIocProperties.SCAN_PACKAGE);

            for (File f : fileList
            ) {

                for (File fa : f.listFiles()
                ) {

                    String faName = fa.getPath();

                    if (faName.endsWith(CLASS_SUFFIX)) {

                        faName = WeaIocFormatUtils.formatClassName(faName, basePath);

                        Class clazz = this.getClass().getClassLoader().loadClass(faName);

                        weaRegisterBeanDefinitionHandler.registerBeanDefinition(clazz);

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
