package com.weaverboot.tools.frameTools.basedao;

import com.weaverboot.tools.baseTools.BaseTools;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
import weaver.general.BaseBean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GeneralDaoTools {

    private GeneralDaoTools(){



    }

    /**
     *
     * 实例化新的RecordSetDataSource
     *
     * @return RecordSetDataSource
     */

    public static RecordSetDataSource getRecordSetDataSource(String dbname) {

        return new RecordSetDataSource(dbname);

    }

    /**
     *
     * 实例化新的RecordSet
     *
     * @return RecordSet
     */

    public static RecordSet getRecordSet() {

        return new RecordSet();

    }

    /**
     *
     * 获取Get方法名称
     *
     * @param column
     * @return
     * @throws Exception
     */

    public static Method getMethodName(Field column,Class tClass) throws Exception{

        return tClass.getMethod("get" + BaseTools.toUpperCaseFirstOne(column.getName()));

    }


    /**
     *
     * 获取Set方法名称
     *
     * @param column 变量
     * @return
     * @throws Exception
     */

    public static Method setMethodName(Field column,Class tClass) throws Exception{

        BaseBean baseBean = new BaseBean();

        return tClass.getMethod("set" + BaseTools.toUpperCaseFirstOne(column.getName()),column.getType());

    }

    /**
     *
     * 获取Set方法名称
     *
     * @param column 变量
     * @return
     * @throws Exception
     */

    public static Method setMethodName(Field column,Class sClass,Class tClass) throws Exception{

        return tClass.getMethod("set" + BaseTools.toUpperCaseFirstOne(column.getName()),sClass);

    }

    /**
     *
     * 根据class类型获取其Get方法名称
     *
     * @param column 字段名称
     * @param sClass 泛型类型
     * @return 泛型类型的get方法
     * @throws Exception
     */

    public static <S> Method getMethodNameByClass(Class<S> sClass,Field column) throws Exception{

        return sClass.getMethod("get" + BaseTools.toUpperCaseFirstOne(column.getName()));

    }


    /**
     *
     * 根据class类型获取其Set方法名称
     *
     * @param column 字段名称
     * @param sClass 泛型类型
     * @return 泛型的set方法
     * @throws Exception
     */

    public static <S> Method setMethodNameByClass(Class<S> sClass,Field column) throws Exception{

        return sClass.getMethod("set" + BaseTools.toUpperCaseFirstOne(column.getName()),column.getType());

    }

    /**
     *
     * where 判断条件
     *
     * @param method 方法
     * @param t 类
     * @return
     * @throws Exception
     */

    public static boolean whereCondition(Method method,Object t) throws Exception{

        return method.invoke(t) != null;

    }

}
