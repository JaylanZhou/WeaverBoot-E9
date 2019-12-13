package com.weaverboot.tools.formBuildTools;

import weaver.formmode.setup.ModeRightInfo;

public class FormBuildTools {

    /**
     *
     * 新增条目授权
     *
     * @param peopleId 赋权用户id
     * @param dataId 赋权数据id
     * @param authorNumber 表单建模模块id（在表单建模模块中，右键显示页面地址可看到id字段）
     */

    public static void inAuthor(String peopleId,int dataId,int authorNumber){

        ModeRightInfo ModeRightInfo = new ModeRightInfo();
        ModeRightInfo.setNewRight(true);
        ModeRightInfo.editModeDataShare(Integer.parseInt(peopleId),authorNumber,dataId);

    }

    private FormBuildTools(){



    }

}
