package com.huawei.l00379880.algs4.chapter2sort;

import java.util.Comparator;

/***********************************************************
 * @Description : 归并算法的三个优化
 *                (1)对小规模子数组使用插入排序
 *                (2)测试数组是否已经有序.当a[mid]<=a[mid+1]
 *                   就可以认为数组已经有序,直接退出即可
 *                (3)不将元素复制到辅助数组,这就需要在递归调用
 *                   的每个层次交换输入数组和辅助数组的角色.所以改为
 *                   src和dst而不是a和aux
 * @author      : 梁山广
 * @date        : 2018/1/1 20:14
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P175MergeBetter {
    /**
     * 小规模数组的大小定义
     */
    private static final int CUTOFF = 7;

    /**
     * 构造方法私有化,从而不允许创造对象,因为这个类下的都是公共静态方法
     */
    private P175MergeBetter() {
    }

    /**
     * 借助辅助数据把a[low]~a[mid]之间的元素与a[mid+1]~a[high]之间的元素进行合并
     *
     * @param src    待排序数组
     * @param dst  辅助数组,用于临时存储a[low]~a[high]之间的元素
     * @param low  下界
     * @param mid  中间
     * @param high 上界
     */
    private static void merge(Comparable[] src, Comparable[] dst, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                // 左边元素用完,只需要把右侧剩下的元素加入到aux即可
                dst[k] = src[j++];
            } else if (j > high) {
                // 右边元素用完,只需要把左侧剩下的元素加入到aux即可
                dst[k] = src[i++];
            } else if (P153CommenFuns.less(src[j], src[i])) {
                // a[j] < a[i]用a[j]
                dst[k] = src[j++];
            } else {
                // a[j] > a[i]用a[i]
                dst[k] = src[i++];
            }
        }
    }

    /**
     * 借助辅助数据把a[low]~a[mid]之间的元素与a[mid+1]~a[high]之间的元素进行合并
     *
     * @param src  待排序数组
     * @param dst  辅助数组,用于临时存储a[low]~a[high]之间的元素
     * @param low  下界
     * @param mid  中间
     * @param high 上界
     */
    private static void merge(Object[] src, Object[] dst, Comparator comparator, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                // 左边元素用完,只需要把右侧剩下的元素加入到aux即可
                dst[k] = src[j++];
            } else if (j > high) {
                // 右边元素用完,只需要把左侧剩下的元素加入到aux即可
                dst[k] = src[i++];
            } else if (P153CommenFuns.less(comparator, src[j], src[i])) {
                // a[j] < a[i]用a[j]
                dst[k] = src[j++];
            } else {
                // a[j] > a[i]用a[i]
                dst[k] = src[i++];
            }
        }
    }

    /**
     * 递归实现数组a的左右两侧的排序
     */
    private static void sort(Comparable[] src, Comparable[] dst, int low, int high) {
        // 优化1:在小规模数组内使用归并排序
        if (high <= low + CUTOFF) {
            P157Insertion.sort(dst, low, high);
            return;
        }
        int mid = low + (high - low) / 2;
        sort(dst, src, low, mid);
        sort(dst, src, mid + 1, high);
        // 优化2:测试数组是否已经有序.当a[mid]<=a[mid+1]
        //       就可以认为数组已经有序,直接退出即可
        // using System.arraycopy() is a bit faster than the above loop
        if (!P153CommenFuns.less(src[mid + 1], src[mid])) {
            System.arraycopy(src, low, dst, low, high - low + 1);
            return;
        }

        merge(src, dst, low, mid, high);
    }

    /**
     * 递归实现数组a的左右两侧的排序
     */
    private static void sort(Object[] src, Object[] dst, Comparator comparator, int low, int high) {
        // 在小规模数组内使用归并排序
        if (high <= low + CUTOFF) {
            P157Insertion.sort(dst, comparator, low, high);
            return;
        }
        int mid = low + (high - low) / 2;
        //优化3:
        sort(dst, src, comparator, low, mid);
        sort(dst, src, comparator, mid + 1, high);
        // 优化2:测试数组是否已经有序.当a[mid]<=a[mid+1]
        //       就可以认为数组已经有序,直接退出即可
        // using System.arraycopy() is a bit faster than the above loop
        if (!P153CommenFuns.less(comparator, src[mid + 1], src[mid])) {
            System.arraycopy(src, low, dst, low, high - low + 1);
            return;
        }
        merge(src, dst, comparator, low, mid, high);
    }


    /**
     * 对外公开的排序函数
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
        Comparable[] aux = a.clone();
        sort(aux, a, low, high);
    }

    /**
     * 对外公开的排序函数
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
        Object[] aux = a.clone();
        sort(aux, a, comparator, low, high);
    }
}
