package com.weaverboot.weaComponent.impl.weaTable.column.impl;

import com.weaverboot.weaComponent.impl.weaTable.column.inte.AbstractWeaTableColumn;

/**
 *
 * 文档模块 weatable 实现类
 *
 * @Author : Jaylan Zhou
 * @Date : 2019-12-25 15:55
 * @Version : 1.0
 */
public class DocWeaTableColumn extends AbstractWeaTableColumn {

    private String t_showorder;

    private String t_icon;

    private String t_type;

    private String t_text;

    private String t_column;

    private String t_transmethod;

    private String t_otherpara;

    public String getT_showorder() {
        return t_showorder;
    }

    public void setT_showorder(String t_showorder) {
        this.t_showorder = t_showorder;
    }

    public String getT_icon() {
        return t_icon;
    }

    public void setT_icon(String t_icon) {
        this.t_icon = t_icon;
    }

    public String getT_type() {
        return t_type;
    }

    public void setT_type(String t_type) {
        this.t_type = t_type;
    }

    public String getT_text() {
        return t_text;
    }

    public void setT_text(String t_text) {
        this.t_text = t_text;
    }

    public String getT_column() {
        return t_column;
    }

    public void setT_column(String t_column) {
        this.t_column = t_column;
    }

    public String getT_transmethod() {
        return t_transmethod;
    }

    public void setT_transmethod(String t_transmethod) {
        this.t_transmethod = t_transmethod;
    }

    public String getT_otherpara() {
        return t_otherpara;
    }

    public void setT_otherpara(String t_otherpara) {
        this.t_otherpara = t_otherpara;
    }
}
