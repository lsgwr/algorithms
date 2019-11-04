package com.huawei.l00379880.algs4.chapter1javabasic;

import org.junit.Test;

/**
 * P98LinkedListBag Tester.
 *
 * @author liang shan guang
 * @datetime 12/31/2017
 * @email liangshanguang2@gmail.com
 * @description
 */
public class P98LinkedListBagTest {
    /**
     * Description:
     */
    @Test
    public void testMain() {
        P98LinkedListBag<Integer> linkedListBag = new P98LinkedListBag<>();
        // System.out.println(linkedListBag.peek()); 会报异常,取不到元素地
        for (int i = 0; i < 10; i++) {
            linkedListBag.add(i + 1);
        }
        System.out.println(linkedListBag.peek());
        System.out.println(linkedListBag);
    }
} 
