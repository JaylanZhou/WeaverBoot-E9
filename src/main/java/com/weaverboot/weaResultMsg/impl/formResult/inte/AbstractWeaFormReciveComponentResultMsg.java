package com.weaverboot.weaResultMsg.impl.formResult.inte;

import com.alibaba.fastjson.JSONObject;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;
import com.weaverboot.weaComponent.impl.weaTab.inte.AbstractWeaTab;
import com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaChildTree;
import com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaFatherTree;

public abstract class AbstractWeaFormReciveComponentResultMsg extends AbstractWeaFormComponentResultMsg {

    public <T extends AbstractWeaForm> T readFormItem(int groupIndex, int itemIndex, Class<T> tClass) {

        this.checkGroupAndItemNum(groupIndex,itemIndex);

        JSONObject jsonObject = (JSONObject) this.getCondition().get(groupIndex).getItems().get(itemIndex);

        T abstractWeaForm = JSONObject.parseObject(jsonObject.toJSONString(), tClass);

        return abstractWeaForm;

    }

    public void saveFormItem(int groupIndex, int itemIndex, AbstractWeaForm abstractWeaForm) {

        this.checkGroupAndItemNum(groupIndex,itemIndex);

        this.getCondition().get(groupIndex).getItems().set(itemIndex,abstractWeaForm);

    }

    public <T extends AbstractWeaFatherTree> T readFatherTreeNode(int fatherIndex, Class<T> tClass) {

        this.checkFatherNum(fatherIndex);

        JSONObject jsonObject = (JSONObject) this.getWeaTree().get(fatherIndex);

        T abstractWeaFatherTree = JSONObject.parseObject(jsonObject.toJSONString(),tClass);

        return abstractWeaFatherTree;

    }

    public void saveFatherTreeNode(int fatherIndex, AbstractWeaFatherTree abstractWeaFatherTree) {

        this.checkFatherNum(fatherIndex);

        this.getWeaTree().set(fatherIndex,abstractWeaFatherTree);

    }

    public <T extends AbstractWeaChildTree> T readChildTreeNode(int fatherTreeIndex, int childTreeIndex,Class<T> tClass) {



        return null;

    }

    public void saveChildTreeNode(AbstractWeaFatherTree abstractWeaFatherTree, int groupIndex, AbstractWeaChildTree abstractWeaChildTree) {



    }

    public <T extends AbstractWeaTab> T readWeaTab( int itemIndex, Class<T> tClass) {

        return null;

    }

    public void saveWeaTab( int itemIndex, AbstractWeaTab abstractWeaTab) {



    }

    private void checkGroupAndItemNum(int groupIndex, int itemIndex){

        int groupNum = this.getCondition().size();

        int groupMax = groupNum - 1;

        if (groupNum < groupIndex){

            throw new RuntimeException("当前group的最大个数为" + groupNum + ",您最大可选择" + groupMax);

        } else {

            int itemNum = this.getCondition().get(groupIndex).getItems().size();

            int itemMax = itemNum - 1;

            if (itemNum < itemIndex) {

                throw new RuntimeException("分组" + groupIndex + "的条数最大为" + itemNum + ",您最大可选择" + itemMax);

            }

        }

    }

    private void checkFatherNum(int fatherIndex){

        int fatherNum = this.getWeaTree().size();

        int fahterMax = fatherNum - 1;

        if (fatherNum < fatherIndex){

            throw new RuntimeException("当前父级树的最大个数为" + fatherNum + ",您最大可选择" + fahterMax);

        }

    }

}
