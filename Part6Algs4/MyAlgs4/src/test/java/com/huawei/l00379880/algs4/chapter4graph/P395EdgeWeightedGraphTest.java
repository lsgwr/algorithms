package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.mylib.file.In;
import org.junit.Test;

/**
 * P395EdgeWeightedGraph Tester.
 * 无向有权图的测试
 *
 * @author liangshanguang
 * @date 02/21/2018
 * @description test
 */
public class P395EdgeWeightedGraphTest {

    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "/Users/liangshanguang/Program/Algorithm/算法第四版资料/algs4-data/tinyEWG.txt";
        In in = new In(filePath);
        P395EdgeWeightedGraph graph = new P395EdgeWeightedGraph(in);
        System.out.println(graph);
    }

}
