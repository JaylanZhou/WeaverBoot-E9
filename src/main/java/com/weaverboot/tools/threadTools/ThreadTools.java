package com.weaverboot.tools.threadTools;

import com.weaverboot.tools.transactionTools.data.RecordSetTransStack;

/**
 *
 * 线程工具类
 *
 * @Author : Jaylan Zhou
 *
 */

public class ThreadTools {

    private ThreadTools(){



    }

    //全局线程变量，用于存放RecordSetTrans栈，管理事务
    public final static ThreadLocal threadLocal = new ThreadLocal();

    /**
     *
     * 检查事务是否被关闭
     *
     * @return true
     */

    public static boolean checkLocalValue(){

        if (threadLocal.get() == null){

            throw new RuntimeException("事务已被关闭，无法执行语句");

        }else{

            return true;

        }

    }

    /**
     *
     * 判断事务是否开启
     *
     * @return 开启true，关闭false
     */

    public static boolean checkOnAuto(){

        RecordSetTransStack recordSetTransStack = getRecordSetTransStack();

        if (recordSetTransStack.isEmpty()){

            return false;

        }else{

            return true;

        }

    }

    public static RecordSetTransStack getRecordSetTransStack(){

        RecordSetTransStack recordSetTransStack;

        if (threadLocal.get() == null){

            recordSetTransStack = new RecordSetTransStack();

            threadLocal.set(recordSetTransStack);

        } else {

            recordSetTransStack = (RecordSetTransStack) threadLocal.get();

        }

        return recordSetTransStack;

    }

}
