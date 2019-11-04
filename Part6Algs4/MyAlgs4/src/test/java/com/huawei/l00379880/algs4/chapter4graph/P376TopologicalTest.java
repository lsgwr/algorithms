package com.huawei.l00379880.algs4.chapter4graph;

import org.junit.Test;

/**
 * P376Topological Tester.
 *
 * @author liangshanguang
 * @date 02/21/2018
 * @description test
 */
public class P376TopologicalTest {

    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "/Users/liangshanguang/Program/Algorithm/算法第四版资料/algs4-data/jobs.txt";
        String delimeter = "/";
        P376SymbolDigraph symbolDigraph = new P376SymbolDigraph(filePath, delimeter);
        P376Topological topological = new P376Topological(symbolDigraph.getGraph());
        for (int v : topological.getOrder()) {
            System.out.println(symbolDigraph.nameOf(v));
        }
    }
} 
