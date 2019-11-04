package com.huawei.l00379880.algs4.chapter2sort;


import com.huawei.l00379880.mylib.math.StdRandom;

import java.util.Arrays;
import java.util.Comparator;

/***********************************************************
 * @Description : 快速排序,三取样切分
 *                适用场景:含有大量重复元素的数组.在切分的时候把数
 *                组分成小于、等于、大于三部分.重复元素多的时候性能
 *                可接近线性
 * @author      : 梁山广
 * @date        : 2018/1/2 20:52
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P189Quick3Way {
    private P189Quick3Way() {
    }

    /**
     * 整个数组排序
     */
    public static void sort(Comparable[] a) {
        // 先打乱,消除算法性能对输入的依赖.从整体来看是为了提高性能地
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }


    /**
     * 局部排序
     */
    public static void sort(Comparable[] a, int low, int high) {
        // 先打乱,消除算法性能对输入的依赖.从整体来看是为了提高性能地
        // 注意high+1是为了能把a[high]页打乱
        StdRandom.shuffle(a, low, high + 1);
        System.out.println("打乱后的数组为:");
        System.out.println(Arrays.toString(a));
        sortSon(a, low, high);
    }

    /**
     * 快速排序子方法.之所以分sortSon和sort两个局部排序,是因为shuffle最好只做一次
     */
    private static void sortSon(Comparable[] a, int low, int high) {
        // 防止非法输入
        if (high <= low) {
            return;
        }
        // 获取下一轮的切分点
        // lt:less than
        int lt = low;
        int i = low + 1;
        // gt: greater than
        int gt = high;
        // 取切分元素
        Comparable v = a[low];
        while (i <= gt) {
            if (P153CommenFuns.less(a[i], v)) {
                P153CommenFuns.exchange(a, lt++, i++);
            } else if (P153CommenFuns.more(a[i], v)) {
                P153CommenFuns.exchange(a, i, gt--);
            } else {
                // a[i]==v,把相等元素的区间分出来
                i++;
            }
        }
        // while循环到i>gt就退出了,此时满足下面注释的条件
        // 现在a[low..lt-1] < v = a[lt..gt] < a[gt+1..high]
        sortSon(a, low, lt - 1);
        sortSon(a, gt + 1, high);
    }


    /**
     * 整个数组排序.可自定义排序规则
     */
    public static void sort(Object[] a, Comparator comparator) {
        // 先打乱,消除算法性能对输入的依赖.从整体来看是为了提高性能地
        StdRandom.shuffle(a);
        sort(a, comparator, 0, a.length - 1);
    }


    /**
     * 局部排序.可自定义排序规则
     */
    public static void sort(Object[] a, Comparator comparator, int low, int high) {
        // 先打乱,消除算法性能对输入的依赖.从整体来看是为了提高性能地
        // 注意high+1是为了能把a[high]页打乱
        StdRandom.shuffle(a, low, high + 1);
        sortSon(a, comparator, low, high);
    }

    /**
     * 快速排序子方法.之所以分sortSon和sort两个局部排序,是因为shuffle最好只做一次.可自定义排序规则
     */
    private static void sortSon(Object[] a, Comparator comparator, int low, int high) {
        // 防止非法输入
        if (high <= low) {
            return;
        }
        // 获取下一轮的切分点
        // lt:less than
        int lt = low;
        int i = low + 1;
        // gt: greater than
        int gt = high;
        // 取切分元素
        Object v = a[low];
        while (i <= gt) {
            if (P153CommenFuns.less(comparator, a[i], v)) {
                P153CommenFuns.exchange(a, lt++, i++);
            } else if (P153CommenFuns.more(comparator, a[i], v)) {
                P153CommenFuns.exchange(a, i, gt--);
            } else {
                // a[i]==v,把相等元素的区间分出来
                i++;
            }
        }
        // 现在a[low..lt-1] < v = a[lt..gt] < a[gt+1..high]
        sortSon(a, comparator, low, lt - 1);
        sortSon(a, comparator, gt + 1, high);
    }

}
