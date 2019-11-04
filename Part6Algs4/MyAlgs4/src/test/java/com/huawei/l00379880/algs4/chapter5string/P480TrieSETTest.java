package com.huawei.l00379880.algs4.chapter5string;

import org.junit.Test;

/**
 * P480TrieSET Tester 不含重复元素的符号表.
 *
 * @author liangshanguang
 * @date 03/17/2018
 * @description test
 */
public class P480TrieSETTest {

    @Test
    public void testMain() {
        P480TrieSET st = new P480TrieSET();
        st.add("by");
        st.add("sea");
        st.add("sells");
        st.add("sells");
        st.add("sells");
        st.add("sells");
        st.add("she");
        st.add("shells");
        st.add("shore");
        st.add("the");
        st.add("the");
        st.add("the");
        st.add("the");


        System.out.println("下面打印出所有的键值对:");
        for (String key : st) {
            System.out.println(key);
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
