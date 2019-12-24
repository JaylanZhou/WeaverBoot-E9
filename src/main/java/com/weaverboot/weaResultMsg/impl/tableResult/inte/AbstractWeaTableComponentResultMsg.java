package com.weaverboot.weaResultMsg.impl.tableResult.inte;

import com.alibaba.fastjson.annotation.JSONField;
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

    private static final String SUF_MARK = "\"";

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

        if (weaTable == null){

            throw new RuntimeException("消息体表单为空，请设置表单内容");

        }

        StringBuilder tableString = new StringBuilder();

        // table
        tableString.append("<table tabletype=\"").append(weaTable.getTableType()).append(SUF_MARK);

        if (weaTable.getPageUID() != null) {

            tableString.append(" pageUid=\"").append(weaTable.getPageUID()).append(SUF_MARK);

        }

        if(weaTable.getPageID() != null) {

            tableString.append(" pageId=\"").append(weaTable.getPageID()).append(SUF_MARK);

        }

        tableString.append(" pagesize=\"").append(weaTable.getPagesize()).append(SUF_MARK);

        if (weaTable.getDatasource() != null) {

            tableString.append(" datasource=\"").append(weaTable.getDatasource()).append(SUF_MARK);

        }

        if (weaTable.getSourceparams() != null) {

            tableString.append(" sourceparams=\"").append(weaTable.getSourceparams()).append(SUF_MARK);

        }

        if(weaTable.getPageBySelf() != null) {

            tableString.append(" pageBySelf=\"").append(weaTable.getPageBySelf()).append(SUF_MARK);

        }


        if(weaTable.getRowstylefield() != null) {

            tableString.append(" rowstylefield=\"").append(weaTable.getRowstylefield()).append(SUF_MARK);

        }

        tableString.append(" mobileshowtype=\"").append(weaTable.getMobileshowtype().getStringVal()).append(SUF_MARK);


        if(weaTable.getMobileshowtemplate() != null) {

            tableString.append(" mobileshowtemplate=\"").append(weaTable.getMobileshowtemplate()).append(SUF_MARK);

        }

        tableString.append(">");

        // sql
        tableString.append("<sql backfields=\"").append(weaTable.getBackfields()).append(SUF_MARK);

        tableString.append(" sqlform=\"").append(Util.toHtmlForSplitPage(weaTable.getSqlform())).append(SUF_MARK);

        tableString.append(" sqlwhere=\"").append(Util.toHtmlForSplitPage(weaTable.getSqlwhere())).append(SUF_MARK);

        if(!"".equals(Util.null2String(weaTable.getSqlorderby()))){

            tableString.append(" sqlorderby=\"").append(weaTable.getSqlorderby()).append(SUF_MARK);

            tableString.append(" sqlsortway=\"").append(weaTable.getSqlsortway()).append(SUF_MARK);

        }

        if(!"".equals(Util.null2String(weaTable.getSqlgroupby()))){

            tableString.append(" sqlgroupby=\"").append(weaTable.getSqlgroupby()).append(SUF_MARK);

        }

        tableString.append(" sqlprimarykey=\"").append(weaTable.getSqlprimarykey()).append(SUF_MARK);

        tableString.append(" sqlisdistinct=\"").append(weaTable.getSqlisdistinct()).append(SUF_MARK);

        String sumColumns =  Util.null2String(weaTable.getSumColumns());

        if(!"".equals(sumColumns)){

            tableString.append(" sumColumns=\"").append(sumColumns).append(SUF_MARK);

        }

        String sumValues = Util.null2String(weaTable.getSumValues());

        if(!"".equals(sumValues)) {

            tableString.append(" sumValues=\"").append(sumValues).append(SUF_MARK);

        }

        String decimalFormat  = Util.null2String(weaTable.getDecimalFormat());

        if(!"".equals(decimalFormat)){

            tableString.append(" decimalFormat=\"").append(decimalFormat).append(SUF_MARK);

        }

        String poolname = Util.null2String(weaTable.getPoolname());

        if(!"".equals(poolname)){

            tableString.append(" poolname=\"").append(poolname).append(SUF_MARK);

        }

        if(!"".equals(Util.null2String(weaTable.getCountColumnsDbType()))){

            tableString.append(" countcolumnsdbtype=\"").append(weaTable.getCountColumnsDbType()).append(SUF_MARK);

        }

        if(!"".equals(Util.null2String(weaTable.getFastpage())) ) {

            tableString.append(" fastpage=\"").append(weaTable.getFastpage()).append(SUF_MARK);

        }

        if(weaTable.getOpenPrimaryKeyOrder()== WeaBooleanEnum.TRUE) {

            tableString.append(" openprimarykeyorder=\"").append(weaTable.getOpenPrimaryKeyOrder().getStringVal()).append(SUF_MARK);

        }

        if(weaTable.getMergeOrder()== WeaBooleanEnum.TRUE) {

            tableString.append(" mergeorder=\"").append(weaTable.getMergeOrder().getStringVal()).append(SUF_MARK);

        }

        tableString.append("/>");

        //checkboxpopedom
        AbstractWeaCheckboxPopedom checkboxpopedom =  weaTable.getCheckboxpopedom();

        if(checkboxpopedom != null){

            tableString.append("<checkboxpopedom  id=\"").append(Util.null2String(checkboxpopedom.getId())).append("\"");

            tableString.append(" popedompara=\"").append(Util.null2String(checkboxpopedom.getPopedompara())).append("\"");

            tableString.append(" showmethod=\"").append(Util.null2String(checkboxpopedom.getShowmethod())).append("\" />");

        }

        List<AbstractWeaCheckboxPopedom> checkboxList = weaTable.getCheckboxList();

        if(checkboxList != null && checkboxList.size() > 0) {

            tableString.append("<checkboxList>");

            for(AbstractWeaCheckboxPopedom item:checkboxList)

            {

                tableString.append("<checkboxpopedom  id=\"").append(Util.null2String(item.getId())).append("\"");

                tableString.append(" popedompara=\"").append(Util.null2String(item.getPopedompara())).append("\"");

                tableString.append(" showmethod=\"").append(Util.null2String(item.getShowmethod())).append("\" />");

            }

            tableString.append("</checkboxList>");

        }

        //operates
        AbstractWeaTableOperates operateBean  = weaTable.getOperates();

        if(operateBean != null){

            tableString.append("<operates>");

            //popedom

            AbstractWeaTablePopedom popedom = operateBean.getPopedom();

            if(popedom != null){

                tableString.append("<popedom ");

                tableString.append(" async=\"").append(popedom.isAsync()).append("\"");

                tableString.append(" transmethod=\"").append(Util.null2String(popedom.getTransmethod())).append("\"");

                tableString.append(" otherpara=\"").append(Util.null2String(popedom.getOtherpara())).append("\"");

                tableString.append(" otherpara2=\"").append(Util.null2String(popedom.getOtherpara2())).append("\" ></popedom>");

            }

            //operate
            List<AbstractWeaTableOperate> operates = operateBean.getOperate();

            if(operates != null && operates.size() > 0){

                for(AbstractWeaTableOperate operate:operates){

                    tableString.append("<operate ");

                    tableString.append(" href=\"").append(Util.null2String(operate.getHref())).append("\"");

                    tableString.append(" text=\"").append(Util.null2String(operate.getText())).append("\"");

                    tableString.append(" otherpara=\"").append(Util.null2String(operate.getOtherpara())).append("\"");

                    tableString.append(" index=\"").append(Util.null2String(operate.getIndex())).append("\"");

                    if(!"".equals(Util.null2String(operate.getLinkvaluecolumn())))tableString.append(" linkvaluecolumn=\"").append(Util.null2String(operate.getLinkvaluecolumn())).append("\"");

                    if(!"".equals(Util.null2String(operate.getLinkkey())))tableString.append(" linkkey=\"").append(Util.null2String(operate.getLinkkey())).append("\"");

                    if(!"".equals(Util.null2String(operate.getTarget()))) tableString.append(" target=\"").append(Util.null2String(operate.getTarget())).append("\"");

                    if(!"".equals(Util.null2String(operate.getCusWidth())))tableString.append(" cusWidth=\"").append(Util.null2String(operate.getCusWidth())).append("\"");

                    if(!"".equals(Util.null2String(operate.getCusHeight())))tableString.append(" cusHeight=\"").append(Util.null2String(operate.getCusHeight())).append("\"");

                    if(!"".equals(Util.null2String(operate.getIsalwaysshow())))tableString.append(" isalwaysshow=\"").append(Util.null2String(operate.getIsalwaysshow())).append("\"");

                    tableString.append(" />");

                }

            }

            tableString.append("</operates>");
        }

        String primarykey = Util.null2String(weaTable.getSqlprimarykey());

        if(primarykey.indexOf(".") > 0){

            primarykey = primarykey.substring(primarykey.indexOf(".") + 1);

        }

        // col
        tableString.append("<head>");

        List<AbstractWeaTableColumn> cols = weaTable.getColumns();

        for (AbstractWeaTableColumn colBean : cols) {

            tableString.append("<col hide=\"").append(colBean.getHide()).append(SUF_MARK);

            if (colBean.getWidth() != null) {

                tableString.append(" width=\"").append(colBean.getWidth()).append(SUF_MARK);

            }

            if (colBean.getBelong() != WeaBelongEnum.PC) {

                tableString.append(" belong=\"").append(colBean.getBelong().getStringVal()).append(SUF_MARK);

            }

            if (colBean.getMobileviewtype() != WeaMobileViewTypeEnum.HIGHLIGHT) {

                tableString.append(" mobileviewtype=\"").append(colBean.getMobileviewtype().getStringVal()).append(SUF_MARK);

            }

            if (colBean.getMobiletransmethod() != null) {

                tableString.append(" mobiletransmethod=\"").append(colBean.getMobiletransmethod()).append(SUF_MARK);

            }

            if (colBean.getMobileotherpara() != null) {

                tableString.append(" mobileotherpara=\"").append(colBean.getMobileotherpara()).append(SUF_MARK);

            }

            if (colBean.getAlign() != null) {

                tableString.append(" align=\"").append(colBean.getAlign()).append(SUF_MARK);

            }

            if (colBean.getDataalign() != null) {

                tableString.append(" dataalign=\"").append(colBean.getDataalign()).append(SUF_MARK);

            }

            if (colBean.getText() != null) {

                tableString.append(" text=\"").append(colBean.getText()).append(SUF_MARK);

            }

            if (colBean.getColumn() != null) {

                tableString.append(" column=\"").append(colBean.getColumn()).append(SUF_MARK);

            }

            if (colBean.getOrderkey() != null) {

                tableString.append(" orderkey=\"").append(colBean.getOrderkey()).append(SUF_MARK);

            }

            if (colBean.getTransmethod() != null) {

                tableString.append(" transmethod=\"").append(colBean.getTransmethod()).append(SUF_MARK);

                if("".equals(Util.null2String(colBean.getDisplay()))) {

                    tableString.append(" display=\"true\" ");

                }

            }

            if (colBean.getTransMethodForce() != null) {

                tableString.append(" transMethodForce=\"").append(colBean.getTransMethodForce()).append(SUF_MARK);

            }

            if (colBean.getOtherpara() != null) {

                tableString.append(" otherpara=\"").append(colBean.getOtherpara()).append(SUF_MARK);

            }

            if(colBean.getOtherpara2() != null) {

                tableString.append(" otherpara2=\"").append(colBean.getOtherpara2()).append(SUF_MARK);

            }

            if (colBean.getCustomCol() != null) {

                tableString.append(" customCol=\"").append(colBean.getCustomCol()).append(SUF_MARK);
            }

            if (colBean.getKey() != null) {

                tableString.append(" _key=\"").append(colBean.getKey()).append(SUF_MARK);

            }

            if (colBean.getCollapse() != null) {

                tableString.append(" collapse=\"").append(colBean.getCollapse()).append(SUF_MARK);

            }

            if(colBean.getDisplay() != null) {

                tableString.append(" display=\"").append(colBean.getDisplay()).append("\" ");

            }

            tableString.append(" showType=\"").append(colBean.getShowType()).append(SUF_MARK);

            tableString.append(" isInputCol=\"").append(colBean.getIsInputCol().toString()).append(SUF_MARK);

            WeaBooleanEnum isPrimarykey  = WeaBooleanEnum.TRUE == colBean.getIsPrimarykey() ? colBean.getIsPrimarykey() : (Util.null2String(colBean.getColumn()).equalsIgnoreCase(primarykey) ? WeaBooleanEnum.TRUE: WeaBooleanEnum.FALSE);

            tableString.append(" isPrimarykey=\"").append(isPrimarykey.toString()).append(SUF_MARK);

            if(colBean.getIsBase64()== WeaBooleanEnum.TRUE) {

                tableString.append(" isBase64=\"").append(colBean.getIsBase64().getStringVal()).append(SUF_MARK);

            }

            if(colBean.getFixed() != null) {

                tableString.append(" fixed=\"").append(colBean.getFixed()).append(SUF_MARK);

            }

            tableString.append("/>");

        }

        tableString.append("</head>");

        tableString.append("</table>");

        return tableString.toString();

    }

}
