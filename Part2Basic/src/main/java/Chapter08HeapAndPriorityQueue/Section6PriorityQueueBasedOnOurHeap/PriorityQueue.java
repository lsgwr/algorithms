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
import Chapter08HeapAndPriorityQueue.Section2to5Heap.MinHeap;

public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    /**
     * 最大堆
     */
    private MaxHeap<E> maxHeap;
    /**
     * 最小堆
     */
    private MinHeap<E> minHeap;

    /**
     * 和JDK自带的PriorityQueue一致，默认是最小堆
     */
    private boolean isMax = false;

    public PriorityQueue() {
        minHeap = new MinHeap<>();
    }

    public PriorityQueue(boolean isMax) {
        this.isMax = isMax;
        if (isMax) {
            maxHeap = new MaxHeap<>();
        } else {
            minHeap = new MinHeap<>();
        }
    }

    @Override
    public int getSize() {
        if (isMax) {
            return maxHeap.size();
        } else {
            return minHeap.size();
        }
    }

    @Override
    public boolean isEmpty() {
        if (isMax) {
            return maxHeap.isEmpty();
        } else {
            return minHeap.isEmpty();
        }
    }

    @Override
    public void enqueue(E e) {
        if (isMax) {
            maxHeap.add(e);
        } else {
            minHeap.add(e);
        }
    }

    @Override
    public E dequeue() {
        if (isMax) {
            return maxHeap.popMax();
        } else {
            return minHeap.popMin();
        }
    }

    @Override
    public E getFront() {
        if (isMax) {
            return maxHeap.getMax();
        } else {
            return minHeap.getMin();
        }
    }
}
