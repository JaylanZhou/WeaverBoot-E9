package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.bean.BrowserBean;
import com.api.browser.util.BrowserInitUtil;
import com.api.browser.util.ConditionType;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.enumTools.weaComponent.CustomBrowserEnum;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 浏览按钮组件
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


public class BrowserWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.BROWSER;

    private static final String TYPE_PREFIX = "browser.";

    private static final String TYPE_KEY = "type";

    private static final String FIELD_DB_TYPE_KEY = "fielddbtype";

    private static final String URL_PREFIX = "/api/public/browser";

    private static final String DATA_URL_PREFIX = URL_PREFIX + "/data/";

    private static final String COMPLETE_URL_PREFIX = URL_PREFIX + "/complete/";

    private static final String CONDITION_URL_PREFIX = URL_PREFIX + "/condition/";

    private static final String URL_FIX = "/";

    /**
     * 组件参数
     */
    private BrowserBean browserConditionParam;

    public BrowserWeaForm(String label,String ... name){

        super();

        this.init(label,name);

    }

    /**
     *
     * 默认无参构造方法
     *
     */

    public BrowserWeaForm(){

        super();

    }

    /**
     *
     * 指定类型浏览按钮（语言默认中文）
     *
     * @param label 浏览按钮标签名称
     * @param browserId 浏览按钮类型
     * @param name domkey
     */

    public BrowserWeaForm(String label,String browserId,String ... name){

        this.setBrowserConditionParam(initBrowserBean(browserId));

        this.init(label,name);

    }

    /**
     *
     * 指定类型与语言，获取浏览按钮
     *
     * @param label 标签名
     * @param browserId 浏览按钮类型
     * @param languageId 语言类型
     * @param name domkey
     */

    public BrowserWeaForm(String label,String browserId,int languageId,String ... name){

        this.setBrowserConditionParam(initBrowserBean(browserId,languageId));

        this.init(label,name);

    }

    public BrowserWeaForm(String label, String browserId, CustomBrowserEnum customBrowserEnum,int languageId,String ... name){

        String browserTypeId = customBrowserEnum.toString();

        BrowserBean browserBean = initBrowserBean(browserTypeId,languageId);

        browserBean.setDataURL(DATA_URL_PREFIX + browserTypeId);

        browserBean.setCompleteURL(COMPLETE_URL_PREFIX + browserTypeId);

        browserBean.setConditionURL(CONDITION_URL_PREFIX + browserTypeId);

        browserBean.setDataParams(initCustomDataMap(browserId));

        browserBean.setCompleteParams(initCustomDataMap(browserId));

        browserBean.setConditionDataParams(initCustomDataMap(browserId));

        this.setBrowserConditionParam(browserBean);

        this.init(label,name);

    }

    public Map<String,Object> initCustomDataMap(String browserId){

        Map<String,Object> dataMap = new HashMap<>();

        dataMap.put(TYPE_KEY , TYPE_PREFIX + browserId);

        dataMap.put(FIELD_DB_TYPE_KEY , TYPE_PREFIX + browserId);

        return dataMap;

    }

    /**
     *
     * 初始化标签名以及domkey的操作
     *
     * @param label 标签名
     * @param name domkey 数组
     */

    public void init(String label,String ... name){

        super.setLabel(label);

        super.setDomkey(name);

    }

    /**
     *
     * 获取浏览按钮（语言默认中文）
     *
     * @param browserId 浏览按钮类型
     * @return
     */

    public BrowserBean initBrowserBean(String browserId){

        return initBrowserBean(browserId,7);

    }

    /**
     *
     * 指定类型与语言，获取浏览按钮
     *
     * @param browserId 浏览按钮类型
     * @param languageId 语言类型
     * @return
     */

    public BrowserBean initBrowserBean(String browserId,int languageId){

        BrowserBean browserBean = new BrowserBean(browserId);

        BrowserInitUtil browserInitUtil = new BrowserInitUtil();

        browserInitUtil.initBrowser(browserBean, languageId);

        return browserBean;

    }

    public BrowserWeaForm addValue(String id,String name){

        List<Map<String,Object>> replaceDataList = this.getBrowserConditionParam().getReplaceDatas();

        if (!BaseTools.notNullList(replaceDataList)){

            replaceDataList = new ArrayList<>();

            this.getBrowserConditionParam().setReplaceDatas(replaceDataList);

        }

        Map<String,Object> valueMap = new HashMap<>();

        valueMap.put("id",id);

        valueMap.put("name",name);

        replaceDataList.add(valueMap);

        return this;

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    public BrowserBean getBrowserConditionParam() {
        return browserConditionParam;
    }

    public void setBrowserConditionParam(BrowserBean browserConditionParam) {
        this.browserConditionParam = browserConditionParam;
    }

}
