/***********************************************************
 * @Description : 测试优先队列
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/1 17:47
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter08HeapAndPriorityQueue.Section6PriorityQueueBasedOnOurHeap;

public class Main {
    public static void main(String[] args) {
        int[] arr = {4, 2, 1, 6, 9, 3, 0};
        // 基于最小堆的优先队列，每次弹出最小值
        PriorityQueue<Integer> priorityQueueMin = new PriorityQueue<>();
        PriorityQueue<Integer> priorityQueueMax = new PriorityQueue<>(true);
        for (int num : arr) {
            priorityQueueMin.enqueue(num);
            priorityQueueMax.enqueue(num);
        }
        System.out.print("最小堆，升序排列：");
        while (!priorityQueueMin.isEmpty()) {
            System.out.print(priorityQueueMin.dequeue() + " ");
        }
        System.out.println();

        System.out.print("最大堆，降序排列：");
        while (!priorityQueueMax.isEmpty()){
            System.out.print(priorityQueueMax.dequeue()+ " ");
        }
    }
}
