package com.weaverboot.tools.componentTools.table;

import com.api.integration.Base;
import com.weaverboot.frame.dao.anno.*;
import com.weaverboot.frame.model.BaseModel;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.enumTools.frame.SelectCondition;
import com.weaverboot.tools.frameTools.basedao.BuildSQLTools;
import com.weaverboot.tools.frameTools.basedao.GetPropertiesTools;
import com.weaverboot.tools.logTools.LogTools;
import com.weaverboot.tools.parseTools.ModelTransTableParserTool;
import com.weaverboot.weaComponent.impl.weaTable.column.anno.WeaMobileTransMethod;
import com.weaverboot.weaComponent.impl.weaTable.column.anno.WeaTransMethod;
import com.weaverboot.weaComponent.impl.weaTable.column.comparator.WeaTableColumnComparator;
import com.weaverboot.weaComponent.impl.weaTable.column.impl.DefaultWeaTableColumn;
import com.weaverboot.weaComponent.impl.weaTable.column.inte.AbstractWeaTableColumn;
import com.weaverboot.weaComponent.impl.weaTable.table.inte.AbstractWeaTable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ModelTransWeaTable {

    private ModelTransWeaTable(){


    }

    public static void parseBaseModel(BaseModel baseModel, AbstractWeaTable abstractWeaTable, SelectCondition selectCondition,Map<String,String> conditionMap){

        Class baseModelClass = baseModel.getClass();

        Field[] fields = baseModelClass.getDeclaredFields();

        parseSqlField(baseModel,baseModelClass,abstractWeaTable,fields,conditionMap);

        parseSqlFrom(baseModel,baseModelClass,abstractWeaTable,conditionMap);

        parseSqlWhere(baseModel,baseModelClass,abstractWeaTable,fields,selectCondition,conditionMap);

        parseColumns(abstractWeaTable,fields);

        parseTableOther(abstractWeaTable,baseModel,baseModelClass);


    }

    private static void parseTableOther(AbstractWeaTable abstractWeaTable,BaseModel baseModel,Class baseModelClass){

        try {

            /**
             *
             * orderBy
             *
             */

            String[] orderByContent = GetPropertiesTools.getOrderByContent(baseModel,baseModelClass);

            if (orderByContent != null && orderByContent.length > 0){

                StringBuilder orderByBuilder = new StringBuilder();

                for (int i = 0; i < orderByContent.length; i++) {

                    orderByBuilder.append(orderByContent[i]).append(",");

                }

                String orderBy = orderByBuilder.substring(0,orderByBuilder.length() - 1);

                abstractWeaTable.setSqlorderby(orderBy);

                abstractWeaTable.setSqlsortway(GetPropertiesTools.getOrderByCondition(baseModel,baseModelClass).toString());

            }

            /**
             *
             * isdistinct
             *
             */

            boolean isDistinct = (Boolean) baseModelClass.getMethod("getIsDistinct").invoke(baseModel);

            abstractWeaTable.setSqlisdistinct(String.valueOf(isDistinct));


        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private static void parseColumns(AbstractWeaTable abstractWeaTable,Field[] fields){

        List<AbstractWeaTableColumn> abstractWeaTableColumnList = new ArrayList<>();

        for (Field field : fields
             ) {

            if (field.isAnnotationPresent(WeaColumn.class)){

                WeaColumn weaColumn = field.getAnnotation(WeaColumn.class);

                AbstractWeaTableColumn abstractWeaTableColumn = new DefaultWeaTableColumn();

                abstractWeaTableColumn.setText(weaColumn.value());

                String column;

                abstractWeaTableColumn.setOrder(weaColumn.order());

                if (BaseTools.notNullString(weaColumn.column())){

                    column = weaColumn.column();

                } else {

                    column = field.getName();

                }

                abstractWeaTableColumn.setColumn(column);

                if (BaseTools.notNullString(weaColumn.orderkey())){

                    abstractWeaTableColumn.setOrderkey(weaColumn.orderkey());

                } else {

                    abstractWeaTableColumn.setOrderkey(column);

                }

                abstractWeaTableColumn.setAlign(weaColumn.align());

                abstractWeaTableColumn.setDataalign(weaColumn.dataAlign());

                abstractWeaTableColumn.setHide(weaColumn.hide());

                if (BaseTools.notNullString(weaColumn.width())) {

                    abstractWeaTableColumn.setWidth(weaColumn.width());

                }

                abstractWeaTableColumn.setShowType(weaColumn.showType());

                abstractWeaTableColumn.setIsPrimarykey(weaColumn.isPrimarykey());

                abstractWeaTableColumn.setIsInputCol(weaColumn.isInputCol());

                if (BaseTools.notNullString(weaColumn.collapse())) {

                    abstractWeaTableColumn.setCollapse(weaColumn.collapse());

                }

                if (BaseTools.notNullString(weaColumn.labelid())) {

                    abstractWeaTableColumn.setLabelid(weaColumn.labelid());

                }

                if (BaseTools.notNullString(weaColumn.thumbnail())){

                    abstractWeaTableColumn.setThumbnail(weaColumn.thumbnail());

                }

                if (BaseTools.notNullString(weaColumn.customCol())){

                    abstractWeaTableColumn.setCustomCol(weaColumn.customCol());

                }

                if (BaseTools.notNullString(weaColumn.key())){

                    abstractWeaTableColumn.setKey(weaColumn.key());

                }

                abstractWeaTableColumn.setDisplay(weaColumn.display());

                abstractWeaTableColumn.setBelong(weaColumn.belong());

                abstractWeaTableColumn.setMobileviewtype(weaColumn.mobileviewtype());

                abstractWeaTableColumn.setIsBase64(weaColumn.isBase64());

                if (BaseTools.notNullString(weaColumn.fixed())) {

                    abstractWeaTableColumn.setFixed(weaColumn.fixed());

                }

                if (field.isAnnotationPresent(WeaTransMethod.class)){

                    WeaTransMethod weaTransMethod = field.getAnnotation(WeaTransMethod.class);

                    if (weaTransMethod.forceExecute()){

                        abstractWeaTableColumn.setTransMethodForce(weaTransMethod.value());

                    } else {

                        abstractWeaTableColumn.setTransmethod(weaTransMethod.value());

                    }

                    if (BaseTools.notNullString(weaTransMethod.otherpara())){

                        abstractWeaTableColumn.setOtherpara(weaTransMethod.otherpara());

                    }

                    if (BaseTools.notNullString(weaTransMethod.otherpara2())){

                        abstractWeaTableColumn.setOtherpara2(weaTransMethod.otherpara2());

                    }



                }

                if (field.isAnnotationPresent(WeaMobileTransMethod.class)){

                    WeaMobileTransMethod weaMobileTransMethod = field.getAnnotation(WeaMobileTransMethod.class);

                    abstractWeaTableColumn.setMobiletransmethod(weaMobileTransMethod.value());

                    abstractWeaTableColumn.setMobileotherpara(weaMobileTransMethod.mobileotherpara());

                }

                abstractWeaTableColumnList.add(abstractWeaTableColumn);

            }

        }

        Collections.sort(abstractWeaTableColumnList,new WeaTableColumnComparator());

        for (AbstractWeaTableColumn ab : abstractWeaTableColumnList
        ) {

            abstractWeaTable.addWeaTableColumn(ab);

        }

    }

    private static void parseSqlFrom(BaseModel baseModel,Class baseModelClass,AbstractWeaTable abstractWeaTable,Map<String,String> conditionMap){

        if (baseModelClass.isAnnotationPresent(WeaSqlFrom.class)){

            WeaSqlFrom weaSqlFrom = (WeaSqlFrom) baseModelClass.getAnnotation(WeaSqlFrom.class);

            abstractWeaTable.setSqlform(ModelTransTableParserTool.parseRegex(weaSqlFrom.value(),conditionMap,baseModel,baseModelClass));

        } else {

            try {

                if (baseModelClass.isAnnotationPresent(TableInfo.class)){

                    TableInfo tableInfo = (TableInfo) baseModelClass.getAnnotation(TableInfo.class);

                    abstractWeaTable.setSqlform(tableInfo.value());

                    if (BaseTools.notNullString(tableInfo.poolname())){

                        abstractWeaTable.setPoolname(tableInfo.poolname());

                    }

                } else {

                    abstractWeaTable.setSqlform(GetPropertiesTools.getTableName(baseModel, baseModelClass));

                }

            } catch (Exception e) {

                LogTools.error("basemodel转换解析sqlFrom出错，原因为:" + e.getMessage());

            }

        }

    }

    private static void parseSqlWhere(BaseModel baseModel,Class baseModelClass,AbstractWeaTable abstractWeaTable,Field[] fields,SelectCondition selectCondition,Map<String,String> conditionMap){

        if (baseModelClass.isAnnotationPresent(WeaSqlWhere.class)){

            WeaSqlWhere weaSqlWhere = (WeaSqlWhere) baseModelClass.getAnnotation(WeaSqlWhere.class);

            String sqlWhere = weaSqlWhere.value();

            abstractWeaTable.setSqlwhere(ModelTransTableParserTool.parseRegex(sqlWhere,conditionMap,baseModel,baseModelClass));

        } else {

            try {

                abstractWeaTable.setSqlwhere(GetPropertiesTools.getWhere(fields,baseModel,selectCondition.toString(),baseModelClass).replaceAll("WHERE",""));

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

    }

    private static void parseSqlField(BaseModel baseModel,Class baseModelClass,AbstractWeaTable abstractWeaTable,Field[] fields,Map<String,String> conditionMap){

        if (baseModelClass.isAnnotationPresent(WeaSqlField.class)){

            WeaSqlField weaSqlField = (WeaSqlField) baseModelClass.getAnnotation(WeaSqlField.class);

            String field = weaSqlField.value();

            abstractWeaTable.setBackfields(ModelTransTableParserTool.parseRegex(field,conditionMap,baseModel,baseModelClass));

            if (BaseTools.notNullString(weaSqlField.groupBy())){

                abstractWeaTable.setSqlgroupby(ModelTransTableParserTool.parseRegex(weaSqlField.groupBy(),conditionMap,baseModel,baseModelClass));

            }

        } else {

            abstractWeaTable.setBackfields(GetPropertiesTools.getColumn(fields));

        }

    }


}
