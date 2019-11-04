package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.mylib.file.In;
import org.junit.Test;

/**
 * P439BellmanFordSP Tester.
 *
 * @author liang shan guang
 * @datetime 02/25/2018
 * @email liangshanguang2@gmail.com
 * @description
 */
public class P439BellmanFordSPTest {
    /**
     * Description:
     */
    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "D:\\l00379880\\GithubProjects\\algs4-data\\tinyEWDn.txt";
        In in = new In(filePath);
        int s = 0;
        P415EdgeWeightedDigraph digraph = new P415EdgeWeightedDigraph(in);

        P439BellmanFordSP bellmanFordSP = new P439BellmanFordSP(digraph, s);

        // 如果有负权重的环就打印出来
        if (bellmanFordSP.hasNegativeCycle()) {
            System.out.println("***************打印负权重的环**************");
            for (P415DirectedEdge edge : bellmanFordSP.getNegativeCycle()) {
                System.out.println(edge);
            }
        } else {
            // 打印最短路径
            System.out.println("***************打印最短路径***************");
            int V = digraph.getV();
            for (int v = 0; v < V; v++) {
                if (bellmanFordSP.hasPathTo(v)) {
                    System.out.printf("%d to %d (%5.2f) ", s, v, bellmanFordSP.distTo(v));
                    for (P415DirectedEdge edge : bellmanFordSP.pathTo(v)) {
                        System.out.print(edge + "   ");
                    }
                    System.out.println();
                } else {
                    System.out.printf("%d to %d          no path\n", s, v);
                }
            }
        }
    }
} 
