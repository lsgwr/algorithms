package com.huawei.l00379880.algs4.chapter2sort;


import com.huawei.l00379880.mylib.math.StdRandom;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/***********************************************************
 * @Description : 快速排序,经典实现
 * @author      : 梁山广
 * @date        : 2018/1/2 10:18
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P182Quick {
    private P182Quick() {
    }

    /**
     * 整个数组排序
     */
    public static void sort(Comparable[] a) {
        // 先打乱,消除算法性能对输入的依赖.从整体来看是为了提高性能地
        StdRandom.shuffle(a);
        sortSon(a, 0, a.length - 1);
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
        int j = partition(a, low, high);
        // 切分点所在的元素不需要进行排序了.肯定是大于左边、小于右侧地
        sortSon(a, low, j - 1);
        sortSon(a, j + 1, high);
    }

    /**
     * 切分
     *
     * @param a    原始数组
     * @param low  切分起始点
     * @param high 切分结束点
     * @return
     */
    private static int partition(Comparable[] a, int low, int high) {
        // 将数组切分为a[low..i-1],a[i],a[i+1..hi]
        int i = low;
        int j = high + 1;
        // 取区间的第一个元素作为切分元素
        Comparable v = a[low];
        while (true) {
            while (P153CommenFuns.less(a[++i], v)) {
                // i一直向右移动,j一直没动
                if (i == high) {
                    break;
                }
            }

            while (P153CommenFuns.more(a[--j], v)) {
                // j一直向左移动,i一直没动
                if (j == low) {
                    break;
                }
            }

            // 正常的话.i和j都移动,当i>=j的时候,本轮比较完毕,直接退出
            if (i >= j) {
                // 跳出外层循环
                break;
            }

            // 当上面三个条件在某个i和是都不满足,即a[++i]>v、a[--j]<v、i<j的时刻,说明该交换了
            P153CommenFuns.exchange(a, i, j);
        }
        // i和j相遇了,此时把相遇点的元素和切分元素互换位置
        P153CommenFuns.exchange(a, low, j);
        // 返回相遇点,作为下一次划分的依据
        return j;
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
        int j = partition(a, comparator, low, high);
        // 切分点所在的元素不需要进行排序了.肯定是大于左边、小于右侧地
        sortSon(a, comparator, low, j - 1);
        sortSon(a, comparator, j + 1, high);
    }

    /**
     * 切分,可自定义排序规则
     *
     * @param a    原始数组
     * @param low  切分起始点
     * @param high 切分结束点
     * @return
     */
    private static int partition(Object[] a, Comparator comparator, int low, int high) {
        // 将数组切分为a[low..i-1],a[i],a[i+1..hi]
        int i = low;
        int j = high + 1;
        // 取区间的第一个元素作为切分元素
        Object v = a[low];
        while (true) {
            while (P153CommenFuns.less(comparator, a[++i], v)) {
                // i一直向右移动,j一直没动
                if (i == high) {
                    break;
                }
            }

            while (P153CommenFuns.more(comparator, a[--j], v)) {
                // j一直向左移动,i一直没动
                if (j == low) {
                    break;
                }
            }

            // 正常的话.i和j都移动,当i>=j的时候,本轮比较完毕,直接退出
            if (i >= j) {
                // 跳出外层循环
                break;
            }

            // 当上面三个条件在某个i和是都不满足,即a[++i]>v、a[--j]<v、i<j的时刻,说明该交换了
            P153CommenFuns.exchange(a, i, j);
        }
        // i和j相遇了,此时把相遇点的元素和切分元素互换位置
        P153CommenFuns.exchange(a, low, j);
        // 返回相遇点,作为下一次划分的依据
        return j;
    }
}
