package com.huawei.l00379880.algs4.chapter3search;

import org.junit.Test;

/**
 * P236SequentialSearchST Tester.
 *
 * @author liangshanguang
 * @date 01/06/2018
 * @description test
 */
public class P236SequentialSearchSTTest {

    @Test
    public void testMain() {

        P236SequentialSearchST<String, Integer> st = new P236SequentialSearchST<String, Integer>();
        st.put("china", 1);
        st.put("india", 3);
        st.put("England", 34);
        st.put("Austrilia", 0);
        st.put("America", 5);
        st.put("Canada", 67);
        st.put("France", 45);
        for (String s : st.keys()) {
            System.out.println(s + "==>" + st.get(s));
        }
        System.out.println("删除America后看看顺序！");
        st.delete("America");
        // 看看删除元素后的输出顺序
        for (String s : st.keys()) {
            System.out.println(s + "==>" + st.get(s));
        }

    }
} 
