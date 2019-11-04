package com.huawei.l00379880.algs4.chapter2sort; 

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/** 
* P202MinPQComparator Tester. 
* 
* @author       liang shan guang 
* @datetime     01/03/2018
* @email        liangshanguang2@gmail.com
* @description  
*/ 
public class P202MinPQComparatorTest { 
    /** 
    * Description:
    */ 
    @Test
    public void testMain(){
        List<Dog> list = new ArrayList<>();
        list.add(new Dog(10, "DogC"));
        list.add(new Dog(5, "DogB"));
        list.add(new Dog(7, "DogD"));
        list.add(new Dog(6, "DogA"));
        Dog[] a = new Dog[list.size()];
        // 把list转换到数组中
        list.toArray(a);
        System.out.println("*****************排序前****************");
        for (Dog dog : a) {
            System.out.println(dog);
        }
        System.out.println("*****************按照年龄排序后****************");
        P202MinPQComparator<Dog> maxPQ1 = new P202MinPQComparator<>(new DogComparatorByAge(), a);
        for (Dog dog : maxPQ1) {
            System.out.println(dog);
        }
        System.out.println("*****************按照名称排序后****************");
        P202MinPQComparator<Dog> maxPQ2 = new P202MinPQComparator<>(new DogComparatorByName(), a);
        for (Dog dog : maxPQ2) {
            System.out.println(dog);
        }
    } 
} 
