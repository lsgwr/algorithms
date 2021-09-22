package com.huawei.l00379880.algs4.chapter1javabasic;

import org.junit.Test;

/**
 * P82FixedCapacityStackOfStrings Tester.
 *
 * @author liang shan guang
 * @datetime 12/31/2017
 * @email liangshanguang2@gmail.com
 * @description 测试字符串定容栈
 */
public class P82FixedCapacityStackOfStringsTest {


    /**
     * Method: main(String[] args)
     */
    @Test
    public void testMain() {
        String[] arr = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        P82FixedCapacityStackOfStrings fixedCapacityStackOfStrings = new P82FixedCapacityStackOfStrings(10);
        for (String a : arr) {
            fixedCapacityStackOfStrings.push(a);
        }
        while (!fixedCapacityStackOfStrings.isEmpty()) {
            System.out.println(fixedCapacityStackOfStrings.pop());
            System.out.println("还剩下:" + fixedCapacityStackOfStrings.size());
        }
    }


} 
