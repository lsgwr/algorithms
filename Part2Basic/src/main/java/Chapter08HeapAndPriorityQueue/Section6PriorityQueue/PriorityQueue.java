/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/18 08:08
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter08HeapAndPriorityQueue.Section6PriorityQueue;

import Chapter03StackAndQueues.Section5ArrayQueue.Queue;
import Chapter08HeapAndPriorityQueue.HeapOfThisLesson.MaxHeap;

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
