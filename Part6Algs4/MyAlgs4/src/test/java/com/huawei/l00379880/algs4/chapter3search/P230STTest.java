package com.huawei.l00379880.algs4.chapter3search;

import org.junit.Test;

/**
 * P230ST Tester.
 *
 * @author liang shan guang
 * @datetime 01/05/2018
 * @email liangshanguang2@gmail.com
 * @description
 */
public class P230STTest {
    /**
     * Description:
     */
    @Test
    public void testMain() {
        String str = "SEARCHEXAMPLE";
        String[] a = str.split("");
        P230ST<String, Integer> st = new P230ST<>();
        for (int i = 0; i < a.length; i++) {
            st.put(a[i], i);
        }
        for (String s : st.keys()) {
            System.out.println(s + "===>" + st.get(s));
        }
    }
} 
