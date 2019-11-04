package com.huawei.l00379880.algs4.chapter2sort;


import java.util.Comparator;


/***********************************************************
 * @Description : 快速排序,优化后地.
 *                1.小数组切换到快速排序
 *                2.使用子数组的一小部分元素的中位数来切分数组.代价是需要计算中位数.
 *                  经验是:取样大小为3并用大小居中的元素切分的效果最好
 * @author      : 梁山广
 * @date        : 2018/1/2 20:34
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P187QuickBetter {
    /**
     * cutoff to insertion sort, must be >= 1
     */
    private static final int INSERTION_SORT_CUTOFF = 8;

    /**
     * cutoff to median-of-3 partitioning
     */
    private static final int MEDIAN_OF_3_CUTOFF = 40;

    private P187QuickBetter() {
    }

    /**
     * 整个数组排序
     */
    public static void sort(Comparable[] a) {
        // 这种算法是人为选取切分元素(通过一定的分析方法,比如概率论中中位数).所以不需要shuffle了
        sort(a, 0, a.length - 1);
    }


    /**
     * 局部排序
     */
    public static void sort(Comparable[] a, int low, int high) {
        int n = high - low + 1;

        // cutoff to insertion sort
        if (n <= INSERTION_SORT_CUTOFF) {
            P157Insertion.sort(a, low, high);
            return;
        }

        // use median-of-3 as partitioning element
        else if (n <= MEDIAN_OF_3_CUTOFF) {
            int m = median3(a, low, low + n / 2, high);
            P153CommenFuns.exchange(a, m, low);
        }

        // use Tukey ninther as partitioning element
        else {
            int eps = n / 8;
            int mid = low + n / 2;
            int m1 = median3(a, low, low + eps, low + eps + eps);
            int m2 = median3(a, mid - eps, mid, mid + eps);
            int m3 = median3(a, high - eps - eps, high - eps, high);
            int ninther = median3(a, m1, m2, m3);
            P153CommenFuns.exchange(a, ninther, low);
        }

        // Bentley-McIlroy 3-way partitioning
        int i = low, j = high + 1;
        int p = low, q = high + 1;
        Comparable v = a[low];
        while (true) {
            while (P153CommenFuns.less(a[++i], v)) {
                if (i == high) {
                    break;
                }
            }
            while (P153CommenFuns.less(v, a[--j])) {
                if (j == low) {
                    break;
                }
            }

            // pointers cross
            if (i == j && P153CommenFuns.equal(a[i], v)) {
                P153CommenFuns.exchange(a, ++p, i);
            }
            if (i >= j) {
                break;
            }

            P153CommenFuns.exchange(a, i, j);
            if (P153CommenFuns.equal(a[i], v)) {
                P153CommenFuns.exchange(a, ++p, i);
            }
            if (P153CommenFuns.equal(a[j], v)) {
                P153CommenFuns.exchange(a, --q, j);
            }
        }


        i = j + 1;
        for (int k = low; k <= p; k++) {
            P153CommenFuns.exchange(a, k, j--);
        }
        for (int k = high; k >= q; k--) {
            P153CommenFuns.exchange(a, k, i++);
        }

        sort(a, low, j);
        sort(a, i, high);
    }


    /**
     * 整个数组排序.可自定义排序规则
     */
    public static void sort(Object[] a, Comparator comparator) {
        // 先打乱,消除算法性能对输入的依赖.从整体来看是为了提高性能地
        sort(a, comparator, 0, a.length - 1);
    }


    /**
     * 局部排序.可自定义排序规则
     */
    public static void sort(Object[] a, Comparator comparator, int low, int high) {
        int n = high - low + 1;

        // cutoff to insertion sort
        if (n <= INSERTION_SORT_CUTOFF) {
            P157Insertion.sort(a, comparator, low, high);
            return;
        }

        // use median-of-3 as partitioning element
        else if (n <= MEDIAN_OF_3_CUTOFF) {
            int m = median3(a, comparator, low, low + n / 2, high);
            P153CommenFuns.exchange(a, m, low);
        }

        // use Tukey ninther as partitioning element
        else {
            int eps = n / 8;
            int mid = low + n / 2;
            int m1 = median3(a, comparator, low, low + eps, low + eps + eps);
            int m2 = median3(a, comparator, mid - eps, mid, mid + eps);
            int m3 = median3(a, comparator, high - eps - eps, high - eps, high);
            int ninther = median3(a, comparator, m1, m2, m3);
            P153CommenFuns.exchange(a, ninther, low);
        }

        // Bentley-McIlroy 3-way partitioning
        int i = low, j = high + 1;
        int p = low, q = high + 1;
        Object v = a[low];
        while (true) {
            while (P153CommenFuns.less(comparator, a[++i], v)) {
                if (i == high) {
                    break;
                }
            }
            while (P153CommenFuns.less(comparator, v, a[--j])) {
                if (j == low) {
                    break;
                }
            }

            // pointers cross
            if (i == j && P153CommenFuns.equal(comparator, a[i], v)) {
                P153CommenFuns.exchange(a, ++p, i);
            }
            if (i >= j) {
                break;
            }

            P153CommenFuns.exchange(a, i, j);
            if (P153CommenFuns.equal(comparator, a[i], v)) {
                P153CommenFuns.exchange(a, ++p, i);
            }
            if (P153CommenFuns.equal(comparator, a[j], v)) {
                P153CommenFuns.exchange(a, --q, j);
            }
        }


        i = j + 1;
        for (int k = low; k <= p; k++) {
            P153CommenFuns.exchange(a, k, j--);
        }
        for (int k = high; k >= q; k--) {
            P153CommenFuns.exchange(a, k, i++);
        }

        sort(a, comparator, low, j);
        sort(a, comparator, i, high);
    }

    /**
     * return the index of the median element among a[i], a[j], and a[k]
     */
    private static int median3(Comparable[] a, int i, int j, int k) {
        return (P153CommenFuns.less(a[i], a[j]) ?
                (P153CommenFuns.less(a[j], a[k]) ? j : P153CommenFuns.less(a[i], a[k]) ? k : i) :
                (P153CommenFuns.less(a[k], a[j]) ? j : P153CommenFuns.less(a[k], a[i]) ? k : i));
    }

    /**
     * return the index of the median element among a[i], a[j], and a[k]
     */
    private static int median3(Object[] a, Comparator comparator, int i, int j, int k) {
        return (P153CommenFuns.less(comparator, a[i], a[j]) ?
                (P153CommenFuns.less(comparator, a[j], a[k]) ? j : P153CommenFuns.less(comparator, a[i], a[k]) ? k : i) :
                (P153CommenFuns.less(comparator, a[k], a[j]) ? j : P153CommenFuns.less(comparator, a[k], a[i]) ? k : i));
    }
}
