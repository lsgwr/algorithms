package com.huawei.l00379880.algs4.chapter2sort; 

import org.junit.Test; 

/** 
* P197OrderedArrayMinPQ Tester.
* 
* @author       liang shan guang 
* @datetime     01/03/2018
* @email        liangshanguang2@gmail.com
* @description  
*/ 
public class P197OrderedArrayMinPQTest {
    /** 
    * Description:
    */ 
    @Test
    public void testMain(){
        P197OrderedArrayMinPQ<String> pq = new P197OrderedArrayMinPQ<>(10);
        pq.insert("b");
        pq.insert("a");
        pq.insert("d");
        pq.insert("c");
        while (!pq.isEmpty()) {
            System.out.println(pq.delMin());
        }
        System.out.println(pq.size());
    } 
} 
