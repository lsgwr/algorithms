package com.huawei.l00379880.algs4.chapter1javabasic;

/***********************************************************
 * @Description : 表示容量固定的字符串栈的抽象数据类型,它只能处理
 *                String值,要求用户制定一个容量而且不支持迭代
 * @author      : 梁山广
 * @date        : 2017/12/31 14:57
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P82FixedCapacityStackOfStrings {
    /**
     * 定容字符串数组,用于存储外面来的字符串
     */
    private String[] a;
    /**
     * 数组的实时大小
     */
    private int n;

    public P82FixedCapacityStackOfStrings(int capacity) {
        // 根据传入的数组大小进行数组初始化
        this.a = new String[capacity];
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
    public void push(String item) {
        a[n++] = item;
    }

    /**
     * 弹出元素,return a[--n]用地很巧,等效于return a[n-1],n=n-1
     */
    public String pop() {
        return a[--n];
    }

}
