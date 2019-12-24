package com.weaverboot.tools.componentTools.table;
import com.cloudstore.dev.api.util.Util_TableMap;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.enumTools.weaComponent.*;
import com.weaverboot.tools.logTools.LogTools;
import com.weaverboot.weaComponent.impl.weaTable.checkboxPopedom.impl.DefaultWeaCheckboxPopedom;
import com.weaverboot.weaComponent.impl.weaTable.checkboxPopedom.inte.AbstractWeaCheckboxPopedom;
import com.weaverboot.weaComponent.impl.weaTable.column.impl.DefaultWeaTableColumn;
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

            abstractWeaTable.setSqlform(sql.getAttributeValue("sqlform"));

            abstractWeaTable.setSqlwhere(sql.getAttributeValue("sqlwhere"));

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

                    AbstractWeaTableColumn col = new DefaultWeaTableColumn(); //暂时以默认字段为根据，日后调整

                    col.setHide(WeaBooleanEnum.TRUE.getStringVal().equals(el.getAttributeValue("hide")) ? WeaBooleanEnum.TRUE : WeaBooleanEnum.FALSE);

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

                    col.setCollapse(el.getAttributeValue("collapse"));

                    col.setDisplay(WeaBooleanEnum.TRUE.getBooleanStringVal().equals(el.getAttributeValue("display")) ? WeaBooleanEnum.TRUE : WeaBooleanEnum.FALSE);

//                    col.setClassName(el.getAttributeValue("className"));

                    col.setIsBase64(WeaBooleanEnum.TRUE.getStringVal().equals(el.getAttributeValue("isBase64")) ? WeaBooleanEnum.TRUE : WeaBooleanEnum.FALSE);

                    col.setFixed(el.getAttributeValue("fixed"));

                    col.setShowType(Util.getIntValue(el.getAttributeValue("showType"), 0));

                    col.setIsInputCol(WeaBooleanEnum.TRUE.getStringVal().equals(el.getAttributeValue("isInputCol")) ? WeaBooleanEnum.TRUE : WeaBooleanEnum.FALSE);

                    col.setIsPrimarykey(WeaBooleanEnum.TRUE.getStringVal().equals(el.getAttributeValue("isPrimarykey")) ? WeaBooleanEnum.TRUE : WeaBooleanEnum.FALSE);

                    weaTableColumnArrayList.add(col);

                }

                abstractWeaTable.setColumns(weaTableColumnArrayList);

            }

        }

    }

}
