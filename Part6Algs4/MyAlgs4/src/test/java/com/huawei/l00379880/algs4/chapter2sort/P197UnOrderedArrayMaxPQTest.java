package com.huawei.l00379880.algs4.chapter2sort;

import org.junit.Test;

/**
 * P197UnOrderedArrayMaxPQ Tester.
 *
 * @author liang shan guang
 * @datetime 01/03/2018
 * @email liangshanguang2@gmail.com
 * @description
 */
public class P197UnOrderedArrayMaxPQTest {
    /**
     * Description:
     */
    @Test
    public void testMain() {
        P197UnOrderedArrayMaxPQ<String> pq = new P197UnOrderedArrayMaxPQ<>(10);
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
