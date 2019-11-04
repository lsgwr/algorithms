package com.huawei.l00379880.algs4.chapter2sort; 

import org.junit.Test; 

/** 
* P197OrderedArrayMaxPQ Tester. 
* 
* @author       liang shan guang 
* @datetime     01/03/2018
* @email        liangshanguang2@gmail.com
* @description  
*/ 
public class P197OrderedArrayMaxPQTest {
    /** 
    * Description:
    */ 
    @Test
    public void testMain(){
        P197OrderedArrayMaxPQ<String> pq = new P197OrderedArrayMaxPQ<>(10);
        pq.insert("b");
        pq.insert("a");
        pq.insert("d");
        pq.insert("c");
        while (!pq.isEmpty()) {
            System.out.println(pq.delMax());
        }
        System.out.println(pq.size());
    } 
} 
