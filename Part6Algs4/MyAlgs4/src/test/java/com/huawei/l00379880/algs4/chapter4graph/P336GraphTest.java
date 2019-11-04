package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.mylib.file.In;
import org.junit.Test;

/**
 * P336Graph Tester.
 *
 * @author liang shan guang
 * @datetime 02/14/2018
 * @email liangshanguang2@gmail.com
 * @description
 */
public class P336GraphTest {
    /**
     * Description:
     */
    @Test
    public void testMain() {
        // 文件里每一行存储了一条边
        String filePath = "D:\\l00379880\\GithubProjects\\algs4-data\\tinyG.txt";
        In in = new In(filePath);
        P336Graph graph = new P336Graph(in);
        System.out.println(graph);
    }
} 
