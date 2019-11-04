package com.huawei.l00379880.algs4.chapter1javabasic;

import org.junit.Test;

/**
 * P87ResizingArrayBag Tester.
 *
 * @author liang shan guang
 * @datetime 12/31/2017
 * @email liangshanguang2@gmail.com
 * @description
 */
public class P87ResizingArrayBagTest {
    /**
     * Description:
     */
    @Test
    public void testMain() {
        P87ResizingArrayBag<String> bag = new P87ResizingArrayBag<>();
        bag.add("Hello");
        bag.add("World");
        bag.add("how");
        bag.add("are");
        bag.add("you");
        for (String s : bag) {
            System.out.println(s);
        }
    }
} 
