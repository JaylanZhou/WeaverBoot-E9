package com.weaverboot.tools.frameTools.basedao;

import weaver.conn.RecordSet;

public class DataBaseInfoConfig {

    private DataBaseInfoConfig(){}

    public final static String DBTYPE = new RecordSet().getDBType(); //Ecology 连接的数据库类型

}
