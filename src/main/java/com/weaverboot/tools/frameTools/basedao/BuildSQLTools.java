package com.weaverboot.tools.frameTools.basedao;

import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.enumTools.frame.OrderByCondition;
import com.weaverboot.tools.enumTools.frame.SelectCondition;

import java.lang.reflect.Field;

public class BuildSQLTools {

    private BuildSQLTools(){}

    /**
     *
     * 建立查询语句
     *
     * @param columns 字段集合
     * @param selectCondition 查询条件
     * @param object 存储的对象
     * @param tClass 对象的类型
     * @return 查询语句
     * @throws Exception
     */

    public static StringBuilder buildLoadListSQL(Field[] columns,SelectCondition selectCondition,Object object,Class tClass) throws Exception {

        StringBuilder sqlBuilder = new StringBuilder();

        buildBasicLoadSQL(sqlBuilder,tClass,object,columns,selectCondition);

        buildOrderBySQL(sqlBuilder,tClass,object);

        return sqlBuilder;

    }

    /**
     *
     * 建立查询语句 - 分页
     *
     * @param columns 字段集合
     * @param selectCondition 查询条件
     * @param object 存储的对象
     * @param tClass 对象的类型
     * @param page 当前页数
     * @param pageSize 每页展示条数
     * @return 查询语句
     * @throws Exception
     */
    public static StringBuilder buildLoadListPageSQL(Field[] columns,SelectCondition selectCondition,Object object,Class tClass,int page,int pageSize) throws Exception {

        StringBuilder sqlBuilder = new StringBuilder();

        buildBasicLoadSQL(sqlBuilder,tClass,object,columns,selectCondition);

        buildPage(null,sqlBuilder,tClass,object,page,pageSize);

        return sqlBuilder;

    }

    /**
     *
     * 建立查询语句 - 分页
     *
     * @param columns 字段集合
     * @param selectCondition 查询条件
     * @param object 存储的对象
     * @param tClass 对象的类型
     * @param page 当前页数
     * @param pageSize 每页展示条数
     * @param dbType 数据库类型
     * @return
     * @throws Exception
     */

    public static StringBuilder buildLoadListPageSQL(Field[] columns,SelectCondition selectCondition,Object object,Class tClass,int page,int pageSize,String dbType) throws Exception {

        StringBuilder sqlBuilder = new StringBuilder();

        buildBasicLoadSQL(sqlBuilder,tClass,object,columns,selectCondition);

        buildPage(dbType,sqlBuilder,tClass,object,page,pageSize);

        return sqlBuilder;

    }

    /**
     *
     * 建立添加语句
     *
     * @param columns 字段集合
     * @param t 实体对象
     * @param tClass 对象类型
     * @return 添加语句
     * @throws Exception
     */

    public static StringBuilder buildAddSQL(Field[] columns,Object t,Class tClass) throws Exception {

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("INSERT INTO ").append(GetPropertiesTools.getTableName(t,tClass)).append(" ").append(GetPropertiesTools.getAddColumn(columns,t,tClass));

        return sqlBuilder;

    }

    /**
     *
     * 建立删除语句
     *
     * @param columns 字段集合
     * @param t 实体对象
     * @param tClass 对象类型
     * @param condition 查询条件
     * @return 删除语句
     * @throws Exception
     */

    public static StringBuilder buildDeleteSQL(Field[] columns,Object t,Class tClass,SelectCondition condition) throws Exception {

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("DELETE FROM ").append(GetPropertiesTools.getTableName(t,tClass)).append(GetPropertiesTools.getWhere(columns,t,condition.toString(),tClass));

        return sqlBuilder;

    }

    /**
     *
     * 建立修改语句
     *
     * @param columns 字段集合
     * @param tClass 对象类型
     * @param t1 修改内容实体对象
     * @param t2 修改条件实体对象
     * @param condition 查询条件
     * @return 修改语句
     * @throws Exception
     */

    public static StringBuilder buildUpdateSQL(Field[] columns,Class tClass,Object t1,Object t2,SelectCondition condition) throws Exception {

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("UPDATE ").append(GetPropertiesTools.getTableName(t1,tClass)).append(" SET ").append(GetPropertiesTools.getUpdateColumn(columns,t1,tClass)).append(GetPropertiesTools.getWhere(columns,t2,condition.toString(),tClass));

        return sqlBuilder;

    }

    /**
     *
     * 建立分页 - 逻辑
     *
     * @param dbType 数据库类型
     * @param sqlBuilder 查询语句
     * @param tClass 对象类型
     * @param object 实体对象
     * @param page 当前页数
     * @param pageSize 每页展示条数
     * @return 分页后语句
     * @throws Exception
     */

    private static StringBuilder buildPage(String dbType,StringBuilder sqlBuilder,Class tClass,Object object,int page,int pageSize) throws Exception {

        dbType = BaseTools.notNullString(dbType) ? dbType : DataBaseInfoConfig.DBTYPE;

        getPageSQL(sqlBuilder,dbType,tClass,object,page,pageSize);

        return sqlBuilder;

    }

    /**
     *
     * 建立分页语句
     *
     * @param sqlBuilder 查询语句
     * @param dbType 数据库类型
     * @param tClass 对象类型
     * @param object 实体对象
     * @param page 当前页数
     * @param pageSize 每页展示条数
     * @return 分页语句
     * @throws Exception
     */

    private static StringBuilder getPageSQL(StringBuilder sqlBuilder,String dbType,Class tClass,Object object,int page,int pageSize) throws Exception {

        if (page <= 0 || pageSize < 0){

            throw new RuntimeException("分页错误，页数不能小于等于0，并且每页展示条数不能小于0");

        }

        int offset = (page - 1) * 10;

        if (dbType.toLowerCase().equals("mysql")){

            buildOrderBySQL(sqlBuilder,tClass,object);

            sqlBuilder.append(" LIMIT ").append(offset).append(",").append(pageSize);

        } else if (dbType.toLowerCase().equals("oracle")){

            if (sqlBuilder.toString().contains("WHERE")){

                sqlBuilder.append("AND rownum > ").append(offset).append("AND rownum <= ").append(pageSize * page);

            } else {

                sqlBuilder.append("where rownum > ").append(offset).append("AND rownum <= ").append(pageSize * page);

            }

            buildOrderBySQL(sqlBuilder,tClass,object);

        } else if (dbType.toLowerCase().equals("sqlserver")){

            throw new RuntimeException("暂不支持SQL Server分页，请手动拼写分页SQL语句并调用BaseCustomDao的informationLoadListCustom方法");

        } else {

            throw new RuntimeException("目前只支持MySQL,Oracle两种数据库的分页，请手动拼写分页SQL语句并调用BaseCustomDao的informationLoadListCustom方法");

        }

        return sqlBuilder;

    }

    /**
     *
     * 查询语句 - 基本语句建立
     *
     * @param sqlBuilder 查询语句
     * @param tClass 对象类型
     * @param object 实体对象
     * @param columns 字段集合
     * @param condition 查询条件
     * @return 查询语句
     * @throws Exception
     */

    private static StringBuilder buildBasicLoadSQL(StringBuilder sqlBuilder,Class tClass,Object object,Field[] columns,SelectCondition condition) throws Exception {

        sqlBuilder.append("SELECT ");

        if((Boolean) tClass.getMethod("getIsDistinct").invoke(object)){

            sqlBuilder.append("DISTINCT ");

        }

        sqlBuilder.append(GetPropertiesTools.getColumn(columns)).append(" FROM ").append(GetPropertiesTools.getTableName(object,tClass)).append(GetPropertiesTools.getWhere(columns,object,condition.toString(),tClass));

        return sqlBuilder;

    }

    /**
     *
     * 查询语句 - 建立 orderby 语句
     *
     * @param sqlBuilder 查询语句
     * @param tClass 对象类型
     * @param object 实体对象
     * @return 查询语句
     * @throws Exception
     */

    private static StringBuilder buildOrderBySQL(StringBuilder sqlBuilder,Class tClass,Object object) throws Exception {

        OrderByCondition orderByCondition = GetPropertiesTools.getOrderByCondition(object,tClass);

        String[] orderByContent = GetPropertiesTools.getOrderByContent(object,tClass);

        if( orderByContent != null && orderByContent.length > 0 && orderByCondition != null){

            String content = "";

            for(int i = 0; i < orderByContent.length; i++){

                if(i == orderByContent.length - 1){

                    content = content + orderByContent[i];

                }else{

                    content = content + orderByContent[i] + ",";

                }

            }

            sqlBuilder.append(" ORDER BY ").append(content).append(" ").append(orderByCondition);

        }

        return sqlBuilder;

    }

}
