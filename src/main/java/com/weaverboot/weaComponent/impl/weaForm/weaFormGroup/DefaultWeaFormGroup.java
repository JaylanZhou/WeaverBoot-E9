package com.weaverboot.weaComponent.impl.weaForm.weaFormGroup;

import com.weaverboot.tools.enumTools.weaComponent.WeaFormGroupConditionType;
import com.weaverboot.weaComponent.impl.weaForm.weaFormGroup.inte.AbstractWeaFormGroup;

/**
 *
 * 组件分组 - 默认实现类
 *
 * 此类的ConditionType为：DEFAULT
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */

public class DefaultWeaFormGroup extends AbstractWeaFormGroup {

    private final WeaFormGroupConditionType conditionType = WeaFormGroupConditionType.DEFAULT; //默认实现类的标记：DEFAULT

    public DefaultWeaFormGroup(){}

    public DefaultWeaFormGroup(String title){

        this.setTitle(title);

    }

    @Override
    public WeaFormGroupConditionType getConditionType() {
        return conditionType;
    }
}
