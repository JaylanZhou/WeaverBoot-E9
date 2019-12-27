package com.weaverboot.weaResultMsg.impl.formResult.inte;

import com.alibaba.fastjson.JSONObject;
import com.weaverboot.weaComponent.impl.weaForm.inte.AbstractWeaForm;
import com.weaverboot.weaComponent.impl.weaForm.weaFormGroup.inte.AbstractWeaFormGroup;
import com.weaverboot.weaComponent.impl.weaTab.inte.AbstractWeaTab;
import com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaChildTree;
import com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaFatherTree;

public abstract class AbstractWeaFormReciveComponentResultMsg extends AbstractWeaFormComponentResultMsg {

    public <T extends AbstractWeaFormGroup> T readWeaFormGroup(int groupIndex,Class<T> tClass){

        JSONObject jsonObject = (JSONObject) this.getCondition().get(groupIndex);

        T abstractWeaFormGroup = jsonObject.parseObject(jsonObject.toJSONString(),tClass);

        return abstractWeaFormGroup;

    }

    public void saveWeaFormGroup(int groupIndex,AbstractWeaFormGroup abstractWeaFormGroup){

        this.getCondition().set(groupIndex,abstractWeaFormGroup);

    }

    public <T extends AbstractWeaFatherTree>T readFatherTreeNode(int fatherIndex, Class<T> tClass){

        //checkFatherNum(fatherIndex);

        JSONObject jsonObject = (JSONObject) this.getWeaTree().get(fatherIndex);

        T abstractWeaFatherTree = jsonObject.parseObject(jsonObject.toJSONString(),tClass);

        return abstractWeaFatherTree;

    }

    public void saveFatherTreeNode(int fatherIndex, AbstractWeaFatherTree abstractWeaFatherTree){

        //checkFatherNum(fatherIndex);

        this.getWeaTree().set(fatherIndex,abstractWeaFatherTree);

    }

    public <T extends AbstractWeaChildTree>T readChildTreeNode(AbstractWeaFatherTree abstractWeaFatherTree, int childIndex, Class<T> tClass) throws IllegalAccessException, InstantiationException {

        JSONObject jsonObject = (JSONObject) abstractWeaFatherTree.getChilds().get(childIndex);

        T abstractWeaChildTree = jsonObject.parseObject(jsonObject.toJSONString(),tClass);

        return abstractWeaChildTree;

    }

    public void saveChildTreeNode(AbstractWeaFatherTree abstractWeaFatherTree,int childIndex,AbstractWeaChildTree abstractWeaChildTree){

        //checkChildNum(abstractWeaFatherTree,childIndex);

        abstractWeaFatherTree.getChilds().set(childIndex,abstractWeaChildTree);

    }

//    public <T extends AbstractWeaTab>T readWeaTab(int itemIndex, Class<T> tClass){
//
//        JSONObject jsonObject = (JSONObject) this.getWeaTab().get(itemIndex);
//
//        T abstractWeaTab = JSONObject.parseObject(jsonObject.toJSONString(),tClass);
//
//        return abstractWeaTab;
//
//    }
//
//    public void saveWeaTab(int itemIndex, AbstractWeaTab abstractWeaTab){
//
//        this.getWeaTab().set(itemIndex,abstractWeaTab);
//
//    }
//
//    private void checkGroupAndItemNum(int groupIndex, int itemIndex){
//
//        int groupNum = this.getCondition().size();
//
//        int groupMax = groupNum - 1;
//
//        if (groupNum < groupIndex){
//
//            throw new RuntimeException("当前group的最大个数为" + groupNum + ",您最大可选择" + groupMax);
//
//        } else {
//
//            int itemNum = this.getCondition().get(groupIndex).getItems().size();
//
//            int itemMax = itemNum - 1;
//
//            if (itemNum < itemIndex) {
//
//                throw new RuntimeException("分组" + groupIndex + "的条数最大为" + itemNum + ",您最大可选择" + itemMax);
//
//            }
//
//        }
//
//    }
//
//    private void checkFatherNum(int fatherIndex){
//
//        int fatherNum = this.getWeaTree().size();
//
//        int fahterMax = fatherNum - 1;
//
//        if (fatherNum < fatherIndex){
//
//            throw new RuntimeException("当前父级树的最大个数为" + fatherNum + ",您最大可选择" + fahterMax);
//
//        }
//
//    }
//
//    private void checkChildNum(AbstractWeaFatherTree abstractWeaFatherTree,int childIndex){
//
//        int childNum = abstractWeaFatherTree.getChilds().size();
//
//        int childMax = childNum - 1;
//
//        if (childNum < childIndex){
//
//            throw new RuntimeException("当前父级树的最大个数为" + childNum + ",您最大可选择" + childIndex);
//
//        }
//
//    }

}
