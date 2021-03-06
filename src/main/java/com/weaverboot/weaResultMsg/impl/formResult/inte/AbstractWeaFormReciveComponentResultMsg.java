package com.weaverboot.weaResultMsg.impl.formResult.inte;

import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.logTools.LogTools;
import com.weaverboot.weaComponent.impl.weaForm.weaFormGroup.inte.AbstractWeaFormGroup;
import com.weaverboot.weaComponent.impl.weaTab.inte.AbstractWeaTab;
import com.weaverboot.weaComponent.impl.weaTree.inte.AbstractWeaTree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Form 接收消息体类的基类
 *
 * 此类提供了所有接收消息体的公有方法，所有自定义的接收消息体均需继承此类
 *
 * @Author : Jaylan Zhou
 *
 * @Date : 2020-01-02
 *
 */

public abstract class AbstractWeaFormReciveComponentResultMsg extends AbstractWeaFormComponentResultMsg {

    /**
     *
     * 通过索引获取分组
     *
     * @param groupIndex 分组的索引位置，从0开始计算
     * @return 获取的分组
     *
     */

    public AbstractWeaFormGroup readWeaFormGroup(int groupIndex){

        return (AbstractWeaFormGroup) this.getCondition().get(groupIndex);

    }

    /**
     *
     * 通过分组名称来获取分组
     *
     * 注意：若是多个分组均为此名称，此方法只会返回排序在先的第一个分组
     *
     * @param title 分组的title
     * @return 读取的分组
     *
     */

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

    /**
     *
     * 通过名称获取分组列表
     *
     * 注意：若多个方法均含有此名称，此方法将会返回包含此名称的所有分组列表
     *
     * @param title
     * @return
     */

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

    /**
     *
     * 根据索引移除分组
     *
     * @param groupIndex 分组的位置索引，从0开始计算
     */

    public void removeWeaFormGroup(int groupIndex) {

        this.getCondition().remove(groupIndex);

    }

    /**
     *
     * 根据名称移除分组
     *
     * 请注意：如果多个分组均为此名称，会移除排序在先的第一个分组
     *
     * @param title 分组的名称
     *
     */

    public void removeWeaFormGroupWithTitle(String title) {

        for (int i = 0; i < this.getCondition().size(); i++) {

            AbstractWeaFormGroup abstractWeaFormGroup = (AbstractWeaFormGroup) this.getCondition().get(i);

            if (BaseTools.notNullString(abstractWeaFormGroup.getTitle()) && abstractWeaFormGroup.getTitle().equals(title)) {

                this.getCondition().remove(i);

                break;

            }

        }

    }

    /**
     *
     * 根据名称移除分组（全部）
     *
     * 注意：若有多个分组均为此名称，会移除所有名为此名称的分组
     *
     * @param title 分组名称
     *
     */

    public void removeWeaFormGroupWithTitleAll(String title) {

        for (int i = 0; i < this.getCondition().size(); i++) {

            AbstractWeaFormGroup abstractWeaFormGroup = (AbstractWeaFormGroup) this.getCondition().get(i);

            if (BaseTools.notNullString(abstractWeaFormGroup.getTitle()) && abstractWeaFormGroup.getTitle().equals(title)) {

                this.getCondition().remove(i);

            }

        }

    }

    /**
     *
     * 在索引指定位置添加一个分组
     *
     * @param groupIndex 索引位置
     * @param abstractWeaFormGroup 添加的分组
     *
     */

    public void addWeaFormGroup(int groupIndex,AbstractWeaFormGroup abstractWeaFormGroup){

        this.getCondition().add(groupIndex,abstractWeaFormGroup);

    }

    /**
     *
     * 在列表的末尾添加一个分组
     *
     * @param abstractWeaFormGroup 添加的分组
     *
     */

    public void addWeaFormGroup(AbstractWeaFormGroup abstractWeaFormGroup){

        this.getCondition().add(abstractWeaFormGroup);

    }

    /**
     *
     * 通过索引读取父级树
     *
     * @param treeIndex 父级树的索引位置，从0开始
     * @return 该位置的父级树
     *
     */

    public AbstractWeaTree readWeaTree(int treeIndex){

        return (AbstractWeaTree) this.getWeaTree().get(treeIndex);

    }

    /**
     *
     * 通过名称获取父级树
     *
     * 注意：如果多个父级树都为此名称，那么会返回排序在先的第一个父级树
     *
     * @param name 父级树名称
     * @return 为此名称的父级树
     *
     */

    public AbstractWeaTree readWeaTreeWithName(String name){

        for (int i = 0; i < this.getWeaTree().size(); i++) {

            AbstractWeaTree ab = (AbstractWeaTree) this.getWeaTree().get(i);

            if (BaseTools.notNullString(ab.getName()) && ab.getName().equals(name)){

                return ab;

            }

        }

        LogTools.writeLog("未找到名为" + name + "的父树");

        return null;

    }

    /**
     *
     * 根据名称获取父级树（列表）
     *
     * 注意：如果有多个父级树都为此名称，那么会返回所有为该名称的父级树列表
     *
     * @param name 搜索的名称
     * @return 为此名称的父级树列表
     *
     */

    public List<AbstractWeaTree> readWeaTreeWithNameAll(String name){

        List<AbstractWeaTree> AbstractWeaTreeList = new ArrayList<>();

        for (int i = 0; i < this.getWeaTree().size(); i++) {

            AbstractWeaTree ab = (AbstractWeaTree) this.getWeaTree().get(i);

            if (BaseTools.notNullString(ab.getName()) && ab.getName().equals(name)){

                AbstractWeaTreeList.add(ab);

            }

        }

        if (BaseTools.notNullList(AbstractWeaTreeList)){

            return AbstractWeaTreeList;

        }

        LogTools.writeLog("未找到名为" + name + "的父树");

        return null;

    }

    /**
     *
     * 根据索引删除父级树
     *
     * @param TreeIndex 索引，从0开始计算
     *
     */

    public void removeWeaTree(int TreeIndex) {

        this.getWeaTree().remove(TreeIndex);

    }

    /**
     *
     * 根据名称删除父级树
     *
     * 注意：如果有多个父级树均为此名称，那么会删除排序在先的第一个父级树
     *
     * @param name 要删除的父级树名称
     *
     */

    public void removeWeaTreeWithName(String name) {

        for (int i = 0; i < this.getWeaTree().size(); i++) {

            AbstractWeaTree AbstractWeaTree = (AbstractWeaTree) this.getWeaTree().get(i);

            if (BaseTools.notNullString(AbstractWeaTree.getName()) && AbstractWeaTree.getName().equals(name)) {

                this.getCondition().remove(i);

                break;

            }

        }

    }

    /**
     *
     * 根据名称删除父级树
     *
     * 注意：如果有多个父级树为此名称，那么会删除所有为此名称的父级树
     *
     * @param title 删除的父级树名称
     */

    public void removeWeaTreeWithNameAll(String title) {

        for (int i = 0; i < this.getWeaTree().size(); i++) {

            AbstractWeaFormGroup abstractWeaFormGroup = (AbstractWeaFormGroup) this.getWeaTree().get(i);

            if (BaseTools.notNullString(abstractWeaFormGroup.getTitle()) && abstractWeaFormGroup.getTitle().equals(title)) {

                this.getCondition().remove(i);

            }

        }

    }

    /**
     *
     * 在该索引位置添加父级树
     *
     * @param treeIndex 添加的位置索引，从0开始
     * @param AbstractWeaTree 添加的父级树
     *
     */

    public void addWeaTree(int treeIndex,AbstractWeaTree AbstractWeaTree){

        this.getWeaTree().add(treeIndex,AbstractWeaTree);

    }

    /**
     *
     * 在父级树列表的尾部添加一个父级树
     *
     * @param AbstractWeaTree 添加的父级树
     *
     */

    public void addWeaTree(AbstractWeaTree AbstractWeaTree){

        this.getWeaTree().add(AbstractWeaTree);

    }

    /**
     *
     * 根据索引查找标签
     *
     * @param tabIndex 索引，从0开始计算
     * @return 该索引位置的标签
     *
     */

    public AbstractWeaTab readWeaTab(int tabIndex){

        return (AbstractWeaTab) this.getWeaTab().get(tabIndex);

    }

    /**
     *
     * 根据名称查找标签
     *
     * 注意：如有多个标签均为此名称，那么会返回排序在先的第一个标签
     *
     * @param title 标签名称
     * @return 该名称的标签
     *
     */

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

    /**
     *
     * 根据名称查找标签（列表）
     *
     * 注意：如有多个标签均为此名称，那么会返回所有为此名称的标签列表
     *
     * @param title 标签名称
     * @return 该名称的标签列表
     *
     */

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

    /**
     *
     * 根据索引删除标签
     *
     * @param weaTabIndex 标签索引，从0开始计算
     */

    public void removeWeaTab(int weaTabIndex) {

        this.getWeaTab().remove(weaTabIndex);

    }

    /**
     *
     * 根据名称删除标签
     *
     * 注意：如果有多个标签为此名称，那么将会删除排序在先的第一个标签
     *
     * @param title 要删除的标签名称
     *
     */

    public void removeWeaTabWithTitle(String title) {

        for (int i = 0; i < this.getWeaTab().size(); i++) {

            AbstractWeaTab abstractWeaTab = (AbstractWeaTab) this.getWeaTab().get(i);

            if (BaseTools.notNullString(abstractWeaTab.getTitle()) && abstractWeaTab.getTitle().equals(title)) {

                this.getWeaTab().remove(i);

                break;

            }

        }

    }

    /**
     *
     * 根据名称删除标签
     *
     * 注意：如果有多个标签为此名称，那么会删除所有为此名称的标签
     *
     * @param title 要删除的标签名称
     *
     */

    public void removeWeaTabWithTitleAll(String title) {

        for (int i = 0; i < this.getWeaTab().size(); i++) {

            AbstractWeaTab abstractWeaTab = (AbstractWeaTab) this.getWeaTab().get(i);

            if (BaseTools.notNullString(abstractWeaTab.getTitle()) && abstractWeaTab.getTitle().equals(title)) {

                this.getWeaTab().remove(i);

            }

        }

    }

    /**
     *
     * 在此列表索引位置添加标签
     *
     * @param tabIndex 索引位置，从0开始计算
     * @param abstractWeaTab 添加的标签
     */

    public void addWeaTab(int tabIndex,AbstractWeaTab abstractWeaTab){

        this.getWeaTab().add(tabIndex,abstractWeaTab);

    }

    /**
     *
     * 在列表的尾部添加标签
     *
     * @param abstractWeaTab 添加的标签
     *
     */

    public void addWeaTab(AbstractWeaTab abstractWeaTab){

        this.getWeaTab().add(abstractWeaTab);

    }

}
