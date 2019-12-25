package com.weaverboot.tools.componentTools.table;

/**
 *
 * 检查WeaTable 的类型，用以判断datas接口中的读取类型
 *
 * @Author : Jaylan Zhou
 * @Date : 2019-12-25 12:30
 * @Version : 1.0
 */
public class WeaTableCheckTypeTools {

    public static final String WORKFLOW_UPCOMING_PAGEUID = "0f57de4d-89bb-4b96-ac78-48ce9b834592";

    public static final String WORKFLOW_INCOMING_PAGEUID = "a789a53d-942d-4c88-85c4-4b95976cb2bb";

    public static final String DOCUMENT_PAGEUID = "2ef55cfa-78a1-4d79-bf5c-7fc110e4e332";

    public static final String HRM_PAGEUID = "920acb49-9262-4553-a9e5-dae1e17ebc89";

    public static final String MEETINGTABLE_PAGEUID = "bc851bdb-0d60-4232-b878-37a04d813cfa";

    private WeaTableCheckTypeTools(){}

    public static boolean tableFromWorkflowUpComing(String sessionKey){

        boolean result = false;

        if (getPageUid(sessionKey).equals(WORKFLOW_UPCOMING_PAGEUID)){

            result = true;

        }

        return result;

    }

    public static boolean tableFromWorkflowInComing(String sessionKey){

        boolean result = false;

        if (getPageUid(sessionKey).equals(WORKFLOW_INCOMING_PAGEUID)){

            result = true;

        }

        return result;

    }

    public static boolean tableFromDocument(String sessionKey){

        boolean result = false;

        if (getPageUid(sessionKey).equals(DOCUMENT_PAGEUID)){

            result = true;

        }

        return result;

    }

    public static boolean tableFromHrm(String sessionKey){

        boolean result = false;

        if (getPageUid(sessionKey).equals(HRM_PAGEUID)){

            result = true;

        }

        return result;

    }

    public static boolean tableFromMeetingTable(String sessionKey){

        boolean result = false;

        if (getPageUid(sessionKey).equals(MEETINGTABLE_PAGEUID)){

            result = true;

        }

        return result;

    }

    public static String getPageUid(String sessionKey){

        String[] sessionKeyArray = sessionKey.split("_");

        if (sessionKeyArray.length < 2){

            throw new RuntimeException("sessionkey:" + sessionKey + " 不合规，请检查此表单的datas接口或者此sessionkey是否来自datas");

        }

        String pageUid = sessionKeyArray[0];

        return pageUid;

    }

}
