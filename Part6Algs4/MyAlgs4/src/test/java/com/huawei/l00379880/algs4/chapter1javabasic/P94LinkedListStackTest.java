package com.huawei.l00379880.algs4.chapter1javabasic;

import org.junit.Test;

/**
 * P94LinkedListStack Tester.
 *
 * @author liang shan guang
 * @datetime 12/31/2017
 * @email liangshanguang2@gmail.com
 * @description 测试链表组成的栈
 */
public class P94LinkedListStackTest {
    /**
     * Description:
     */
    @Test
    public void testMain() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        P94LinkedListStack<Integer> linkedListStack = new P94LinkedListStack<>();
        for (int i : arr) {
            linkedListStack.push(i);
        }
        System.out.println(linkedListStack);
        System.out.println(linkedListStack.peek());
        for (int i = 0; i < 6; i++) {
            linkedListStack.pop();
        }
        // foreach访问每个元素
        for (Integer integer : linkedListStack) {
            System.out.println(integer);
        }
        System.out.println(linkedListStack.peek());
        //测试异常
//        for (int i = 0; i < 5; i++) {
//            linkedListStack.pop();
//        }
    }
} 
