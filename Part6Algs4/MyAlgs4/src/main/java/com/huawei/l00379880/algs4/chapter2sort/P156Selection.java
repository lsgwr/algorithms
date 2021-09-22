package com.huawei.l00379880.algs4.chapter2sort;

import java.util.Comparator;

/***********************************************************
 * @Description : 选择排序,P155~P156
 * @author      : 梁山广
 * @date        : 2017/12/31 21:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P156Selection {
    /**
     * 构造方法私有化,从而不允许创造对象,因为这个类下的都是公共静态方法
     */
    private P156Selection() {
    }

    /**
     * 选择排序,可以传入任何实现了Comparable接口的数据类型,
     * 常见的基本数据类型都实现了Comparable接口了(包装类)
     *
     * @param a 何实现了Comparable接口的数据类型,
     *          可以是基本数据类型(包装类)或任何实现了Comparable接口的自定义数据类型
     */
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    /**
     * 局部排序,双闭区间!!!!
     */
    public static void sort(Comparable[] a, int low, int high) {
        // 防止非法输入
        if (high <= low) {
            return;
        }
        // 将a[]按照升序排列
        for (int i = low; i <= high; i++) {
            // 初始化最小元素
            int min = i;
            // 将a[i]和a[i+1]~a[high]之间的元素的最小值进行交换
            for (int j = i + 1; j <= high; j++) {
                if (P153CommenFuns.less(a[j], a[min])) {
                    min = j;
                }
            }
            // 将第i小的元素放到a[i]中.数组的第i个位置的左边是i个最小的元素而且已经按照从小到
            // 大的顺序排好了,因此不会再被访问到了
            P153CommenFuns.exchange(a, i, min);
        }
    }


    /**
     * 选择排序,可以传入任何实现了自定义Comparator类的数据类型,
     *
     * @param comparator 自定义比较接口
     * @param a          任何实现了Comparator接口的数据类型,
     */
    public static void sort(Object[] a, Comparator comparator) {
        sort(a, comparator, 0, a.length - 1);
    }

    /**
     * 局部排序,双闭区间!!!!
     */
    public static void sort(Object[] a, Comparator comparator, int low, int high) {
        // 防止非法输入
        if (high <= low) {
            return;
        }
        // 将a[]按照升序排列
        for (int i = low; i <= high; i++) {
            // 初始化最小元素
            int min = i;
            // 将a[i]和a[i+1]~a[N]之间的元素的最小值进行交换
            for (int j = i + 1; j <= high; j++) {
                if (P153CommenFuns.less(comparator, a[j], a[min])) {
                    min = j;
                }
            }
            // 将第i小的元素放到a[i]中.数组的第i个位置的左边是i个最小的元素而且已经按照从小到
            // 大的顺序排好了,因此不会再被访问到了
            P153CommenFuns.exchange(a, i, min);
        }
    }
}
