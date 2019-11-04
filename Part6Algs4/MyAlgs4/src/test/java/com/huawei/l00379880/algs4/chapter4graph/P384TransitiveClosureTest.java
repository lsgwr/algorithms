package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.mylib.file.In;
import org.junit.Test;

/**
 * P384TransitiveClosure Tester.
 *
 * @author liangshanguang
 * @date 02/21/2018
 * @description test
 */
public class P384TransitiveClosureTest {

    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "/Users/liangshanguang/Program/Algorithm/算法第四版资料/algs4-data/tinyDG.txt";
        In in = new In(filePath);
        P366Digraph digraph = new P366Digraph(in);

        P384TransitiveClosure tc = new P384TransitiveClosure(digraph);

        // print header
        System.out.print("     ");
        int V = digraph.getV();
        for (int v = 0; v < V; v++)
            System.out.printf("%3d", v);
        System.out.println();
        System.out.println("--------------------------------------------");

        // print transitive closure
        for (int v = 0; v < V; v++) {
            System.out.printf("%3d: ", v);
            for (int w = 0; w < V; w++) {
                if (tc.reachable(v, w)) {
                    System.out.printf("  T");
                } else {
                    System.out.printf("   ");
                }
            }
            System.out.println();
        }
    }

}
