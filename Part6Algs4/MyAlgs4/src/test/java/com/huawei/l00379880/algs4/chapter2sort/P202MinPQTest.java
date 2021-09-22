package com.huawei.l00379880.algs4.chapter2sort; 

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/** 
* P202MinPQ Tester. 
* 
* @author       liang shan guang 
* @datetime     01/03/2018
* @email        liangshanguang2@gmail.com
* @description  
*/ 
public class P202MinPQTest {
    /**
     * Description:
     */
    @Test
    public void testMain() {
        P202MinPQ<String> pq = new P202MinPQ<>(4);
        pq.insert("b");
        pq.insert("a");
        pq.insert("d");
        pq.insert("c");
        while (!pq.isEmpty()) {
            System.out.println(pq.delMin());
        }
        System.out.println(pq.size());
    }

    @Test
    public void testComparable() {
        List<DogComparble> list = new ArrayList<>();
        list.add(new DogComparble(10, "DogC"));
        list.add(new DogComparble(5, "DogB"));
        list.add(new DogComparble(7, "DogD"));
        list.add(new DogComparble(6, "DogA"));
        DogComparble[] a = new DogComparble[list.size()];
        // 把list转换到数组中
        list.toArray(a);
        System.out.println("遍历最小堆!但不删除元素");
        P202MinPQ<DogComparble> minPQ = new P202MinPQ<>(a);
        for (DogComparble dogComparble : minPQ) {
            System.out.println(dogComparble);
        }
        System.out.println(minPQ.size());
        System.out.println("依次弹出最小堆中的元素:");
        while (!minPQ.isEmpty()) {
            System.out.println(minPQ.delMin());
        }
        System.out.println(minPQ.size());
    }
} 
