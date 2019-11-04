package com.huawei.l00379880.algs4.chapter2sort;

import java.util.Comparator;

/***********************************************************
 * @Description : 自小而大地合并,没有用递归P175~P176
 * @author      : 梁山广
 * @date        : 2018/1/1 21:58
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P176MergeBottomUp {
    /**
     * 构造方法私有化,从而不允许创造对象,因为这个类下的都是公共静态方法
     */
    private P176MergeBottomUp() {
    }

    /**
     * 借助辅助数据把a[low]~a[mid]之间的元素与a[mid+1]~a[high]之间的元素进行合并
     *
     * @param a    待排序数组
     * @param aux  辅助数组,用于临时存储a[low]~a[high]之间的元素
     * @param low  下界
     * @param mid  中间
     * @param high 上界
     */
    private static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high) {
        // 先把a[low]~a[high]之间的元素全部复制到aux中
        for (int k = low; k <= high; k++) {
            aux[k] = a[k];
        }
        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                // 左边元素用完,只需要把右侧剩下的元素加入到aux即可
                a[k] = aux[j++];
            } else if (j > high) {
                // 右边元素用完,只需要把左侧剩下的元素加入到aux即可
                a[k] = aux[i++];
            } else if (P153CommenFuns.less(aux[j], aux[i])) {
                // a[j] < a[i]用a[j]
                a[k] = aux[j++];
            } else {
                // a[j] > a[i]用a[i]
                a[k] = aux[i++];
            }
        }
    }

    /**
     * 借助辅助数据把a[low]~a[mid]之间的元素与a[mid+1]~a[high]之间的元素进行合并
     *
     * @param a    待排序数组
     * @param aux  辅助数组,用于临时存储a[low]~a[high]之间的元素
     * @param low  下界
     * @param mid  中间
     * @param high 上界
     */
    private static void merge(Object[] a, Object[] aux, Comparator comparator, int low, int mid, int high) {
        // 先把a[low]~a[high]之间的元素全部复制到aux中
        for (int k = low; k <= high; k++) {
            aux[k] = a[k];
        }
        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                // 左边元素用完,只需要把右侧剩下的元素加入到aux即可
                a[k] = aux[j++];
            } else if (j > high) {
                // 右边元素用完,只需要把左侧剩下的元素加入到aux即可
                a[k] = aux[i++];
            } else if (P153CommenFuns.less(comparator, aux[j], aux[i])) {
                // a[j] < a[i]用a[j]
                a[k] = aux[j++];
            } else {
                // a[j] > a[i]用a[i]
                a[k] = aux[i++];
            }
        }
    }


    /**
     * 对外公开的排序函数,没用递归
     */
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    /**
     * 子数组排序,双闭区间
     */
    public static void sort(Comparable[] a, int low, int high) {
        // 防止非法输入
        if (high <= low) {
            return;
        }
        // 获取子数组长度
        int N = high - low + 1;
        // 辅助数组aux和a一样大
        Comparable[] aux = new Comparable[a.length];
        // 进行logN次两两归并.len为步长
        for (int len = 1; len < N; len *= 2) {
            // 待归并的子数组大小len不断变大
            for (int lowTemp = low; lowTemp <= high - len; lowTemp += len + len) {
                int midTemp = lowTemp + len - 1;
                // 最后一个子数组的大小只有在数组大小是len的整数倍的时候才会len.
                // 会比len小
                int highTemp = Math.min(lowTemp + len + len - 1, lowTemp + N - 1);
                // 比如第一轮是合并(a[0],a[1]),内循环一次是(a[2],a[3])、(a[4],a[5])、
                merge(a, aux, lowTemp, midTemp, highTemp);
            }
        }
    }

    /**
     * 对外公开的排序函数,没用递归
     */
    public static void sort(Object[] a, Comparator comparator) {
        sort(a, comparator, 0, a.length - 1);
    }

    /**
     * 子数组排序,双闭区间
     */
    public static void sort(Object[] a, Comparator comparator, int low, int high) {
        // 防止非法输入
        if (high <= low) {
            return;
        }
        // 获取子数组长度
        int N = high - low + 1;
        // 辅助数组aux和a一样大
        Object[] aux = new Object[a.length];
        // 进行logN次两两归并.len为步长
        for (int len = 1; len < N; len *= 2) {
            // 待归并的子数组大小len不断变大
            for (int lowTemp = low; lowTemp <= high - len; lowTemp += len + len) {
                int midTemp = lowTemp + len - 1;
                // 最后一个子数组的大小只有在数组大小是len的整数倍的时候才会len.
                // 会比len小
                int highTemp = Math.min(lowTemp + len + len - 1, lowTemp + N - 1);
                // 比如第一轮是合并(a[0],a[1]),内循环一次是(a[2],a[3])、(a[4],a[5])、
                merge(a, aux, comparator, lowTemp, midTemp, highTemp);
            }
        }
    }

}
