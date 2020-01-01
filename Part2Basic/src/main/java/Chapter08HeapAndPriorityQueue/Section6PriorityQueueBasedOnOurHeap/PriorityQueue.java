/***********************************************************
 * @Description : 优先队列总是弹出最大/最小的元素，
 * 当前类是基于最大堆的，所以每次都是弹出最大的元素
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/18 08:08
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter08HeapAndPriorityQueue.Section6PriorityQueueBasedOnOurHeap;

import Chapter03StackAndQueues.Section5ArrayQueue.Queue;
import Chapter08HeapAndPriorityQueue.Section2to5Heap.MaxHeap;

public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.popMax();
    }

    @Override
    public E getFront() {
        return maxHeap.getMax();
    }
}
