package com.weaverboot.weaComponent.impl.weaForm.weaFormGroup;

import com.weaverboot.tools.enumTools.weaComponent.WeaFormGroupConditionType;
import com.weaverboot.weaComponent.impl.weaForm.weaFormGroup.inte.AbstractWeaFormGroup;

public class DefaultWeaFormGroup extends AbstractWeaFormGroup {

    private final WeaFormGroupConditionType conditionType = WeaFormGroupConditionType.DEFAULT;

    @Override
    public WeaFormGroupConditionType getConditionType() {
        return conditionType;
    }
}
