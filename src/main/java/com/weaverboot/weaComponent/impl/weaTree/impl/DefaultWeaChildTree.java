package com.weaverboot.weaComponent.impl.weaTree.impl;

import com.weaverboot.tools.enumTools.weaComponent.WeaChildTreeConditionType;
import com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaChildTree;

public class DefaultWeaChildTree extends AbstractWeaChildTree {

    private final WeaChildTreeConditionType conditionType = WeaChildTreeConditionType.DEFAULT;

    @Override
    public WeaChildTreeConditionType getConditionType() {
        return conditionType;
    }

}
