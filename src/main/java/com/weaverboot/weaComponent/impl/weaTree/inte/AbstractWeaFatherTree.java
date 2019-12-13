package com.weaverboot.weaComponent.impl.weaTree.inte;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWeaFatherTree extends AbstractWeaTree {

    private boolean haschild = true;

    private List<? super AbstractWeaChildTree> childs;

    public boolean isHaschild() {
        return haschild;
    }

    public void setHaschild(boolean haschild) {
        this.haschild = haschild;
    }

    public List<? super AbstractWeaChildTree> getChilds() {
        return childs;
    }

    public void setChilds(List<? super AbstractWeaChildTree> childs) {
        this.childs = childs;
    }


    public AbstractWeaFatherTree(){

        init();

    }

    private void init(){

        childs = new ArrayList<>();

    }

    public List<? super AbstractWeaChildTree> addChild(AbstractWeaChildTree abstractWeaChildTree){

        childs.add(abstractWeaChildTree);

        return childs;

    }

}
