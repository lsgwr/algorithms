/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/13 23:54
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter2SortingBasic.Section1SelectionSort;

public class SelectionSort {
    // 算法类不允许产生任何实例，简单的单例模式，没有做同步

    private SelectionSort() {
    }

    /**
     * 对指定的整型数组进行排序,默认是进行从小到大排序(升序)
     *
     * @param arr 指定的数组
     */
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            // 寻找[i, n)区间里的最小值的索引
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 把本轮的最小元素的位置和循环的起始位置进行置换
            swap(arr, i, minIndex);
        }
    }

    /**
     * 把指定数组的两个指定下标的元素互换
     *
     * @param arr 指定数组
     * @param i   指定下标1
     * @param j   指定下标2
     */
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 6, 8, 4, 2, 9, 10, 7, 1, 5};
        sort(arr);
        for (int i : arr) {
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
