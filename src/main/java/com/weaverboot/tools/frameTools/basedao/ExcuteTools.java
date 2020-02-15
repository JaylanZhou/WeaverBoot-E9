package com.weaverboot.tools.frameTools.basedao;

import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.logTools.LogTools;
import com.weaverboot.tools.threadTools.ThreadTools;
import com.weaverboot.tools.transactionTools.data.RecordSetTransStack;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
import weaver.conn.RecordSetTrans;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.general.Util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExcuteTools {

    private ExcuteTools(){



    }

    /**
     *
     * 查询出的值放入list之中返回
     *
     * @param columns 类中变量数组
     * @param sql 传入的SQL语句
     * @return
     * @throws Exception
     */

    public static List getResultList(Field[] columns, String sql, Class tClass, boolean outLog) throws Exception {

        BaseBean baseBean = new BaseBean();

        List result = new ArrayList();

        List<Field> annoFieldList = new ArrayList<Field>();

        if(ThreadTools.checkOnAuto()) {

            RecordSetTransStack recordSetTransStack = (RecordSetTransStack) ThreadTools.threadLocal.get();

            RecordSetTrans recordSet = recordSetTransStack.getNowRecordSetTrans();

            recordSet.execute(sql);

            if (outLog) {

                LogTools.info("事务执行语句:" + sql);

            }

            while(recordSet.next()){

                Object obj = tClass.newInstance();

                for (int i = 0; i < columns.length; i++) {

                    if (BaseTools.checkIsAbleColumn(columns[i])) {

                        DataORMTools.saveData(columns[i],obj,recordSet);

                    } else {

                        annoFieldList.add(columns[i]);

                    }

                }

                obj = RefTools.refColumn(obj,annoFieldList,tClass,outLog);

                annoFieldList.clear();

                result.add(obj);

            }

        }else{

            RecordSet recordSet = GeneralDaoTools.getRecordSet();

            if (outLog){

                LogTools.info("执行语句:" + sql);

            }

            recordSet.execute(sql);

            while(recordSet.next()){

                Object obj = tClass.newInstance();

                for (int i = 0; i < columns.length; i++) {

                    if (BaseTools.checkIsAbleColumn(columns[i])) {

                        DataORMTools.saveData(columns[i],obj,recordSet);

                    } else {

                        annoFieldList.add(columns[i]);

                    }

                }

                obj = RefTools.refColumn(obj,annoFieldList,tClass,outLog);

                annoFieldList.clear();

                result.add(obj);

            }

        }

        return result;

    }





    /**
     *
     * 查询结果单字段
     * @param name
     * @param sql
     * @return
     */

    public static String getResultOneColumn(String name,String sql,boolean outLog) throws Exception {

        BaseBean baseBean = new BaseBean();

        String result = "";

        if(ThreadTools.checkOnAuto()) {

            RecordSetTransStack recordSetTransStack = (RecordSetTransStack) ThreadTools.threadLocal.get();

            RecordSetTrans recordSet = recordSetTransStack.getNowRecordSetTrans();

            recordSet.execute(sql);

            if (outLog) {

                LogTools.info("事务执行语句:" + sql);

            }

            int check = 0;

            while (recordSet.next()){

                check++;

                if(check > 1){

                    throw new RuntimeException("查询结果大于一条，请检测");

                }

                result = Util.null2String(recordSet.getString(name));

            }

        }else{

            RecordSet recordSet = GeneralDaoTools.getRecordSet();

            recordSet.execute(sql);

            if (outLog) {

                LogTools.info("执行语句:" + sql);

            }

            int check = 0;

            while (recordSet.next()){

                check++;

                if(check > 1){

                    throw new RuntimeException("查询结果大于一条，请检测");

                }

                result = Util.null2String(recordSet.getString(name));

            }

        }

        return result;

    }

    /**
     *
     * 外部数据源- 查询出的值放入list中返回
     *
     * @param columns 类中变量数组
     * @param sql 传入的SQL语句
     * @return
     * @throws Exception
     */

    public static List getResultList(Field[] columns,String sql,RecordSetDataSource recordSetDataSource,Class tClass,boolean outLog) throws Exception {

        BaseBean baseBean = new BaseBean();

        List result = new ArrayList();

        List<Field> annoFieldList = new ArrayList<Field>();

        if (outLog) {

            LogTools.info("执行语句:" + sql);

        }

        recordSetDataSource.execute(sql);

        while(recordSetDataSource.next()){

            Object obj = tClass.newInstance();

            for (int i = 0; i < columns.length; i++) {

                if (BaseTools.checkIsAbleColumn(columns[i])) {

                    DataORMTools.saveData(columns[i],obj,recordSetDataSource);

                } else {

                    annoFieldList.add(columns[i]);

                }

            }

            obj = RefTools.refColumn(obj,annoFieldList,tClass,outLog);

            annoFieldList.clear();

            result.add(obj);

        }

        return result;

    }

    /**
     *
     * 自定义语句查询结果存为List
     *
     * @param obj 此查询语句对应的实体类
     * @param sql 查询语句
     * @return
     * @throws Exception
     */

    public static List getResultListCustom(Object obj,String sql,Class tClass,boolean outLog) throws Exception {

        List result = new ArrayList();

        List<Field> annoFieldList = new ArrayList<Field>();

        if(ThreadTools.checkOnAuto()) {

            RecordSetTransStack recordSetTransStack = (RecordSetTransStack) ThreadTools.threadLocal.get();

            RecordSetTrans recordSet = recordSetTransStack.getNowRecordSetTrans();

            Field[] columns = obj.getClass().getDeclaredFields();

            recordSet.execute(sql);

            if (outLog) {

                LogTools.info("事务执行语句:" + sql);

            }

            while (recordSet.next()){

                Object objNew = obj.getClass().newInstance();

                for (int i = 0; i < columns.length; i++) {

                    if (BaseTools.checkIsAbleColumn(columns[i])) {

                        DataORMTools.saveData(columns[i],obj,recordSet);

                    } else {

                        annoFieldList.add(columns[i]);

                    }

                }

                obj = RefTools.refColumn(obj,annoFieldList,tClass,outLog);

                annoFieldList.clear();

                result.add(objNew);

            }

        }else{

            RecordSet recordSet = GeneralDaoTools.getRecordSet();

            Field[] columns = obj.getClass().getDeclaredFields();

            recordSet.execute(sql);

            if (outLog) {

                LogTools.info("执行语句:" + sql);

            }

            while (recordSet.next()){

                Object objNew = obj.getClass().newInstance();

                for (int i = 0; i < columns.length; i++) {

                    if (BaseTools.checkIsAbleColumn(columns[i])) {

                        DataORMTools.saveData(columns[i],obj,recordSet);

                    } else {

                        annoFieldList.add(columns[i]);

                    }

                }

                obj = RefTools.refColumn(obj,annoFieldList,tClass,outLog);

                annoFieldList.clear();

                result.add(objNew);

            }

        }

        return result;

    }

    /**
     *
     * 写操作执行方法
     *
     * @param sql 传入的SQL
     */

    public static void writeExcute(String sql,boolean outLog) throws Exception {

        BaseBean baseBean = new BaseBean();

        if(ThreadTools.checkOnAuto()){

            RecordSetTransStack recordSetTransStack = (RecordSetTransStack) ThreadTools.threadLocal.get();

            RecordSetTrans recordSet = recordSetTransStack.getNowRecordSetTrans();

            boolean isSuccess = recordSet.execute(sql);

            if (outLog) {

                LogTools.info("执行事务:" + sql);

            }

            if(!isSuccess){

                LogTools.error("您于 " + TimeUtil.getCurrentDateString() + " 的操作失败，执行的Sql语句为: " + sql);
                throw new RuntimeException("操作失败，请联系管理员！");

            }

        }else {

            RecordSet recordSet = GeneralDaoTools.getRecordSet();

            boolean isSuccess = recordSet.execute(sql);

            if (outLog) {

                LogTools.info("执行语句:" + sql);

            }

            if (!isSuccess) {

                LogTools.error("您于 " + TimeUtil.getCurrentDateString() + " 的操作失败，执行的Sql语句为: " + sql);
                throw new RuntimeException("操作失败，请联系管理员！");

            }

        }

    }

}
