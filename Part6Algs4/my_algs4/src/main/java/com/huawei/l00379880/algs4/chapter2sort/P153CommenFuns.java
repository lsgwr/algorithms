package com.huawei.l00379880.algs4.chapter2sort;

import java.util.*;

/***********************************************************
 * @Description : 排序中用到的通用方法,P153
 * @author      : 梁山广
 * @date        : 2018/1/1 15:22
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P153CommenFuns {
    /**
     * 构造方法私有化,从而不允许创造对象,因为这个类下的都是公共静态方法
     */
    private P153CommenFuns() {
    }

    /**
     * 对元素进行比较,看看是否是v小于w
     *
     * @param v 比较元素
     * @param w 被比较元素
     */
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 对于不是继承Comparable接口而是自己专门在外面定义了Comparator类的情形,
     * 需要自己从外面把比较器Comparator传进来
     *
     * @param comparator 自定义比较器类
     * @param v          比较元素
     * @param w          被比较元素
     * @return
     */
    public static boolean less(Comparator comparator, Object v, Object w) {
        return comparator.compare(v, w) < 0;
    }

    /**
     * 对元素进行比较,看看是否是v等于w
     *
     * @param v 比较元素
     * @param w 被比较元素
     */
    public static boolean equal(Comparable v, Comparable w) {
        return v.compareTo(w) == 0;
    }

    /**
     * 对于不是继承Comparable接口而是自己专门在外面定义了Comparator类的情形,
     * 需要自己从外面把比较器Comparator传进来
     *
     * @param comparator 自定义比较器类
     * @param v          比较元素
     * @param w          被比较元素
     * @return
     */
    public static boolean equal(Comparator comparator, Object v, Object w) {
        return comparator.compare(v, w) == 0;
    }

    /**
     * 对元素进行比较,看看是否是v大于w
     *
     * @param v 比较元素
     * @param w 被比较元素
     */
    public static boolean more(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    /**
     * 对于不是继承Comparable接口而是自己专门在外面定义了Comparator类的情形,
     * 需要自己从外面把比较器Comparator传进来
     *
     * @param comparator 自定义比较器类
     * @param v          比较元素
     * @param w          被比较元素
     * @return
     */
    public static boolean more(Comparator comparator, Object v, Object w) {
        return comparator.compare(v, w) > 0;
    }

    /**
     * 交换数组中的指定两个位置的元素,不限制数据类型,不一定要实现Comparator,
     * 因为单纯交换设计不到比较
     *
     * @param a 基本数据类型或任何实现了Comparable接口的自定义数据类型
     * @param i 待交换位置,注意是下标不是元素
     * @param j 待交换位置,注意是下标不是元素
     */
    public static void exchange(Object[] a, int i, int j) {
        Object t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 打印数组,元素数据不要求
     */
    public static void show(Object[] a) {
        // 在单行中显示数组
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        // 记得要换行
        System.out.println();
    }

    /**
     * 判断数组的指定区间(low~high]是不是已经升序排好了
     */
    public static boolean isSortedAsc(Comparable[] a, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            // 如果后面的元素小于前面的那个,认为还没拍序好
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        //始终没发现异常的相邻数据对,感觉有点像反证法
        return true;
    }

    /**
     * 判断数组是不是已经升序排好了,属于上面的特殊情况
     */
    public static boolean isSortedAsc(Comparable[] a) {
        return isSortedAsc(a, 0, a.length - 1);
    }

    /**
     * 判断数组的区间(low~high]是不是已经升序排好了,自定义比较器类
     */
    public static boolean isSortedAsc(Comparable[] a, Comparator comparator, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            // 如果后面的元素小于前面的那个,认为还没拍序好
            if (less(comparator, a[i], a[i - 1])) {
                return false;
            }
        }
        //始终没发现异常的相邻数据对,感觉有点像反证法
        return true;
    }

    /**
     * 判断数组是不是已经升序排好了,属于上面的特殊情况.自定义比较器类
     */
    public static boolean isSortedAsc(Comparable[] a, Comparator comparator) {
        return isSortedAsc(a, comparator, 0, a.length - 1);
    }

    /**
     * 判断数组指定区间(low~high]是不是已经降序排好了
     */
    public static boolean isSortedDesc(Comparable[] a, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            // 如果后面的元素大于前面的那个,认为还没拍序好
            if (more(a[i], a[i - 1])) {
                return false;
            }
        }
        //始终没发现异常的相邻数据对,感觉有点像反证法
        return true;
    }

    /**
     * 判断数组是不是已经降序排好了,属于上面的特殊情况
     */
    public static boolean isSortedDesc(Comparable[] a) {
        return isSortedDesc(a, 0, a.length - 1);
    }

    /**
     * 判断数组的指定区间(low~high]是不是已经升序排好了,自定义比较器类
     */
    public static boolean isSortedDesc(Comparable[] a, Comparator comparator, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            // 如果后面的元素小于前面的那个,认为还没拍序好
            if (more(comparator, a[i], a[i - 1])) {
                return false;
            }
        }
        //始终没发现异常的相邻数据对,感觉有点像反证法
        return true;
    }

    /**
     * 判断数组是不是已经升序排好了,属于上面的特殊情况.自定义比较器类
     */
    public static boolean isSortedDesc(Comparable[] a, Comparator comparator) {
        return isSortedDesc(a, comparator, 0, a.length - 1);
    }


    /**
     * 反转数组,list的话只需要先用toArray方法转成数组即可
     * toArray和asList是两个数组<==>List的最常用转换方法
     */
    public static void reverseArray(Object[] a) {
        List<Object> list = new ArrayList<>();
        list = Arrays.asList(a);
        Collections.reverse(list);
        list.toArray(a);
    }
}
