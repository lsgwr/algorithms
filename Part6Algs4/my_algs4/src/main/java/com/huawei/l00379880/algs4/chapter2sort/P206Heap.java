package com.huawei.l00379880.algs4.chapter2sort;

import java.util.Comparator;

/***********************************************************
 * @Description : 堆排序,P205~P208。升序
 * @author      : 梁山广
 * @date        : 2018/1/4 21:14
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P206Heap {
    private P206Heap() {
    }

    /**
     * 堆排序,见P206的详细讲解
     *
     * @param pq 实现了Comparable接口的数组
     */
    public static void sort(Comparable[] pq) {
        int n = pq.length;
        //构造大根堆
        //跳过只有一个结点的堆即大小为1的堆(堆的最下一层元素数占所有元素的1/2)，从数组的中间开始扫描，调用sink()方法，层层递减，最后在1位置上调用sink()方法后结束。
        //此次扫描目的是构造一个堆有序的数组并使最大元素位于数组的开头(次大的元素在附近)
        for (int k = n / 2; k >= 1; k--) {
            sink(pq, k, n);
        }
        // 进行排序
        //下沉排序
        //1.每次排序都先将最大的元素与最后一个元素交换位置，接着缩小数组，对除去最后一个元素的堆进行下沉排序
        //2.对缩小后的数组进行下沉排序，若数组长度大于1，则跳转到第一步继续执行
        while (n > 1) {
            exchange(pq, 1, n--);
            sink(pq, 1, n);
        }
    }

    public static void sort(Object[] pq, Comparator comparator) {
        int n = pq.length;
        // 构造最大堆
        for (int k = n / 2; k >= 1; k--) {
            sink(pq, comparator, k, n);
        }
        // 进行排序
        while (n > 1) {
            exchange(pq, 1, n--);
            sink(pq, comparator, 1, n);
        }
    }

    /**
     * 通用的下沉操作
     */
    private static void sink(Comparable[] pq, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(pq, j, j + 1)) {
                j++;
            }
            // 顶点元素大于两个子节点,那么退出即可.想的的时候无所谓,下面多交换一次而已
            if (more(pq, k, j)) {
                break;
            }
            exchange(pq, k, j);
            // 开始下一轮
            k = j;
        }
    }

    /**
     * 通用的下沉操作,可自定义比较器
     */
    private static void sink(Object[] pq, Comparator comparator, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(pq, comparator, j, j + 1)) {
                j++;
            }
            // 定点元素大于两个子节点,那么退出即可.想的的时候无所谓,下面多交换一次而已
            if (more(pq, comparator, k, j)) {
                break;
            }
            exchange(pq, k, j);
            // 开始下一轮
            k = j;
        }
    }

    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    private static boolean more(Comparable[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) > 0;
    }

    private static boolean less(Object[] pq, Comparator comparator, int i, int j) {
        return comparator.compare(pq[i - 1], pq[j - 1]) < 0;
    }

    private static boolean more(Object[] pq, Comparator comparator, int i, int j) {
        return comparator.compare(pq[i - 1], pq[j - 1]) > 0;
    }

    private static void exchange(Object[] pq, int i, int j) {
        Object swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }

}
