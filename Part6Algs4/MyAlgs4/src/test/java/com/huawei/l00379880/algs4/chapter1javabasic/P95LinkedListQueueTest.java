package com.huawei.l00379880.algs4.chapter1javabasic;

import org.junit.Test;

/**
 * P95LinkedListQueue Tester.
 *
 * @author liang shan guang
 * @datetime 12/31/2017
 * @email liangshanguang2@gmail.com
 * @description
 */
public class P95LinkedListQueueTest {
    /**
     * Description:
     */
    @Test
    public void testMain() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        P95LinkedListQueue<Integer> linkedListQueue = new P95LinkedListQueue<>();
        for (int i : arr) {
            linkedListQueue.enqueue(i);
        }
        System.out.println(linkedListQueue);
        System.out.println(linkedListQueue.peek());
        for (int i = 0; i < 6; i++) {
            linkedListQueue.dequeue();
        }
        // foreach访问每个元素
        for (Integer integer : linkedListQueue) {
            System.out.println(integer);
        }
        System.out.println(linkedListQueue.peek());
        //测试异常
//        for (int i = 0; i < 5; i++) {
//            linkedListQueue.dequque();
//        }
    }
} 
