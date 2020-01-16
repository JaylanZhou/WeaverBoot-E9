package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.bean.BrowserBean;
import com.api.browser.util.BrowserInitUtil;
import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

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
