package com.huawei.l00379880.algs4.chapter2sort; 

import org.junit.Test; 

/** 
* P198UnOrderedLinkedListMaxPQ Tester. 
* 
* @author       liang shan guang 
* @datetime     01/03/2018
* @email        liangshanguang2@gmail.com
* @description  
*/ 
public class P198UnOrderedLinkedListMaxPQTest { 
    /** 
    * Description:
    */ 
    @Test
    public void testMain(){
        P198UnOrderedLinkedListMaxPQ<String> pq = new P198UnOrderedLinkedListMaxPQ<>(10);
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
