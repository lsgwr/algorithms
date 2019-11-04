package com.huawei.l00379880.algs4.chapter2sort;

/***********************************************************
 * @Description : 数组实现的有序优先队列,体现在每次insert的时候都保证有序.
 *                其实也是P197的.
 * @author      : 梁山广
 * @date        : 2018/1/3 9:59
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P197OrderedArrayMinPQ<Key extends Comparable<Key>> {
    /**
     * 优先队列里(Priority Queue)的元素
     */
    private Key[] pq;
    /**
     * 优先队列的实时大小
     */
    private int n;

    public P197OrderedArrayMinPQ(int capacity) {
        // 不能直接新建泛型数组.编译前就会报错
        this.pq = (Key[]) new Comparable[capacity];
        this.n = n;
    }

    /**
     * 队列是否为空
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 返回队列大小
     */
    public int size() {
        return n;
    }

    /**
     * 插入元素
     */
    public void insert(Key key) {
        int i = n - 1;
        // 待插入元素寻找插入点.插入排序内循环
        while (i >= 0 && P153CommenFuns.more(key, pq[i])) {
            // 插入点往后的元素统一后移一位.从最后一个元素开始到插入点i,依次后撤
            pq[i + 1] = pq[i];
            i--;
        }
        // 插入点元素设为key
        pq[i + 1] = key;
        // 队列元素加1
        n++;
    }

    /**
     * 删除最小元素
     */
    public Key delMin() {
        return pq[--n];
    }
}
