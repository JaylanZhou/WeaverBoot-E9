package com.weaverboot.weaComponent.impl.weaTable.table.inte;

import com.api.browser.util.BoolAttr;
import com.cloudstore.eccom.constant.WeaBoolAttr;
import com.cloudstore.eccom.constant.WeaMobileShowType;
import com.cloudstore.eccom.pc.table.WeaTableCheckboxpopedom;
import com.cloudstore.eccom.pc.table.WeaTableColumn;
import com.cloudstore.eccom.pc.table.WeaTableOperates;
import com.cloudstore.eccom.pc.table.WeaTableType;
import com.weaverboot.weaComponent.impl.weaTable.column.inte.AbstractWeaTableColumn;
import com.weaverboot.weaComponent.inte.AbstractWeaComponent;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWeaTable extends AbstractWeaComponent {

    // table
    private String pageUID;

    private String pageID;

    private WeaTableType tableType=WeaTableType.NONE;//支持 none ,check ,

    private String pagesize;

    private String instanceid;

    private String datasource;

    private String sourceparams;

    private String pageBySelf;
    // sql
    private String backfields;

    private String sqlform;

    private String sqlwhere;

    private String sqlorderby;

    private String sqlprimarykey;

    private String sqlsortway = "";

    private String sqlisdistinct;

    private String poolname;

    private String sqlgroupby;

    //行颜色控制字段
    private String rowstylefield;

    //是否打开主键排序字段
    private WeaBoolAttr openPrimaryKeyOrder = WeaBoolAttr.FALSE;

    //是否打开复合排序
    private WeaBoolAttr mergeOrder = WeaBoolAttr.FALSE;

    //快速分页 true 或 false
    private String fastpage;
    //合计
    private String sumColumns;

    private String sumValues;

    private String decimalFormat;
    //列相关
    private List<AbstractWeaTableColumn> columns;
    //操作菜单相关 operate的权限控制
    private WeaTableOperates operates;
    //主要用于checkbox 权限控制
    private WeaTableCheckboxpopedom checkboxpopedom;
    //主要用于控制多套checkbox控制 一般不用
    private List<WeaTableCheckboxpopedom> checkboxList;

    //千分位合计处理字段 字段名逗号分隔
    private String countColumnsDbType;

    //移动端属性 single multi
    private WeaMobileShowType mobileshowtype=WeaMobileShowType.ListView;

    //移动端模版属性
    private String mobileshowtemplate;

    public AbstractWeaTable() {

        this.tableType = WeaTableType.NONE;

        this.openPrimaryKeyOrder = WeaBoolAttr.FALSE.FALSE;

        this.mobileshowtype = WeaMobileShowType.ListView;

        this.columns = new ArrayList();

        this.operates = new WeaTableOperates();

        this.checkboxpopedom = new WeaTableCheckboxpopedom();

        this.checkboxList = new ArrayList();
    }

    public String getPageUID() {
        return pageUID;
    }

    public void setPageUID(String pageUID) {
        this.pageUID = pageUID;
    }

    public String getPageID() {
        return pageID;
    }

    public void setPageID(String pageID) {
        this.pageID = pageID;
    }

    public WeaTableType getTableType() {
        return tableType;
    }

    public void setTableType(WeaTableType tableType) {
        this.tableType = tableType;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getInstanceid() {
        return instanceid;
    }

    public void setInstanceid(String instanceid) {
        this.instanceid = instanceid;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public String getSourceparams() {
        return sourceparams;
    }

    public void setSourceparams(String sourceparams) {
        this.sourceparams = sourceparams;
    }

    public String getPageBySelf() {
        return pageBySelf;
    }

    public void setPageBySelf(String pageBySelf) {
        this.pageBySelf = pageBySelf;
    }

    public String getBackfields() {
        return backfields;
    }

    public void setBackfields(String backfields) {
        this.backfields = backfields;
    }

    public String getSqlform() {
        return sqlform;
    }

    public void setSqlform(String sqlform) {
        this.sqlform = sqlform;
    }

    public String getSqlwhere() {
        return sqlwhere;
    }

    public void setSqlwhere(String sqlwhere) {
        this.sqlwhere = sqlwhere;
    }

    public String getSqlorderby() {
        return sqlorderby;
    }

    public void setSqlorderby(String sqlorderby) {
        this.sqlorderby = sqlorderby;
    }

    public String getSqlprimarykey() {
        return sqlprimarykey;
    }

    public void setSqlprimarykey(String sqlprimarykey) {
        this.sqlprimarykey = sqlprimarykey;
    }

    public String getSqlsortway() {
        return sqlsortway;
    }

    public void setSqlsortway(String sqlsortway) {
        this.sqlsortway = sqlsortway;
    }

    public String getSqlisdistinct() {
        return sqlisdistinct;
    }

    public void setSqlisdistinct(String sqlisdistinct) {
        this.sqlisdistinct = sqlisdistinct;
    }

    public String getPoolname() {
        return poolname;
    }

    public void setPoolname(String poolname) {
        this.poolname = poolname;
    }

    public String getSqlgroupby() {
        return sqlgroupby;
    }

    public void setSqlgroupby(String sqlgroupby) {
        this.sqlgroupby = sqlgroupby;
    }

    public String getRowstylefield() {
        return rowstylefield;
    }

    public void setRowstylefield(String rowstylefield) {
        this.rowstylefield = rowstylefield;
    }

    public WeaBoolAttr getOpenPrimaryKeyOrder() {
        return openPrimaryKeyOrder;
    }

    public void setOpenPrimaryKeyOrder(WeaBoolAttr openPrimaryKeyOrder) {
        this.openPrimaryKeyOrder = openPrimaryKeyOrder;
    }

    public WeaBoolAttr getMergeOrder() {
        return mergeOrder;
    }

    public void setMergeOrder(WeaBoolAttr mergeOrder) {
        this.mergeOrder = mergeOrder;
    }

    public String getFastpage() {
        return fastpage;
    }

    public void setFastpage(String fastpage) {
        this.fastpage = fastpage;
    }

    public String getSumColumns() {
        return sumColumns;
    }

    public void setSumColumns(String sumColumns) {
        this.sumColumns = sumColumns;
    }

    public String getSumValues() {
        return sumValues;
    }

    public void setSumValues(String sumValues) {
        this.sumValues = sumValues;
    }

    public String getDecimalFormat() {
        return decimalFormat;
    }

    public void setDecimalFormat(String decimalFormat) {
        this.decimalFormat = decimalFormat;
    }

    public List<AbstractWeaTableColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<AbstractWeaTableColumn> columns) {
        this.columns = columns;
    }

    public WeaTableOperates getOperates() {
        return operates;
    }

    public void setOperates(WeaTableOperates operates) {
        this.operates = operates;
    }

    public WeaTableCheckboxpopedom getCheckboxpopedom() {
        return checkboxpopedom;
    }

    public void setCheckboxpopedom(WeaTableCheckboxpopedom checkboxpopedom) {
        this.checkboxpopedom = checkboxpopedom;
    }

    public List<WeaTableCheckboxpopedom> getCheckboxList() {
        return checkboxList;
    }

    public void setCheckboxList(List<WeaTableCheckboxpopedom> checkboxList) {
        this.checkboxList = checkboxList;
    }

    public String getCountColumnsDbType() {
        return countColumnsDbType;
    }

    public void setCountColumnsDbType(String countColumnsDbType) {
        this.countColumnsDbType = countColumnsDbType;
    }

    public WeaMobileShowType getMobileshowtype() {
        return mobileshowtype;
    }

    public void setMobileshowtype(WeaMobileShowType mobileshowtype) {
        this.mobileshowtype = mobileshowtype;
    }

    public String getMobileshowtemplate() {
        return mobileshowtemplate;
    }

    public void setMobileshowtemplate(String mobileshowtemplate) {
        this.mobileshowtemplate = mobileshowtemplate;
    }
}
