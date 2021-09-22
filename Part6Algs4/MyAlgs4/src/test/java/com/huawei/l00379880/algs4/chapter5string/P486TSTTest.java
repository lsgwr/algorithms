package com.huawei.l00379880.algs4.chapter5string;

import org.junit.Test;

/**
 * P486TST Tester.
 *
 * @author liangshanguang
 * @date 03/17/2018
 * @description test
 */
public class P486TSTTest {

    @Test
    public void testMain() {
        P486TST<Integer> st = new P486TST();
        st.put("by", 4);
        st.put("sea", 6);
        st.put("sells", 1);
        st.put("she", 0);
        st.put("shells", 3);
        st.put("shore", 7);
        st.put("the", 5);


        System.out.println("下面打印出所有的键值对:");
        for (String key : st.keys()) {
            System.out.println(key + " " + st.get(key));
        }

        System.out.println("打印指定字符串s中的最长匹配前缀");
        System.out.println(st.longestPrefixOf("shellsort"));
        System.out.println(st.longestPrefixOf("quicksort"));
        System.out.println();

        System.out.println("打印包含指定前缀的单词");
        for (String she : st.keysWithPrefix("she")) {
            System.out.println(she);
        }
        System.out.println();

        System.out.println("符合指定规则的键:");
        for (String s : st.keysThatMatch(".he")) {
            System.out.println(s);
        }
    }
} 
