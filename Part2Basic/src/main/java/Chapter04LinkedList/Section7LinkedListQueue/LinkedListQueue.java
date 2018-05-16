/***********************************************************
 * @Description : 基于链表实现的队列
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/16 00:35
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04LinkedList.Section7LinkedListQueue;

import Chapter03StackAndQueues.Section5ArrayQueue.Queue;

public class LinkedListQueue<Element> implements Queue<Element> {

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
    public void enqueue(Element element) {
        // 从队列尾部入队
        if (tail == null) {
            // tail为null说明之前没有元素
            tail = new Node(element);
            // 第一个元素，head和tail应该都指向它
            head = tail;
        } else {
            // 之前有元素，tail后移以为直线加入地Node
            tail.next = new Node(element);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public Element dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列不能为空！");
        }
        // 从队头删除元素(出队)
        // 临时保存头结点
        Node retNode = head;
        // 头节点指针后移
        head = head.next;
        // 把原来的头结点置空
        retNode.next = null;
        if (head == null) {
            // 如果头结点指针指向null,说明队列中没元素了,tail也该为null
            tail = null;
        }
        size--;
        return retNode.element;
    }

    @Override
    public Element getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列不能为空！");
        }
        return head.element;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: front");
        Node cur = head;
        while (cur != null) {
            sb.append(cur + "->");
            cur = cur.next;
        }
        sb.append("NULL tail");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
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
