/***********************************************************
 * @Description : 二分搜索
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/29 11:06
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第3章_分治法.第2节_二分搜索;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    /**
     * 在数组arr中二分搜索target在数组中的下标，没有返回-1
     *
     * @param arr    要查找的数组
     * @param target 目标值
     * @return 找到返回下标，找不到返回-1
     */
    public int binarySearch(int[] arr, int target) {
        // 这个sort函数无比记得加上，这是二分搜索法应用的前提
        Arrays.sort(arr);
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            // mid为查找范围的中间坐标
            int mid = low + (high - low) / 2;
            if (target < arr[mid]) {
                // 目标值小于中间值，则在左半边查找
                high = mid - 1;
            } else if (target > arr[mid]) {
                // 目标值大于中间值，则在右半边查找
                low = mid + 1;
            } else {
                // 目标值等于中间值，返回找到的元素的下标
                return mid;
            }
        }
        // 最终没找到，返回-1
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        int target = sc.nextInt();
        System.out.println("要查找的元素在数组中的下标为："+new Main().binarySearch(arr, target));
    }
}
/**
 * 要查找的元素在数组中的下标为：3
 */
