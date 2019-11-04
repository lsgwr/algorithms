package com.huawei.l00379880.algs4.chapter2sort;

import java.util.Comparator;

/***********************************************************
 * @Description : 希尔排序 P162~P165
 *                数组中任意间隔为h的子数组都是有序地,这样的数组叫做h数组
 * @author      : 梁山广
 * @date        : 2018/1/1 18:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P163Shell {
    /**
     * 构造方法私有化,从而不允许创造对象,因为这个类下的都是公共静态方法
     */
    private P163Shell() {
    }

    /**
     * 希尔排序,可以传入任何实现了Comparable接口的数据类型,
     * 常见的基本数据类型都实现了Comparable接口了(包装类)
     *
     * @param a 何实现了Comparable接口的数据类型,
     *          可以是基本数据类型(包装类)或任何实现了Comparable接口的自定义数据类型
     */
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    /**
     * 局部排序.实现下标[low,high]的排序.左右区间是关闭地.双闭区间!!!!
     *
     * @param a    数组
     * @param low  数组局部下标
     * @param high 数组局部上标
     */
    public static void sort(Comparable[] a, int low, int high) {
        // 防止非法输入
        if (high <= low) {
            return;
        }
        // 将a[]按照升序排列
        // 元素个数
        int N = high - low + 1;
        int h = 1;
        // 确定初始的h(最接近N的h:1、4、13、40、121、364、1093),
        // 比如16个元素的数组,初始h选13,10个元素的初始h选4
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        // 下面
        while (h >= 1) {
            // 将数组a中所有间隔为h的子数组升序排列.最终整个数组就会是升序地了
            for (int i = low + h; i < low + N; i++) {
                //这一句很重要:将a[i]插入到a[i-h]、a[i-2*h]、a[i-3*h]...之中(前提是i-n*h要大于0)
                for (int j = i; j >= (low + h) && P153CommenFuns.less(a[j], a[j - h]); j = j - h) {
                    P153CommenFuns.exchange(a, j, j - h);
                }
            }
            // 这个条件是为了方便跳出while循环
            h = h / 3;
        }
    }


    /**
     * 希尔排序,可以传入任何实现了自定义Comparator类的数据类型,
     *
     * @param a          任何实现了Comparator接口的数据类型,
     * @param comparator 自定义比较接口
     */
    public static void sort(Object[] a, Comparator comparator) {
        sort(a, comparator, 0, a.length - 1);
    }

    /**
     * 局部排序.实现下标[low,high]的排序.左右区间是关闭地.双闭区间!!!!!
     *
     * @param a          数组
     * @param comparator 比较器
     * @param low        数组局部下标
     * @param high       数组局部上标
     */
    public static void sort(Object[] a, Comparator comparator, int low, int high) {
        // 防止非法输入
        if (high <= low) {
            return;
        }
        // 将a[]按照升序排列
        // 元素个数
        int N = high - low + 1;
        int h = 1;
        // 确定初始的h(最接近N的h:1、4、13、40、121、364、1093),
        // 比如16个元素的数组,初始h选13,10个元素的初始h选4
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        // 下面
        while (h >= 1) {
            // 将数组a中所有间隔为h的子数组升序排列.最终整个数组就会是升序地了
            for (int i = low + h; i < low + N; i++) {
                //这一句很重要:将a[i]插入到a[i-h]、a[i-2*h]、a[i-3*h]...之中(前提是i-n*h要大于0)
                for (int j = i; j >= (low + h) && P153CommenFuns.less(comparator, a[j], a[j - h]); j = j - h) {
                    P153CommenFuns.exchange(a, j, j - h);
                }
            }
            // 这个条件是为了方便跳出while循环
            h = h / 3;
        }
    }

}
