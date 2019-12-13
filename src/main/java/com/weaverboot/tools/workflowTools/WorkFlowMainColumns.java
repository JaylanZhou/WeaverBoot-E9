package com.weaverboot.tools.workflowTools;

import weaver.general.BaseBean;

/**
 *
 * 创建流程 - 主要信息
 *
 * @Author : Jaylan Zhou
 *
 */

public class WorkFlowMainColumns extends BaseBean {

    private String workFlowId;

    private String creatorId;

    private String requestLevel;

    private String requestName;

    public String getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(String workFlowId) {
        this.workFlowId = workFlowId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getRequestLevel() {
        return requestLevel;
    }

    public void setRequestLevel(String requestLevel) {
        this.requestLevel = requestLevel;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }
}
