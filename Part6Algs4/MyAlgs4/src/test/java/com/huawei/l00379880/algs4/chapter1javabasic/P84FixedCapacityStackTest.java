package com.huawei.l00379880.algs4.chapter1javabasic;

import org.junit.Test;

/**
 * P84FixedCapacityStack Tester.
 *
 * @author liang shan guang
 * @datetime 12/31/2017
 * @email liangshanguang2@gmail.com
 * @description
 */
public class P84FixedCapacityStackTest {

    /**
     * Method: main(String[] args)
     * 测试整型
     */
    @Test
    public void testMain1() {
        System.out.println("*************************************开始字符串测试*****************************************");
        String[] arr = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        P84FixedCapacityStack<String> fixedCapacityStack = new P84FixedCapacityStack<>(10);
        for (String a : arr) {
            fixedCapacityStack.push(a);
            System.out.println("推入元素:" + a + ",剩余元素个数为:" + fixedCapacityStack.size());
        }
        while (!fixedCapacityStack.isEmpty()) {
            System.out.println("弹出元素:" + fixedCapacityStack.pop() + ",剩余元素个数为:" + fixedCapacityStack.size());
        }
    }

    /**
     * Method: main(String[] args)
     * 测试字符串类型
     */
    @Test
    public void testMain2() {
        System.out.println("*************************************开始整型测试*****************************************");
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        P84FixedCapacityStack<Integer> fixedCapacityStack = new P84FixedCapacityStack<>(10);
        for (int a : arr) {
            fixedCapacityStack.push(a);
            System.out.println("推入元素:" + a + ",剩余元素个数为:" + fixedCapacityStack.size());
        }
        while (!fixedCapacityStack.isEmpty()) {
            System.out.println("弹出元素:" + fixedCapacityStack.pop() + ",剩余元素个数为:" + fixedCapacityStack.size());
        }
    }


} 
