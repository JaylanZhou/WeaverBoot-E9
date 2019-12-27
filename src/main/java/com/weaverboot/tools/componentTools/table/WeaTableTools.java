package com.weaverboot.tools.componentTools.table;
import com.cloudstore.dev.api.util.Util_TableMap;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.enumTools.weaComponent.*;
import com.weaverboot.tools.logTools.LogTools;
import com.weaverboot.weaComponent.impl.weaTable.checkboxPopedom.impl.DefaultWeaCheckboxPopedom;
import com.weaverboot.weaComponent.impl.weaTable.checkboxPopedom.inte.AbstractWeaCheckboxPopedom;
import com.weaverboot.weaComponent.impl.weaTable.column.factory.impl.DefaultWeaTableColumnFactory;
import com.weaverboot.weaComponent.impl.weaTable.column.factory.inte.WeaTableColumnFactory;
import com.weaverboot.weaComponent.impl.weaTable.column.impl.DefaultWeaTableColumn;
import com.weaverboot.weaComponent.impl.weaTable.column.impl.DocWeaTableColumn;
import com.weaverboot.weaComponent.impl.weaTable.column.inte.AbstractWeaTableColumn;
import com.weaverboot.weaComponent.impl.weaTable.operate.impl.DefaultWeaTableOperate;
import com.weaverboot.weaComponent.impl.weaTable.operate.inte.AbstractWeaTableOperate;
import com.weaverboot.weaComponent.impl.weaTable.operates.impl.DefaultWeaTableOperates;
import com.weaverboot.weaComponent.impl.weaTable.operates.inte.AbstractWeaTableOperates;
import com.weaverboot.weaComponent.impl.weaTable.table.inte.AbstractWeaTable;
import com.weaverboot.weaComponent.impl.weaTable.tablePopedom.impl.DefaultWeaTablePopedom;
import com.weaverboot.weaComponent.impl.weaTable.tablePopedom.inte.AbstractWeaTablePopedom;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;
import weaver.general.Util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 组件：WeaTable 工具类
 *
 * @Author : Jaylan Zhou
 * @RefAuthor : Andy Zhang
 * @Date : 2019-12-23 16:40
 * @Version : 1.0
 */

public class WeaTableTools {

    private static final String SUF_MARK = "\"";

    private static WeaTableColumnFactory weaTableColumnFactory = new DefaultWeaTableColumnFactory();

    private WeaTableTools(){



    }

    /**
     * checkTableStringData 判断是否有设置
     * @return
     */
    public static <T extends AbstractWeaTable> T checkTableStringConfig(String tableKey, Class<T> tClass) throws Exception {

        String xmlString = Util_TableMap.getVal(tableKey);

        if (!BaseTools.notNullString(xmlString))

        {

            LogTools.writeLog("xmlString is null!!");

            throw new Exception("tableString为null！sessionKey: " + tableKey);

        }

        StringReader xmlReader = new StringReader(xmlString);

        InputSource xmlSource = new InputSource(xmlReader);

        SAXBuilder builder = new SAXBuilder();

        Document doc = builder.build(xmlSource);

        Element root = doc.getRootElement(); // 根节点

        if (root == null) {

            LogTools.writeLog("element is null!!");

            throw new Exception("tableString为null！sessionKey: " + tableKey);
        }

        AbstractWeaTable abstractWeaTable = tClass.newInstance();

        parseWeaTableBasic(abstractWeaTable,root);

        parseWeaTableSql(abstractWeaTable,root);

        parseDefaultWeaCheckboxPopedom(abstractWeaTable,root);

        parseWeaTableCheckboxList(abstractWeaTable,root);

        parseWeaTableOperates(abstractWeaTable,root);

        parseWeaTableHead(abstractWeaTable,root);

        return (T)abstractWeaTable;

    }

    public static void setTableStringVal(String sessionKey,String tableString){

        Util_TableMap.setVal(sessionKey, tableString);

    }

    /**
     *
     * 解析table基本信息
     *
     * @param abstractWeaTable 拼装的table
     * @param root 根节点
     * @return
     */

    private static void parseWeaTableBasic(AbstractWeaTable abstractWeaTable,Element root){



        switch (root.getAttributeValue("tabletype")){

            case "none" :

                abstractWeaTable.setTableType(WeaTableTypeEnum.NONE);

                break;

            case "radio" :

                abstractWeaTable.setTableType(WeaTableTypeEnum.RADIO);

                break;

            case "checkbox" :

                abstractWeaTable.setTableType(WeaTableTypeEnum.CHECKBOX);

                break;

            case "thumbnail" :

                abstractWeaTable.setTableType(WeaTableTypeEnum.THUMBNAIL);

                break;

            case "officalDoc" :

                abstractWeaTable.setTableType(WeaTableTypeEnum.OFFICALDOC);

                break;


            default:

                break;
        }

        abstractWeaTable.setPageUID(root.getAttributeValue("pageUid"));

        abstractWeaTable.setPageID(root.getAttributeValue("pageId"));

        abstractWeaTable.setPagesize(root.getAttributeValue("pagesize"));

        abstractWeaTable.setDatasource(root.getAttributeValue("datasource"));

        abstractWeaTable.setSourceparams(root.getAttributeValue("sourceparams"));

        abstractWeaTable.setPageBySelf(root.getAttributeValue("pageBySelf"));

        abstractWeaTable.setInstanceid(root.getAttributeValue("instanceid"));

        abstractWeaTable.setRandomFieldOpen(WeaBooleanEnum.TRUE.getStringVal().equals(root.getAttributeValue("randomfieldopen"))? WeaBooleanEnum.TRUE: WeaBooleanEnum.FALSE);

        abstractWeaTable.setMobileshowtype(WeaMobileShowTypeEnum.ListView.getStringVal().equals(root.getAttributeValue("mobileshowtype"))? WeaMobileShowTypeEnum.ListView: WeaMobileShowTypeEnum.Table);

        abstractWeaTable.setMobileshowtemplate(root.getAttributeValue("mobileshowtemplate"));

//        abstractWeaTable.setCounttransmethod(BoolAttr.TRUE.getStringVal().equals(root.getAttributeValue("counttransmethod"))?BoolAttr.TRUE:BoolAttr.FALSE);

//        abstractWeaTable.setConfigMethod(root.getAttributeValue("configmethod"));

        abstractWeaTable.setPageBySelf(root.getAttributeValue("pageBySelf"));

    }

    /**
     *
     * 拼装table的sql部分信息
     *
     * @param abstractWeaTable
     * @param root
     * @return
     */

    private static void parseWeaTableSql(AbstractWeaTable abstractWeaTable,Element root){

        Element sql = root.getChild("sql");

        if (sql!=null) {

            abstractWeaTable.setBackfields(sql.getAttributeValue("backfields"));

            String sqlForm = sql.getAttributeValue("sqlform").replaceAll("\\\\'","'");

            abstractWeaTable.setSqlform(sqlForm);

            String sqlwhere = sql.getAttributeValue("sqlwhere").replaceAll("\\\\'","'");

            abstractWeaTable.setSqlwhere(sqlwhere);

            abstractWeaTable.setSqlorderby(sql.getAttributeValue("sqlorderby"));

            abstractWeaTable.setSqlsortway(sql.getAttributeValue("sqlsortway"));

            abstractWeaTable.setSqlgroupby(sql.getAttributeValue("sqlgroupby"));

            abstractWeaTable.setSqlprimarykey(sql.getAttributeValue("sqlprimarykey"));

            abstractWeaTable.setSqlisdistinct(sql.getAttributeValue("sqlisdistinct"));

            abstractWeaTable.setSumColumns(sql.getAttributeValue("sumColumns"));

            abstractWeaTable.setSumValues(sql.getAttributeValue("sumValues"));

            abstractWeaTable.setDecimalFormat(sql.getAttributeValue("decimalFormat"));

            abstractWeaTable.setPoolname(sql.getAttributeValue("poolname"));

            abstractWeaTable.setPoolname(sql.getAttributeValue("countcolumnsdbtype"));

            abstractWeaTable.setFastpage(sql.getAttributeValue("fastpage"));

            abstractWeaTable.setOpenPrimaryKeyOrder(WeaBooleanEnum.TRUE.getStringVal().equals(sql.getAttributeValue("openprimarykeyorder"))? WeaBooleanEnum.TRUE: WeaBooleanEnum.FALSE);

            abstractWeaTable.setMergeOrder(WeaBooleanEnum.TRUE.getStringVal().equals(sql.getAttributeValue("mergeorder"))? WeaBooleanEnum.TRUE: WeaBooleanEnum.FALSE);

        }

    }

    /**
     *
     * 拼装checkboxpopedom部分信息
     *
     * @param abstractWeaTable
     * @param root
     * @return
     */

    private static void parseDefaultWeaCheckboxPopedom(AbstractWeaTable abstractWeaTable,Element root){

        Element checkboxpopedom = root.getChild("checkboxpopedom");

        if (checkboxpopedom!=null) {

            abstractWeaTable.setCheckboxpopedom(new DefaultWeaCheckboxPopedom());

            abstractWeaTable.getCheckboxpopedom().setId(checkboxpopedom.getAttributeValue("id"));

            abstractWeaTable.getCheckboxpopedom().setPopedompara(checkboxpopedom.getAttributeValue("popedompara"));

            abstractWeaTable.getCheckboxpopedom().setShowmethod(checkboxpopedom.getAttributeValue("showmethod"));

        }

    }

    /**
     *
     * 拼装checkboxlist 部分信息
     *
     * @param abstractWeaTable
     * @param root
     * @return
     */

    private static void parseWeaTableCheckboxList(AbstractWeaTable abstractWeaTable,Element root){

        Element checkboxList = root.getChild("checkboxList");

        if (checkboxList!=null) {

            abstractWeaTable.setCheckboxList(new ArrayList<AbstractWeaCheckboxPopedom>());

            for(Object item:checkboxList.getChildren()) {

                Element el=(Element)item;

                DefaultWeaCheckboxPopedom dom = new DefaultWeaCheckboxPopedom();

                dom.setId(el.getAttributeValue("id"));

                dom.setPopedompara(el.getAttributeValue("popedompara"));

                dom.setShowmethod(el.getAttributeValue("showmethod"));

                abstractWeaTable.getCheckboxList().add(dom);

            }

        }

    }

    /**
     *
     * 拼装tableoperates 部分信息
     *
     * @param abstractWeaTable
     * @param root
     * @return
     */

    private static void parseWeaTableOperates(AbstractWeaTable abstractWeaTable,Element root){

        Element operates = root.getChild("operates");

        if (operates!=null) {

            Element popedom = operates.getChild("popedom");

            AbstractWeaTableOperates stob = new DefaultWeaTableOperates(); //暂时以defaultOperates代替

            if(popedom!=null)

            {

                AbstractWeaTablePopedom pdom = new DefaultWeaTablePopedom(); // 暂时以default 代替

                pdom.setAsync(Boolean.valueOf(popedom.getAttributeValue("async")));

                pdom.setOtherpara(popedom.getAttributeValue("otherpara"));

                pdom.setOtherpara2(popedom.getAttributeValue("otherpara2"));

                pdom.setTransmethod(popedom.getAttributeValue("transmethod"));

                stob.setPopedom(pdom);

            }

            List<AbstractWeaTableOperate> list=new ArrayList<>();

            List operateList=operates.getChildren("operate");

            if(operateList!=null) {

                for (Object item : operateList) {

                    Element el = (Element) item;

                    AbstractWeaTableOperate opt = new DefaultWeaTableOperate(); //暂时以default代替

                    opt.setText(el.getAttributeValue("text"));

                    opt.setHref(el.getAttributeValue("href"));

                    opt.setOtherpara(el.getAttributeValue("otherpara"));

                    opt.setIndex(el.getAttributeValue("index"));

                    list.add(opt);

                }

            }

            stob.setOperate(list);

            abstractWeaTable.setOperates(stob);

        }

    }

    /**
     *
     * 拼装head部分信息
     *
     * @param abstractWeaTable
     * @param root
     * @return
     */

    private static void parseWeaTableHead(AbstractWeaTable abstractWeaTable,Element root){

        Element head = root.getChild("head");

        if (head!=null) {

            List<AbstractWeaTableColumn> weaTableColumnArrayList = new ArrayList<>();

            List colList = head.getChildren("col");

            if(colList!=null) {

                for (Object item : colList) {

                    Element el = (Element) item;

                    AbstractWeaTableColumn col = weaTableColumnFactory.buildWeaTableColumn(abstractWeaTable); //暂时以默认字段为根据，日后调整

                    col.setHide(WeaBooleanEnum.TRUE.getBooleanStringVal().equals(el.getAttributeValue("hide")) ? WeaBooleanEnum.TRUE : WeaBooleanEnum.FALSE);

                    col.setWidth(el.getAttributeValue("width"));

                    switch (Util.null2String(el.getAttributeValue("belong"))){

                        case "0" :

                            col.setBelong(WeaBelongEnum.PC);

                            break;

                        case "1" :

                            col.setBelong(WeaBelongEnum.MOBILE);

                            break;

                        case "2" :

                            col.setBelong(WeaBelongEnum.PCMOBILE);

                            break;


                        default:

                            break;
                    }

                    col.setMobileviewtype(WeaMobileViewTypeEnum.HIGHLIGHT.equals(el.getAttributeValue("mobileviewtype")) ? WeaMobileViewTypeEnum.HIGHLIGHT : WeaMobileViewTypeEnum.DETAIL);

                    col.setMobiletransmethod(el.getAttributeValue("mobiletransmethod"));

                    col.setMobileotherpara(el.getAttributeValue("mobileotherpara"));

                    col.setText(el.getAttributeValue("text"));

                    col.setColumn(el.getAttributeValue("column"));

                    col.setOrderkey(el.getAttributeValue("orderkey"));

                    col.setTransmethod(el.getAttributeValue("transmethod"));

                    col.setTransMethodForce(el.getAttributeValue("transMethodForce"));

                    col.setOtherpara(el.getAttributeValue("otherpara"));

                    col.setOtherpara2(el.getAttributeValue("otherpara2"));

                    col.setKey(el.getAttributeValue("_key"));

                    col.setLabelid(el.getAttributeValue("labelid"));

                    col.setCollapse(el.getAttributeValue("collapse"));

                    col.setDisplay(WeaBooleanEnum.FALSE.getBooleanStringVal().equals(el.getAttributeValue("display")) ? WeaBooleanEnum.FALSE : WeaBooleanEnum.TRUE);

//                    col.setClassName(el.getAttributeValue("className"));

                    col.setIsBase64(WeaBooleanEnum.TRUE.getStringVal().equals(el.getAttributeValue("isBase64")) ? WeaBooleanEnum.TRUE : WeaBooleanEnum.FALSE);

                    col.setFixed(el.getAttributeValue("fixed"));

                    col.setShowType(Util.getIntValue(el.getAttributeValue("showType"), 0));

                    col.setIsInputCol(WeaBooleanEnum.TRUE.getStringVal().equals(el.getAttributeValue("isInputCol")) ? WeaBooleanEnum.TRUE : WeaBooleanEnum.FALSE);

                    col.setIsPrimarykey(WeaBooleanEnum.TRUE.getStringVal().equals(el.getAttributeValue("isPrimarykey")) ? WeaBooleanEnum.TRUE : WeaBooleanEnum.FALSE);

                    if (col instanceof DocWeaTableColumn){

                        parseDocCol((DocWeaTableColumn) col,el);

                    }

                    weaTableColumnArrayList.add(col);

                }

                abstractWeaTable.setColumns(weaTableColumnArrayList);

            }

        }

    }

    private static void parseDocCol(DocWeaTableColumn col,Element el){


        col.setT_column(el.getAttributeValue("t_column"));

        col.setT_icon(el.getAttributeValue("t_icon"));

        col.setT_otherpara(el.getAttributeValue("t_otherpara"));

        col.setT_type(el.getAttributeValue("t_type"));

        col.setT_text(el.getAttributeValue("t_text"));

        col.setT_showorder(el.getAttributeValue("t_showorder"));

        col.setT_transmethod(el.getAttributeValue("t_transmethod"));

    }

    public static String toTableString(AbstractWeaTable weaTable) {

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

            if (colBean.getLabelid() != null){

                tableString.append(" labelid=\"").append(colBean.getLabelid()).append("\" ");

            }

            if (colBean.getThumbnail() != null){

                tableString.append(" thumbnail=\"").append(colBean.getThumbnail()).append("\" ");

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

            if (colBean instanceof DocWeaTableColumn){

                buildDocXmlString(tableString, (DocWeaTableColumn) colBean);

            }

            tableString.append("/>");

        }

        tableString.append("</head>");

        tableString.append("</table>");

        return tableString.toString();

    }

    private static void buildDocXmlString(StringBuilder tableString,DocWeaTableColumn docWeaTableColumn){

        if(docWeaTableColumn.getT_showorder() != null) {

            tableString.append(" t_showorder=\"").append(docWeaTableColumn.getT_showorder()).append(SUF_MARK);

        }

        if(docWeaTableColumn.getT_icon() != null) {

            tableString.append(" t_icon=\"").append(docWeaTableColumn.getT_icon()).append(SUF_MARK);

        }

        if(docWeaTableColumn.getT_type() != null) {

            tableString.append(" t_type=\"").append(docWeaTableColumn.getT_type()).append(SUF_MARK);

        }

        if(docWeaTableColumn.getT_text() != null) {

            tableString.append(" t_text=\"").append(docWeaTableColumn.getT_text()).append(SUF_MARK);

        }

        if(docWeaTableColumn.getT_column() != null) {

            tableString.append(" t_column=\"").append(docWeaTableColumn.getT_column()).append(SUF_MARK);

        }

        if(docWeaTableColumn.getT_text() != null) {

            tableString.append(" t_transmethod=\"").append(docWeaTableColumn.getT_transmethod()).append(SUF_MARK);

        }

        if(docWeaTableColumn.getT_text() != null) {

            tableString.append(" t_otherpara=\"").append(docWeaTableColumn.getT_otherpara()).append(SUF_MARK);

        }



    }


}
