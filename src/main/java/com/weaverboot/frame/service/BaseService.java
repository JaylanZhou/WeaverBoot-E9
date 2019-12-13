package com.weaverboot.frame.service;
import com.weaverboot.frame.dao.BaseCustomDao;
import com.weaverboot.frame.dao.BaseDao;
import com.weaverboot.frame.model.BaseModel;
import com.weaverboot.tools.enumTools.frame.SelectCondition;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.soa.workflow.request.RequestInfo;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 *
 * Service层父类
 *
 * @param <D> 此模块中对应的Dao层文件
 * @param <T> 此模块中对应的Model层文件
 *
 * @Author : Jaylan Zhou
 *
 */

@Deprecated
public class BaseService<D extends BaseDao,T extends BaseModel> extends BaseBean {


        protected D baseDao;

        protected BaseCustomDao customDao = new BaseCustomDao();

    {
        try {
            baseDao = this.getTClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("BaseDao初始化错误，原因为:" + e.getMessage());
        }
    }


    /**
     *
     * 查询出列表的方法
     *
     * @param t 此类中set的字段作为查询条件字段
     * @param condition 查询条件,封装在枚举SelectCondition中
     * @throws Exception
     */

    public List<T> informationLoadList(T t, SelectCondition condition) throws Exception {


       List<T> result = this.baseDao.informationLoadList(t,condition);

        return result;

    }

    /**
     *
     * 外部数据源 - 查询出列表的方法
     *
     * @param t 此类中set的字段作为查询条件字段
     * @param dbname 外部数据源配置名称
     * @param condition 查询条件,封装在枚举SelectCondition中
     * @throws Exception
     */

    public List<T> informationLoadList(T t, String dbname, SelectCondition condition) throws Exception {


        List<T> result = this.baseDao.informationLoadList(t,dbname,condition);

        return result;

    }

    /**
     *
     * 查询出单个信息的方法
     *
     * @param t 此类中set的字段作为查询条件字段
     * @param condition 查询条件,封装在枚举SelectCondition中
     * @throws Exception
     */

    public T informationLoadOne(T t, SelectCondition condition) throws Exception {


        T result = (T) this.baseDao.informationLoadOne(t,condition);

        return result;

    }

    /**
     *
     * 外部数据源 - 查询出单个信息的方法
     *
     * @param t 此类中set的字段作为查询条件字段
     * @param dbname 外部数据源配置名称
     * @param condition 查询条件,封装在枚举SelectCondition中
     * @throws Exception
     */

    public T informationLoadOne(T t, String dbname, SelectCondition condition) throws Exception {


        T result = (T) this.baseDao.informationLoadOne(t,dbname,condition);

        return result;

    }


    /**
     *
     * 添加方法
     *
     * @param t 对应的泛型类
     * @throws Exception
     */

    public void informationAdd(T t) throws Exception {

        this.baseDao.informationAdd(t);

    }

    /**
     *
     * 删除方法
     *
     * @param t 对应的泛型类
     * @throws Exception
     */

    public void informationDelete(T t, SelectCondition condition) throws Exception {

        this.baseDao.informationDelete(t,condition);

    }


    /**
     *
     * 逻辑删除
     *
     * @param t
     */

    public void informationLogicDelete(T t,SelectCondition condition) throws Exception {

        this.baseDao.informationLogicDelete(t,condition);

    }


    /**
     * 修改方法
     *
     * @param t1 此实体对象中存储了要修改的字段内容
     * @param t2 此实体对象中存储了作为查询条件的字段内容
     * @param condition
     * @throws Exception
     */

    public void informationUpdate(T t1,T t2,SelectCondition condition) throws Exception {

        this.baseDao.informationUpdate(t1,t2,condition);

    }


    /**
     *
     * 新增条目授权
     *
     * @param peopleId 赋权用户id
     * @param dataId 赋权数据id
     * @param authorNumber 表单建模模块id（在表单建模模块中，右键显示页面地址可看到id字段）
     */

    public void inAuthor(String peopleId,int dataId,int authorNumber){

        ModeRightInfo ModeRightInfo = new ModeRightInfo();
        ModeRightInfo.setNewRight(true);
        ModeRightInfo.editModeDataShare(Integer.parseInt(peopleId),authorNumber,dataId);

    }

    private Class<D> getTClass() {

        //获取泛型的类型
        Class<D> entityClass = (Class<D>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];

        return entityClass;


    }

    /**
     *
     * 获取此流程表的id
     *
     * @param workflowId
     * @return
     * @throws Exception
     */

    @Deprecated
    public String getFormId(String workflowId) throws Exception {

        String sql = "SELECT formid FROM workflow_base WHERE id = " + workflowId;

        String formId = this.customDao.informationLoadOneColumnCustom("formid",sql);

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

    @Deprecated
    public String getFormName(String workflowId) throws Exception {

        String sql = "SELECT tablename FROM workflow_bill WHERE id = (SELECT formid FROM workflow_base WHERE id = " + workflowId + " and isbill = 1)";

        String tablename = this.customDao.informationLoadOneColumnCustom("tablename",sql);

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

    @Deprecated
    public String getDtFormName(String formname , String num) throws Exception {

        String tablename = formname + "_dt" + num;

        return tablename;

    }

    /**
     *
     * 获取当前最大id
     *
     * @return
     * @throws Exception
     */

    @Deprecated
    public int getMaxId() throws Exception {

        return this.baseDao.getMaxId();

    }

    /**
     *
     * 流程错误信息展示
     *
     * @param requestInfo action中默认的参数requestInfo
     * @param id 一个id
     * @param message 套展示的错误信息
     */

    @Deprecated
    public void showErrorMessageInPage(RequestInfo requestInfo, String id, String message){

        requestInfo.getRequestManager().setMessageid(id);

        requestInfo.getRequestManager().setMessagecontent(message);

    }




}
