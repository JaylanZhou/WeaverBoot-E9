package com.weaverboot.tools.transactionTools.data;

import weaver.conn.RecordSetTrans;
import weaver.general.BaseBean;

public class RecordSetTransStack {

    private int pointer;

    private final static int INCREMENT = 10;

    private RecordSetTrans[] recordSetTranArray;

    private BaseBean baseBean = new BaseBean();

    public RecordSetTransStack(){

        init();

    }

    private void init(){

        this.pointer = -1;

        recordSetTranArray = new RecordSetTrans[10];

    }

    public void pushRecordSetTrans(){

        if(this.isFull()){

            this.recordSetTranArray = this.autoIncrement(this.recordSetTranArray);

        }

        this.pointer++;

        RecordSetTrans recordSetTrans = new RecordSetTrans();

        recordSetTrans.setAutoCommit(false);

        this.recordSetTranArray[this.pointer] = recordSetTrans;



    }

    public int getTransNum(){

        return (this.pointer + 1);

    }

    public void popRecordSetTrans(){

        if(this.isEmpty()){

            throw new RuntimeException("框架报错：事务资源已空");

        }

        this.recordSetTranArray[this.pointer] = null;

        this.pointer--;

    }

    public RecordSetTrans getNowRecordSetTrans(){

        return this.recordSetTranArray[this.pointer];

    }

    public void flushTrans(){

        this.init();

    }

    public boolean isEmpty(){

        return this.pointer == -1;

    }

    public boolean isFull(){

        return this.pointer == this.recordSetTranArray.length - 1;

    }

    public RecordSetTrans[] autoIncrement(RecordSetTrans[] recordSetTranArray){

        RecordSetTrans[] increment = new RecordSetTrans[recordSetTranArray.length + this.INCREMENT];

        for (int i = 0; i < recordSetTranArray.length; i++) {

            increment[i] = recordSetTranArray[i];

        }

        return increment;

    }

    public RecordSetTrans[] getRecordSetTranArray() {
        return recordSetTranArray;
    }

    public void setRecordSetTranArray(RecordSetTrans[] recordSetTranArray) {
        this.recordSetTranArray = recordSetTranArray;
    }
}
