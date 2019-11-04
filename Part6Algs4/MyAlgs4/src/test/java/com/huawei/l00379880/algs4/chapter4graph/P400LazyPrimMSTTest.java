package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.mylib.file.In;
import org.junit.Test;

/**
 * P400LazyPrimMST Tester.
 *
 * @author liangshanguang
 * @date 02/22/2018
 * @description test
 */
public class P400LazyPrimMSTTest {

    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "/Users/liangshanguang/Program/Algorithm/算法第四版资料/algs4-data/tinyEWG.txt";
        In in = new In(filePath);
        P395EdgeWeightedGraph G = new P395EdgeWeightedGraph(in);
        P400LazyPrimMST mst = new P400LazyPrimMST(G);
        for (P394Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.printf("%.5f\n", mst.getWeight());
    }
} 
