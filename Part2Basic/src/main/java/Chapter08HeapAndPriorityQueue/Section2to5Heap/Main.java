/***********************************************************
 * @Description : 测试Heapify和不heapify的效率差距,Heapify后的效率会提升些
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/18 00:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter08HeapAndPriorityQueue.Section2to5Heap;

import Chapter08HeapAndPriorityQueue.Part1BasicChapter4HeapAndSort.MaxHeap;

import java.util.Random;

public class Main {
    /**
     * 最大堆测试
     *
     * @param testData  测试数据
     * @param isHeapify 是否要直接把数组转化成最大堆，数据量小时建议为true
     * @return 函数执行时间
     */
    private static double testMaxHeap(Integer[] testData, boolean isHeapify) {

        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap<>(testData);
        } else {
            maxHeap = new MaxHeap<>(testData.length);
            for (int num : testData) {
                maxHeap.insert(num);
            }
        }

        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            arr[i] = maxHeap.popMax();
        }

        // 最大堆每次弹出最大值，所以后面的值肯定比前面的值小
        for (int i = 1; i < testData.length; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException("最大堆内部错误！");
            }
        }
        System.out.println("Test MaxHeap completed.");

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    /**
     * 最小堆测试
     *
     * @param testData  测试数据
     * @param isHeapify 是否要直接把数组转化成最小堆，数据量小时建议为true
     * @return 函数执行时间
     */
    private static double testMinHeap(Integer[] testData, boolean isHeapify) {

        long startTime = System.nanoTime();

        MinHeap<Integer> minHeap;
        if (isHeapify) {
            minHeap = new MinHeap<>(testData);
        } else {
            minHeap = new MinHeap<>(testData.length);
            for (int num : testData) {
                minHeap.add(num);
            }
        }

        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            arr[i] = minHeap.popMin();
        }
        // 最小堆每次弹出最小值，所以后面的值肯定比前面的值大
        for (int i = 1; i < testData.length; i++) {
            if (arr[i - 1] > arr[i]) {
                throw new IllegalArgumentException("最小堆内部错误");
            }
        }
        System.out.println("Test MinHeap completed.");

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int N = 100000;

        Random random = new Random();
        Integer[] testData = new Integer[N];
        for (int i = 0; i < N; i++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
            if (testData[i] == 0) {
                System.out.println("出现零啦");
            }
        }

        // 逐个扔进去，逐个heapify，性能比较低
        double time1 = testMaxHeap(testData, false);
        System.out.println("Without heapify: " + time1 + " s");

        // 一次性扔进去，直接对整个数组heapify
        // 但是当数据量很大时，效果就和上面差不多了，数据越大，上面的方法性能相对就越高
        double time2 = testMaxHeap(testData, true);
        System.out.println("With heapify: " + time2 + " s");

        // 逐个扔进去，逐个heapify，性能比较低
        double time3 = testMinHeap(testData, false);
        System.out.println("Without heapify: " + time3 + " s");

        // 一次性扔进去，直接对整个数组heapify
        // 但是当数据量很大时，效果就和上面差不多了，数据越大，上面的方法性能相对就越高
        double time4 = testMinHeap(testData, true);
        System.out.println("With heapify: " + time4 + " s");
    }
}
/***
 * 下面是只测试最大堆未heapify和heapify后的性能比较
 *
 * 1w数据时：
 * Test MaxHeap completed.
 * Without heapify: 0.0086161 s
 * Test MaxHeap completed.
 * With heapify: 0.0042903 s
 *
 * 10w数据时：
 * Test MaxHeap completed.
 * Without heapify: 0.0443523 s
 * Test MaxHeap completed.
 * With heapify: 0.0229158 s
 *
 * 100w数据时：
 * Test MaxHeap completed.
 * Without heapify: 0.6039805 s
 * Test MaxHeap completed.
 * With heapify: 0.594649 s
 *
 * 1000w数据时：
 * Test MaxHeap completed.
 * Without heapify: 13.2986475 s
 * Test MaxHeap completed.
 * With heapify: 11.2369169 s
 */
