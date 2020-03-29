/***********************************************************
 * @Description : 快速排序
 * 核心思想：
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/29 12:46
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第3章_分治法.第4节_快速排序;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 基准切分，左边小于基准元素，右边大于基准元素
     *
     * @param arr  原始数组
     * @param low  切分区间的下界
     * @param high 切分区间的上界
     * @return 完成最终划分后基准元素所在的位置
     */
    private int partition(int[] arr, int low, int high) {
        // 初始基准元素
        int i = low, j = high, pivot = arr[low];
        while (i < j){
            while (i < j && arr[j] > pivot){
                // 向pivot左侧扫描
                j--;
            }
            if (i < j){
                // arr[i]和arr[j]交换后i向右移动一位
                swap(arr, i++, j);
            }
            while (i < j && arr[i] <=pivot){
                // 向pivot右侧访问
                i++;
            }
            if (i < j){
                // arr[i]和arr[j]交换后j向左移动一位
                swap(arr, i, j--);
            }
        }
        // 返回最终划分完基准元素所在的位置
        return i;
    }

    public void quickSort(int[] arr, int low, int high){
        int mid = 0;
        if (low < high){
            mid = partition(arr, low, high);
            quickSort(arr, low, mid - 1);
            quickSort(arr, mid+1, high);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        new Main().quickSort(arr, 0, N -1);
        System.out.println(Arrays.toString(arr));
    }
}
