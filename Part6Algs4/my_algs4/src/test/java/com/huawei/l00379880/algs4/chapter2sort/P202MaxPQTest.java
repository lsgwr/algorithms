package com.huawei.l00379880.algs4.chapter2sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * P202MaxPQ Tester.
 *
 * @author liang shan guang
 * @datetime 01/03/2018
 * @email liangshanguang2@gmail.com
 * @description
 */
public class P202MaxPQTest {
    /**
     * Description:
     */
    @Test
    public void testMain() {
        P202MaxPQ<String> pq = new P202MaxPQ<>(4);
        pq.insert("b");
        pq.insert("a");
        pq.insert("m");
        pq.insert("q");
        pq.insert("a");
        pq.insert("d");
        pq.insert("d");
        pq.insert("c");
        while (!pq.isEmpty()) {
            System.out.println(pq.delMax());
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
        System.out.println("遍历最大堆!但不删除元素");
        P202MaxPQ<DogComparble> maxPQ = new P202MaxPQ<>(a);
        for (DogComparble dogComparble : maxPQ) {
            System.out.println(dogComparble);
        }
        System.out.println(maxPQ.size());
        System.out.println("依次弹出最大堆中的元素:");
        while (!maxPQ.isEmpty()) {
            System.out.println(maxPQ.delMax());
        }
        System.out.println(maxPQ.size());
    }
} 
