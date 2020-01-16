package com.weaverboot.frame.ioc.handler.replace.weaReplaceApiAdvice;

import java.util.Comparator;

/**
 * @Author : Jaylan Zhou
 * @Date : 2020-01-16 16:05
 * @Version : 1.0
 */
public class WeaReplaceApiAdviceComparator implements Comparator<WeaReplaceApiAdvice> {

    @Override
    public int compare(WeaReplaceApiAdvice o1, WeaReplaceApiAdvice o2) {

        if (o1.getOrder() > o2.getOrder()){

            return 1;

        } else if (o1.getOrder() < o2.getOrder()){

            return -1;

        }

        return 0;

    }
}
