/***********************************************************
 * @Description : 基于JDK自带的链表java.util.LinkedList实现的队列
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/12/28 00:35
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04LinkedList.Section7LinkedListQueue;

import Chapter03StackAndQueues.Section5ArrayQueue.Queue;

import java.util.LinkedList;

public class JdkLinkedListQueue<Element> implements Queue<Element> {
    private LinkedList<Element> list;

    public JdkLinkedListQueue() {
        this.list = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(Element element) {
        list.add(element);
    }

    @Override
    public Element dequeue() {
        return list.remove();
    }

    @Override
    public Element getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列不能为空！");
        }
        return list.peekFirst();
    }

    @Override
    public String toString() {
        return "Queue: front" + list + " tail";
    }

    public static void main(String[] args) {
        JdkLinkedListQueue<Integer> queue = new JdkLinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
