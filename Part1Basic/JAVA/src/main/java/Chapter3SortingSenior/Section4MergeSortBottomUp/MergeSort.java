/***********************************************************
 * @Description : 自底向上的归并排序
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/15 17:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter3SortingSenior.Section4MergeSortBottomUp;

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
     * 自底向上的归并排序
     *
     * @param arr 待排序数组
     */
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        // 每次增大一倍步幅去自底向上归并
        for (int size = 1; size <= n; size += size) {
            //关键点1： i + size < n是为了防止下面__merge的时候越界(因为i + size可能大于n了)
            for (int i = 0; i + size < n; i += size + size) {
                // 对arr[i...i+size-1]和arr[i+size...i+size+size-1] 进行归并
                // 关键点2：虽然前面保证了1 + size < n，但是1 + size + size 也可能超过最大下标n-1,
                //         此处取它和n-1的更小值来保证merge的时候确实是两部分
                merge(arr, i, i + size - 1, Math.min(i + size + size - 1, n - 1));
            }
        }
    }

    public static void main(String[] args) {
        // 测试自定义的算法辅助函数
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, N);
        // 测试数组是否有序
        SortTestHelper.testSort("Chapter3SortingSenior.Section4MergeSortBottomUp.MergeSort", arr);
        // SortTestHelper.printArray(arr);
    }
}
/**
 * MergeSort : 296ms
 */
