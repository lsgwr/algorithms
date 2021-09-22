/***********************************************************
 * @Description : 直接输入数组的方式构造堆，其复杂度为O(n);将n个元素挨个插入空堆来构造堆，复杂度是O(nlogn)
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/19 00:47
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter4Heap.Section5Heapify;

public class HeapSort1 {

    private HeapSort1() {

    }

    /**
     * 根据capacity构造最大堆
     *
     * @param arr 待排序数组
     */
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        MaxHeap<Comparable> maxHeap = new MaxHeap<>(n);
        for (int i = 0; i < n; i++) {
            maxHeap.insert(arr[i]);
        }
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.popMax() + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        // 测试自定义的算法辅助函数
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, N);
        // 测试数组是否有序
        // sort函数一定不要穿数组长度，要不老会报错
        SortTestHelper.testSort("Chapter4Heap.Section5Heapify.HeapSort1", arr);
    }
}
