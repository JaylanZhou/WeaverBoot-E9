package com.weaverboot.weaComponent.impl.weaTree.impl;

import com.weaverboot.tools.enumTools.weaComponent.WeaTreeConditionType;
import com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaTree;


public class DefaultWeaTree extends AbstractWeaTree {

    private final WeaTreeConditionType conditionType = WeaTreeConditionType.DEFAULT;

    @Override
    public WeaTreeConditionType getConditionType() {
        return conditionType;
    }
}
