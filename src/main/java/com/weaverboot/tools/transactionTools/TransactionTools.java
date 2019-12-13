package com.weaverboot.tools.transactionTools;

import com.weaverboot.tools.threadTools.ThreadTools;
import com.weaverboot.tools.transactionTools.data.RecordSetTransStack;
import weaver.conn.RecordSetTrans;
import weaver.general.BaseBean;

/**
 *
 * 事务操作类
 *
 * @Author : Jaylan Zhou
 *
 */

public class TransactionTools extends BaseBean {

    private RecordSetTrans recordSetTrans = null;

    private RecordSetTransStack recordSetTransStack;

    public TransactionTools(){

        init();

    }

    private void init(){

        this.recordSetTransStack = ThreadTools.getRecordSetTransStack();

    }

    /**
     *
     * 开启事务
     *
     * @return
     */

    public RecordSetTrans transactionStart(){

        try{

            recordSetTransStack.pushRecordSetTrans();

            this.writeLog("事务" + this.recordSetTransStack.getTransNum() + "已开启,当前事务个数：" + this.recordSetTransStack.getTransNum());

            return recordSetTransStack.getNowRecordSetTrans();

        }catch (Exception e){

            e.printStackTrace();

           throw new RuntimeException("创建事务失败！原因为:" + e.getMessage());

        }

    }

    /**
     *
     * 提交事务
     *
     */

    public void transactionCommit(){

        try {

            if(!recordSetTransStack.isEmpty()) {

                this.recordSetTransStack.getNowRecordSetTrans().commit();

                this.recordSetTransStack.popRecordSetTrans();

                this.writeLog("事务" + (Integer.valueOf(recordSetTransStack.getTransNum()) + 1) + "已提交，当前事务个数：" + recordSetTransStack.getTransNum());

            }else{

                this.writeLog("事务已空，无法提交");

            }

            if(recordSetTransStack.isEmpty()) {

                ThreadTools.threadLocal.remove();

            }

        }catch (Exception e){

            e.printStackTrace();

            transactionRollback();

            throw new RuntimeException("事务提交失败，原因为:" + e.getMessage());

        }

    }

    /**
     *
     * 回滚事务
     *
     */

    public void transactionRollback(){

        try {

            if(!this.recordSetTransStack.isEmpty()) {

                this.recordSetTransStack.getNowRecordSetTrans().rollback();

                this.recordSetTransStack.popRecordSetTrans();

                this.writeLog("事务" + (Integer.valueOf(recordSetTransStack.getTransNum()) + 1) + "已回滚，当前事务个数" + this.recordSetTransStack.getTransNum());

            }else{

                this.writeLog("当前事务已经关闭，无法回滚");

            }

            if(recordSetTransStack.isEmpty()) {

                ThreadTools.threadLocal.remove();

            }

        }catch (Exception e){

            this.writeLog("事务回滚失败，原因为:"  + e.getMessage());

            throw new RuntimeException("事务回滚失败，原因为:" + e.getMessage());

        }

    }

    /**
     *
     * 判断当前RecordSetTrans 是否开启
     *
     * @return 开启状态
     */

    @Deprecated
    public boolean isOpen() {

        if(this.recordSetTrans == null){

            return false;

        }else{

            return true;

        }

    }

    /**
     *
     * 关闭当前RecordSetTrans
     *
     */

    public void closeThisRecordTrans(){

            ThreadTools.threadLocal.remove();

    }

    /**
     *
     * 清除当前线程的RecordSetTrans
     *
     *//*
    public void closeRecordTrans() {

        if (ThreadTools.threadLocal.get() != null) {

            TransactionTools transactionTools = (TransactionTools) ThreadTools.threadLocal.get();

            if (transactionTools.getRecordSetTrans() == null) {

                throw new RuntimeException("当前事务资源已关闭！");

            } else {

                ThreadTools.threadLocal.remove();

                this.recordSetTrans = null;

            }

        }

    }*/

    /**
     *
     * 获取当前线程的recordSetTrans
     *
     * @return 当前线程的recordSetTrans
     */

    public RecordSetTrans getRecordSetTrans() {

        return recordSetTrans;

    }

    @Deprecated
    public static TransactionTools getNowTransactionTools(){

        TransactionTools transactionTools = (TransactionTools) ThreadTools.threadLocal.get();

        if(transactionTools == null){

            throw new RuntimeException("当前未有运行的事务");

        }else{

            return transactionTools;

        }

    }


}
