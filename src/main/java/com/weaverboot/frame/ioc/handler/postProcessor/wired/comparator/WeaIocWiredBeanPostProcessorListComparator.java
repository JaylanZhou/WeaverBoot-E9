package com.weaverboot.frame.ioc.handler.postProcessor.wired.comparator;

import com.weaverboot.frame.ioc.anno.classAnno.WeaIocWiredBeanPostProcessor;
import com.weaverboot.frame.ioc.beans.bean.definition.inte.AbstractWeaBeanDefinition;

import java.util.Comparator;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-02-12 14:49
 * @Version : 1.0
 */
public class WeaIocWiredBeanPostProcessorListComparator implements Comparator<AbstractWeaBeanDefinition> {

    @Override
    public int compare(AbstractWeaBeanDefinition o1, AbstractWeaBeanDefinition o2) {

        WeaIocWiredBeanPostProcessor weaIocWiredBeanPostProcessorO1 = (WeaIocWiredBeanPostProcessor) o1.getBeanClass().getAnnotation(WeaIocWiredBeanPostProcessor.class);

        WeaIocWiredBeanPostProcessor weaIocWiredBeanPostProcessorO2 = (WeaIocWiredBeanPostProcessor) o2.getBeanClass().getAnnotation(WeaIocWiredBeanPostProcessor.class);

        int o1Order = weaIocWiredBeanPostProcessorO1.order();

        int o2Order = weaIocWiredBeanPostProcessorO2.order();

        if (o1Order < o2Order){

            return -1;

        } else if (o1Order > o2Order){

            return 1;

        } else {

            return 0;

        }

    }
}
