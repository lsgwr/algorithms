/***********************************************************
 * @Description : 二分查找的递归实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/22 23:59
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter5BinarySearchTree.Section1BinarySearch;

public class BinarySearch2 {

    /**
     * 不允许私自实例化
     */
    private BinarySearch2() {
    }

    /**
     * 递归方式实现二分法
     */
    private static int binarySearch(Comparable[] arr, int left, int right, Comparable target) {
        if (left > right) {
            return -1;
        }
        // middle = (left+right)/2中，left + right 可能越界;，完美的实现方式如下
        int middle = left + (right - left) / 2;
        if (arr[middle] == target) {
            return middle;
        } else if (arr[middle].compareTo(target) < 0) {
            // 在右半部分中[middle+1...right]
            return binarySearch(arr, middle + 1, right, target);
        } else {
            // 在左半部分中[left...middle-1]
            return binarySearch(arr, left, middle - 1, target);
        }
    }

    /**
     * 递归实现二分查找树
     */
    public static int binarySearch(Comparable[] arr, Comparable target) {
        int n = arr.length;
        return binarySearch(arr, 0, n - 1, target);
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        // 1.测试非递归方法
        long startTime = System.currentTimeMillis();
        // 对于我们的待查找数组[0...N)
        // 对[0...N)区间的数值使用二分查找，最终结果应该就是数字本身
        // 对[N...2*N)区间的数值使用二分查找，因为这些数字不在arr中，结果为-1
        for (int i = 0; i < 2 * n; i++) {
            int v = binarySearch(arr, i);
            if (i < n) {
                assert (v == i);
            } else {
                assert (v == -1);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Binary Search(With Recursion): " + (endTime - startTime) + " ms");

    }
}
