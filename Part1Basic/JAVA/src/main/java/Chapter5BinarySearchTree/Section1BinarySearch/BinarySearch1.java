/***********************************************************
 * @Description : 非递归方式实现二分查找
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/22 23:52
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter5BinarySearchTree.Section1BinarySearch;

public class BinarySearch1 {

    /**
     * 不允许外部构造实例
     */
    private BinarySearch1() {

    }

    /**
     * 二分查找法，在有序数组中arr中，查找target
     * 找到target就返回target对应的索引，找不到就返回-1
     */
    public static int binarySearch(Comparable[] arr, Comparable target) {
        int n = arr.length;
        // 在arr[left...right]之中查找target
        int left = 0, right = n - 1;
        while (left <= right) {
            // middle = (left+right)/2中，left + right 可能越界;，完美的实现方式如下
            int middle = left + (right - left) / 2;
            if (arr[middle] == target) {
                return middle;
            } else if (arr[middle].compareTo(target) < 0) {
                // 在右半部分中[middle+1...right]
                left = middle + 1;
            } else {
                // 在左半部分中[left...middle-1]
                right = middle - 1;
            }
        }
        return -1;
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
        System.out.println("Binary Search(Without Recursion): " + (endTime - startTime) + " ms");

    }
}
