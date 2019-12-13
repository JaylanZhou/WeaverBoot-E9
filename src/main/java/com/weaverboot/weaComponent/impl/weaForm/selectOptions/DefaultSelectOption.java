package com.weaverboot.weaComponent.impl.weaForm.selectOptions;


public class DefaultSelectOption {

    private String key;

    private String showname;

    private boolean selected;

    private boolean disabled;

    private boolean visible;//下拉框的值是否显示，默认显示

    private String [] childitemid;//在查询列表 搜索条件中存在子选项时使用,存在于父选项中 qc:376384

    public DefaultSelectOption(String showname,String key){

        this.showname = showname;

        this.key = key;

        this.selected = false;

        this.disabled = false;

        this.visible = true;

    }

    public DefaultSelectOption selected(){

        this.selected = true;

        return this;

    }

    public DefaultSelectOption cancelSelected(){

        this.selected = false;

        return this;

    }

    public DefaultSelectOption enabled(){

        this.disabled = false;

        return this;

    }

    public DefaultSelectOption disabled(){

        this.disabled = true;

        return this;

    }

    public DefaultSelectOption canVisible(){

        this.visible = true;

        return this;

    }

    public DefaultSelectOption notVisible(){

        this.visible = false;

        return this;

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String[] getChilditemid() {
        return childitemid;
    }

    public void setChilditemid(String[] childitemid) {
        this.childitemid = childitemid;
    }
}
