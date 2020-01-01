/***********************************************************
 * @Description : 优先队列总是弹出最大/最小的元素
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/18 08:08
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter08HeapAndPriorityQueue.Section6PriorityQueueBasedOnHeap;

import Chapter03StackAndQueues.Section5ArrayQueue.Queue;
import Chapter08HeapAndPriorityQueue.Section2to5Heap.MaxHeap;

public class PriorityQueue<Element extends Comparable<Element>> implements Queue<Element> {
    private MaxHeap<Element> maxHeap;

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
    public void enqueue(Element element) {
        maxHeap.insert(element);
    }

    @Override
    public Element dequeue() {
        return maxHeap.popMax();
    }

    @Override
    public Element getFront() {
        return maxHeap.getMax();
    }
}
