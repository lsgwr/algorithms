/***********************************************************
 * @Description : 2-5 插入排序,因为要交换的次数很多，所以运行速度低于选择排序
 *                插入排序与选择排序的不同：
 *                + 选择排序是从指定位置往后找(后)
 *                + 插入排序是从指定位置往前找(前)
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/14 22:13
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter2SortingBasic.Section5InsertionSort;

public class InsertionSort {

    /**
     * 把指定数组的两个指定下标的元素互换
     *
     * @param arr 指定数组
     * @param i   指定下标1
     * @param j   指定下标2
     */
    private static void swap(Object[] arr, int i, int j) {
        Object tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 插入排序的核心实现,默认是从小到大进行排序
     *
     * @param arr 待排序的数组
     */
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        // 注意下标从1开始，不停往前比较，下标为0的元素默认有效
        for (int i = 1; i < n; ++i) {
            // 从位置i开始，从前挨个比较(前面已经是有序地了，这个是前提)，一直比较到前面的某个元素比自己小就停下
            for (int j = i; j > 0; j--) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    // 后面的元素比前面的小，接着往前置换
                    swap(arr, j, j - 1);
                } else {
                    // 一直到j前面的元素j-1小于j了，说明升序排列完成，直接退出即可
                    break;
                }
            }

        }
    }

    public static void main(String[] args) {
        // 测试自定义的算法辅助函数
        int N = 10000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, N);
        // 测试数组是否有序
        SortTestHelper.testSort("Chapter2SortingBasic.Section5InsertionSort.InsertionSort", arr);
    }
}
