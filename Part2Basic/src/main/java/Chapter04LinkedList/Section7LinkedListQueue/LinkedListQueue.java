/***********************************************************
 * @Description : 基于链表实现的队列
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/16 00:35
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04LinkedList.Section7LinkedListQueue;

import Chapter03StackAndQueues.Section5ArrayQueue.Queue;

public class LinkedListQueue<Element> implements Queue {

    /**
     * 每个节点的封装
     */
    private class Node {
        /**
         * 节点元素值
         */
        Element element;
        /**
         * 指向的下一个节点
         */
        Node next;

        public Node(Element element, Node next) {
            this.element = element;
            this.next = next;
        }

        public Node(Element element) {
            this(element, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    private Node head, tail;
    private int size;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(Object o) {

    }

    @Override
    public Object dequeue() {
        return null;
    }

    @Override
    public Object getFront() {
        return null;
    }
}
