package com.weaverboot.tools.frameTools.basedao;

import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.logTools.LogTools;
import com.weaverboot.tools.threadTools.ThreadTools;
import com.weaverboot.tools.transactionTools.data.RecordSetTransStack;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
import weaver.conn.RecordSetTrans;
import weaver.general.BaseBean;
import weaver.general.Util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

public class DataORMTools  {

    private DataORMTools(){}

    /**
     *
     * 查询后数据ORM List
     *
     * @param columns
     * @param sql
     * @param tClass
     * @param outLog
     * @param result
     * @param annoFieldList
     * @param baseBean
     * @throws Exception
     */
    public static void loadORMListTrans(Field[] columns, String sql, Class tClass, boolean outLog, List result, List<Field> annoFieldList, BaseBean baseBean) throws Exception {

        RecordSetTransStack recordSetTransStack = (RecordSetTransStack) ThreadTools.threadLocal.get();

        RecordSetTrans recordSet = recordSetTransStack.getNowRecordSetTrans();

        recordSet.execute(sql);

        if (outLog) {

            LogTools.writeLog("事务执行语句:" + sql);

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

    }

    /**
     *
     * 单个字段存储逻辑 - 不带事务
     *
     * @param field 字段
     * @param objNew 存储的实体对象
     * @param recordSet RecordSet
     * @throws Exception
     */

    public static void saveData(Field field, Object objNew, RecordSet recordSet) throws Exception {

        String value = Util.null2String(recordSet.getString(field.getName()));

        saveLogic(field,objNew,value);

    }

    /**
     *
     * 单个字段存储逻辑 - 带事务
     *
     * @param field 字段
     * @param objNew 存储的实体对象
     * @param recordSet RecordSetTrans
     * @throws Exception
     */

    public static void saveData(Field field, Object objNew, RecordSetTrans recordSet) throws Exception {

        String value = Util.null2String(recordSet.getString(field.getName()));

        saveLogic(field,objNew,value);

    }

    /**
     *
     * 单个字段存储逻辑 - 不带事务
     *
     * @param field 字段
     * @param objNew 存储的实体对象
     * @param recordSet recordset
     * @throws Exception
     */

    public static void saveData(Field field, Object objNew, RecordSetDataSource recordSet) throws Exception {

        String value = Util.null2String(recordSet.getString(field.getName()));

        saveLogic(field,objNew,value);

    }

    /**
     *
     * 对应数据类型转换
     *
     * @param value 转换类型的值
     * @param tClass 字段的类型
     * @param <T> 字段的类型
     * @return 转换后的值
     */

    private static <T> T tarnsData(String value,Class<T> tClass){

        String className = tClass.getName();

        if (BaseTools.notNullString(className)){

            if (className.equals("int") || className.equals("java.lang.Integer")){

                return (T)new Integer(value);

            } else if(className.equals("float") || className.equals("java.lang.Float")){

                return (T)new Float(value);

            } else if(className.equals("java.math.BigDecimal")){

                return (T)new BigDecimal(value);

            } else if (className.equals("boolean") || className.equals("java.lang.Boolean")){

                return (T)new Boolean(value);

            } else if (className.equals("double") || className.equals("java.lang.Double")){

                return (T)new Double(value);

            } else if (className.equals("long") || className.equals("java.lang.Long")){

                return (T)new Long(value);

            } else if (className.equals("short") || className.equals("java.lang.Short")){

                return (T)new Short(value);

            } else if (className.equals("byte") || className.equals("java.lang.Byte")){

                return (T)new Byte(value);

            }

        }

        return (T)value;

    }

    /**
     *
     * 存储字段值的具体逻辑
     *
     * @param field 要存储的字段
     * @param objNew 要存储的对象
     * @param value 存储的值
     * @throws Exception
     */

    private static void saveLogic(Field field,Object objNew,String value) throws Exception {

        Method setMethod = GeneralDaoTools.setMethodName(field,objNew.getClass());

        if (!value.equals("")) {

            Object transValue = tarnsData(value,field.getType());

            setMethod.invoke(objNew,transValue);

        }

    }

}
