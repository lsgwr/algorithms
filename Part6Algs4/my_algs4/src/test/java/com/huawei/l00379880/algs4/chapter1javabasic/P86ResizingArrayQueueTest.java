package com.huawei.l00379880.algs4.chapter1javabasic;

import org.junit.Test;

/**
 * P86ResizingArrayQueue Tester.
 *
 * @author liang shan guang
 * @datetime 12/31/2017
 * @email liangshanguang2@gmail.com
 * @description
 */
public class P86ResizingArrayQueueTest {
    /**
     * Description:
     */
    @Test
    public void testMain() {
        P86ResizingArrayQueue<String> queue = new P86ResizingArrayQueue<>();
        queue.enqueue("Hello");
        queue.enqueue("World");
        queue.enqueue("how");
        queue.enqueue("are");
        queue.enqueue("you");
        for (String s : queue) {
            System.out.println(s);
        }
    }
} 
