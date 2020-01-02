package com.weaverboot.weaComponent.impl.weaTab.impl;

import com.weaverboot.tools.enumTools.weaComponent.WeaTabConditionType;
import com.weaverboot.weaComponent.impl.weaTab.inte.AbstractWeaTab;

public class DefaultWeaTab extends AbstractWeaTab {

    private final WeaTabConditionType conditionType = WeaTabConditionType.DEFAULT;

    public DefaultWeaTab(String title){

        super();

        this.setTitle(title);

    }

    @Override
    public WeaTabConditionType getConditionType() {
        return conditionType;
    }
}
