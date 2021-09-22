/***********************************************************
 * @Description : 利用索引最大堆进行排序，尽管这不是索引最大堆得擅长之处
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/22 18:04
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter4Heap.Section8MaxIndexHeap;

public class IndexHeapSort {
    /**
     * 我们的算法类不允许产生任何实例
     */
    private IndexHeapSort() {
    }

    public static void sort(Comparable[] arr) {

        int n = arr.length;

        IndexMaxHeap<Comparable> indexMaxHeap = new IndexMaxHeap<>(n);
        for (int i = 0; i < n; i++) {
            indexMaxHeap.insert(arr[i], i);
        }

        for (int i = n - 1; i >= 0; i--) {
            arr[i] = indexMaxHeap.popMax();
        }
    }

    /**
     * 测试 Index Heap Sort
     */
    public static void main(String[] args) {

        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("Chapter4Heap.Section8MaxIndexHeap.IndexHeapSort", arr);

        return;
    }

}
