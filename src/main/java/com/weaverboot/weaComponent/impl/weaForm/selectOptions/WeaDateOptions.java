package com.weaverboot.weaComponent.impl.weaForm.selectOptions;

/**
 *
 * 日期组合 - 下拉框选择值属性类
 *
 * @Author : Jaylan Zhou
 * @Date : 2020-01-03 18:29
 * @Version : 1.0
 */
public class WeaDateOptions {

    private String name;

    private String value;

    public WeaDateOptions(){}

    public WeaDateOptions(String name,String value){

        this.name = name;

        this.value = value;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
