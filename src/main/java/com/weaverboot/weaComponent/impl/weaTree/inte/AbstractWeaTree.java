package com.weaverboot.weaComponent.impl.weaTree.inte;

import com.weaverboot.tools.enumTools.weaComponent.WeaTreeConditionType;
import com.weaverboot.weaComponent.inte.AbstractWeaComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractWeaTree extends AbstractWeaComponent {

    private WeaTreeConditionType conditionType;

    private String key;

    private String name;

    private String domid;

    private boolean isopen = false;

    private String linkUrl;

    private String icon;

    private String pid;

    private String type;

    private Map<String, Object> prop;

    private boolean isSelected;

    private boolean canClick;

    private String shadowInfo;

    private boolean haschild = true;

    private List<? super AbstractWeaTree> childs;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomid() {
        return domid;
    }

    public void setDomid(String domid) {
        this.domid = domid;
    }

    public boolean isIsopen() {
        return isopen;
    }

    public void setIsopen(boolean isopen) {
        this.isopen = isopen;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getProp() {
        return prop;
    }

    public void setProp(Map<String, Object> prop) {
        this.prop = prop;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isCanClick() {
        return canClick;
    }

    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    public String getShadowInfo() {
        return shadowInfo;
    }

    public void setShadowInfo(String shadowInfo) {
        this.shadowInfo = shadowInfo;
    }

    public boolean isHaschild() {
        return haschild;
    }

    public void setHaschild(boolean haschild) {
        this.haschild = haschild;
    }

    public List<? super AbstractWeaTree> getChilds() {
        return childs;
    }

    public void setChilds(List<? super AbstractWeaTree> childs) {
        this.childs = childs;
    }

    public AbstractWeaTree addChild(AbstractWeaTree abstractWeaTree){

        this.setHaschild(true);

        if(this.childs == null){

            this.childs = new ArrayList<>();

        }

        this.childs.add(abstractWeaTree);

        return this;

    }

    public WeaTreeConditionType getConditionType() {
        return conditionType;
    }

    public void setConditionType(WeaTreeConditionType conditionType) {
        this.conditionType = conditionType;
    }
}
