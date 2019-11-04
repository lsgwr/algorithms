package com.huawei.l00379880.algs4.chapter3search;

import org.junit.Test;

/**
 * P297SeparateChainingHashST Tester.
 *
 * @author liangshanguang
 * @date 02/12/2018
 * @description test
 */
public class P297SeparateChainingHashSTTest {

    @Test
    public void testMain() {
        P297SeparateChainingHashST<String, Integer> st = new P297SeparateChainingHashST<>();
        st.put("China", 1);
        st.put("India", 3);
        st.put("England", 34);
        st.put("Austrilia", 0);
        st.put("America", 5);
        st.put("Canada", 67);
        st.put("France", 45);
        st.put("Germany", 11);
        // print keys
        for (String s : st.keys()) {
            System.out.println(s + " " + st.get(s));
        }
    }
} 
