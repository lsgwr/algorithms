package com.huawei.l00379880.algs4.chapter2sort;

import java.util.Comparator;

/***********************************************************
 * @Description : 
 * @author      : 梁山广
 * @date        : 2018/1/1 18:17
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class DogComparatorByName implements Comparator<Dog> {
    @Override
    public int compare(Dog o1, Dog o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
