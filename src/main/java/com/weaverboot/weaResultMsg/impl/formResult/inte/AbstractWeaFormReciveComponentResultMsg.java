package com.weaverboot.weaResultMsg.impl.formResult.inte;

import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.logTools.LogTools;
import com.weaverboot.weaComponent.impl.weaForm.weaFormGroup.inte.AbstractWeaFormGroup;
import com.weaverboot.weaComponent.impl.weaTab.inte.AbstractWeaTab;
import com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaFatherTree;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWeaFormReciveComponentResultMsg extends AbstractWeaFormComponentResultMsg {

    public AbstractWeaFormGroup readWeaFormGroup(int groupIndex){

        return (AbstractWeaFormGroup) this.getCondition().get(groupIndex);

    }

    public AbstractWeaFormGroup readWeaFormGroupWithTitle(String title){

        for (int i = 0; i < this.getCondition().size(); i++) {

            AbstractWeaFormGroup ab = (AbstractWeaFormGroup) this.getCondition().get(i);

            if (BaseTools.notNullString(ab.getTitle()) && ab.getTitle().equals(title)){

                return ab;

            }

        }

        LogTools.writeLog("未找到名为" + title + "的组");

        return null;

    }

    public List<AbstractWeaFormGroup> readWeaFormGroupWithTitleAll(String title){

        List<AbstractWeaFormGroup> abstractWeaFormGroupList = new ArrayList<>();

        for (int i = 0; i < this.getCondition().size(); i++) {

            AbstractWeaFormGroup ab = (AbstractWeaFormGroup) this.getCondition().get(i);

            if (BaseTools.notNullString(ab.getTitle()) && ab.getTitle().equals(title)){

                abstractWeaFormGroupList.add(ab);

            }

        }

        if (BaseTools.notNullList(abstractWeaFormGroupList)){

            return abstractWeaFormGroupList;

        }

        LogTools.writeLog("未找到名为" + title + "的组");

        return null;

    }

    public void removeWeaFormGroup(int groupIndex) {

        this.getCondition().remove(groupIndex);

    }

    public void removeWeaFormGroupWithTitle(String title) {

        for (int i = 0; i < this.getCondition().size(); i++) {

            AbstractWeaFormGroup abstractWeaFormGroup = (AbstractWeaFormGroup) this.getCondition().get(i);

            if (BaseTools.notNullString(abstractWeaFormGroup.getTitle()) && abstractWeaFormGroup.getTitle().equals(title)) {

                this.getCondition().remove(i);

                break;

            }

        }

    }

    public void removeWeaFormGroupWithTitleAll(String title) {

        for (int i = 0; i < this.getCondition().size(); i++) {

            AbstractWeaFormGroup abstractWeaFormGroup = (AbstractWeaFormGroup) this.getCondition().get(i);

            if (BaseTools.notNullString(abstractWeaFormGroup.getTitle()) && abstractWeaFormGroup.getTitle().equals(title)) {

                this.getCondition().remove(i);

            }

        }

    }

    public void addWeaFormGroup(int groupIndex,AbstractWeaFormGroup abstractWeaFormGroup){

        this.getCondition().add(groupIndex,abstractWeaFormGroup);

    }

    public void addWeaFormGroup(AbstractWeaFormGroup abstractWeaFormGroup){

        this.getCondition().add(abstractWeaFormGroup);

    }


    public AbstractWeaFatherTree readWeaFatherTree(int treeIndex){

        return (AbstractWeaFatherTree) this.getWeaTree().get(treeIndex);

    }

    public AbstractWeaFatherTree readWeaFatherTreeWithName(String name){

        for (int i = 0; i < this.getWeaTree().size(); i++) {

            AbstractWeaFatherTree ab = (AbstractWeaFatherTree) this.getWeaTree().get(i);

            if (BaseTools.notNullString(ab.getName()) && ab.getName().equals(name)){

                return ab;

            }

        }

        LogTools.writeLog("未找到名为" + name + "的父树");

        return null;

    }

    public List<AbstractWeaFatherTree> readWeaFatherTreeWithNameAll(String name){

        List<AbstractWeaFatherTree> abstractWeaFatherTreeList = new ArrayList<>();

        for (int i = 0; i < this.getWeaTree().size(); i++) {

            AbstractWeaFatherTree ab = (AbstractWeaFatherTree) this.getWeaTree().get(i);

            if (BaseTools.notNullString(ab.getName()) && ab.getName().equals(name)){

                abstractWeaFatherTreeList.add(ab);

            }

        }

        if (BaseTools.notNullList(abstractWeaFatherTreeList)){

            return abstractWeaFatherTreeList;

        }

        LogTools.writeLog("未找到名为" + name + "的父树");

        return null;

    }

    public void removeWeaFatherTree(int fatherTreeIndex) {

        this.getWeaTree().remove(fatherTreeIndex);

    }

    public void removeWeaFatherTreeWithName(String name) {

        for (int i = 0; i < this.getWeaTree().size(); i++) {

            AbstractWeaFatherTree abstractWeaFatherTree = (AbstractWeaFatherTree) this.getWeaTree().get(i);

            if (BaseTools.notNullString(abstractWeaFatherTree.getName()) && abstractWeaFatherTree.getName().equals(name)) {

                this.getCondition().remove(i);

                break;

            }

        }

    }

    public void removeWeaFatherTreeWithNameAll(String title) {

        for (int i = 0; i < this.getWeaTree().size(); i++) {

            AbstractWeaFormGroup abstractWeaFormGroup = (AbstractWeaFormGroup) this.getWeaTree().get(i);

            if (BaseTools.notNullString(abstractWeaFormGroup.getTitle()) && abstractWeaFormGroup.getTitle().equals(title)) {

                this.getCondition().remove(i);

            }

        }

    }

    public void addWeaFatherTree(int treeIndex,AbstractWeaFatherTree abstractWeaFatherTree){

        this.getWeaTree().add(treeIndex,abstractWeaFatherTree);

    }

    public void addWeaFatherTree(AbstractWeaFatherTree abstractWeaFatherTree){

        this.getWeaTree().add(abstractWeaFatherTree);

    }

    public AbstractWeaTab readWeaTab(int tabIndex){

        return (AbstractWeaTab) this.getWeaTab().get(tabIndex);

    }

    public AbstractWeaTab readWeaTabWithTitle(String title){

        for (int i = 0; i < this.getWeaTree().size(); i++) {

            AbstractWeaTab ab = (AbstractWeaTab) this.getWeaTab().get(i);

            if (BaseTools.notNullString(ab.getTitle()) && ab.getTitle().equals(title)){

                return ab;

            }

        }

        LogTools.writeLog("未找到名为" + title + "的标签页");

        return null;

    }

    public List<AbstractWeaTab> readWeaTabWithTitleAll(String title){

        List<AbstractWeaTab> abstractWeaTabList = new ArrayList<>();

        for (int i = 0; i < this.getWeaTab().size(); i++) {

            AbstractWeaTab ab = (AbstractWeaTab) this.getWeaTab().get(i);

            if (BaseTools.notNullString(ab.getTitle()) && ab.getTitle().equals(title)){

                abstractWeaTabList.add(ab);

            }

        }

        if (BaseTools.notNullList(abstractWeaTabList)){

            return abstractWeaTabList;

        }

        LogTools.writeLog("未找到名为" + title + "的标签");

        return null;

    }

    public void removeWeaTab(int weaTabIndex) {

        this.getWeaTab().remove(weaTabIndex);

    }

    public void removeWeaTabWithTitle(String title) {

        for (int i = 0; i < this.getWeaTab().size(); i++) {

            AbstractWeaTab abstractWeaTab = (AbstractWeaTab) this.getWeaTab().get(i);

            if (BaseTools.notNullString(abstractWeaTab.getTitle()) && abstractWeaTab.getTitle().equals(title)) {

                this.getWeaTab().remove(i);

                break;

            }

        }

    }

    public void removeWeaTabWithTitleAll(String title) {

        for (int i = 0; i < this.getWeaTab().size(); i++) {

            AbstractWeaTab abstractWeaTab = (AbstractWeaTab) this.getWeaTab().get(i);

            if (BaseTools.notNullString(abstractWeaTab.getTitle()) && abstractWeaTab.getTitle().equals(title)) {

                this.getWeaTab().remove(i);

            }

        }

    }

    public void addWeaTab(int tabIndex,AbstractWeaTab abstractWeaTab){

        this.getWeaTab().add(tabIndex,abstractWeaTab);

    }

    public void addWeaTab(AbstractWeaTab abstractWeaTab){

        this.getWeaTab().add(abstractWeaTab);

    }

}
