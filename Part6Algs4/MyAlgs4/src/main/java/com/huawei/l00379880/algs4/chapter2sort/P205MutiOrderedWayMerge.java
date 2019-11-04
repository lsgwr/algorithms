package com.huawei.l00379880.algs4.chapter2sort;

import java.util.ArrayList;
import java.util.List;

/***********************************************************
 * @Description : 多路有序输入源归并,一定注意是有序!!
 *                好处:有记录输入源,优先队列的maxN为几就可以,
 *                非常节省空间!!每路输入源有多长根本不Care
 *                类似MapReduce.
 * @author      : 梁山广
 * @date        : 2018/1/4 20:37
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P205MutiOrderedWayMerge {
    public static void main(String[] args) {
        // 下面三个数组无序即可,因为maxPQ会帮你依次弹出最大地/因为minPQ会帮你依次弹出最小地
        // 一定注意内外应该相同,是max都是max,是min都是min
        Integer[] arr1 = new Integer[]{3, 0, 5, 87, 7, 9};
        Integer[] arr2 = new Integer[]{1, 45, 84, 68, 5};
        Integer[] arr3 = new Integer[]{29, 6, 84, 13, 5};

        // 1.降序排列
        System.out.println("升序排列:");
        P202MaxPQ<Integer> maxPQ1 = new P202MaxPQ<>(arr1);
        P202MaxPQ<Integer> maxPQ2 = new P202MaxPQ<>(arr2);
        P202MaxPQ<Integer> maxPQ3 = new P202MaxPQ<>(arr3);
        List<P202MaxPQ<Integer>> streamList1 = new ArrayList<>();
        streamList1.add(maxPQ1);
        streamList1.add(maxPQ2);
        streamList1.add(maxPQ3);
        multiMergeDesc(streamList1);

        System.out.println();
        // 2.升序排列
        System.out.println("降序排列:");
        P202MinPQ<Integer> minPQ1 = new P202MinPQ<>(arr1);
        P202MinPQ<Integer> minPQ2 = new P202MinPQ<>(arr2);
        P202MinPQ<Integer> minPQ3 = new P202MinPQ<>(arr3);
        List<P202MinPQ<Integer>> streamList2 = new ArrayList<>();
        streamList2.add(minPQ1);
        streamList2.add(minPQ2);
        streamList2.add(minPQ3);
        multiMergeAsc(streamList2);
    }

    /**
     * 对多路有序输入源进行归一排序输出,降序输出.输入必须是从大到小排列的
     */
    public static void multiMergeDesc(List<P202MaxPQ<Integer>> streamList) {
        // 下面的最大索引优先队列的长度
        int N = streamList.size();
        // 上面的输入源有几路,最多就能容几个
        P203IndexMaxPQ<Integer> indexMaxPQ = new P203IndexMaxPQ<>(N);
        for (int i = 0; i < N; i++) {
            if (!streamList.get(i).isEmpty()) {
                indexMaxPQ.insert(i, streamList.get(i).delMax());
            }
        }
        //下面开始进行归一,就是不断删除和补充的过程
        while (!indexMaxPQ.isEmpty()) {
            System.out.print(indexMaxPQ.maxKey() + " ");
            // 要输出到归一流的最大元素
            int i = indexMaxPQ.delMax();
            // 那个输入源来的元素被弹出了,就再从那个输入源接着取元素
            if (!streamList.get(i).isEmpty()) {
                indexMaxPQ.insert(i, streamList.get(i).delMax());
            }
        }
    }


    /**
     * 对多路有序输入源进行归一排序输出,升序输出,输入必须是从小到大排列的
     */
    public static void multiMergeAsc(List<P202MinPQ<Integer>> streamList) {
        // 下面的最大索引优先队列的长度
        int N = streamList.size();
        // 上面的输入源有几路,最多就能容几个
        P203IndexMinPQ<Integer> indexMinPQ = new P203IndexMinPQ<>(N);
        for (int i = 0; i < N; i++) {
            if (!streamList.get(i).isEmpty()) {
                indexMinPQ.insert(i, streamList.get(i).delMin());
            }
        }
        //下面开始进行归一,就是不断删除和补充的过程
        while (!indexMinPQ.isEmpty()) {
            System.out.print(indexMinPQ.minKey() + " ");
            // 要输出到归一流的最小元素
            int i = indexMinPQ.delMin();
            // 那个输入源来的元素被弹出了,就再从那个输入源接着取元素
            if (!streamList.get(i).isEmpty()) {
                indexMinPQ.insert(i, streamList.get(i).delMin());
            }
        }
    }
}
