package com.weaverboot.weaComponent.impl.weaTab.inte;

import com.weaverboot.tools.enumTools.weaComponent.WeaTabConditionType;
import com.weaverboot.weaComponent.inte.AbstractWeaComponent;

public abstract class AbstractWeaTab extends AbstractWeaComponent {

    private String color;

    private String groupid;

    private boolean showcount;

    private String title;

    private String state;

    private WeaTabConditionType conditionType;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public boolean isShowcount() {
        return showcount;
    }

    public void setShowcount(boolean showcount) {
        this.showcount = showcount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public WeaTabConditionType getConditionType() {
        return conditionType;
    }

    public void setConditionType(WeaTabConditionType conditionType) {
        this.conditionType = conditionType;
    }
}
