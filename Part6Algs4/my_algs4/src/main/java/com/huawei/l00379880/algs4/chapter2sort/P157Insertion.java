package com.huawei.l00379880.algs4.chapter2sort;

import java.util.Comparator;

/***********************************************************
 * @Description : 插入排序
 * @author      : 梁山广
 * @date        : 2018/1/1 15:55
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P157Insertion {
    /**
     * 构造方法私有化,从而不允许创造对象,因为这个类下的都是公共静态方法
     */
    private P157Insertion() {
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
     * 对数组指定范围内的元素进行排序,双闭区间!!!!
     *
     * @param a    子数组所在的数组
     * @param low  子数组下界
     * @param high 子数组上界
     */
    public static void sort(Comparable[] a, int low, int high) {
        // 防止非法输入
        if (high <= low) {
            return;
        }
        // 将a[]按照升序排列
        for (int i = low; i <= high; i++) {
            // 将a[i]插入到已经升序排好地a[low]~a[i-1]中
            for (int j = i; j > low && P153CommenFuns.less(a[j], a[j - 1]); j--) {
                // 待插入元素a[i]从下标j的元素开始不断向前找,一直到遇到比自己小的元素才停止,
                // 否则就不停向前交换.一直找到a[low]还没找到比自己小的就不交换了
                P153CommenFuns.exchange(a, j, j - 1);
            }
        }
    }

    /**
     * 插入排序,可以传入任何实现了自定义Comparator类的数据类型,
     *
     * @param a          任何实现了Comparator接口的数据类型,
     * @param comparator 自定义比较接口
     */
    public static void sort(Object[] a, Comparator comparator) {
        sort(a, comparator, 0, a.length - 1);
    }

    /**
     * 对数组指定范围内的元素进行排序,双闭区间!!!!
     *
     * @param a    子数组所在的数组
     * @param low  子数组下界
     * @param high 子数组上界
     */
    public static void sort(Object[] a, Comparator comparator, int low, int high) {
        // 防止非法输入
        if (high <= low) {
            return;
        }
        // 将a[]按照升序排列
        for (int i = low; i <= high; i++) {
            // 将a[i]插入到已经升序排好地a[low]~a[i-1]中
            for (int j = i; j > low && P153CommenFuns.less(comparator, a[j], a[j - 1]); j--) {
                // 待插入元素a[i]从下标j的元素开始不断向前找,一直到遇到比自己小的元素才停止,
                // 否则就不停向前交换.一直找到a[low]还没找到比自己小的就不交换了
                P153CommenFuns.exchange(a, j, j - 1);
            }
        }
    }

}
