package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.mylib.file.In;
import org.junit.Test;

/**
 * P415EdgeWeightedDigraph Tester.
 * 见图4.4.4(P416)和图4.4.5(P417)
 *
 * @author liangshanguang
 * @date 02/23/2018
 * @description test
 */
public class P415EdgeWeightedDigraphTest {

    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "/Users/liangshanguang/Program/Algorithm/算法第四版资料/algs4-data/tinyEWD.txt";
        In in = new In(filePath);
        P415EdgeWeightedDigraph G = new P415EdgeWeightedDigraph(in);
        System.out.println(G);
    }
} 
