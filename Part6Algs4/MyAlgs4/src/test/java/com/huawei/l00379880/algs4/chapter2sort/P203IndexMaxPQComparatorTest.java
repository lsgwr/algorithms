package com.huawei.l00379880.algs4.chapter2sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * P203IndexMaxPQComparator Tester.
 *
 * @author liang shan guang
 * @datetime 01/04/2018
 * @email liangshanguang2@gmail.com
 * @description
 */
public class P203IndexMaxPQComparatorTest {
    /**
     * Description:
     */
    @Test
    public void testMain() {
        List<Dog> list = new ArrayList<>();
        list.add(new Dog(10, "DogC"));
        list.add(new Dog(5, "DogB"));
        list.add(new Dog(7, "DogD"));
        list.add(new Dog(6, "DogA"));
        Dog[] a = new Dog[list.size()];
        // 1. 按照狗名排序
        System.out.println("按照够名从大到小输出:");
        P203IndexMaxPQComparator<Dog> pq1 = new P203IndexMaxPQComparator<>(new DogComparatorByName(), list.size());
        // 把list转换到数组中
        for (int i = 0; i < list.size(); i++) {
            pq1.insert(i, list.get(i));
        }
        // 输出按照狗名从大到小排序的结果
        for (Integer index : pq1) {
            System.out.println("index=" + index + ",object=" + pq1.keyOf(index));
        }
        // 2.按照狗龄排序
        System.out.println("按照狗龄从大到小输出:");
        P203IndexMaxPQComparator<Dog> pq2 = new P203IndexMaxPQComparator<>(new DogComparatorByAge(), list.size());
        // 把list转换到数组中
        for (int i = 0; i < list.size(); i++) {
            pq2.insert(i, list.get(i));
        }
        // 输出按照狗龄从大到小排序的结果
        for (Integer index : pq2) {
            System.out.println("index=" + index + ",object=" + pq2.keyOf(index));
        }
    }
} 
