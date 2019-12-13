package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.bean.BrowserBean;
import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

public class BrowserWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.BROWSER;

    /**
     * 组件参数
     */
    private BrowserBean browserConditionParam;

    public BrowserWeaForm(String label,String ... name){

        super();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public BrowserWeaForm(){

        super();

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
