/***********************************************************
 * @Description : 2-6 插入排序优化，通过临时变量tmp来减少交换次数.
 *                    插入排序比选择排序优秀地一点在于可以提前结束
 *                    循环，不像选择排序一样，平均每次都要遍历一半
 *                    的数组元素。所以，越是近似有序的数组，越适合
 *                    插入排序
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/14 22:57
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter2SortingBasic.Section6InsertionSortOptimize;

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
            // 先把起始位置的元素暂存
            Comparable tmp = arr[i];
            // 从位置i开始，从前挨个比较(前面已经是有序地了，这个是前提)，一直比较到前面的某个元素比自己小就停下
            for (int j = i; j > 0; j--) {
                if (tmp.compareTo(arr[j - 1]) < 0) {
                    // 起始位置元素比前面的小，就把前面的值附到后面来元素上
                    arr[j] = arr[j - 1];
                } else {
                    // 一直到j前面的元素j-1小于tmp了，说明升序排列完成，把之前存的起始位置元素tmp插入到此处即可
                    arr[j] = tmp;
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
        SortTestHelper.testSort("Chapter2SortingBasic.Section6InsertionSortOptimize.InsertionSort", arr);
    }
}
/**
 * InsertionSort : 121ms
 */
