package com.huawei.l00379880.algs4.chapter2sort;

import com.huawei.l00379880.mylib.math.StdRandom;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * P203IndexMaxPQ Tester.
 *
 * @author liang shan guang
 * @datetime 01/04/2018
 * @email liangshanguang2@gmail.com
 * @description
 */
public class P203IndexMaxPQTest {
    @Test
    public void testComparable() {
        List<DogComparble> list = new ArrayList<>();
        list.add(new DogComparble(5, "DogB"));
        list.add(new DogComparble(10, "DogC"));
        list.add(new DogComparble(6, "DogA"));
        list.add(new DogComparble(7, "DogD"));
        P203IndexMaxPQ<DogComparble> pq = new P203IndexMaxPQ<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            pq.insert(i, list.get(i));
        }
        // 按照年龄从大到小排序
        for (Integer index : pq) {
            System.out.println("index=" + index + ",object=" + pq.keyOf(index));
        }
    }

    /**
     * Description:
     */
    @Test
    public void testMain() {
        // insert a bunch of strings
        String[] strings = {"it", "was", "the", "best", "of", "times", "it", "was", "the", "worst"};

        P203IndexMaxPQ<String> pq = new P203IndexMaxPQ<>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        System.out.println("依次输出优先队列中的最大元素!");
        // print each key using the iterator
        for (int i : pq) {
            // i为在数组中的下标
            System.out.println(i + " " + strings[i]);
        }

        System.out.println();

        System.out.println("随机对队列中的元素进行增加(字符串写两遍)或减小(取字符串的第一个子父),然后输出(按照从大到小的顺序)");
        // increase or decrease the key
        for (int i = 0; i < strings.length; i++) {
            if (StdRandom.uniform() < 0.5)
                pq.increaseKey(i, strings[i] + strings[i]);
            else
                pq.decreaseKey(i, strings[i].substring(0, 1));
        }

        // delete and print each key
        while (!pq.isEmpty()) {
            String key = pq.maxKey();
            int i = pq.delMax();
            System.out.println(i + " " + key);
        }
        System.out.println();


        //因为前面删光了,所以reinsert the same strings
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // delete them in random order
        int[] perm = new int[strings.length];
        for (int i = 0; i < strings.length; i++)
            perm[i] = i;
        StdRandom.shuffle(perm);
        for (int i = 0; i < perm.length; i++) {
            String key = pq.keyOf(perm[i]);
            pq.delete(perm[i]);
            System.out.println(perm[i] + " " + key);
        }
    }
} 
