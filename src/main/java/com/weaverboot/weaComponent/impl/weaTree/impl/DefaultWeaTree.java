package com.weaverboot.weaComponent.impl.weaTree.impl;

import com.weaverboot.tools.enumTools.weaComponent.WeaTreeConditionType;
import com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaTree;


public class DefaultWeaTree extends AbstractWeaTree {

    private final WeaTreeConditionType conditionType = WeaTreeConditionType.DEFAULT;

    public DefaultWeaTree(String name,String domid,String key){

        super();

        this.setName(name);

        this.setDomid(domid);

        this.setKey(key);


    }

    @Override
    public WeaTreeConditionType getConditionType() {
        return conditionType;
    }
}
