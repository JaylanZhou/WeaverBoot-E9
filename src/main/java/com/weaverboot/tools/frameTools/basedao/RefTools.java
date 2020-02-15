package com.weaverboot.tools.frameTools.basedao;

import com.weaverboot.frame.dao.anno.Association;
import com.weaverboot.frame.dao.anno.Collection;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.logTools.LogTools;
import com.weaverboot.tools.threadTools.ThreadTools;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;
import weaver.general.BaseBean;
import weaver.general.Util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RefTools extends BaseBean {

    private RefTools(){



    }

    /**
     *
     * 查询关联字段对象 - 集合
     *
     * @param sClass 关联字段 class 类型
     * @param refColumn 关联字段名称
     * @param refValue 关联字段的值
     * @param tableName 表名
     * @param <S> 关联字段的泛型
     * @return 关联字段查询出的数据集合
     * @throws Exception
     */

    public static <S> List<S> getRefList(Class<S> sClass, String refColumn, String refValue, String tableName, String whereColumn, Class tClass,boolean outLog) throws Exception {

        if(!BaseTools.notNullString(whereColumn)){

            whereColumn = refColumn;

        }

        S s = sClass.newInstance();

        Field[] objectColumns = sClass.getDeclaredFields();

        String fields = GetPropertiesTools.getColumn(objectColumns);

        StringBuilder sql = new StringBuilder();

        sql = sql.append("SELECT ").append(fields).append(" FROM ").append(tableName).append(" WHERE ").append(whereColumn).append(" = '").append(refValue).append("'");

        List<S> sList = RefTools.getRefLogic(sClass,objectColumns,sql.toString(),outLog);

        return sList;

    }

    /**
     *
     * 查询关联字段对象 - 实体类
     *
     * @param sClass 关联字段 class 类型
     * @param refColumn 关联字段名称
     * @param refValue 关联字段的值
     * @param tableName 表名
     * @param <S> 关联字段的泛型
     * @return 关联字段查询出的数据实体类
     * @throws Exception
     */

    public static <S> S getRefModel(Class<S> sClass,String refColumn,String refValue,String tableName,String whereColumn,Class tClass,boolean outLog) throws Exception {

        if(!BaseTools.notNullString(whereColumn)){

            whereColumn = refColumn;

        }

        S s = sClass.newInstance();

        Field[] objectColumns = sClass.getDeclaredFields();

        String fields = GetPropertiesTools.getColumn(objectColumns);

        StringBuilder sql = new StringBuilder();

        sql = sql.append("SELECT ").append(fields).append(" FROM ").append(tableName).append(" WHERE ").append(whereColumn).append(" = '").append(refValue).append("'");

        List<S> sList = RefTools.getRefLogic(sClass,objectColumns,sql.toString(),outLog);

        if(sList.size() > 1){

            StringBuilder e = new StringBuilder();

            e.append("关联字段'").append(refColumn).append("'查询出来的数据是多条，请检查此字段的注解");

            throw new RuntimeException(e.toString());

        }

        s = sList.get(0);

        return s;

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

    private static <S> List<S> getRefLogic(Class<S> sClass,Field[] columns,String sql,boolean outLog) throws Exception {

        BaseBean baseBean = new BaseBean();

        List<S> result = new ArrayList();

        List<Field> annoFieldList = new ArrayList<Field>();

        if(ThreadTools.checkOnAuto()) {

            RecordSetTrans recordSet = (RecordSetTrans) ThreadTools.threadLocal.get();

            recordSet.execute(sql);

            if (outLog) {

                LogTools.info("事务执行语句:" + sql);

            }

            while(recordSet.next()){

                Object obj = sClass.newInstance();

                for (int i = 0; i < columns.length; i++) {

                    if (BaseTools.checkIsAbleColumn(columns[i])) {

                        Method setMethod = GeneralDaoTools.setMethodNameByClass(sClass, columns[i]);

                        setMethod.invoke(obj, Util.null2String(recordSet.getString(columns[i].getName())));

                    } else {

                        annoFieldList.add(columns[i]);

                    }

                }

                obj = RefTools.refColumn(obj,annoFieldList,obj.getClass(),outLog);

                annoFieldList.clear();

                result.add((S) obj);

            }

        }else{

            RecordSet recordSet = GeneralDaoTools.getRecordSet();

            if (outLog){

                LogTools.info("执行语句:" + sql);

            }

            recordSet.execute(sql);

            while(recordSet.next()){

                Object obj = sClass.newInstance();

                for (int i = 0; i < columns.length; i++) {

                    if (BaseTools.checkIsAbleColumn(columns[i])) {

                        Method setMethod = GeneralDaoTools.setMethodNameByClass(sClass, columns[i]);

                        setMethod.invoke(obj, Util.null2String(recordSet.getString(columns[i].getName())));

                    } else {

                        annoFieldList.add(columns[i]);

                    }

                }

                obj = RefTools.refColumn(obj,annoFieldList,obj.getClass(),outLog);

                annoFieldList.clear();

                result.add((S) obj);

            }

        }

        return result;

    }

    /**
     *
     * 检测字段注解配置及获取关联对象
     *
     * @param obj 需要检测的对象
     * @param annoFieldList 配置了注解的字段数组
     * @return
     * @throws Exception
     */

    public static Object refColumn(Object obj,List<Field> annoFieldList,Class tClass,boolean outLog) throws Exception {

        for (Field field : annoFieldList
        ) {

            Method setMethod = GeneralDaoTools.setMethodName(field,field.getType(),tClass);

            if (field.isAnnotationPresent(Association.class)) {

                Class sClass = field.getType();

                Association association = field.getAnnotation(Association.class);

                String refColumn = association.refColumn();

                Field refColumnField = obj.getClass().getDeclaredField(refColumn);

                Method method = GeneralDaoTools.getMethodName(refColumnField,tClass);

                String refValue = (String) method.invoke(obj);

                if(!BaseTools.notNullString(refValue)){

                    Object sClassObject = sClass.newInstance();

                    sClassObject = RefTools.checkAndBuildAnnotationField(sClassObject);

                    setMethod.invoke(obj,sClassObject);

                    continue;

                }

                String tableName = RefTools.getRefModelTableName(association, field);

                String whereColumn = association.whereColumn();

                setMethod.invoke(obj, RefTools.getRefModel(sClass, refColumn, refValue, tableName,whereColumn,tClass,outLog));

            } else if (field.isAnnotationPresent(Collection.class)) {

                if (field.getType() != List.class) {

                    throw new RuntimeException("关联对象返回格式非List");

                }

                Collection collection = field.getAnnotation(Collection.class);

                Type genericType = field.getGenericType();

                if(genericType instanceof ParameterizedType){

                    ParameterizedType pt = (ParameterizedType) genericType;

                    //得到泛型里的class类型对象
                    Class<?> sClass = (Class<?>)pt.getActualTypeArguments()[0];

                    String refColumn = collection.refColumn();

                    Field refColumnField = obj.getClass().getDeclaredField(refColumn);

                    Method method = GeneralDaoTools.getMethodName(refColumnField,tClass);

                    String refValue = (String) method.invoke(obj);

                    if(!BaseTools.notNullString(refValue)){

                        Object sClassObject = sClass.newInstance();

                        sClassObject = RefTools.checkAndBuildAnnotationField(sClassObject);

                        setMethod.invoke(obj,sClassObject);

                        continue;

                    }

                    String tableName = RefTools.getRefListTableName(collection, sClass);

                    String whereColumn = collection.whereColumn();

                    setMethod.invoke(obj, RefTools.getRefList(sClass, refColumn, refValue, tableName,whereColumn,tClass,outLog));

                } else {

                    throw new RuntimeException("List泛型类型需要明确指定");

                }
            }

        }

        return obj;

    }

    public static Object checkAndBuildAnnotationField(Object object) throws Exception {

        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields
             ) {

            if (field.isAnnotationPresent(Association.class) || field.isAnnotationPresent(Collection.class)) {

                Method method = GeneralDaoTools.setMethodName(field,field.getType(),object.getClass());

                Object annotationObject = field.getType().newInstance();

                method.invoke(object,annotationObject);

                RefTools.checkAndBuildAnnotationField(annotationObject);

            }

        }

        return object;

    }

    /**
     *
     * 获取关联表的表名（单条目）
     *
     * @param association 字段上的注解
     * @param field 字段
     * @return
     */

    private static String getRefModelTableName(Association association,Field field){

        String tableName;

        if(BaseTools.notNullString(association.tableName())){

            tableName = association.tableName();

        } else {

            try {

                tableName = (String) field.getType().getMethod("getTableName").invoke(field.getType().newInstance());

            } catch (Exception e){

                throw new RuntimeException("返回对象中没有指定表名");

            }

        }

        return tableName;

    }

    /**
     *
     * 获取关联表的表名（多条目）
     *
     * @param collection 字段上的集合注解
     * @param sClass 主表对象的class泛型
     * @return
     */

    private static String getRefListTableName(Collection collection,Class sClass){

        String tableName;

        if(BaseTools.notNullString(collection.tableName())){

            tableName = collection.tableName();

        } else {

            try {

                tableName = (String) sClass.getMethod("getTableName").invoke(sClass.newInstance());

            } catch (Exception e){

                throw new RuntimeException("返回对象中没有指定表名");

            }

        }

        return tableName;

    }

    /**
     *
     * 获取类中变量名称
     *
     * @return
     */

    private Field[] getDataField(Class tClass){

        Field[] field = tClass.getDeclaredFields();

        if (field == null || field.length == 0){

            throw new RuntimeException("这个传入的实体类中没有字段？");

        }

        return field;

    }

}
