package com.huawei.l00379880.algs4.chapter3search;

import org.junit.Test;

/**
 * P252BinarySearchTree Tester.
 *
 * @author liangshanguang
 * @date 01/07/2018
 * @description test
 */
public class P252BinarySearchTreeTest {

    @Test
    public void testMain() {
        P252BinarySearchTree<String, Integer> st = new P252BinarySearchTree<>();
        st.put("China", 1);
        st.put("India", 3);
        st.put("England", 34);
        st.put("Austrilia", 0);
        st.put("America", 5);
        st.put("Canada", 67);
        st.put("France", 45);
        System.out.println("*************顺序遍历,也即按照key的顺序遍历***********");
        for (String s : st.keys()) {
            System.out.println(s + "==>" + st.get(s));
        }
        System.out.println("*********广度优先遍历***********");
        for (String s : st.levelOrder()) {
            System.out.println(s + "==>" + st.get(s));
        }
        System.out.println("二叉树的高度："+st.height());
        System.out.println("******删除England后看看顺序！****");
        st.delete("England");

        // 看看删除元素后的输出顺序
        System.out.println("********顺序遍历,也即按照key的顺序遍历***********");
        for (String s : st.keys()) {
            System.out.println(s + "==>" + st.get(s));
        }
        System.out.println("*********广度优先遍历***********");
        for (String s : st.levelOrder()) {
            System.out.println(s + "==>" + st.get(s));
        }
    }
} 
