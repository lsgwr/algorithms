package com.huawei.l00379880.algs4.chapter3search;

import com.huawei.l00379880.algs4.chapter1javabasic.P95LinkedListQueue;

/***********************************************************
 * @Description : 基于无序(所以不需Comparable)链表的顺序查找
 * @author      : 梁山广
 * @date        : 2018/1/5 20:39
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P236SequentialSearchST<Key, Value> {
    /**
     * 符号表中的实时键值对数目
     */
    private int n;
    private Node first;

    private class Node {
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public P236SequentialSearchST() {

    }


    /**
     * 2018/1/5 21:31  获取符号表的元素数目
     */
    public int size() {
        return n;
    }

    /**
     * 2018/1/5 21:32 判断符号表是否为空
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 描述: 判断函数中是否含有指定的键
     * 日期: 2018/1/6 下午7:19
     * 作者: 梁山广
     */
    public boolean contains(Key key) {

        if (key == null) {
            throw new IllegalArgumentException("传入的key不能为空！");
        }
        return get(key) != null;
    }

    /**
     * 获取键对应的值
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的键key不能为空！");
        }
        // euqals方法不需要重写？
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.value;
            }
        }
        return null;
    }

    /**
     * 描述: 向无序链表中更新元素或者插入元素
     * 日期: 2018/1/6 下午7:27
     * 作者: 梁山广
     */
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("传入的键key不能为null");
        }
        if (value == null) {
            throw new IllegalArgumentException("传入的键值value不能为空");
        }
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                // 找到就返回
                return;
            }
        }
        // 未命中。新建节点放在第一个点的位置上
        // 新增的节点设置为first节点，所以最后相当于输入顺序的逆序输出，不过无所谓了，这个本来就是无序链表
        first = new Node(key, value, first);
        n++;
    }

    /**
     * 描述: 删除指定的键
     * 日期: 2018/1/6 下午7:46
     * 作者: 梁山广
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的键不能为空");
        }
        // 从第一个点开始递归往后查找，找到的话就返回找到的点下一个点的位置
        first = delete(first, key);
    }

    /**
     * 描述: 删除的递归子方法。找到要删除的点后，令删除点前面的点指向删除点后面的点，从而将待删除点释放。
     * 通过递归又使删除点前前面的点指向删除点前面的点.....递归下来就使得待删除前被删除释放，其前面所有的点都往后撤了一位
     * 日期: 2018/1/6 下午7:56
     * 作者: 梁山广
     */
    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.equals(x.key)) {
            n--;
            return x.next;
        }

        x.next = delete(x.next, key);
        return x;
    }


    /**
     * 返回所有key组成的队列
     *
     * @return 这里的P95LinkedListQueue已经实现了Comparable接口
     */
    public Iterable<Key> keys() {
        P95LinkedListQueue<Key> queue = new P95LinkedListQueue<>();
        for (Node x = first; x != null; x = x.next) {
            queue.enqueue(x.key);
        }
        return queue;
    }


}
