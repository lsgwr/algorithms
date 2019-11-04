package com.huawei.l00379880.algs4.chapter1javabasic;

import org.junit.Test;

/**
 * P85ResizingArrayStack Tester.
 *
 * @author liang shan guang
 * @datetime 12/31/2017
 * @email liangshanguang2@gmail.com
 * @description
 */
public class P85ResizingArrayStackTest {

    @Test
    public void testMain() {
        P85ResizingArrayStack<Integer> resizingArrayStack = new P85ResizingArrayStack<>();
        for (int i = 0; i < 100; i++) {
            resizingArrayStack.push(i);
        }
        // 看看访问出来是不是逆序地
        for (Integer integer : resizingArrayStack) {
            System.out.println(integer);
        }
    }

    @Test
    public void testMain2() {
        P85ResizingArrayStack<String> stack = new P85ResizingArrayStack<>();
        stack.push("Hello");
        stack.push("World");
        stack.push("how");
        stack.push("are");
        stack.push("you");
        for (String s : stack) {
            System.out.println(s);
        }
    }
}
