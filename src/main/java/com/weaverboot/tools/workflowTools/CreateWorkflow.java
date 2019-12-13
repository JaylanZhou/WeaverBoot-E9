package com.weaverboot.tools.workflowTools;

import com.weaver.general.Util;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.workflowTools.anno.WorkflowFieldInfo;
import weaver.general.BaseBean;
import weaver.workflow.webservices.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * 创建工作流 - 工具类
 *
 * @Author : Jaylan Zhou
 *
 */

public class CreateWorkflow extends BaseBean {


    /**
     *
     * 通过 webservice 创建流程
     *
     * 三个类主要有以下作用：
     * WorkflowMainColumns：流程信息类，包含了workFlowId，creatorId，requestLevel，requestName
     * Object：流程主表实体类
     * List<List>：外层List为明细表总集合，内层List为明细表实体类的集合
     *
     * 字段信息的name，edit，view属性，可在对应的实体类字段上标注@WorkflowFieldInfo注解，进行设置
     *
     * @param workFlowMainColumns 主要字段数据实体类
     * @param object 主表数据实体类
     * @param list 明细表数据集合
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     *
     * @return WebService调用创建流程接口时需要的对象 ： WorkflowRequestInfo
     */

    public WorkflowRequestInfo createByWebService(WorkFlowMainColumns workFlowMainColumns,Object object, List<List> list) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        WorkflowMainTableInfo wmi = new WorkflowMainTableInfo();

        if (object != null) {

            wmi.setRequestRecords(this.setMainTableInfo(object));

        }

        WorkflowDetailTableInfo[] wdti = null;

        if (BaseTools.notNullList(list)) {

            wdti = new WorkflowDetailTableInfo[list.size()];

            wdti = this.setDetailTableInfo(wdti,list);

        }

        WorkflowRequestInfo wri = this.setMainColumns(wmi,wdti,workFlowMainColumns);

        return wri;

    }

    /**
     *
     * 通过本地调用创建流程
     *
     * 三个类主要有以下作用：
     * WorkflowMainColumns：流程信息类，包含了workFlowId，creatorId，requestLevel，requestName
     * Object：流程主表实体类
     * List<List>：外层List为明细表总集合，内层List为明细表实体类的集合
     *
     * 字段信息的name，edit，view属性，可在对应的实体类字段上标注@WorkflowFieldInfo注解，进行设置
     * @param workFlowMainColumns 主要字段数据实体类
     * @param object 主表数据实体类
     * @param list 明细表数据集合
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     *
     * @return requestId
     */

    public String createByLocal(WorkFlowMainColumns workFlowMainColumns,Object object, List<List> list) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        WorkflowMainTableInfo wmi = new WorkflowMainTableInfo();

        if (object != null) {

            wmi.setRequestRecords(this.setMainTableInfo(object));

        }

        WorkflowDetailTableInfo[] wdti = null;

        if (BaseTools.notNullList(list)) {

            wdti = new WorkflowDetailTableInfo[list.size()];

            wdti = this.setDetailTableInfo(wdti,list);

        }

        WorkflowRequestInfo wri = this.setMainColumns(wmi,wdti,workFlowMainColumns);

        WorkflowService workflowService = new WorkflowServiceImpl();

        String requestid = workflowService.doCreateWorkflowRequest(wri,	Util.getIntValue(workFlowMainColumns.getCreatorId()));// 返回requestid

        return requestid;

    }

    /**
     *
     * 存储字段 - 逻辑
     *
     * @param field 字段变量反射信息
     * @param workflowRequestTableField 主表条目信息类（底层）
     * @param object 数据实体类
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */

    private WorkflowRequestTableField setWorkflowRequestTableField(Field field,WorkflowRequestTableField workflowRequestTableField,Object object) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        workflowRequestTableField = new WorkflowRequestTableField();

        Method method = object.getClass().getMethod("get" + BaseTools.toUpperCaseFirstOne(field.getName()));

        if(BaseTools.notNullString((String) method.invoke(object))) {

            if (field.isAnnotationPresent(WorkflowFieldInfo.class)) {

                WorkflowFieldInfo workflowFieldInfo = field.getAnnotation(WorkflowFieldInfo.class);

                if (BaseTools.notNullString(workflowFieldInfo.name())) {

                    workflowRequestTableField.setFieldName(workflowFieldInfo.name());//被留言人

                } else {

                    workflowRequestTableField.setFieldName(field.getName());

                }

                workflowRequestTableField.setFieldValue((String) method.invoke(object));//被留言人字段的值，111为被留言人id

                workflowRequestTableField.setView(workflowFieldInfo.view());//字段是否可见

                workflowRequestTableField.setEdit(workflowFieldInfo.edit());//字段是否可编辑

            } else {

                workflowRequestTableField.setFieldName(field.getName());

                workflowRequestTableField.setFieldValue((String) method.invoke(object));//被留言人字段的值，111为被留言人id

                workflowRequestTableField.setView(true);//字段是否可见

                workflowRequestTableField.setEdit(true);//字段是否可编辑

            }

        }

        return workflowRequestTableField;

    }

    /**
     *
     * 存储主表字段
     *
     * @param object 主表数据实体类
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */

    private WorkflowRequestTableRecord[] setMainTableInfo(Object object) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Field[] objectFields = this.getFields(object);

        WorkflowRequestTableField[] wrti = new WorkflowRequestTableField[objectFields.length];

        int statement = 0;

        for (int i = 0; i < wrti.length; i++){

            if(BaseTools.checkIsAbleColumn(objectFields[i])) {

                wrti[statement] = this.setWorkflowRequestTableField(objectFields[i], wrti[i], object);

                statement++;

            }

        }

        WorkflowRequestTableRecord[] wrtri = new WorkflowRequestTableRecord[1];

        wrtri[0] = new WorkflowRequestTableRecord();

        wrtri[0].setWorkflowRequestTableFields(wrti);

        return wrtri;

    }

    /**
     *
     * 存储明细表字段
     *
     * @param workflowDetailTableInfos
     * @param list
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */

    private WorkflowDetailTableInfo[] setDetailTableInfo(WorkflowDetailTableInfo[] workflowDetailTableInfos,List<List> list) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        for (int i = 0; i < list.size(); i++) {

            List detailList = list.get(i);

            WorkflowRequestTableRecord[] wrtri = new WorkflowRequestTableRecord[detailList.size()];

            for (int j = 0; j < detailList.size(); j++
            ) {


                Object o = detailList.get(j);

                Field[] detailObjectFields = this.getFields(o);

                WorkflowRequestTableField[] wrti = new WorkflowRequestTableField[detailObjectFields.length];

                int statement = 0;

                for (int k = 0; k < wrti.length; k++) {

                    if(BaseTools.checkIsAbleColumn(detailObjectFields[k])) {

                        wrti[statement] = this.setWorkflowRequestTableField(detailObjectFields[k], wrti[k], o);

                        statement++;

                    }

                }

                wrtri[j] = new WorkflowRequestTableRecord();

                wrtri[j].setWorkflowRequestTableFields(wrti);

            }

            workflowDetailTableInfos[i] = new WorkflowDetailTableInfo();

            workflowDetailTableInfos[i].setWorkflowRequestTableRecords(wrtri);

        }

        return workflowDetailTableInfos;

    }

    /**
     *
     * 存储流程主要信息
     *
     * @param wmi 主表内容
     * @param wdti 明细表内容
     * @return
     */

    private WorkflowRequestInfo setMainColumns(WorkflowMainTableInfo wmi,WorkflowDetailTableInfo[] wdti,WorkFlowMainColumns workFlowMainColumns){

        WorkflowBaseInfo wbi = new WorkflowBaseInfo();

        wbi.setWorkflowId(workFlowMainColumns.getWorkFlowId());

        WorkflowRequestInfo wri = new WorkflowRequestInfo();//流程基本信息

        if(!BaseTools.notNullString(workFlowMainColumns.getCreatorId())){

            this.writeLog("请设置creatorid");

            throw new RuntimeException("请设置creatorid");

        }

        if(!BaseTools.notNullString(workFlowMainColumns.getRequestLevel())){

            this.writeLog("请设置requestlevel");

            throw new RuntimeException("请设置requestlevel");

        }

//        if(!BaseTools.notNullString(workFlowMainColumns.getRequestName())){
//
//            this.writeLog("请设置requestname");
//
//            throw new RuntimeException("请设置requestname");
//
//        }

        if(!BaseTools.notNullString(workFlowMainColumns.getWorkFlowId())){

            this.writeLog("请设置workflowid");

            throw new RuntimeException("请设置workflowid");

        }

        wri.setCreatorId(workFlowMainColumns.getCreatorId());//创建人id

        wri.setRequestLevel(workFlowMainColumns.getRequestLevel());//0 正常，1重要，2紧急

        wri.setRequestName(workFlowMainColumns.getRequestName());//流程标题

        wri.setWorkflowMainTableInfo(wmi);//添加主字段数据

        wri.setWorkflowBaseInfo(wbi);

        if (wdti != null) {

            wri.setWorkflowDetailTableInfos(wdti);

        }

        return wri;

    }

    /**
     *
     * 获取对象全局变量反射信息数组
     *
     * @param object 数据实体类
     * @return 全局变量反射信息数组
     */

    private Field[] getFields(Object object){

        Field[] fields = object.getClass().getDeclaredFields();

        return fields;

    }

}
