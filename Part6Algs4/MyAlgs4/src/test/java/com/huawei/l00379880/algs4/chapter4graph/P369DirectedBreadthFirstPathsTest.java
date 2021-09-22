package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.mylib.file.In;
import org.junit.Test;

/**
 * P369DirectedBreadthFirstPaths Tester.
 *
 * @author liangshanguang
 * @date 02/19/2018
 * @description test
 */
public class P369DirectedBreadthFirstPathsTest {

    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "/Users/liangshanguang/Program/Algorithm/算法第四版资料/algs4-data/tinyDG.txt";
        In in = new In(filePath);
        P366Digraph digraph = new P366Digraph(in);
        // source、v、w都是顶点的标号范围为[0,V)
        int source = 3;
        P369DirectedBreadthFirstPaths bfs = new P369DirectedBreadthFirstPaths(digraph, source);
        for (int v = 0; v < digraph.getV(); v++) {
            if (bfs.hasPathTo(v)) {
                System.out.println("顶点" + source + "到顶点" + v + "的距离为(边的条数)：" + bfs.distTo(v));
                System.out.print(source + " to " + v + ":");
                for (Integer x : bfs.pathTo(v)) {
                    if (x == source) {
                        // 第1个点是起点
                        System.out.print(x);
                    } else {
                        System.out.print("-->" + x);
                    }
                }
                System.out.println();
            } else {
                System.out.println(source + " to " + v + ": 没有路径可以到达!");
            }
        }
    }
} 
