package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.mylib.file.In;
import org.junit.Test;

/**
 * P366Digraph Tester.
 * 结果图见P366右侧
 *
 * @author liangshanguang
 * @date 02/19/2018
 * @description test
 */
public class P366DigraphTest {

    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "/Users/liangshanguang/Program/Algorithm/算法第四版资料/algs4-data/tinyDG.txt";
        In in = new In(filePath);
        P366Digraph digraph = new P366Digraph(in);
        System.out.println(digraph);
    }
} 
