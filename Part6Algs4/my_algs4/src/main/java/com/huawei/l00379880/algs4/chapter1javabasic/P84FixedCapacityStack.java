package com.huawei.l00379880.algs4.chapter1javabasic;

/***********************************************************
 * @Description : 表示容量固定的-泛型数据类型栈-的抽象数据类型,
 *                可以支持任何数据类型,要求用户制定一个容量而且
 *                不支持迭代
 * @author      : 梁山广
 * @date        : 2017/12/31 15:52:30
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P84FixedCapacityStack<Item> {
    /**
     * 定容泛型元素数组,用于存储外面来的Item
     */
    private Item[] a;
    /**
     * 数组的实时大小
     */
    private int n;

    public P84FixedCapacityStack(int capacity) {
        // 根据传入的数组大小进行数组初始化.
        // 之所以没有用this.a = Item[capacity],是因为由于某些历史和技术原因,
        // 直接创建泛型数组在Java中是不允许的,
        // 所以此处只能先创建通用的Object数组然后进行强制转换
        this.a = (Item[]) new Object[capacity];
        // 初始化数组大小
        n = 0;
    }

    /**
     * 数组为空等效实数组大小为0
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 返回栈的大小
     */
    public int size() {
        return n;
    }

    /**
     * 推入元素,a[n++=item用地很巧,等效于a[n]=item,n=n+1
     */
    public void push(Item item) {
        a[n++] = item;
    }

    /**
     * 弹出元素,return a[--n]用地很巧,等效于return a[n-1],n=n-1
     */
    public Item pop() {
        return a[--n];
    }

}
