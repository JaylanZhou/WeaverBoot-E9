package com.weaverboot.weaComponent.impl.weaTable.operates.inte;

import com.weaverboot.weaComponent.impl.weaTable.operate.inte.AbstractWeaTableOperate;
import com.weaverboot.weaComponent.impl.weaTable.tablePopedom.inte.AbstractWeaTablePopedom;
import com.weaverboot.weaComponent.inte.WeaComponent;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * table 操作组 - 基类
 *
 * 所有的 table 操作组均拓展自此类
 *
 * @Author : Jaylan Zhou
 * @Date : 2019-12-24 10:31
 * @Version : 1.0
 */
public abstract class AbstractWeaTableOperates implements WeaComponent {

    private AbstractWeaTablePopedom popedom;

    private List<AbstractWeaTableOperate> operate = new ArrayList();

    public AbstractWeaTablePopedom getPopedom() {
        return this.popedom;
    }

    public AbstractWeaTableOperates setPopedom(AbstractWeaTablePopedom popedom) {
        this.popedom = popedom;
        return this;
    }

    public List<AbstractWeaTableOperate> getOperate() {
        return this.operate;
    }

    public AbstractWeaTableOperates setOperate(List<AbstractWeaTableOperate> operate) {

        this.operate = operate;

        return this;

    }

}
