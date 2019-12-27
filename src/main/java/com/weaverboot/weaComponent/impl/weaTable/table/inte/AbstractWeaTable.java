package com.weaverboot.weaComponent.impl.weaTable.table.inte;
import com.weaverboot.tools.enumTools.weaComponent.WeaBooleanEnum;
import com.weaverboot.tools.enumTools.weaComponent.WeaMobileShowTypeEnum;
import com.weaverboot.tools.enumTools.weaComponent.WeaTableTypeEnum;
import com.weaverboot.weaComponent.impl.weaTable.checkboxPopedom.inte.AbstractWeaCheckboxPopedom;
import com.weaverboot.weaComponent.impl.weaTable.column.inte.AbstractWeaTableColumn;
import com.weaverboot.weaComponent.impl.weaTable.operate.inte.AbstractWeaTableOperate;
import com.weaverboot.weaComponent.impl.weaTable.operates.impl.DefaultWeaTableOperates;
import com.weaverboot.weaComponent.impl.weaTable.operates.inte.AbstractWeaTableOperates;
import com.weaverboot.weaComponent.inte.AbstractWeaComponent;
import com.weaverboot.weaComponent.impl.weaTable.checkboxPopedom.impl.DefaultWeaCheckboxPopedom;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * WeaTable 默认实现类
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2019-12-23
 *
 */

public abstract class AbstractWeaTable extends AbstractWeaComponent {

    // table
    private String pageUID;

    private String pageID;

    private WeaTableTypeEnum tableType = WeaTableTypeEnum.NONE;//支持 none ,check ,

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
    private WeaBooleanEnum openPrimaryKeyOrder = WeaBooleanEnum.FALSE;

    //是否打开复合排序
    private WeaBooleanEnum mergeOrder = WeaBooleanEnum.FALSE;

    //快速分页 true 或 false
    private String fastpage;
    //合计
    private String sumColumns;

    private String sumValues;

    private String decimalFormat;
    //列相关
    private List<AbstractWeaTableColumn> columns;
    //操作菜单相关 operate的权限控制
    private AbstractWeaTableOperates operates;
    //主要用于checkbox 权限控制
    private AbstractWeaCheckboxPopedom checkboxpopedom;
    //主要用于控制多套checkbox控制 一般不用
    private List<AbstractWeaCheckboxPopedom> checkboxList;

    //千分位合计处理字段 字段名逗号分隔
    private String countColumnsDbType;

    //移动端属性 single multi
    private WeaMobileShowTypeEnum mobileshowtype= WeaMobileShowTypeEnum.ListView;

    //移动端模版属性
    private String mobileshowtemplate;

    //dataSource数据randomField数据
    private WeaBooleanEnum randomFieldOpen;

    //合计栏Tranmethod开关 0关 1开
    private WeaBooleanEnum counttransmethod = WeaBooleanEnum.FALSE;

    public AbstractWeaTable() {

        this.tableType = WeaTableTypeEnum.NONE;

        this.openPrimaryKeyOrder = WeaBooleanEnum.FALSE.FALSE;

        this.mobileshowtype = WeaMobileShowTypeEnum.ListView;

        this.columns = new ArrayList();

        this.operates = new DefaultWeaTableOperates();

        this.checkboxpopedom = new DefaultWeaCheckboxPopedom();

        this.checkboxList = new ArrayList();

    }

    public abstract <T extends AbstractWeaTableColumn> T readWeaTableColumn(int index,Class<T> tClass);

    public abstract <T extends AbstractWeaTableColumn> T readWeaTableColumnWithName(String columnText,Class<T> tClass);

    public abstract <T extends AbstractWeaTableColumn> List<T> readWeaTableColumnWithNameArray(String columnText,Class<T> tClass);

    public abstract <T extends AbstractWeaTableColumn> T readWeaTableColumnWithColumn(String column,Class<T> tClass);

    public abstract <T extends AbstractWeaTableColumn> List<T> readWeaTableColumnWithColumnArray(String column,Class<T> tClass);

    public abstract <T extends AbstractWeaTableColumn> void addWeaTableColumn(int index,T t);

    public abstract <T extends AbstractWeaTableColumn> void addWeaTableColumn(T t);

    public abstract void removeWeaTableColumn(int index);

    public abstract void removeWeaTableColumnWithName(String columnText);

    public abstract void removeWeaTableColumnWithNameAll(String columnText);

    public abstract <T extends AbstractWeaTableOperate> T readWeaTableOperate(int index, Class<T> tClass);

    public abstract <T extends AbstractWeaTableOperate> T readWeaTableOperateWithName(String operateText,Class<T> tClass);

    public abstract <T extends AbstractWeaTableOperate> List<T> readWeaTableOperateWithNameArray(String operateText,Class<T> tClass);

    public abstract <T extends AbstractWeaTableOperate> void addWeaTableOperate(int index, T t);

    public abstract <T extends AbstractWeaTableOperate> void addWeaTableOperate(T t);

    public abstract void removeWeaTableOperate(int index);

    public abstract void removeWeaTableOperateWithName(String operateText);

    public abstract void removeWeaTableOperateWithNameAll(String operateText);

    public abstract <T extends AbstractWeaCheckboxPopedom> T readWeaCheckboxPopedom(int index,Class<T> tClass);

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

    public WeaTableTypeEnum getTableType() {
        return tableType;
    }

    public void setTableType(WeaTableTypeEnum tableType) {
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

    public WeaBooleanEnum getOpenPrimaryKeyOrder() {
        return openPrimaryKeyOrder;
    }

    public void setOpenPrimaryKeyOrder(WeaBooleanEnum openPrimaryKeyOrder) {
        this.openPrimaryKeyOrder = openPrimaryKeyOrder;
    }

    public WeaBooleanEnum getMergeOrder() {
        return mergeOrder;
    }

    public void setMergeOrder(WeaBooleanEnum mergeOrder) {
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

    public AbstractWeaTableOperates getOperates() {
        return operates;
    }

    public void setOperates(AbstractWeaTableOperates operates) {
        this.operates = operates;
    }

    public AbstractWeaCheckboxPopedom getCheckboxpopedom() {
        return checkboxpopedom;
    }

    public void setCheckboxpopedom(AbstractWeaCheckboxPopedom checkboxpopedom) {
        this.checkboxpopedom = checkboxpopedom;
    }

    public List<AbstractWeaCheckboxPopedom> getCheckboxList() {
        return checkboxList;
    }

    public void setCheckboxList(List<AbstractWeaCheckboxPopedom> checkboxList) {
        this.checkboxList = checkboxList;
    }

    public String getCountColumnsDbType() {
        return countColumnsDbType;
    }

    public void setCountColumnsDbType(String countColumnsDbType) {
        this.countColumnsDbType = countColumnsDbType;
    }

    public WeaMobileShowTypeEnum getMobileshowtype() {
        return mobileshowtype;
    }

    public void setMobileshowtype(WeaMobileShowTypeEnum mobileshowtype) {
        this.mobileshowtype = mobileshowtype;
    }

    public String getMobileshowtemplate() {
        return mobileshowtemplate;
    }

    public void setMobileshowtemplate(String mobileshowtemplate) {
        this.mobileshowtemplate = mobileshowtemplate;
    }

    public WeaBooleanEnum getRandomFieldOpen() {
        return randomFieldOpen;
    }

    public void setRandomFieldOpen(WeaBooleanEnum randomFieldOpen) {
        this.randomFieldOpen = randomFieldOpen;
    }

    public WeaBooleanEnum getCounttransmethod() {
        return counttransmethod;
    }

    public void setCounttransmethod(WeaBooleanEnum counttransmethod) {
        this.counttransmethod = counttransmethod;
    }

}
