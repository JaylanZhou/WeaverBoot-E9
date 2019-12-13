package com.weaverboot.frame.dao;

import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.frameTools.basedao.GetPropertiesTools;

import java.util.List;

/**
 *
 * 自定义查询 Dao层文件
 *
 * @Author : Jaylan Zhou
 *
 */

public class BaseCustomDao {

    private boolean outLog = false;

    /**
     *
     * 查操作自定义SQL方法
     *
     * @param sql 自定义的SQL语句
     * @throws Exception
     */

    public <T> List<T> informationLoadListCustom(Class<T> clazz,String sql) throws Exception {

        List result = GetPropertiesTools.getResultListCustom(clazz.newInstance(),sql,this.outLog);

        return result;

    }

    /**
     *
     * 外部数据源 - 查操作自定义SQL方法
     *
     * @param sql 自定义的SQL语句
     * @param dbname 外部数据源配置名称
     * @throws Exception
     */

    public <T> List<T> informationLoadListCustom(Class<T> clazz,String sql,String dbname) throws Exception {

        List result = GetPropertiesTools.getResultListCustom(clazz.newInstance(),sql,dbname,this.outLog);

        return result;

    }

    /**
     *
     * 查操作自定义SQL方法(单条目)
     *
     * @param sql 自定义的SQL语句
     * @throws Exception
     */

    public <T> T informationLoadOneCustom(Class<T> clazz,String sql) throws Exception {

        List<T> result = GetPropertiesTools.getResultListCustom(clazz.newInstance(),sql,this.outLog);

        if(!BaseTools.notNullList(result)){

            return null;

        }else if (result.size() > 1){

            throw new RuntimeException("结果个数不符");

        }

        return result.get(0);


    }

    /**
     *
     * 外部数据源 - 查操作自定义SQL方法(单条目)
     *
     * @param sql 自定义的SQL语句
     * @param dbname 外部数据源配置名称
     * @throws Exception
     */

    public <T> T informationLoadOneCustom(Class<T> clazz,String sql,String dbname) throws Exception {

        List<T> result = GetPropertiesTools.getResultListCustom(clazz.newInstance(),sql,dbname,this.outLog);

        if(!BaseTools.notNullList(result)){

            return null;

        }else if (result.size() > 1){

            throw new RuntimeException("结果个数不符");

        }

        return result.get(0);


    }

    /**
     *
     * 查操作自定义SQL方法（单个操作）
     *
     * @param sql 自定义的SQL语句
     * @throws Exception
     */

    public String informationLoadOneColumnCustom(String name,String sql) throws Exception {

        String result = GetPropertiesTools.getResultOneColumnCustom(name,sql,this.outLog);

        if (!BaseTools.notNullString(result)){

            return null;

        }

        return result;

    }

    /**
     *
     * 外部数据源 - 查操作自定义SQL方法（单个操作）
     *
     * @param sql 自定义的SQL语句
     * @param dbname 外部数据源配置名称
     * @throws Exception
     */

    public String informationLoadOneColumnCustom(String name,String sql,String dbname) throws Exception {

        String result = GetPropertiesTools.getResultOneColumnCustom(name,sql,dbname,this.outLog);

        if (!BaseTools.notNullString(result)){

            return null;

        }

        return result;

    }

    /**
     *
     * 增删改操作的自定义方法
     *
     * @param sql 自定义的SQL语句
     * @throws Exception
     */

    public void informationWriteCustom(String sql) throws Exception {

        GetPropertiesTools.writeExcuteCustom(sql,this.outLog);

    }

    public boolean isOutLog() {

        return outLog;

    }

    public void setOutLog(boolean outLog) {

        this.outLog = outLog;

    }

}
