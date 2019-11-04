package com.huawei.l00379880.algs4.chapter3search;

import org.junit.Test;

/**
 * P301LinearProbingHashST Tester.
 *
 * @author liangshanguang
 * @date 02/12/2018
 * @description test
 */
public class P301LinearProbingHashSTTest {

    @Test
    public void testMain() {
        P301LinearProbingHashST<String, Integer> st = new P301LinearProbingHashST<String, Integer>();
        st.put("China", 1);
        st.put("India", 3);
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
