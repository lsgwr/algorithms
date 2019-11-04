package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.mylib.file.In;
import org.junit.Test;

/**
 * P423DijkstraSP Tester.
 *
 * @author liangshanguang
 * @date 02/23/2018
 * @description test
 */
public class P423DijkstraSPTest {

    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "/Users/liangshanguang/Program/Algorithm/算法第四版资料/algs4-data/tinyEWD.txt";
        In in = new In(filePath);
        P415EdgeWeightedDigraph G = new P415EdgeWeightedDigraph(in);
        int s = 0;

        // compute shortest paths
        P423DijkstraSP sp = new P423DijkstraSP(G, s);


        // print shortest path
        for (int t = 0; t < G.getV(); t++) {
            if (sp.hasPathTo(t)) {
                System.out.printf("%d to %d (%.2f)  ", s, t, sp.distTo(t));
                for (P415DirectedEdge e : sp.pathTo(t)) {
                    System.out.print(e + "   ");
                }
                System.out.println();
            } else {
                System.out.printf("%d to %d         no path\n", s, t);
            }
        }
    }
} 
