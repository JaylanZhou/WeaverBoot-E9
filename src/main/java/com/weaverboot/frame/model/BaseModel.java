package com.weaverboot.frame.model;

import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.enumTools.frame.OrderByCondition;

import java.lang.reflect.Field;

/**
 *
 * 基本实体类
 * 注意 : tableName必须Set，否则会找不到表名
 *
 * 建议 : 搭配生成器一起用，可以免去配置的麻烦，如需生成器请联系 Jaylan Zhou
 *
 * @author : Jaylan Zhou
 *
 */

public abstract class BaseModel {

    private String tableName;

    private int authorNumber;

    private boolean isDistinct;

    private String[] orderByContent = null;

    private OrderByCondition orderByCondition;

//    private WeaTable weaTable;

    private int uid;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getAuthorNumber() {
        return authorNumber;
    }

    public void setAuthorNumber(int authorNumber) {
        this.authorNumber = authorNumber;
    }

    public String[] getOrderByContent() {
        return orderByContent;
    }

    @Deprecated
    public void setOrderByContent(String ... orderByContent) {
        this.orderByContent = orderByContent;
    }

    public OrderByCondition getOrderByCondition() {
        return orderByCondition;
    }

    @Deprecated
    public void setOrderByCondition(OrderByCondition orderByCondition) {
        this.orderByCondition = orderByCondition;
    }

    public boolean getIsDistinct() {
        return isDistinct;
    }

    public void setIsDistinct(boolean distinct) {
        isDistinct = distinct;
    }

//    public WeaTable getWeaTable() {
//        return weaTable;
//    }
//
//    public void setWeaTable(WeaTable weaTable) {
//        this.weaTable = weaTable;
//    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setOrderBy(OrderByCondition orderByCondition,String ... orderByContent){

        this.orderByCondition = orderByCondition;

        this.orderByContent = orderByContent;

    }

    protected String getAllColumns(Class inClass){

        Field[] fields = inClass.getDeclaredFields();

        StringBuilder allColumnsBuilder = new StringBuilder();

        for (Field field : fields
             ) {

            if (BaseTools.checkIsAbleColumn(field)){

                allColumnsBuilder.append(field.getName()).append(",");

            }

        }

        String allColumns = allColumnsBuilder.toString();

        if (BaseTools.notNullString(allColumns)){

            allColumns = allColumns.substring(0,allColumns.length() - 1);

        }

        return allColumns;

    }

}
