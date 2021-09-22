package com.huawei.l00379880.algs4.chapter2sort;

import java.util.Comparator;

/***********************************************************
 * @Description : 原地归并,P170~P174
 * @author      : 梁山广
 * @date        : 2018/1/1 20:14
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P170Merge {
    /**
     * 构造方法私有化,从而不允许创造对象,因为这个类下的都是公共静态方法
     */
    private P170Merge() {
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
     * 递归实现数组a的左右两侧的排序
     */
    private static void sort(Comparable[] a, Comparable[] aux, int low, int high) {
        if (high <= low) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(a, aux, low, mid);
        sort(a, aux, mid + 1, high);
        merge(a, aux, low, mid, high);
    }

    /**
     * 递归实现数组a的左右两侧的排序
     */
    private static void sort(Object[] a, Object[] aux, Comparator comparator, int low, int high) {
        if (high <= low) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(a, aux, comparator, low, mid);
        sort(a, aux, comparator, mid + 1, high);
        merge(a, aux, comparator, low, mid, high);
    }

    /**
     * 对外公开的排序函数
     */
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    /**
     * 局部排序,双闭区间
     */
    public static void sort(Comparable[] a, int low, int high) {
        // 防止非法输入
        if (high <= low) {
            return;
        }
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, low, high);
    }

    /**
     * 对外公开的排序函数
     */
    public static void sort(Object[] a, Comparator comparator) {
        Object[] aux = new Object[a.length];
        sort(a, aux, comparator, 0, a.length - 1);
    }

    /**
     * 局部排序,双闭区间
     */
    public static void sort(Object[] a, Comparator comparator, int low, int high) {
        // 防止非法输入
        if (high <= low) {
            return;
        }
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, comparator, low, high);
    }
}
