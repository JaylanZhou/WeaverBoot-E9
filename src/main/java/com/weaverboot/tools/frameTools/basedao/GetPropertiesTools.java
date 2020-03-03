package com.weaverboot.tools.frameTools.basedao;

import com.weaverboot.frame.dao.anno.TableInfo;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.enumTools.frame.OrderByCondition;
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
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GetPropertiesTools {

    private GetPropertiesTools(){



    }

    /**
     *
     * 获取实体类字段
     *
     * @param columns 类中变量数组
     * @return
     */

    public static String getColumn(Field[] columns) {

        if(columns == null || columns.length == 0){

            throw new RuntimeException("你的实体类没有字段，请检查");

        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < columns.length; i++) {

            if (BaseTools.checkIsAbleColumn(columns[i])) {

                stringBuilder.append(columns[i].getName()).append(",");

            }

        }

        String result = stringBuilder.substring(0,stringBuilder.length() - 1);

        return result;

    }

    /**
     *
     * 根据实体类中set值的情况拼装WHERE语句
     *
     * @param columns 类中变量数组
     * @param t 对应的实体类
     * @param condition 组合查询条件，封装在SelectCondition中
     * @return
     * @throws Exception
     */

    public static String getWhere(Field[] columns,Object t,String condition,Class tClass) throws Exception {

        Method method = null;

        String result = "";

        StringBuilder sqlBuilder = new StringBuilder();

        if (condition == null){

            condition = "";

        }

        //condition = Util.null2String(condition);

        int checkNum = 0;

        for (int i = 0; i < columns.length; i++) {

            if (BaseTools.checkIsAbleColumn(columns[i])) {

                method = GeneralDaoTools.getMethodName(columns[i],tClass);

                if (GeneralDaoTools.whereCondition(method, t)) {

                    sqlBuilder.append(columns[i].getName()).append(" = '").append(method.invoke(t)).append("' ").append(condition).append(" ");

                    checkNum++;

                }

            }

        }

        result = sqlBuilder.toString();

        sqlBuilder.delete(0,sqlBuilder.length());

        if(!result.equals("") && result.length() > condition.length() + 1){

            result = (sqlBuilder.append(" WHERE ").append(result.substring(0,result.length() - (condition.length() + 1)))).toString();

        }

        if(checkNum > 1 && condition.equals("")){

            throw new RuntimeException("多条件查询请输入查询条件！");

        }

        return result;

    }

    /**
     *
     * 拼装INSERT语句
     *
     * @param columns 类中变量数组
     * @param t 对应的实体类
     * @return
     */

    public static String getAddColumn(Field[] columns,Object t,Class tClass) throws Exception{

        Method method = null;

        StringBuilder sqlBuilder = new StringBuilder("(");

        String result = "";

        for (int i = 0; i < columns.length; i++) {

            if (BaseTools.checkIsAbleColumn(columns[i])) {

                method = GeneralDaoTools.getMethodName(columns[i],tClass);

                if (GeneralDaoTools.whereCondition(method, t)) {

                    sqlBuilder.append(columns[i].getName()).append(",");

                }

            }

        }

        result = sqlBuilder.toString();

        sqlBuilder = new StringBuilder(result.substring(0,result.length() - 1));

        sqlBuilder.append(")VALUES(");

        for (int i = 0; i < columns.length; i++) {

            if (BaseTools.checkIsAbleColumn(columns[i])) {

                method = GeneralDaoTools.getMethodName(columns[i],tClass);

                if (GeneralDaoTools.whereCondition(method, t)) {

                    sqlBuilder.append("'").append(method.invoke(t)).append("',");

                }

            }

        }

        result = sqlBuilder.toString();

        result = result.substring(0,result.length() - 1) + ")";

        return result;

    }

    /**
     *
     * 拼装Update语句
     *
     * @param columns 类中变量数组
     * @param t 对应的实体类
     * @return
     */

    public static String getUpdateColumn(Field[] columns,Object t,Class tClass) throws Exception{

        Method method = null;

        String result = "";

        StringBuilder sqlBuilder = new StringBuilder();

        for (int i = 0; i < columns.length; i++) {

            if (BaseTools.checkIsAbleColumn(columns[i])) {

                method = GeneralDaoTools.getMethodName(columns[i],tClass);

                if (GeneralDaoTools.whereCondition(method, t) && !columns[i].getName().equals("id")) {

                    sqlBuilder.append(columns[i].getName()).append(" = '").append(method.invoke(t)).append("',");


                }

            }

        }

        result = sqlBuilder.toString();

        if(BaseTools.notNullString(result)){

            result = result.substring(0,result.length() - 1);

        }else{

            throw new RuntimeException("未检测到修改字段，请检查修改实体类set值是否为空");

        }


        return result;

    }

    /**
     *
     * 查询出的值放入list中返回
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
     * @param dbname 外部数据源配置名称
     * @return
     * @throws Exception
     */

    public static List getResultList(Field[] columns,String sql,String dbname,Class tClass,boolean outLog) throws Exception {

        BaseBean baseBean = new BaseBean();

        List result = new ArrayList();

        List<Field> annoFieldList = new ArrayList<Field>();

        RecordSetDataSource recordSetDataSource = GeneralDaoTools.getRecordSetDataSource(dbname);

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

        BaseBean baseBean = new BaseBean();

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

                        DataORMTools.saveData(columns[i],objNew,recordSet);

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

                       DataORMTools.saveData(columns[i],objNew,recordSet);

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
     * 获取泛型类 表名
     *
     * @param t 泛型类
     * @return
     * @throws Exception
     */

    public static String getTableName(Object t,Class tClass) throws Exception {

        String tableName;

        if (tClass.isAnnotationPresent(TableInfo.class)){

            TableInfo tableInfo = (TableInfo) tClass.getAnnotation(TableInfo.class);

            tableName = tableInfo.value();

        } else {

            Method method = tClass.getMethod("getTableName");

            tableName = (String) method.invoke(t);

        }

        if (!BaseTools.notNullString(tableName)){

            throw new RuntimeException("请为" + tClass + "指定表名");

        }

        return tableName;

    }

    /**
     *
     * 获取此类中的排序条件
     *
     * @param t
     * @return
     * @throws Exception
     */
    public static OrderByCondition getOrderByCondition(Object t,Class tClass) throws Exception {

        Method method = tClass.getMethod("getOrderByCondition");

        OrderByCondition orderByCondition = (OrderByCondition) method.invoke(t);

        return orderByCondition;

    }

    /**
     *
     * 获取排序依据内容
     *
     * @param t
     * @return
     * @throws Exception
     */

    public static String[] getOrderByContent(Object t,Class tClass) throws Exception {

        Method method = tClass.getMethod("getOrderByContent");

        String[] orderByContent = (String[]) method.invoke(t);

        return orderByContent;

    }

    /**
     *
     * 获取类中变量名称
     *
     * @return
     */

    public static Field[] getDataField(Class tClass){

        Field[] field = tClass.getDeclaredFields();

        if (field == null || field.length == 0){

            throw new RuntimeException("这个传入的实体类中没有字段？");

        }

        return field;

    }



    /**
     *
     * 写操作执行方法
     *
     * @param sql 传入的SQL
     */

    public static void writeExcuteCustom(String sql,boolean outLog) throws Exception {

        BaseBean baseBean = new BaseBean();

        if(ThreadTools.checkOnAuto()){

            RecordSetTransStack recordSetTransStack = (RecordSetTransStack) ThreadTools.threadLocal.get();

            RecordSetTrans recordSet = recordSetTransStack.getNowRecordSetTrans();

            boolean isSuccess = recordSet.execute(sql);

            if(outLog) {

                LogTools.info("执行自定义事务:" + sql);

            }

            if(!isSuccess){

                LogTools.error("您于 " + TimeUtil.getCurrentDateString() + " 的操作失败，执行的Sql语句为: " + sql);
                throw new RuntimeException("操作失败，请联系管理员！");

            }

        }else {

            RecordSet recordSet = GeneralDaoTools.getRecordSet();

            boolean isSuccess = recordSet.execute(sql);

            if(outLog){

                LogTools.info("执行自定义语句:" + sql);

            }

            if (!isSuccess) {

                LogTools.error("您于 " + TimeUtil.getCurrentDateString() + " 的操作失败，执行的Sql语句为: " + sql);
                throw new RuntimeException("操作失败，请联系管理员！");

            }

        }

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

    public static List getResultListCustom(Object obj, String sql,boolean outLog) throws Exception {

        BaseBean baseBean = new BaseBean();

        List result = new ArrayList();

        if(ThreadTools.checkOnAuto()) {

            RecordSetTransStack recordSetTransStack = (RecordSetTransStack) ThreadTools.threadLocal.get();

            RecordSetTrans recordSet = recordSetTransStack.getNowRecordSetTrans();

            Field[] columns = obj.getClass().getDeclaredFields();

            recordSet.execute(sql);

            if(outLog) {

                LogTools.info("事务执行自定义语句:" + sql);

            }

            while (recordSet.next()){

                Object objNew = obj.getClass().newInstance();

                for (int i = 0; i < columns.length; i++) {

                    DataORMTools.saveData(columns[i],objNew,recordSet);

                }

                result.add(objNew);

            }

        }else{

            RecordSet recordSet = GeneralDaoTools.getRecordSet();

            Field[] columns = obj.getClass().getDeclaredFields();

            recordSet.execute(sql);

            if(outLog){

                LogTools.info("执行自定义语句:" + sql);

            }

            while (recordSet.next()){

                Object objNew = obj.getClass().newInstance();

                for (int i = 0; i < columns.length; i++) {

                    DataORMTools.saveData(columns[i],objNew,recordSet);

                }

                result.add(objNew);

            }

        }

        return result;

    }

    /**
     *
     * 自定义语句查询结果存为List
     *
     * @param obj 此查询语句对应的实体类
     * @param sql 查询语句
     * @param dbname 外部数据源配置名称
     * @return
     * @throws Exception
     */

    public static List getResultListCustom(Object obj, String sql,String dbname,boolean outLog) throws Exception {

        BaseBean baseBean = new BaseBean();

        List result = new ArrayList();

        RecordSetDataSource recordSetDataSource = GeneralDaoTools.getRecordSetDataSource(dbname);

        Field[] columns = obj.getClass().getDeclaredFields();

        recordSetDataSource.execute(sql);

        if(outLog){

           LogTools.info("执行自定义外部数据源查询:" + sql);

        }

        while (recordSetDataSource.next()){

            Object objNew = obj.getClass().newInstance();

            for (int i = 0; i < columns.length; i++) {

                DataORMTools.saveData(columns[i],objNew,recordSetDataSource);

            }

            result.add(objNew);

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
    public static String getResultOneColumnCustom(String name,String sql,boolean outLog) throws Exception {

        BaseBean baseBean = new BaseBean();

        String result = "";

        if(ThreadTools.checkOnAuto()) {

            RecordSetTransStack recordSetTransStack = (RecordSetTransStack) ThreadTools.threadLocal.get();

            RecordSetTrans recordSet = recordSetTransStack.getNowRecordSetTrans();

            recordSet.execute(sql);

            if(outLog) {

                LogTools.info("事务执行自定义语句:" + sql);

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

            if(outLog){

                LogTools.info("执行自定义语句:" + sql);

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
     * 外部数据源 - 查询结果单字段
     * @param name
     * @param sql
     * @param dbname 外部数据源配置名称
     * @return
     */
    public static String getResultOneColumnCustom(String name,String sql,String dbname,boolean outLog) throws Exception {

        String result = "";

        BaseBean baseBean = new BaseBean();

        RecordSetDataSource recordSetDataSource = GeneralDaoTools.getRecordSetDataSource(dbname);

        recordSetDataSource.execute(sql);

        if(outLog){

            LogTools.info("执行自定义查询语句:" + sql);

        }

        int check = 0;

        while (recordSetDataSource.next()){

            check++;

            if(check > 1){

                throw new RuntimeException("查询结果大于一条，请检测");

            }

            result = Util.null2String(recordSetDataSource.getString(name));

        }

        return result;

    }
    
}
