package com.huawei.l00379880.algs4.chapter1javabasic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/***********************************************************
 * @Description : 基于链表实现地背包,P96~P98
 * @author      : 梁山广
 * @date        : 2017/12/31 20:13
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P98LinkedListBag<Item> implements Iterable<Item> {

    /**
     * 背包顶节点
     */
    private Node<Item> first;

    /**
     * 背包的实时元素数目
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
    public P98LinkedListBag() {
        // 成员初始化
        this.first = null;
        this.n = 0;
    }

    /**
     * 判断背包是否为空
     */
    public boolean isEmpty() {
        // 第一个元素为null表示没元素了
        // 还可以用n == 0
        return first == null;
    }

    /**
     * 返回背包的大小
     */
    public int size() {
        return n;
    }

    /**
     * 从首头推入元素
     */
    public void add(Item item) {
        // 先将头节点保存到一个一个oldFirst节点中
        Node<Item> oldFirst = first;
        // 新建一个新的节点作为头节点赋给first
        first = new Node<Item>();
        // 把头节点值设置为要插入的值
        first.item = item;
        // 新的头节点指向老的头节点,即将链表穿起来了,实现了在开头推入自己的元素
        first.next = oldFirst;
        // 链表节点数+1(在这里即背包的长度+1)
        n++;
    }


    /**
     * 获取背包的开头元素,不用pop是因为不想删
     */
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("背包是空的!更别说取第一个节点的值了!");
        }
        return first.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListBagIterator<Item>(first);
    }

    private class LinkedListBagIterator<Item> implements Iterator<Item> {

        /**
         * 当前节点
         */
        private Node<Item> current;

        /**
         * 初始化,把第一个节点赋给当前节点
         */
        public LinkedListBagIterator(Node<Item> first) {
            this.current = first;
        }

        /**
         * 取过来的节点为空表示背包为空,肯定没有下一个节点了
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("背包已经为空!没有下一个元素了!");
            }
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
