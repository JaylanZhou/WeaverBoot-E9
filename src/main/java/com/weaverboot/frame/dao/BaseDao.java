package com.weaverboot.frame.dao;

import com.weaverboot.frame.model.BaseModel;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.enumTools.frame.SelectCondition;
import com.weaverboot.tools.frameTools.basedao.BuildSQLTools;
import com.weaverboot.tools.frameTools.basedao.ExcuteTools;
import com.weaverboot.tools.frameTools.basedao.GetPropertiesTools;
import weaver.conn.RecordSetDataSource;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 *
 * Dao层基本类
 *
 * @param <T> 此类为继承BaseModel的实体类，Dao层操作的实体类
 * @author Jaylan Zhou
 *
 */

public abstract class BaseDao<T extends BaseModel> {

    private BaseCustomDao baseCustomDao = new BaseCustomDao();

    //是否打印日志
    private boolean outLog = true;

    /**
     *
     * 查询出列表的方法
     *
     * @param t 此类中set的字段作为查询条件字段
     * @param selectCondition 查询条件,封装在枚举SelectCondition中
     * @throws Exception
     */

    public List<T> informationLoadList(T t, SelectCondition selectCondition) throws Exception {

        Class tClass = this.getTClass();

        Field[] columns = GetPropertiesTools.getDataField(tClass);

        StringBuilder sqlBuilder = BuildSQLTools.buildLoadListSQL(columns,selectCondition,t,tClass);

        List<T> result = ExcuteTools.getResultList(columns,sqlBuilder.toString(),tClass, outLog);

        return result;

    }

    /**
     *
     * 查询出列表的方法 - 分页
     *
     * @param t 此类中set的字段作为查询条件字段
     * @param selectCondition 查询条件,封装在枚举SelectCondition中
     * @param page 当前页数
     * @param pageSize 每页展示条数
     * @throws Exception
     */

    public List<T> informationLoadListPage(T t, SelectCondition selectCondition,int page,int pageSize) throws Exception {

        Class tClass = this.getTClass();

        Field[] columns = GetPropertiesTools.getDataField(tClass);

        StringBuilder sqlBuilder = BuildSQLTools.buildLoadListPageSQL(columns,selectCondition,t,tClass,page,pageSize);

        List<T> result = ExcuteTools.getResultList(columns,sqlBuilder.toString(),tClass, outLog);

        return result;

    }

    /**
     *
     * 外部数据源 - 查询出列表的方法
     *
     * @param t 此类中set的字段作为查询条件字段
     * @param dbname 外部数据源配置名称
     * @param selectCondition 查询条件,封装在枚举SelectCondition中
     * @throws Exception
     */

    public List<T> informationLoadList(T t, String dbname, SelectCondition selectCondition) throws Exception {

        Class tClass = this.getTClass();

        Field[] columns = GetPropertiesTools.getDataField(tClass);

        RecordSetDataSource recordSetDataSource = new RecordSetDataSource(dbname);

        StringBuilder sqlBuilder = BuildSQLTools.buildLoadListSQL(columns,selectCondition,t,tClass);

        List<T> result = ExcuteTools.getResultList(columns,sqlBuilder.toString(),recordSetDataSource,tClass,outLog);

        return result;

    }

    /**
     *
     * 外部数据源 - 查询出列表的方法 - 分页
     *
     * @param t 此类中set的字段作为查询条件字段
     * @param dbname 外部数据源配置名称
     * @param selectCondition 查询条件,封装在枚举SelectCondition中
     * @param page 当前页数
     * @param pageSize 每页展示条数
     * @throws Exception
     */

    public List<T> informationLoadListPage(T t, String dbname, SelectCondition selectCondition,int page,int pageSize) throws Exception {

        Class tClass = this.getTClass();

        Field[] columns = GetPropertiesTools.getDataField(tClass);

        RecordSetDataSource recordSetDataSource = new RecordSetDataSource(dbname);

        StringBuilder sqlBuilder = BuildSQLTools.buildLoadListPageSQL(columns,selectCondition,t,tClass,page,pageSize,recordSetDataSource.getDBType());

        List<T> result = ExcuteTools.getResultList(columns,sqlBuilder.toString(),recordSetDataSource,tClass,outLog);

        return result;

    }

    /**
     *
     * 查询结果为一个时的方法
     *
     * @param t 此类中set的字段作为查询条件字段
     * @param selectCondition 查询条件,封装在枚举SelectCondition中
     * @throws Exception
     */

    public T informationLoadOne(T t, SelectCondition selectCondition) throws Exception {

        List<T> result = this.informationLoadList(t,selectCondition);

        if(BaseTools.notNullList(result)&&result.size() == 1){

            T t1 = result.get(0);

            return t1;

        }else if(!BaseTools.notNullList(result)){

            return null;

        } else {

            throw new RuntimeException("查询结果不符合条件");

        }


    }

    /**
     *
     * 外部数据源 - 查询结果为一个时的方法
     *
     * @param t 此类中set的字段作为查询条件字段
     * @param selectCondition 查询条件,封装在枚举SelectCondition中
     * @throws Exception
     */

    public T informationLoadOne(T t, String dbname,SelectCondition selectCondition) throws Exception {

        List<T> result = this.informationLoadList(t,dbname,selectCondition);

        if(BaseTools.notNullList(result)&&result.size() == 1){

            T t1 = result.get(0);

            return t1;

        }else if(!BaseTools.notNullList(result)){

            return null;

        } else {

            throw new RuntimeException("查询结果不符合条件");

        }


    }

    /**
     *
     * 添加方法
     *
     * @param t
     * @throws Exception
     */

    public void informationAdd(T t) throws Exception {

        Class tClass = this.getTClass();

        Field[] columns = GetPropertiesTools.getDataField(tClass);

        StringBuilder sqlBuilder = BuildSQLTools.buildAddSQL(columns,t,tClass);

        ExcuteTools.writeExcute(sqlBuilder.toString(),outLog);

    }

    /**
     *
     * 删除方法
     *
     * @param t
     */

    public void informationDelete(T t,SelectCondition selectCondition) throws Exception {

        Class tClass = this.getTClass();

        Field[] columns = GetPropertiesTools.getDataField(tClass);

        StringBuilder sqlBuilder = BuildSQLTools.buildDeleteSQL(columns,t,tClass,selectCondition);

        ExcuteTools.writeExcute(sqlBuilder.toString(),outLog);

    }

    /**
     * 修改方法
     *
     * @param t1 此实体对象中存储了要修改的字段内容
     * @param t2 此实体对象中存储了作为查询条件的字段内容
     * @param selectCondition
     * @throws Exception
     */

    public void informationUpdate(T t1,T t2,SelectCondition selectCondition) throws Exception {

        Class tClass = this.getTClass();

        Field[] columns = GetPropertiesTools.getDataField(tClass);

        StringBuilder sqlBuilder = BuildSQLTools.buildUpdateSQL(columns,tClass,t1,t2,selectCondition);

        ExcuteTools.writeExcute(sqlBuilder.toString(),outLog);

    }

    /**
     *
     * 查操作自定义SQL方法（已过时，请调用customDao）
     *
     * @param sql 自定义的SQL语句
     * @throws Exception
     */

    @Deprecated
    public List informationLoadListCustom(Object obj,String sql) throws Exception {

        Class tClass = this.getTClass();

        boolean outLog = this.getOutLog();

        List result = ExcuteTools.getResultListCustom(obj,sql,tClass,outLog);

        return result;

    }

    /**
     *
     * 查操作自定义SQL方法(单条目,已过时，请调用customDao)
     *
     * @param sql 自定义的SQL语句
     * @throws Exception
     */

    @Deprecated
    public Object informationLoadOneCustom(Object obj,String sql) throws Exception {

        Class tClass = this.getTClass();

        boolean outLog = this.getOutLog();

        List result = ExcuteTools.getResultListCustom(obj,sql,tClass,outLog);

        if(!BaseTools.notNullList(result)){

            return null;

        }else if (result.size() > 1){

            throw new RuntimeException("结果个数不符");

        }

        return result.get(0);


    }

    /**
     *
     * 查操作自定义SQL方法（单个字段，已过时，请调用customDao）
     *
     * @param sql 自定义的SQL语句
     * @throws Exception
     */

    @Deprecated
    public String informationLoadOneColumnCustom(String name,String sql) throws Exception {

        boolean outLog = this.getOutLog();

        String result = ExcuteTools.getResultOneColumn(name,sql,outLog);

        if (!BaseTools.notNullString(result)){

            return null;

        }

        return result;

    }

    /**
     *
     * 查询当前自增id 最大值(通过存储过程查询)
     *
     * @return
     * @throws Exception
     */

    public int getMaxId() throws Exception {

        StringBuilder sqlBuilder = new StringBuilder();

        Class tClass = this.getTClass();

        sqlBuilder.append("SELECT ").append("IDENT_CURRENT('").append(GetPropertiesTools.getTableName(tClass.newInstance(),tClass)).append("') AS maxId");

        String maxIdString = this.baseCustomDao.informationLoadOneColumnCustom("maxId",sqlBuilder.toString());

        if(!BaseTools.notNullString(maxIdString)){

            maxIdString =  "0";

        }

        return Integer.parseInt(maxIdString);

    }

    /**
     *
     * 通过 Max(id) 的方法查询当前最大值
     *
     * @return
     * @throws Exception
     */
    public int getMaxIdByClassic() throws Exception {

        StringBuilder sqlBuilder = new StringBuilder();

        Class tClass = this.getTClass();

        sqlBuilder.append("SELECT MAX(id) AS maxId FROM ").append(GetPropertiesTools.getTableName(tClass.newInstance(),tClass));

        String maxIdString = this.baseCustomDao.informationLoadOneColumnCustom("maxId",sqlBuilder.toString());

        if(!BaseTools.notNullString(maxIdString)){

            maxIdString =  "0";

        }

        return Integer.parseInt(maxIdString);

    }

    /**
     *
     * 增删改操作的自定义方法（已过时，请调用customDao）
     *
     * @param sql 自定义的SQL语句
     * @throws Exception
     */

    @Deprecated
    public void informationWriteCustom(String sql) throws Exception {

        boolean outLog = this.getOutLog();

        ExcuteTools.writeExcute(sql,outLog);

    }


    /**
     *
     * 逻辑删除（适用于拥有isDelete字段）
     *
     * @throws Exception
     */

    public void informationLogicDelete(T t,SelectCondition selectCondition) throws Exception {

        String condition = String.valueOf(selectCondition);

        Class tClass = this.getTClass();

        Field[] columns = GetPropertiesTools.getDataField(tClass);

        boolean outLog = this.getOutLog();

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("UPDATE ").append(GetPropertiesTools.getTableName(t,tClass)).append(" SET isDelete = 1").append(GetPropertiesTools.getWhere(columns,t,condition,tClass));

        ExcuteTools.writeExcute(sqlBuilder.toString(),outLog);

    }

    /**
     * 通过反射来获取泛型的Class
     * <p>
     * 主要实现机制：
     */

    private Class<T> getTClass() {

        //获取泛型的类型
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];

        return entityClass;
    }

    /**
     *
     * 获取打印日志的结果
     *
     * @return
     */
    public boolean getOutLog() {

        return outLog;

    }

    /**
     *
     * 可设置此dao是否打印日志
     *
     * @param outLog 打印日志为true，不打印为false
     */

    public void setOutLog(boolean outLog) {

        this.outLog = outLog;

    }
}
