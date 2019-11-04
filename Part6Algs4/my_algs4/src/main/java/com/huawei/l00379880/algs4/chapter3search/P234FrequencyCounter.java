package com.huawei.l00379880.algs4.chapter3search;

/***********************************************************
 * @Description : 频率计数器
 * @author      : 梁山广
 * @date        : 2018/1/5 20:08
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P234FrequencyCounter {
    public static void main(String[] args) {
        //单次最少要有两个字母
        int minLen = 2;
        String sentence = "it was the best of times it was the worst of times" +
                " it was the age of wisdom it was the age of foolishness" +
                " it was the epoch of belief it was the epoch of incredulity" +
                " it was the season of light it was the season of darkness" +
                " it was the spring of hope it was the winter of despair";
        String[] words = sentence.split(" ");
        P230ST<String, Integer> st = new P230ST<>();
        for (String word : words) {
            if (word.length() < minLen) {
                continue;
            }
            if (!st.contains(word)) {
                st.put(word, 1);
            } else {
                st.put(word, st.get(word) + 1);
            }
        }

        // 找出出现频率最高的单词
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            System.out.println(word+"===>"+st.get(word));
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }
        System.out.println("单词:" + max + "出现次数最多,为" + st.get(max) + "此次");
    }
}
