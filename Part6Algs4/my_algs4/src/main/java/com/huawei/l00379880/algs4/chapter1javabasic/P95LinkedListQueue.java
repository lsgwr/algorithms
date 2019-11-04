package com.huawei.l00379880.algs4.chapter1javabasic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/***********************************************************
 * @Description : 基于链表实现地队列,P95~P96
 * @author      : 梁山广
 * @date        : 2017/12/31 18:20
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P95LinkedListQueue<Item> implements Iterable<Item> {

    /**
     * 队列头节点
     */
    private Node<Item> first;

    /**
     * 队列尾节点
     */
    private Node<Item> last;

    /**
     * 队列的实时元素数目
     */
    private int n;

    /**
     * 这个方法定义在成员变量声明后是为了可以把父类上的Item传进来
     * 定义成static可以提高性能,自行百度下静态内部类的作用,
     * 外部类可以访问静态内部类的资源,但静态内部类不可以访问外部类的资源,防止内部类不小心影响外部类
     */
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * 成员变量初始化
     */
    public P95LinkedListQueue() {
        // 成员初始化
        this.first = null;
        this.last = null;
        this.n = 0;
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        // 第一个元素为null表示没元素了
        // 还可以用n == 0
        return first == null;
    }

    /**
     * 返回队列的大小
     */
    public int size() {
        return n;
    }

    /**
     * 从队尾入队
     */
    public void enqueue(Item item) {
        // 先将队尾节点保存到一个一个oldLast节点中
        Node<Item> oldLast = last;
        // 新建一个新的节点作为头节点赋给first
        last = new Node<Item>();
        // 把头节点值设置为要插入的值
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            // 如果原来的队列为空,name插入一个节点后,头尾节点应该是一个
            first = last;
        } else {
            // 当之前有节点的时候,只需要让之前的旧尾节点oldLast指向现在的新尾节点last即可
            oldLast.next = last;
        }
        // 链表节点数+1(在这里即队列的长度+1)
        n++;
    }

    /**
     * 从队头出队
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("队列已经空啦!不能再出队元素啦");
        }
        // 获取第一个元素的值,用于后面返回
        Item item = first.item;
        // 让头元素的指针指向第二个元素,头元素会被JVM自动回收.删除首节点完成
        first = first.next;
        n--;
        // 如果队列已经空了,最好释放last,防止对象游离
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    /**
     * 获取队列的开头元素,不用pop是因为不想删
     */
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("队列已经空啦!更别说取第一个节点的值了!");
        }
        return first.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListQueueIterator<Item>(first);
    }

    private class LinkedListQueueIterator<Item> implements Iterator<Item> {

        /**
         * 当前节点
         */
        private Node<Item> current;

        /**
         * 初始化,把第一个节点赋给当前节点.队列和栈一样,也是从头开始弹出元素
         */
        public LinkedListQueueIterator(Node<Item> first) {
            this.current = first;
        }

        /**
         * 取过来的节点为空表示队列为空,肯定没有下一个节点了
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("队列已经为空!没有下一个元素了!");
            }
            // 下面的和pop方法思路一样
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("暂不支持此方法!");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // 必须重写了iterator方法才能用foreach
        for (Item item : this) {
            sb.append(item);
            sb.append(" ");
        }
        return sb.toString();
    }

}
