package com.weaverboot.weaComponent.impl.weaTree.inte;

public abstract class AbstractWeaChildTree extends AbstractWeaTree {

    private boolean haschild = false;

    public boolean isHaschild() {
        return haschild;
    }

    public void setHaschild(boolean haschild) {
        this.haschild = haschild;
    }

}
