package com.weaverboot.weaComponent.impl.weaForm.impl;

import com.api.browser.util.ConditionType;
import com.weaverboot.tools.enumTools.weaComponent.WeaComponentSizeTypeEnum;
import com.weaverboot.tools.enumTools.weaComponent.WeaUploadListTypeEnum;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;

import java.util.List;

/**
 *
 * 上传组件
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */


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

    private String uploadUrl; //文件上传服务器接口地址

    private String category;

    private WeaUploadListTypeEnum listType;

    private int maxFilesNumber;//限制文件上传个数

    private int maxUploadSize;//最大单个上传文件大小/MB

    private int totolMaxUploadSize; //总文件上传大小/MB

    private Object uploadParams; //POST 上传 fromdata 参数

    private boolean multiSelection;//true：多选；false：单选

    private WeaComponentSizeTypeEnum btnSize;//按钮大小

    private List<Object> datas;//附件上传默认值

    private boolean autoUpload; //是否自动上传

    private boolean isDetail; //是否为明细表

    private boolean sortable; //是否可拖拽

    private boolean showBatchLoad; //是否展示全部下载按钮

    private String batchLoadUrl; //批量下载地址

    private boolean showClearAll; //是否展示全部清除按钮

    private boolean showUpload; //是否启用显示上传附件按钮

    private int imgwidth; //listType = 'img' 情况下列表图片宽度

    private int imgheight; //listType = 'img' 情况下列表图片高度

    private String errorMsg; //自定义错误提示信息，将直接展示错误信息，屏蔽上传功能

    private boolean forceIE9Flash; //IE9 是否启用 flash

    private boolean clearWhenReset; //当上传地址、参数等配置变化时，是否重置缓存列表

    private String fileDataName; //文件 formdata 的键名

    private Object customOptions; //自定义 plupload 参数

    private boolean editable; //是否显示编辑按钮

    private boolean showUploader; //是否显示上传人名称

    private boolean showUploadTime; //是否显示上传时间

    private boolean showCancleUpload; //是否显示取消上传按钮

    private boolean oneline; //是否将上传人，时间和文件名显示在同一行（只对主表有效）

    private boolean replaceWhenOver; //配合 maxFilesNumber 使用，为 true 时，文件数量超出后后上传文件取代前面上传的文件；为 false 时，文件数量超出后停止上传并弹出提示信息

    private boolean useDefaultListB; //自定义列表模式使用，展示按钮下方上传列表（使用组件默认列表样式）

    private boolean showEditButton; //控制编辑按钮显隐

    private String prohibitType; //禁止上传类型

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

    public WeaUploadListTypeEnum getListType() {
        return listType;
    }

    public void setListType(WeaUploadListTypeEnum listType) {
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

    public boolean getMultiSelection() {
        return multiSelection;
    }

    public void setMultiSelection(boolean multiSelection) {
        this.multiSelection = multiSelection;
    }

    public int getTotolMaxUploadSize() {
        return totolMaxUploadSize;
    }

    public void setTotolMaxUploadSize(int totolMaxUploadSize) {
        this.totolMaxUploadSize = totolMaxUploadSize;
    }

    public Object getUploadParams() {
        return uploadParams;
    }

    public void setUploadParams(Object uploadParams) {
        this.uploadParams = uploadParams;
    }

    public WeaComponentSizeTypeEnum getBtnSize() {
        return btnSize;
    }

    public void setBtnSize(WeaComponentSizeTypeEnum btnSize) {
        this.btnSize = btnSize;
    }

    public boolean getAutoUpload() {
        return autoUpload;
    }

    public void setAutoUpload(boolean autoUpload) {
        this.autoUpload = autoUpload;
    }

    public boolean getIsDetail() {
        return isDetail;
    }

    public void setDetail(boolean detail) {
        isDetail = detail;
    }

    public boolean getSortable() {
        return sortable;
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }

    public boolean getShowBatchLoad() {
        return showBatchLoad;
    }

    public void setShowBatchLoad(boolean showBatchLoad) {
        this.showBatchLoad = showBatchLoad;
    }

    public String getBatchLoadUrl() {
        return batchLoadUrl;
    }

    public void setBatchLoadUrl(String batchLoadUrl) {
        this.batchLoadUrl = batchLoadUrl;
    }

    public boolean getShowClearAll() {
        return showClearAll;
    }

    public void setShowClearAll(boolean showClearAll) {
        this.showClearAll = showClearAll;
    }

    public boolean getShowUpload() {
        return showUpload;
    }

    public void setShowUpload(boolean showUpload) {
        this.showUpload = showUpload;
    }

    public int getImgwidth() {
        return imgwidth;
    }

    public void setImgwidth(int imgwidth) {
        this.imgwidth = imgwidth;
    }

    public int getImgheight() {
        return imgheight;
    }

    public void setImgheight(int imgheight) {
        this.imgheight = imgheight;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean getForceIE9Flash() {
        return forceIE9Flash;
    }

    public void setForceIE9Flash(boolean forceIE9Flash) {
        this.forceIE9Flash = forceIE9Flash;
    }

    public boolean getClearWhenReset() {
        return clearWhenReset;
    }

    public void setClearWhenReset(boolean clearWhenReset) {
        this.clearWhenReset = clearWhenReset;
    }

    public String getFileDataName() {
        return fileDataName;
    }

    public void setFileDataName(String fileDataName) {
        this.fileDataName = fileDataName;
    }

    public Object getCustomOptions() {
        return customOptions;
    }

    public void setCustomOptions(Object customOptions) {
        this.customOptions = customOptions;
    }

    public boolean getEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean getShowUploader() {
        return showUploader;
    }

    public void setShowUploader(boolean showUploader) {
        this.showUploader = showUploader;
    }

    public boolean getShowUploadTime() {
        return showUploadTime;
    }

    public void setShowUploadTime(boolean showUploadTime) {
        this.showUploadTime = showUploadTime;
    }

    public boolean getShowCancleUpload() {
        return showCancleUpload;
    }

    public void setShowCancleUpload(boolean showCancleUpload) {
        this.showCancleUpload = showCancleUpload;
    }

    public boolean getOneline() {
        return oneline;
    }

    public void setOneline(boolean oneline) {
        this.oneline = oneline;
    }

    public boolean getReplaceWhenOver() {
        return replaceWhenOver;
    }

    public void setReplaceWhenOver(boolean replaceWhenOver) {
        this.replaceWhenOver = replaceWhenOver;
    }

    public boolean getUseDefaultListB() {
        return useDefaultListB;
    }

    public void setUseDefaultListB(boolean useDefaultListB) {
        this.useDefaultListB = useDefaultListB;
    }

    public boolean getShowEditButton() {
        return showEditButton;
    }

    public void setShowEditButton(boolean showEditButton) {
        this.showEditButton = showEditButton;
    }

    public String getProhibitType() {
        return prohibitType;
    }

    public void setProhibitType(String prohibitType) {
        this.prohibitType = prohibitType;
    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }
}
