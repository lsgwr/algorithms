package com.huawei.l00379880.algs4.chapter2sort;

/************************************************************************
 * @Description : 数组实现的无序有限队列,体现在每次delMin的是都都找一下最小值
 * @author      : 梁山广
 * @date        : 2018/1/3 9:59
 * @email       : liangshanguang2@gmail.com
 ***********************************************************************/
public class P197UnOrderedArrayMinPQ<Key extends Comparable<Key>> {
    /**
     * 优先队列里(Priority Queue)的元素
     */
    private Key[] pq;
    /**
     * 优先队列的实时大小
     */
    private int n;

    public P197UnOrderedArrayMinPQ(int capacity) {
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
        pq[n++] = key;
    }

    /**
     * 删除最小元素
     */
    public Key delMin() {
        // 初始化最小元素的下标
        int min = 0;
        // 寻找最小元素
        for (int i = 1; i < n; i++) {
            // 若果临时最小元素大于当前元素,就更新最小元素为当前元素
            if (P153CommenFuns.more(pq[min], pq[i])) {
                min = i;
            }
        }
        // 把最小元素交换到数组头
        P153CommenFuns.exchange(pq, min, n - 1);
        // 返回最小元素,数组长度减1.删掉体现为会被新来的元素覆盖掉
        return pq[--n];
    }
}
