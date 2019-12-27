package com.weaverboot.weaComponent.impl.weaTree.impl;

import com.weaverboot.tools.enumTools.weaComponent.WeaFatherTreeConditionType;
import com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaFatherTree;


public class DefaultWeaFatherTree extends AbstractWeaFatherTree {

    private final WeaFatherTreeConditionType conditionType = WeaFatherTreeConditionType.DEFAULT;

    @Override
    public WeaFatherTreeConditionType getConditionType() {
        return conditionType;
    }
}
