/***********************************************************
 * @Description : 归并排序
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/29 11:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第3章_分治法.第3节_归并排序;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    /**
     * 合并区间[low, mid)和[mid, high]使得区间[low, high)有序
     *
     * @param arr  要排序地数组
     * @param low  区间下界
     * @param mid  区间中间
     * @param high 区间上界
     */
    private void merge(int[] arr, int low, int mid, int high) {
        int[] arrTmp = new int[high - low + 1];
        int i = low, j = mid + 1, k = 0;
        // 区间[low, mid)和[mid, high]同步比较
        while (i <= mid && j <= high) {
            // 按照从小到大防放到辅助数组B[]中
            if (arr[i] <= arr[j]) {
                arrTmp[k++] = arr[i++];
            } else {
                arrTmp[k++] = arr[j++];
            }
        }
        // 把两个区间剩余的元素直接放入临时数组arrTmp中
        while (i <= mid) {
            arrTmp[k++] = arr[i++];
        }
        while (j <= high) {
            arrTmp[k++] = arr[j++];
        }
        // 把临时数组设置回原始数组arr中
        for (i = low, k = 0; i <= high; i++) {
            arr[i] = arrTmp[k++];
        }
    }

    /**
     * 基于归并算法的排序
     *
     * @param arr  原始数组
     * @param low  待排序区间下界
     * @param high 待排序区间上界
     */
    void mergeSort(int[] arr, int low, int high) {
        if (low < high) {
            // 取中点
            int mid = (low + high) / 2;
            // 对左右两部分进行归并排序
            mergeSort(arr, low, mid);
            mergeSort(arr, mid+1, high);
            // 把排好序的左右两部分进行合并，相当于递归回退的时候做，类似回溯法了
            merge(arr, low, mid, high);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        new Main().mergeSort(arr, 0, N -1);
        System.out.println(Arrays.toString(arr));
    }
}
/**
 * [6, 8, 12, 15, 20, 38, 42, 50]
 */
