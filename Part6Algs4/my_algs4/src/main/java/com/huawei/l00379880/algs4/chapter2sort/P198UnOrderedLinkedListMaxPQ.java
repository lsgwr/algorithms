package com.huawei.l00379880.algs4.chapter2sort;

import com.huawei.l00379880.algs4.chapter1javabasic.P94LinkedListStack;

import java.util.NoSuchElementException;

/********************************************************************
 * @Description : 链表实现的最大无序优先队列,体现在每次pop的时候都pop最大地
 *                Key 可比较的泛型数据类型
 * @author      : 梁山广
 * @date        : 2018/1/3 11:02
 * @email       : liangshanguang2@gmail.com
 *****************************************************************/
public class P198UnOrderedLinkedListMaxPQ<Key extends Comparable<Key>> {
    /**
     * 栈顶节点
     */
    private Node<Key> first;

    /**
     * 栈的实时元素数目
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

    public P198UnOrderedLinkedListMaxPQ(int capacity) {
        // 成员初始化
        this.first = null;
        this.n = 0;
    }

    /**
     * 判断栈是否为空
     */
    public boolean isEmpty() {
        // 第一个元素为null表示没元素了
        // 还可以用n == 0
        return first == null;
    }

    /**
     * 返回队列大小
     */
    public int size() {
        return n;
    }

    /**
     * 插入元素,从首头推入元素.在这里要保证链表有序.这里实现了在链表的任意位置插入节点
     */
    public void insert(Key key) {
        // 先将头节点保存到一个一个oldFirst节点中
        Node<Key> oldFirst = first;
        // 新建一个新的节点作为头节点赋给first
        first = new Node<Key>();
        // 把头节点值设置为要插入的值
        first.item = key;
        // 新的头节点指向老的头节点,即将链表穿起来了,实现了在开头推入自己的元素
        first.next = oldFirst;
        // 链表节点数+1(在这里即栈的长度+1)
        n++;
    }

    /**
     * 删除最大元素,遍历链表,找到最大节点,把其换到表头,然后pop出去
     */
    public Key delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("栈已经空啦!不能再弹出元素啦");
        }

        // 1.循环遍历链表,找到最大的节点移动到链表头
        // 先把第一个节点认为是最大的节点(临时最大节点)
        Node<Key> max = first;
        // 最大节点前面的那个节点
        Node<Key> maxPrevious = first;
        // 循环指针
        Node<Key> current = first;
        // 保存旧的首节点
        Node<Key> oldFirst = first;
        // 是否有节点比首节点大从而需要被移到首节点的位置上
        boolean flag = false;
        // 链表循环,当找到临时最大节点比之小的节点时,更新临时最大节点max
        while (current.next != null) {
            if (P153CommenFuns.less(max.item, current.next.item)) {
                flag = true;
                // 更新临时最大节点
                max = current.next;
                maxPrevious = current;
            }
            // 循环后移
            current = current.next;
        }
        // 2. 把max节点移到链表首节点位置
        if (flag) {
            if (max.next != null) {
                // max的前一个节点指向max的后一个节点
                maxPrevious.next = max.next;
            } else {
                // max后面没节点了
                // max的后一个节点指向first
                max.next = first;
                // 如果最大元素后面没元素了,那么移动最前面之后,最大元素前面的那个节点的next页要设置为null,要不就循环了
                maxPrevious.next = null;
            }
            // 给第一个节点重新赋值
            first = max;
            first.next = oldFirst;
        }
        // 3.获取第一个元素的值,用于后面返回
        Key key = first.item;
        // 让头元素的指针指向第二个元素,头元素会被JVM自动回收.删除首节点完成
        first = first.next;
        n--;
        return key;
    }
}
