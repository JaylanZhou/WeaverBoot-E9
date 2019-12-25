package com.weaverboot.weaResultMsg.impl.tableResult.inte;

import com.alibaba.fastjson.annotation.JSONField;
import com.weaverboot.tools.componentTools.table.WeaTableTools;
import com.weaverboot.tools.enumTools.weaComponent.WeaBelongEnum;
import com.weaverboot.tools.enumTools.weaComponent.WeaBooleanEnum;
import com.weaverboot.tools.enumTools.weaComponent.WeaMobileViewTypeEnum;
import com.weaverboot.weaComponent.impl.weaTable.checkboxPopedom.inte.AbstractWeaCheckboxPopedom;
import com.weaverboot.weaComponent.impl.weaTable.column.inte.AbstractWeaTableColumn;
import com.weaverboot.weaComponent.impl.weaTable.operate.inte.AbstractWeaTableOperate;
import com.weaverboot.weaComponent.impl.weaTable.operates.inte.AbstractWeaTableOperates;
import com.weaverboot.weaComponent.impl.weaTable.table.inte.AbstractWeaTable;
import com.weaverboot.weaComponent.impl.weaTable.tablePopedom.inte.AbstractWeaTablePopedom;
import com.weaverboot.weaResultMsg.inte.AbstractWeaComponentResultMsg;
import weaver.general.Util;

import java.util.List;

public abstract class AbstractWeaTableComponentResultMsg extends AbstractWeaComponentResultMsg {

    @JSONField(serialize = false)
    private AbstractWeaTable weaTable;

    private int type;

    private String datas;

    private boolean status;

    private int code;

    private String msg;

    private String msgShowType;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgShowType() {
        return msgShowType;
    }

    public void setMsgShowType(String msgShowType) {
        this.msgShowType = msgShowType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public AbstractWeaTable getWeaTable() {
        return weaTable;
    }

    public void setWeaTable(AbstractWeaTable weaTable) {
        this.weaTable = weaTable;
    }

    public String toTableString() {

        return WeaTableTools.toTableString(this.weaTable);

    }

}
