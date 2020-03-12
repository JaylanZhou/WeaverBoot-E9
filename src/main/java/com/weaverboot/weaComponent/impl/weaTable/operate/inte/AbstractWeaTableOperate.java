package com.weaverboot.weaComponent.impl.weaTable.operate.inte;

import com.weaverboot.weaComponent.inte.WeaComponent;

/**
 *
 * table 操作菜单 - 基类
 *
 * @Author : Jaylan Zhou
 * @Date : 2019-12-24 10:25
 * @Version : 1.0
 */
public abstract class AbstractWeaTableOperate implements WeaComponent {

    private String otherpara; //其他字段，配合

    private String text; //操作菜单名称

    private String linkvaluecolumn;

    private String linkkey;

    private String href; //操作菜单地址

    private String target;

    private String index; //操作菜单索引

    private String cusWidth; //操作菜单宽度

    private String cusHeight; //操作菜单高度

    private String isalwaysshow; //是否一直显示

    public AbstractWeaTableOperate() {
    }

    public AbstractWeaTableOperate(String text, String href) {

        this.text = text;

        this.href = href;

    }

    public AbstractWeaTableOperate(String text, String href, String index) {

        this.text = text;

        this.href = href;

        this.index = index;

    }

    public String getOtherpara() {

        return this.otherpara;

    }

    public AbstractWeaTableOperate setOtherpara(String otherpara) {

        this.otherpara = otherpara;

        return this;

    }

    public String getText() {

        return this.text;

    }

    public AbstractWeaTableOperate setText(String text) {

        this.text = text;

        return this;

    }

    public String getLinkvaluecolumn() {

        return this.linkvaluecolumn;

    }

    public AbstractWeaTableOperate setLinkvaluecolumn(String linkvaluecolumn) {

        this.linkvaluecolumn = linkvaluecolumn;

        return this;

    }

    public String getLinkkey() {

        return this.linkkey;

    }

    public AbstractWeaTableOperate setLinkkey(String linkkey) {

        this.linkkey = linkkey;

        return this;

    }

    public String getHref() {

        return this.href;

    }

    public AbstractWeaTableOperate setHref(String href) {

        this.href = href;

        return this;

    }

    public String getTarget() {

        return this.target;

    }

    public AbstractWeaTableOperate setTarget(String target) {

        this.target = target;

        return this;

    }

    public String getIndex() {

        return this.index;

    }

    public AbstractWeaTableOperate setIndex(String index) {

        this.index = index;

        return this;

    }

    public String getCusWidth() {

        return this.cusWidth;

    }

    public AbstractWeaTableOperate setCusWidth(String cusWidth) {

        this.cusWidth = cusWidth;

        return this;

    }

    public String getCusHeight() {

        return this.cusHeight;

    }

    public AbstractWeaTableOperate setCusHeight(String cusHeight) {

        this.cusHeight = cusHeight;

        return this;

    }

    public String getIsalwaysshow() {

        return this.isalwaysshow;

    }

    public AbstractWeaTableOperate setIsalwaysshow(String isalwaysshow) {

        this.isalwaysshow = isalwaysshow;

        return this;

    }

}
