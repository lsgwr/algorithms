/***********************************************************
 * @Description : 测试Heapify和不heapify的效率差距,Heapify后的效率会提升些
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/18 00:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter08HeapAndPriorityQueue.Section2to5Heap;

import java.util.Random;

public class Main {
    private static double testHeap(Integer[] testData, boolean isHeapify) {
        long startTime = System.nanoTime();
        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap<>(testData);
        } else {
            maxHeap = new MaxHeap<>(testData.length);
            for (int num : testData) {
                maxHeap.add(num);
            }
        }
        // 存储最大堆弹出的元素，因为每次都是弹出最大值，所以arr中的结果一定是从大到小排序地
        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            arr[i] = maxHeap.popMax();
        }
        for (int i = 1; i < testData.length; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException("最大堆实现不对！");
            }
        }
        System.out.println("Test MaxHeap completed.");
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
        double time1 = testHeap(testData, false);
        System.out.println("Without heapify: " + time1 + " s");

        // 一次性扔进去，每个节点都占好位置，然后再统一调整即可，性能会高很多。
        // 但是当数据量很大时，效果就和上面差不多了，数据越大，上面的方法性能相对就越高
        double time2 = testHeap(testData, true);
        System.out.println("With heapify: " + time2 + " s");
    }
}
/***
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
