package com.weaverboot.weaComponent.impl.browser;

import com.api.browser.bean.BrowserTabBean;
import com.weaverboot.weaComponent.inte.AbstractWeaComponent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWeaBrowser extends AbstractWeaComponent {

    private String type;  					//浏览框类型

    private String name; 					//浏览框隐藏域名称

    private int viewAttr; 					//浏览框属性 1：只读 2：可编辑 3：必填

    private String title; 					//浏览框标题

    private String desc;  					//浏览框说明信息

    private boolean isSingle; 				//是否单选

    private String value; 					//浏览框的值

    private String onChange; 				//onChange回调函数

    private String icon;     				//浏览框标题矢量图标

    private String iconColor; 				//浏览框标题图标颜色

    private String iconBgcolor;				//浏览框标题图标背景色

    private String linkUrl;   				//浏览框数据连接

    private Map<String,Object> modalStyle;  //浏览框样式

    private Map<String,Object> inputStyle;  //浏览框输入框样式

    private int isDetail; 					//是否是明细字段

    private String dataURL;					//浏览框数据URL

    private String destDataURL;				//浏览框已选数据URL

    private String conditionURL; 			//浏览框高级查询条件URL

    private String completeURL; 			//浏览框联想搜索URL

    private Map<String,Object> dataParams; 	//浏览框数据URL参数

    private Map<String,Object> destDataParams;//destDataURL默认参数

    private Map<String,Object> conditionDataParams;//destDataURL默认参数

    private Map<String,Object> completeParams; //浏览框联想搜索URL参数

    private int isAutoComplete; 			   //是否需要联想搜索

    private boolean hasAddBtn; 				//是否有添加按钮

    private String ddUrl; 					//点击添加按钮URL

    private String addOnClick; 				//添加按钮事件

    private String _callbackForAdd; 		//回调函数

    private Map<String,Object> _callbackForAddParams; //回调函数参数

    private List<String> relateFieldid; 	//表字段名称数组

    private List<BrowserTabBean> tabs;  	//浏览框tab数据

    private List<Map<String,Object>> appendDatas; //初始化数据 [{id:'',name:''},....]

    private List<Map<String,Object>> replaceDatas; //初始化数据 [{id:'',name:''},....]

    private boolean hasAdvanceSerach = true; //是否需要高级搜索

    private String  quickSearchName;		 //快速搜索输入框名称和条件中设置的字段对应，若高级查询条件中有isquicksearch = true则以高级查询为准,该属性可以为空

    private boolean isMultCheckbox;          //列表多选显示形式：true checkbox false 穿梭框

    private String  idSeparator;         	 //id 分隔符

    private int  pageSize;   				 //列表每页显示记录数

    private boolean hasBorder;

    private boolean showCheckStrictly = true;//

    private boolean checkStrictly = true;

    private boolean hideVirtualOrg = false;

    private boolean expandfirstnode=false;//是否默认展开一级节点(树浏览框)

    private boolean asynLoadAll=false;//是否自动展开节点

    private int defaultExpandedLevel=0;//自动展开几级节点

    private String searchPlaceholder="";//浏览框输入提示

    private boolean scrollx=false;//弹出框的table是否显示横向滚动条

    private boolean noOperate = true;//是否显示操作列

    private Map<String,Object> otherParams;//额外参数


    // ESB 请求响应数据浏览按钮 属性 begin

    private String contextType; //数据判断类型（1,根据check判断，其它前端自行判断）

    private String selectUrl;		//浏览按钮选择类别URL

    private String needSelect;	// needSelect=1时会先请求selectoptions，根据选中项，获取detail参数请求列表数据；默认情况下会直接请求列表数据。

    // ESB 请求响应数据浏览按钮 属性 end

    private void init(){

        this.viewAttr =  2;

        this.isSingle = true;

        this.isDetail = 0;

        this.isAutoComplete = 1;

        this.hasAddBtn  = false;

        this.quickSearchName  = "";

        this.dataParams = new HashMap<String,Object>();

        this.destDataParams = new HashMap<String,Object>();

        this.conditionDataParams = new HashMap<String,Object>();

        this.completeParams = new HashMap<String,Object>();

        this.otherParams = new HashMap<String,Object>();

        this.idSeparator  = ",";

        this.pageSize  = -1;

    }

    public AbstractWeaBrowser(){

        init();

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getViewAttr() {
        return viewAttr;
    }

    public void setViewAttr(int viewAttr) {
        this.viewAttr = viewAttr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public void setSingle(boolean single) {
        isSingle = single;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOnChange() {
        return onChange;
    }

    public void setOnChange(String onChange) {
        this.onChange = onChange;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconColor() {
        return iconColor;
    }

    public void setIconColor(String iconColor) {
        this.iconColor = iconColor;
    }

    public String getIconBgcolor() {
        return iconBgcolor;
    }

    public void setIconBgcolor(String iconBgcolor) {
        this.iconBgcolor = iconBgcolor;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Map<String, Object> getModalStyle() {
        return modalStyle;
    }

    public void setModalStyle(Map<String, Object> modalStyle) {
        this.modalStyle = modalStyle;
    }

    public Map<String, Object> getInputStyle() {
        return inputStyle;
    }

    public void setInputStyle(Map<String, Object> inputStyle) {
        this.inputStyle = inputStyle;
    }

    public int getIsDetail() {
        return isDetail;
    }

    public void setIsDetail(int isDetail) {
        this.isDetail = isDetail;
    }

    public String getDataURL() {
        return dataURL;
    }

    public void setDataURL(String dataURL) {
        this.dataURL = dataURL;
    }

    public String getDestDataURL() {
        return destDataURL;
    }

    public void setDestDataURL(String destDataURL) {
        this.destDataURL = destDataURL;
    }

    public String getConditionURL() {
        return conditionURL;
    }

    public void setConditionURL(String conditionURL) {
        this.conditionURL = conditionURL;
    }

    public String getCompleteURL() {
        return completeURL;
    }

    public void setCompleteURL(String completeURL) {
        this.completeURL = completeURL;
    }

    public Map<String, Object> getDataParams() {
        return dataParams;
    }

    public void setDataParams(Map<String, Object> dataParams) {
        this.dataParams = dataParams;
    }

    public Map<String, Object> getDestDataParams() {
        return destDataParams;
    }

    public void setDestDataParams(Map<String, Object> destDataParams) {
        this.destDataParams = destDataParams;
    }

    public Map<String, Object> getConditionDataParams() {
        return conditionDataParams;
    }

    public void setConditionDataParams(Map<String, Object> conditionDataParams) {
        this.conditionDataParams = conditionDataParams;
    }

    public Map<String, Object> getCompleteParams() {
        return completeParams;
    }

    public void setCompleteParams(Map<String, Object> completeParams) {
        this.completeParams = completeParams;
    }

    public int getIsAutoComplete() {
        return isAutoComplete;
    }

    public void setIsAutoComplete(int isAutoComplete) {
        this.isAutoComplete = isAutoComplete;
    }

    public boolean isHasAddBtn() {
        return hasAddBtn;
    }

    public void setHasAddBtn(boolean hasAddBtn) {
        this.hasAddBtn = hasAddBtn;
    }

    public String getDdUrl() {
        return ddUrl;
    }

    public void setDdUrl(String ddUrl) {
        this.ddUrl = ddUrl;
    }

    public String getAddOnClick() {
        return addOnClick;
    }

    public void setAddOnClick(String addOnClick) {
        this.addOnClick = addOnClick;
    }

    public String get_callbackForAdd() {
        return _callbackForAdd;
    }

    public void set_callbackForAdd(String _callbackForAdd) {
        this._callbackForAdd = _callbackForAdd;
    }

    public Map<String, Object> get_callbackForAddParams() {
        return _callbackForAddParams;
    }

    public void set_callbackForAddParams(Map<String, Object> _callbackForAddParams) {
        this._callbackForAddParams = _callbackForAddParams;
    }

    public List<String> getRelateFieldid() {
        return relateFieldid;
    }

    public void setRelateFieldid(List<String> relateFieldid) {
        this.relateFieldid = relateFieldid;
    }

    public List<BrowserTabBean> getTabs() {
        return tabs;
    }

    public void setTabs(List<BrowserTabBean> tabs) {
        this.tabs = tabs;
    }

    public List<Map<String, Object>> getAppendDatas() {
        return appendDatas;
    }

    public void setAppendDatas(List<Map<String, Object>> appendDatas) {
        this.appendDatas = appendDatas;
    }

    public List<Map<String, Object>> getReplaceDatas() {
        return replaceDatas;
    }

    public void setReplaceDatas(List<Map<String, Object>> replaceDatas) {
        this.replaceDatas = replaceDatas;
    }

    public boolean isHasAdvanceSerach() {
        return hasAdvanceSerach;
    }

    public void setHasAdvanceSerach(boolean hasAdvanceSerach) {
        this.hasAdvanceSerach = hasAdvanceSerach;
    }

    public String getQuickSearchName() {
        return quickSearchName;
    }

    public void setQuickSearchName(String quickSearchName) {
        this.quickSearchName = quickSearchName;
    }

    public boolean isMultCheckbox() {
        return isMultCheckbox;
    }

    public void setMultCheckbox(boolean multCheckbox) {
        isMultCheckbox = multCheckbox;
    }

    public String getIdSeparator() {
        return idSeparator;
    }

    public void setIdSeparator(String idSeparator) {
        this.idSeparator = idSeparator;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isHasBorder() {
        return hasBorder;
    }

    public void setHasBorder(boolean hasBorder) {
        this.hasBorder = hasBorder;
    }

    public boolean isShowCheckStrictly() {
        return showCheckStrictly;
    }

    public void setShowCheckStrictly(boolean showCheckStrictly) {
        this.showCheckStrictly = showCheckStrictly;
    }

    public boolean isCheckStrictly() {
        return checkStrictly;
    }

    public void setCheckStrictly(boolean checkStrictly) {
        this.checkStrictly = checkStrictly;
    }

    public boolean isHideVirtualOrg() {
        return hideVirtualOrg;
    }

    public void setHideVirtualOrg(boolean hideVirtualOrg) {
        this.hideVirtualOrg = hideVirtualOrg;
    }

    public boolean isExpandfirstnode() {
        return expandfirstnode;
    }

    public void setExpandfirstnode(boolean expandfirstnode) {
        this.expandfirstnode = expandfirstnode;
    }

    public boolean isAsynLoadAll() {
        return asynLoadAll;
    }

    public void setAsynLoadAll(boolean asynLoadAll) {
        this.asynLoadAll = asynLoadAll;
    }

    public int getDefaultExpandedLevel() {
        return defaultExpandedLevel;
    }

    public void setDefaultExpandedLevel(int defaultExpandedLevel) {
        this.defaultExpandedLevel = defaultExpandedLevel;
    }

    public String getSearchPlaceholder() {
        return searchPlaceholder;
    }

    public void setSearchPlaceholder(String searchPlaceholder) {
        this.searchPlaceholder = searchPlaceholder;
    }

    public boolean isScrollx() {
        return scrollx;
    }

    public void setScrollx(boolean scrollx) {
        this.scrollx = scrollx;
    }

    public boolean isNoOperate() {
        return noOperate;
    }

    public void setNoOperate(boolean noOperate) {
        this.noOperate = noOperate;
    }

    public Map<String, Object> getOtherParams() {
        return otherParams;
    }

    public void setOtherParams(Map<String, Object> otherParams) {
        this.otherParams = otherParams;
    }

    public String getContextType() {
        return contextType;
    }

    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    public String getSelectUrl() {
        return selectUrl;
    }

    public void setSelectUrl(String selectUrl) {
        this.selectUrl = selectUrl;
    }

    public String getNeedSelect() {
        return needSelect;
    }

    public void setNeedSelect(String needSelect) {
        this.needSelect = needSelect;
    }
}
