package com.weaverboot.tools.workflowTools;

import com.weaverboot.frame.dao.BaseCustomDao;
import weaver.soa.workflow.request.RequestInfo;

public class WorkFlowTools {

    private WorkFlowTools(){



    }

    /**
     *
     * 获取此流程表的id
     *
     * @param workflowId
     * @return
     * @throws Exception
     */

    public static String getFormId(String workflowId,BaseCustomDao baseCustomDao) throws Exception {

        String sql = "SELECT formid FROM workflow_base WHERE id = " + workflowId;

        String formId = baseCustomDao.informationLoadOneColumnCustom("formid",sql);

        return formId;

    }

    /**
     *
     * 获取此流程的表名
     *
     * @param workflowId
     * @return
     * @throws Exception
     */

    public static String getFormName(String workflowId, BaseCustomDao baseCustomDao) throws Exception {

        String sql = "SELECT tablename FROM workflow_bill WHERE id = (SELECT formid FROM workflow_base WHERE id = " + workflowId + " and isbill = 1)";

        String tablename = baseCustomDao.informationLoadOneColumnCustom("tablename",sql);

        return tablename;

    }

    /**
     *
     * 获取主表的明细表名称
     *
     * @param formname 主表表名
     * @param num 明细表数字
     * @return
     * @throws Exception
     */

    public static String getDtFormName(String formname , String num) throws Exception {

        String tablename = formname + "_dt" + num;

        return tablename;

    }

    /**
     *
     * 流程错误信息展示
     *
     * @param requestInfo action中默认的参数requestInfo
     * @param id 一个id
     * @param message 套展示的错误信息
     */

    public static void showErrorMessageInPage(RequestInfo requestInfo, String id, String message){

        requestInfo.getRequestManager().setMessageid(id);

        requestInfo.getRequestManager().setMessagecontent(message);

    }

}
