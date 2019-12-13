package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

import java.util.List;

public class UploadWeaForm extends AbstractWeaForm {

    private final ConditionType conditionType = ConditionType.UPLOAD;

    public UploadWeaForm(String label,String ... name){

        super();

        init();

        super.setLabel(label);

        super.setDomkey(name);

    }

    public UploadWeaForm(){

        super();

    }

    /***附件上传URL 附件上传目录**/
    private String uploadUrl;

    private String category;

    private String listType;

    private int maxFilesNumber;//限制文件上传个数

    private int maxUploadSize;//最大上传文件大小/MB

    private boolean multiSelection;//true：多选；false：单选

    private String btnSize;//按钮大小

    private List<Object> datas;//附件上传默认值

    private void init(){

        this.uploadUrl = "/api/doc/upload/uploadFile";

        this.category = "string";

        this.maxUploadSize = 100;

        this.maxFilesNumber = 10;

    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public int getMaxFilesNumber() {
        return maxFilesNumber;
    }

    public void setMaxFilesNumber(int maxFilesNumber) {
        this.maxFilesNumber = maxFilesNumber;
    }

    public int getMaxUploadSize() {
        return maxUploadSize;
    }

    public void setMaxUploadSize(int maxUploadSize) {
        this.maxUploadSize = maxUploadSize;
    }

    public boolean isMultiSelection() {
        return multiSelection;
    }

    public void setMultiSelection(boolean multiSelection) {
        this.multiSelection = multiSelection;
    }

    public String getBtnSize() {
        return btnSize;
    }

    public void setBtnSize(String btnSize) {
        this.btnSize = btnSize;
    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }
}
