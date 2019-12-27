package com.weaverboot.weaComponent.impl.weaTree.inte;

import com.weaverboot.tools.enumTools.weaComponent.WeaChildTreeConditionType;

public abstract class AbstractWeaChildTree extends AbstractWeaTree {

    private WeaChildTreeConditionType conditionType;

    private boolean haschild = false;

    public boolean isHaschild() {
        return haschild;
    }

    public void setHaschild(boolean haschild) {
        this.haschild = haschild;
    }

    public WeaChildTreeConditionType getConditionType() {
        return conditionType;
    }

    public void setConditionType(WeaChildTreeConditionType conditionType) {
        this.conditionType = conditionType;
    }
}
