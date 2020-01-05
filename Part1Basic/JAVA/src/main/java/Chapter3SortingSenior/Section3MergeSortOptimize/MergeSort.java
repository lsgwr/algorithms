/***********************************************************
 * @Description : 归并排序的算法优化(判断arr[mid]是否大于arr[mid+1]是地话再接着往下进行merge)
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/15 12:56
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter3SortingSenior.Section3MergeSortOptimize;

import java.util.Arrays;

public class MergeSort {
    /**
     * 静态方法类不要实例化
     */
    private MergeSort() {
    }

    /**
     * 对当前轮的元素进行归并:将arr[left...middle]和arr[mid+1...right]两部分进行归并。注意都是闭区间
     */
    private static void merge(Comparable[] arr, int left, int middle, int right) {
        // 声明辅助数组,注意copyOfRange是左闭右开区间
        Comparable[] aux = Arrays.copyOfRange(arr, left, right + 1);
        // 开始合并
        // 左侧游标
        int i = left;
        // 右侧游标
        int j = middle + 1;
        // k为要在arr中放入元素的位置
        for (int k = left; k <= right; k++) {
            // 先判断特殊情况，一边已经快全部被拿走了，另一边还没动
            if (i > middle) {
                // 说明左边的元素排完了，只能用右边地了
                arr[k] = aux[j - left];
                j++;
            } else if (j > right) {
                // 右边元素已经排完了，只能用左边的了
                arr[k] = aux[i - left];
                i++;
            } else if (aux[i - left].compareTo(aux[j - left]) < 0) {
                // 左半边 < 右半边
                arr[k] = aux[i - left];
                i++;
            } else {
                // 右半边 < 左半边
                arr[k] = aux[j - left];
                j++;
            }
        }

    }

    /**
     * 递归使用归并排序，对arr[left...right]的范围内的元素进行排序，注意左右都是闭区间
     */
    private static void sort(Comparable[] arr, int left, int right) {
        // 如果左侧元素下标大于等于右侧元素下标，说明归并完成，直接退出即可
        if (left >= right) {
            return;
        }
        int middle = (left + right) / 2;
        sort(arr, left, middle);
        sort(arr, middle + 1, right);
        // arr[middle] < arr[middle+1]的时候整个数组arr已经有序了
        if (arr[middle].compareTo(arr[middle + 1]) > 0) {
            // merge操作
            merge(arr, left, middle, right);
        }
    }

    /**
     * 最终被调用的函数
     */
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        // 左逼右闭区间
        sort(arr, 0, n - 1);
    }

    public static void main(String[] args) {
        // 测试自定义的算法辅助函数
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, N);
        // 测试数组是否有序
        SortTestHelper.testSort("Chapter3SortingSenior.Section2MergeSort.MergeSort", arr);
        // SortTestHelper.printArray(arr);
    }
}
/**
 * MergeSort : 265ms
 */
