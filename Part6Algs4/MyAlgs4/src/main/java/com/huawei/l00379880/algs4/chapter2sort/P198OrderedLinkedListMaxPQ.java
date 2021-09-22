package com.huawei.l00379880.algs4.chapter2sort;

import com.huawei.l00379880.algs4.chapter1javabasic.P94LinkedListStack;

import java.util.NoSuchElementException;

/********************************************************************
 * @Description : 链表实现的最大有序优先队列,体现在每次push的时候都保证有序.
 *                Key 可比较的泛型数据类型
 * @author      : 梁山广
 * @date        : 2018/1/3 11:02
 * @email       : liangshanguang2@gmail.com
 *****************************************************************/
public class P198OrderedLinkedListMaxPQ<Key extends Comparable<Key>> {
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

    public P198OrderedLinkedListMaxPQ(int capacity) {
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
        //1.先把元素保存到插入到链表头.这是为了防止第一个元素插入式链表为空

        // 先将头节点保存到一个一个oldFirst节点中
        Node<Key> oldFirst = first;
        // 新建一个新的节点作为头节点赋给first,记住一定要新建node!!要不到下面赋值就赋乱了!!
        // 自己
        Node<Key> node = new Node<>();
        // 把头节点值设置为要插入的值
        node.item = key;
        // 新的头节点指向老的头节点,即将链表穿起来了,实现了在开头推入自己的元素
        node.next = oldFirst;
        first = node;
        // 2.循环遍历链表,找到比插入节点大的位置进行插入
        // 保存当前节点,避免丢失
        Node<Key> current = first;
        // 控制是否需要对插入节点进行移动的标志位
        boolean flag = false;
        // 寻找插入点.当链表找到头,或者找到第一个元素比之小的节点是,退出while循环
        while (current.next != null && P153CommenFuns.less(first.item, current.next.item)) {
            flag = true;
            current = current.next;
        }
        // current即为插入点.如果在首节点位置就行的话,前面已经插入了,这里不需要执行了
        if (flag) {
            // 如果待插入位置的节点有下一个节点,就把待插入节点(现在在第一个节点的位置上)
            if (current.next != null) {
                node.next = current.next;
            } else {
                node.next = null;
            }
            // 插入点的下一个元素指向待插入元素
            current.next = first;
            first = oldFirst;
        }
        // 链表节点数+1(在这里即栈的长度+1)
        n++;
    }

    /**
     * 删除最小元素
     */
    public Key delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("栈已经空啦!不能再弹出元素啦");
        }
        // 获取第一个元素的值,用于后面返回
        Key key = first.item;
        // 让头元素的指针指向第二个元素,头元素会被JVM自动回收.删除首节点完成
        first = first.next;
        n--;
        return key;
    }
}
